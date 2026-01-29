# Pagination with Spring Data JPA for Telemetry Data

## 1. Why Pagination Is Mandatory (Not Optional)

Imagine your system:

* 10,000 assets
* Each asset → millions of telemetry records

If you do:

```java
SELECT * FROM telemetry;
```

You will:

* Kill memory
* Kill DB
* Kill response time
* Kill yourself in production

Pagination solves this by:

* Fetching **small, controlled chunks**
* Avoiding full table scans and memory blowups

---

## 2. Pagination Concept (DB Level)

Pagination translates to SQL like:

### PostgreSQL / MySQL

```sql
SELECT *
FROM telemetry
ORDER BY recorded_at DESC
LIMIT 20 OFFSET 40;
```

Meaning:

* Page size = 20
* Page number = 3 (0-based → offset = 2 × 20 = 40)

---

## 3. Spring Data JPA Pagination Building Blocks

### Core Interfaces

* `Pageable`
* `PageRequest`
* `Page<T>`
* `Slice<T>`

---

## 4. Pageable – The Input

```java
Pageable pageable = PageRequest.of(
    0,          // page number (0-based)
    20,         // page size
    Sort.by("recordedAt").descending()
);
```

---

## 5. Page<T> – The Output

```java
Page<Telemetry> page = telemetryRepository.findAll(pageable);
```

### What `Page` gives you

* `getContent()` → actual data
* `getTotalElements()` → total rows
* `getTotalPages()`
* `hasNext()`
* `hasPrevious()`

---

## 6. Repository Examples

### Basic Pagination

```java
public interface TelemetryRepository
        extends JpaRepository<Telemetry, Long> {

    Page<Telemetry> findAll(Pageable pageable);
}
```

---

### Pagination with Filtering (Very Common)

```java
Page<Telemetry> findByAssetId(
    Long assetId,
    Pageable pageable
);
```

Generated SQL:

```sql
SELECT *
FROM telemetry
WHERE asset_id = ?
ORDER BY recorded_at DESC
LIMIT ? OFFSET ?
```

---

## 7. Pagination in REST API (Typical Controller)

```java
@GetMapping("/assets/{assetId}/telemetry")
public Page<Telemetry> getTelemetry(
        @PathVariable Long assetId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
) {
    Pageable pageable = PageRequest.of(
        page, size, Sort.by("recordedAt").descending()
    );
    return telemetryRepository.findByAssetId(assetId, pageable);
}
```

---

## 8. Page vs Slice (Very Important for Telemetry)

### Page<T>

* Executes **two queries**

    1. `SELECT data`
    2. `SELECT COUNT(*)`
* Needed when:

    * UI needs **total pages**
    * Admin dashboards

---

### Slice<T>

```java
Slice<Telemetry> findByAssetId(Long assetId, Pageable pageable);
```

* Executes **only one query**
* Knows only:

    * `hasNext()`
* Best for:

    * Infinite scrolling
    * High-volume telemetry
    * Performance-sensitive APIs

---

## 9. Pagination + Sorting (Best Practice)

Never paginate **without sorting**.

### Bad

```java
PageRequest.of(0, 20);
```

### Why?

* DB returns rows in undefined order
* Same record can appear in multiple pages

### Good

```java
PageRequest.of(
    0, 20,
    Sort.by("recordedAt").descending()
);
```

---

## 10. Pagination with Joins (Common Pitfall)

### Problem

```java
Page<Asset> findAll(Pageable pageable);
```

If `Asset` has `@OneToMany telemetryList`, JPA may:

* Fetch assets
* Trigger N+1 queries

---

### Solution 1: Fetch only what you need (DTO)

```java
@Query("""
SELECT new com.example.AssetSummary(a.id, a.name)
FROM Asset a
""")
Page<AssetSummary> findAssetSummaries(Pageable pageable);
```

---

### Solution 2: Use `@EntityGraph`

```java
@EntityGraph(attributePaths = "location")
Page<Asset> findAll(Pageable pageable);
```

Avoid using `@EntityGraph` with large collections.

---

## 11. Pagination & Indexing (Critical)

Always index:

* Filter columns
* Sort columns

Example:

```sql
CREATE INDEX idx_telemetry_asset_time
ON telemetry(asset_id, recorded_at DESC);
```

Without index:

* Pagination still works
* Performance will be terrible

---

## 12. Offset Pagination Problem (Advanced)

### Offset-based pagination issue

```sql
LIMIT 20 OFFSET 1000000;
```

* DB scans & skips 1,000,000 rows
* Gets slower as page number increases

---

## 13. Keyset Pagination (Better for Telemetry)

Instead of page number:

```java
List<Telemetry> findByAssetIdAndRecordedAtLessThanOrderByRecordedAtDesc(
    Long assetId,
    LocalDateTime lastSeen,
    Pageable pageable
);
```

SQL:

```sql
WHERE recorded_at < ?
ORDER BY recorded_at DESC
LIMIT 20
```

Best for:

* Infinite scroll
* Time-series data




