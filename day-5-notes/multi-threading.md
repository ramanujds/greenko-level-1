# Problem Context 

Greenko operates:

* Wind Turbines
* Solar Panels

Each asset continuously sends:

* Temperature
* Power output
* Health metrics

### Challenges

* Millions of events per minute
* Real-time processing
* No blocking
* Independent processing per asset
* High throughput

**Single-threaded processing will collapse**.

---

# High-Level Architecture 

```
Assets  →  Ingestion Layer  →  Processing Layer  →  Storage / Alerts
            (Kafka / API)      (Multithreaded)
```

---


# Domain Model

### AssetType

```java
enum AssetType {
    TURBINE,
    SOLAR_PANEL
}
```

---

### AssetData 

```java
class AssetData {
    final String assetId;
    final AssetType type;
    final double power;
    final double temperature;

    AssetData(String assetId, AssetType type,
              double power, double temperature) {
        this.assetId = assetId;
        this.type = type;
        this.power = power;
        this.temperature = temperature;
    }
}
```

---

# Runnable Task 


```java
class AssetDataProcessor implements Runnable {

    private final AssetData data;

    AssetDataProcessor(AssetData data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println(
            Thread.currentThread().getName() +
            " processing " + data.assetId
        );

        validate();
        analyze();
        persist();
        checkAlerts();
    }

    private void validate() {
        if (data.temperature > 100) {
            System.out.println("High temperature detected for " + data.assetId);
        }
    }

    private void analyze() {
        try { Thread.sleep(200); } catch (Exception ignored) {}
    }

    private void persist() {
        System.out.println("Persisted data for " + data.assetId);
    }

    private void checkAlerts() {
        if (data.power < 10) {
            System.out.println("Low power alert for " + data.assetId);
        }
    }
}
```


# Thread Pool Setup (Production Style)

**Wrong approach**

```java
new Thread(task).start(); // millions of threads → disaster
```

**Correct approach**

```java
ExecutorService executor =
    Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
```

---

# Processing Millions of Asset Events

```java
public class GreenkoProcessingSystem {

    public static void main(String[] args) {

        ExecutorService executor =
            Executors.newFixedThreadPool(8); // CPU-bound

        for (int i = 1; i <= 1000; i++) {

            AssetData data = new AssetData(
                "TURBINE-" + i,
                AssetType.TURBINE,
                Math.random() * 100,
                Math.random() * 120
            );

            Runnable task = new AssetDataProcessor(data);
            executor.submit(task);
        }

        executor.shutdown();
    }
}
```

---

# What Happens Internally

* Main thread only **submits tasks**
* Worker threads:

    * Validate
    * Analyze
    * Store
    * Trigger alerts
* Tasks run **concurrently**
* CPU is fully utilized
* Throughput increases drastically

---

# Why Multithreading Is Mandatory Here

### Without Multithreading

* Sequential processing
* Data backlog
* Delayed alerts
* Asset failures unnoticed

### With Multithreading

* Parallel processing
* Near real-time insights
* Faster alerting
* Scales with CPU cores

---



# Important Engineering Considerations 

### 1. Thread Safety

* Each task is independent
* No shared mutable state

### 2. Backpressure

* Executor queue controls load

### 3. Fault Isolation

* One asset failure ≠ system failure

### 4. Scalability

* Increase threads or nodes

---

# Summary
* Multithreading is essential for high-throughput real-time processing.
* Use `ExecutorService` for efficient thread management.
* Each asset event is processed independently.
* This architecture ensures scalability, fault tolerance, and responsiveness.


