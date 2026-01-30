# Callable, Future, and CompletableFuture

## 1. Why `Callable` and `Future` Exist

### Problem with `Runnable`

```java
Runnable r = () -> processTelemetry();
```

Limitations:

* Cannot return a result
* Cannot throw checked exceptions

But in Greenko:

* We **need results** (health score, anomaly status)
* We **need error handling**

This is where **`Callable`** comes in.

---

## 2. `Callable<T>`

```java
Callable<AssetHealth> task = () -> {
    // heavy computation
    return AssetHealth.CRITICAL;
};
```

* Can return a result of type `T`
* Can throw checked exceptions

## 3. `Future<T>`

A **placeholder** for a result that will be available **later**.

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

Future<AssetHealth> future =
        executor.submit(() -> analyzeTelemetry(data));
```

### Getting the result

```java
AssetHealth health = future.get(); // blocks
```

---

## 4. Example – Telemetry Health Analysis

### Use case

* Telemetry arrives
* Health analysis is CPU-heavy
* Must not block ingestion

### Code

```java
Future<HealthStatus> future =
    executor.submit(() -> calculateHealth(telemetry));

saveTelemetry(telemetry);

// Later
HealthStatus status = future.get();
updateAssetHealth(status);
```

### What’s happening

* Telemetry saved immediately
* Health analysis runs in background
* Result fetched when needed

---

## 5. Limitations of `Future`

This is important.

### 5.1 Blocking Nature

```java
future.get(); // blocks thread
```

Bad for:

* High-throughput telemetry
* Reactive pipelines

---

### 5.2 No Chaining

You **cannot** say:

```java
future.thenApply(...)
```

---

### 5.3 No Easy Exception Handling

Exception handling is painful:

```java
try {
    future.get();
} catch (ExecutionException e) {
}
```

---

## 6. Enter `CompletableFuture`

Introduced in **Java 8**.

> A `CompletableFuture` represents a **non-blocking, asynchronous pipeline**.



## 7. Creating a `CompletableFuture`

### Run async (no return)

```java
CompletableFuture.runAsync(() -> ingestTelemetry(data));
```

---

### Supply async (returns value)

```java
CompletableFuture<HealthStatus> future =
    CompletableFuture.supplyAsync(() -> analyzeTelemetry(data));
```

---

## 8. Telemetry Pipeline with `CompletableFuture`

### Requirement

1. Save telemetry
2. Analyze health
3. Trigger alert if needed
4. Update dashboard

### CompletableFuture Solution

```java
CompletableFuture
    .supplyAsync(() -> analyzeTelemetry(data))
    .thenApply(health -> enrichHealth(health))
    .thenAccept(health -> {
        if (health.isCritical()) {
            sendAlert(health);
        }
        updateDashboard(health);
    })
    .exceptionally(ex -> {
        log.error("Telemetry processing failed", ex);
        return null;
    });
```

✔ No blocking
✔ Clear flow
✔ Error handling built-in

---

## 9. Parallel Tasks with `CompletableFuture`

Greenko use case:

* Voltage analysis
* Temperature analysis
* Vibration analysis

### Run in parallel

```java
CompletableFuture<Integer> voltage =
    CompletableFuture.supplyAsync(() -> analyzeVoltage(data));

CompletableFuture<Integer> temp =
    CompletableFuture.supplyAsync(() -> analyzeTemperature(data));

CompletableFuture<Integer> vibration =
    CompletableFuture.supplyAsync(() -> analyzeVibration(data));
```

### Combine results

```java
CompletableFuture<HealthScore> combined =
    voltage.thenCombine(temp, (v, t) -> v + t)
           .thenCombine(vibration, (vt, vib) -> vt + vib);
```

---

## 10. Waiting for All Tasks

```java
CompletableFuture.allOf(voltage, temp, vibration)
    .thenRun(() -> updateFinalHealth());
```

---

## 11. Custom Thread Pool (Very Important)

Never rely on common pool for heavy telemetry systems.

```java
ExecutorService analysisPool =
    Executors.newFixedThreadPool(10);

CompletableFuture
    .supplyAsync(() -> analyzeTelemetry(data), analysisPool);
```

---
## 12. Exception Handling

```java
CompletableFuture
    .supplyAsync(() -> analyzeTelemetry(data))
    .exceptionally(ex -> {
        log.error("Analysis failed", ex);
        return defaultHealth();
    });
    
```


## 13. When to Use What

* **Callable + Future**

    * Simple background computation
    * Legacy code
    * One-off async task

* **CompletableFuture**

    * Telemetry pipelines
    * Alerting workflows
    * Microservices orchestration
    * High-throughput systems


