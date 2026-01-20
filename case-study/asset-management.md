## Asset Management System - Java Case Study

### Domain Model

```text
Asset
- id
- name
- type (TURBINE, MACHINE, VEHICLE)
- location
- status (ACTIVE, INACTIVE, MAINTENANCE)
- installedDate
```

---

# 1. Asset Modeling & OOP Fundamentals

### Use Case: Asset Lifecycle Modeling

**Problem**
Model different kinds of assets with shared and specialized behavior.

### Java Concepts

* Abstraction
* Inheritance
* Polymorphism
* Encapsulation

### Design

```java
abstract class Asset {
    private UUID id;
    private String name;
    private AssetStatus status;

    public abstract AssetHealth evaluateHealth();
}
```

```java
class Turbine extends Asset {
    private int bladeCount;

    @Override
    public AssetHealth evaluateHealth() {
        return AssetHealth.GOOD;
    }
}
```


```java

class Machine extends Asset {
    private String modelNumber;

    @Override
    public AssetHealth evaluateHealth() {
        return AssetHealth.POOR;
    }
}

```

```java

class SolarPanel extends Asset {
    private double efficiencyRating;

    @Override
    public AssetHealth evaluateHealth() {
        return AssetHealth.GOOD;
    }
}


```


# 2. Asset Registry using Collections

### Use Case: In-Memory Asset Store

**Problem**
Store and manage assets efficiently in memory.

### Java Concepts

* List vs Set vs Map
* equals() & hashCode()
* Immutability

### Implementation Idea

```java
Map<UUID, Asset> assetRegistry = new ConcurrentHashMap<>();
```

### Exercises

* Prevent duplicate assets
* Retrieve assets by ID
* Update asset status safely

### Discussion Topics

* Why Map over List?
* HashMap vs ConcurrentHashMap
* Object identity vs equality

---

# 3. Asset Search & Filtering (Streams API)

### Use Case: Query Assets by Type & Status

**Problem**
Fetch all ACTIVE turbines in a given location.

### Java Concepts

* Streams
* Predicate
* Method references
* Optional


### Exercises

* Convert imperative loops → streams
* Compare readability & performance
* Handle null safely using Optional

---
# 4. Asset Reporting (Collectors)
### Use Case: Generate Asset Summary Report
### Java Concepts
* Collectors
* GroupingBy
* Mapping
* PartitioningBy

# 5. Asset Validation Framework

### Use Case: Validate Asset Before Registration

### Java Concepts

* Functional interfaces
* Strategy pattern
* Lambdas

```java
List<Predicate<Asset>> validators = List.of(
    a -> a.getName() != null,
    a -> a.getInstalledDate().isBefore(LocalDate.now())
);
```

```java
boolean isValid = validators.stream().allMatch(v -> v.test(asset));
```

### Exercises

* Add new validation without modifying code
* Custom exceptions

---

# 6. Sorting & Ranking Assets

### Use Case: Sort Assets by Age or Status

### Java Concepts

* Comparator
* Comparable
* Method references



# 7. Asset Snapshot & Immutability

### Use Case: Generate Read-Only Asset Snapshot

### Java Concepts

* Immutability
* Defensive copying
* Records (Java 16+)

```java
public record AssetSnapshot(
    UUID id,
    String name,
    AssetStatus status
) {}
```


# 8. Bulk Asset Import (Performance Focus)

### Use Case: Import 10,000 Assets

### Java Concepts

* Bulk operations
* Streams vs loops
* Performance considerations

```java
assets.forEach(assetRegistry::put);
```

### Discussion

* When NOT to use streams
* Memory & GC considerations

---

# 9. Asset Caching Layer

### Use Case: Cache Frequently Accessed Assets

### Java Concepts

* LRU cache
* LinkedHashMap
* Synchronization

```java
Map<UUID, Asset> cache =
    new LinkedHashMap<>(100, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > 100;
        }
    };
```

---

# 10. Concurrent Asset Updates

### Use Case: Multiple Engineers Updating Assets

### Java Concepts

* Synchronization
* Locks
* Atomic operations


### Topics to Discuss

* Race conditions
* Optimistic vs pessimistic locking

---

# 11. Asset Event Hooks (Observer Pattern)

### Use Case: Notify on Asset Status Change

### Java Concepts

* Observer pattern
* Functional callbacks


# 12. Exception Design & Error Handling

### Use Case: Asset Not Found / Invalid State

### Java Concepts

* Custom exceptions
* Checked vs unchecked
* Meaningful error messages


