# Thread Synchronization in Java
## 1. What Is Thread Synchronization?

**Thread synchronization** is the mechanism used to:

* Control **access to shared resources**
* Prevent **race conditions**
* Ensure **data consistency & visibility**

> In simple words: **making sure only one thread modifies shared data at a time, in a predictable way**.

---

## 2. Why Synchronization Is Needed (The Core Problem)

### Threads Share Memory

In Java:

* Each thread has its own **stack**
* All threads share the **heap**

So if two threads modify the same object → **chaos**.

---

## 3. Greenko Case Study – Telemetry Count Example

### Scenario

* Multiple telemetry ingestion threads
* Each increments a counter for an asset

### Shared Resource

```java
int telemetryCount = 0;
```

---

## 4. Without Synchronization (Race Condition)

```java
telemetryCount++;
```

### Why this is NOT atomic

It expands to:

1. Read value
2. Increment
3. Write back

Two threads interleaving:

```text
Thread A reads 10
Thread B reads 10
Thread A writes 11
Thread B writes 11   (lost update)
```

Final value should be **12**, but is **11**.

This bug:

* Happens randomly
* Is hard to reproduce
* Appears only under load

---

## 5. Fix #1 – `synchronized` Keyword

### Method-level synchronization

```java
public synchronized void increment() {
    telemetryCount++;
}
```

✔ Mutual exclusion
✔ Memory visibility
✔ Simple & safe

---

### Block-level synchronization

```java
public void increment() {
    synchronized (this) {
        telemetryCount++;
    }
}
```

Used when only **part** of method needs locking.

---

## 6. Example – Asset Health Update

### Problem

Multiple telemetry threads update the same asset health.

```java
class Asset {
    HealthStatus status;
}
```

### Without synchronization

```java
asset.setStatus(newStatus);
```

Health can flip unpredictably.

---

### With synchronization

```java
class Asset {
    private HealthStatus status;

    public synchronized void updateHealth(HealthStatus newStatus) {
        this.status = newStatus;
    }
}
```

Ensures:

* One update at a time
* Correct ordering

---

## 7. What Does `synchronized` Actually Do?

### Guarantees:

1. **Only one thread enters critical section**
2. **Establishes happens-before relationship**
3. **Flushes memory (visibility)**

> When a thread exits a synchronized block, all changes become visible to other threads.

---

## 8. Fix #2 – `volatile` (Visibility Only)

### Example – Alert flag

```java
volatile boolean alertTriggered = false;
```

Used when:

* One thread writes
* Others only read

---

## 9. Fix #3 – Atomic Classes (Preferred for Counters)

```java
AtomicInteger telemetryCount = new AtomicInteger();

telemetryCount.incrementAndGet();
```

✔ Lock-free
✔ High performance
✔ Thread-safe

Perfect for:

* Telemetry counters
* Metrics
* Health scores

---

## 10. Fix #4 – Concurrent Collections

### Problem

Multiple threads update asset map

```java
Map<String, Asset> assets = new HashMap<>();
```

### Solution

```java
Map<String, Asset> assets = new ConcurrentHashMap<>();
```

✔ Internal fine-grained locking
✔ No global lock
✔ Scales well

---

## 11. Example – Synchronization Failure Impact

### Without synchronization

* Wrong telemetry counts
* Incorrect asset health
* Missed alerts
* Inconsistent dashboards

### With synchronization

* Accurate data
* Deterministic behavior
* Trustworthy system

## 12. When NOT to Synchronize

* Immutable objects
* Thread-local variables
* Read-only data
* Local method variables



