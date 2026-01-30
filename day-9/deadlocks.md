# Deadlocks in Java: What They Are & How to Avoid Them
## 1. What Is Deadlock?

> **Deadlock** happens when two or more threads are permanently blocked, each waiting for a resource held by the other.

No exception.
No CPU spike.
System just… **freezes**.

---

## 2. The Four Necessary Conditions (Must Know)

Deadlock occurs **only if all four are true**:

1. **Mutual exclusion** – resource can be held by only one thread
2. **Hold and wait** – thread holds one resource and waits for another
3. **No preemption** – resource can’t be forcibly taken
4. **Circular wait** – circular dependency between threads

Break **any one** → no deadlock.

---

## 3. Classic Deadlock Example (Simple & Clear)

### Shared resources

```java
Object assetLock = new Object();
Object telemetryLock = new Object();
```

### Thread 1

```java
synchronized (assetLock) {
    sleep(100);
    synchronized (telemetryLock) {
        System.out.println("Thread 1 processing");
    }
}
```

### Thread 2

```java
synchronized (telemetryLock) {
    sleep(100);
    synchronized (assetLock) {
        System.out.println("Thread 2 processing");
    }
}
```

---

## 4. What Actually Happens (Timeline)

```text
Thread-1 locks assetLock
Thread-2 locks telemetryLock

Thread-1 waits for telemetryLock
Thread-2 waits for assetLock
```

Both threads are now **waiting forever**.

![Image](https://afteracademy.com/images/what-is-deadlock-and-what-are-its-four-necessary-conditions-deadlock-example.png)


## 5. Realistic Deadlock Scenario

### Scenario

* Thread A: Telemetry ingestion updates **Asset**
* Thread B: Health calculation updates **Telemetry summary**

### Lock order mismatch

```text
Thread A: Asset → Telemetry
Thread B: Telemetry → Asset
```

Under load → system freezes → ingestion stops → alerts missed.

---

## 6. How to Avoid Deadlock (Most Important Part)

### Solution 1: **Consistent Lock Ordering** (BEST & SIMPLEST)

Always acquire locks in the **same order**.

### Fix

```java
synchronized (assetLock) {
    synchronized (telemetryLock) {
        // safe
    }
}
```

Apply **everywhere**.

✔ Breaks circular wait
✔ Most commonly used

---

## 7. Solution 2: Use a Single Lock 

Instead of:

```java
assetLock + telemetryLock
```

Use:

```java
Object systemLock = new Object();
```

```java
synchronized (systemLock) {
    updateAsset();
    updateTelemetry();
}
```

 - Simple
 - Lower concurrency


## 8. Solution 3: Use `ReentrantLock` with Timeout

Avoid waiting forever.

```java
Lock lock1 = new ReentrantLock();
Lock lock2 = new ReentrantLock();

if (lock1.tryLock(1, TimeUnit.SECONDS)) {
    try {
        if (lock2.tryLock(1, TimeUnit.SECONDS)) {
            try {
                // critical section
            } finally {
                lock2.unlock();
            }
        }
    } finally {
        lock1.unlock();
    }
}
```

✔ No permanent deadlock
✔ Recovery possible
✔ Useful in high-availability systems

---

## 9. Solution 4: Minimize Lock Scope

Bad:

```java
synchronized(lock) {
    readFromDB();
    callExternalAPI();
    updateState();
}
```

Good:

```java
Data data = readFromDB();

synchronized(lock) {
    updateState(data);
}
```

✔ Shorter lock duration
✔ Less contention
✔ Fewer deadlocks

---

## 10. Solution 5: Prefer High-Level Concurrency APIs

Avoid manual locking when possible.

Instead of:

```java
synchronized(map) { ... }
```

Use:

```java
ConcurrentHashMap
AtomicInteger
BlockingQueue
ExecutorService
```

These are:

* Deadlock-safe by design
* Well-tested
* Optimized

---

## 11. How to Detect Deadlock 

### JVM Tool

```bash
jstack <pid>
```

Look for:

```text
Found one Java-level deadlock:
```

### Symptoms

* CPU low
* Threads stuck in `BLOCKED`
* No progress
* No errors




