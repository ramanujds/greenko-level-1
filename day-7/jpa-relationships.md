# JPA Relationships in Asset & Telemetry Systems

## 1. Asset & Telemetry – Domain Understanding

### Core concepts in such a system

* **Asset**: Physical or logical entity
  Example: Vehicle, Machine, Sensor Device
* **Telemetry**: Time-series data emitted by assets
  Example: temperature, speed, battery level
* **Location**
* **AssetType**
* **User / Owner**

---

## 2. Why Spring Data JPA here?

In an asset-telemetry system:

* You have **rich relationships**
* You need **CRUD + queries**
* You want **ORM abstraction**, not raw SQL

Spring Data JPA gives:

* Repository abstraction
* Automatic query generation
* Clean mapping between Java objects and tables
* Transaction management

---

## 3. Basic JPA Entity Introduction

### Asset Entity

```java
@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetCode;

    private String name;

    // relationships will come here
}
```

### Telemetry Entity

```java
@Entity
@Table(name = "telemetry")
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperature;
    private Double speed;

    private LocalDateTime recordedAt;
}
```

---

## 4. One-to-Many Mapping

**Asset → Telemetry**

### Business Meaning

* One Asset generates **many telemetry records**
* Each telemetry record belongs to **one asset**

---

### Database View

```
assets
------
id (PK)

telemetry
---------
id (PK)
asset_id (FK)
```

---

### Entity Mapping

#### Asset side

```java
@OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
private List<Telemetry> telemetryList;
```

#### Telemetry side

```java
@ManyToOne
@JoinColumn(name = "asset_id", nullable = false)
private Asset asset;
```

---

### Important Annotations Explained

#### `@ManyToOne`

* Indicates many telemetry rows belong to one asset
* Owning side of the relationship

#### `@JoinColumn`

* Creates **foreign key column**
* `name = "asset_id"` → column name in telemetry table
* If omitted, JPA creates `asset_id` automatically

---

## 5. Why `mappedBy`?

```java
@OneToMany(mappedBy = "asset")
```

* Prevents **duplicate foreign keys**
* Tells JPA:
  “Telemetry owns the relationship, not Asset”

Without `mappedBy`, JPA would create:

```
assets_telemetry (join table) - unnecessary
```

---

## 6. Many-to-One vs One-to-Many (Rule of Thumb)

| Relationship | Use Case                  |
| ------------ | ------------------------- |
| `ManyToOne`  | Always define (mandatory) |
| `OneToMany`  | Optional, for navigation  |

In real systems:

* **Always define `@ManyToOne`**
* Define `@OneToMany` only if needed

---

## 7. Many-to-Many Mapping

**Asset ↔ User (Owners / Operators)**

### Business Meaning

* One asset can have **multiple users**
* One user can manage **multiple assets**

---

### Database Design

```
assets
users
asset_users (join table)
```

---

### Entity Mapping

#### Asset Entity

```java
@ManyToMany
@JoinTable(
    name = "asset_users",
    joinColumns = @JoinColumn(name = "asset_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
)
private Set<User> users;
```

---

### Annotation Breakdown

#### `@JoinTable`

Defines **junction table**

```java
@JoinTable(
  name = "asset_users",
  joinColumns = ...
  inverseJoinColumns = ...
)
```

* `joinColumns` → FK pointing to **owning entity**
* `inverseJoinColumns` → FK pointing to **other entity**

---

#### `@JoinColumn`

Used **inside `@JoinTable`**

```java
@JoinColumn(name = "asset_id")
```

Means:

* Column `asset_id` refers to Asset’s primary key

---

## 8. One-to-One Mapping

**Asset → Location**

### Business Meaning

* Each asset has **one current location**
* Location belongs to **one asset**

---

### Mapping

```java
@OneToOne
@JoinColumn(name = "location_id")
private Location location;
```

---

### Why `@JoinColumn` here?

* Stores FK in `assets` table
* Simple, efficient
* No join table needed

---

## 9. Embedded Objects (Telemetry Metadata)

Sometimes telemetry has **non-entity value objects**

### Example

```java
@Embeddable
public class SensorMetadata {
    private String firmwareVersion;
    private String manufacturer;
}
```

```java
@Embedded
private SensorMetadata metadata;
```

Creates columns:

```
firmware_version
manufacturer
```

No separate table.

---

## 10. Fetch Types (Critical for Telemetry Systems)

### Default Fetching

| Relationship | Default |
| ------------ | ------- |
| `ManyToOne`  | EAGER   |
| `OneToMany`  | LAZY    |

---

### Best Practice

```java
@ManyToOne(fetch = FetchType.LAZY)
private Asset asset;
```

Why?

* Telemetry tables grow **very fast**
* Avoid unnecessary joins
* Prevent performance issues

---

## 11. Cascade Types (Very Important)

### Example

```java
@OneToMany(
    mappedBy = "asset",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
```

#### What it means:

* Saving Asset → saves telemetry
* Deleting Asset → deletes telemetry
* Removing telemetry from list → deletes row

---

## 12. Spring Data JPA Repository Example

```java
public interface TelemetryRepository
        extends JpaRepository<Telemetry, Long> {

    List<Telemetry> findByAssetId(Long assetId);

    List<Telemetry> findByRecordedAtBetween(
        LocalDateTime start,
        LocalDateTime end
    );
}
```

No SQL written.

---





