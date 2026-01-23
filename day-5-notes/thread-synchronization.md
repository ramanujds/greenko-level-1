# Why Do We Need Thread Synchronization?

## The Core Problem: Shared Mutable Data

Multithreading becomes **dangerous** when:

* Multiple threads
* Access the **same shared resource**
* At least one thread **modifies** it

This leads to:

* **Race conditions**
* **Inconsistent data**
* **Hard-to-reproduce bugs**

> Multithreading itself is not the problem.
> **Uncontrolled access to shared data is.**

---

## Example: The Bank Account Analogy

Imagine:

* A **single bank account**
* Two people withdrawing money **at the same time**

If access is not controlled:

* Both see the same balance
* Both withdraw
* Balance becomes incorrect

That’s exactly what happens in code.

---

# Problem Example (WITHOUT Synchronization)

## Scenario: Asset Energy Counter 

Multiple threads update **total energy generated** by assets.

---

### Shared Resource

```java
class EnergyCounter {
    int totalEnergy = 0;

    void addEnergy(int units) {
        totalEnergy = totalEnergy + units;
    }
}
```

---

### Runnable Task

```java
class EnergyTask implements Runnable {

    private final EnergyCounter counter;

    EnergyTask(EnergyCounter counter) {
        this.counter = counter;
    }
    
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.addEnergy(1);
        }
    }
}
```

---

### Main Program

```java
public class Main {
    public static void main(String[] args) throws Exception {

        EnergyCounter counter = new EnergyCounter();

        Thread t1 = new Thread(new EnergyTask(counter));
        Thread t2 = new Thread(new EnergyTask(counter));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.totalEnergy);
    }
}
```

---

### Expected Output

```
2000
```

### Actual Output (varies!)

```
1834
1972
1650
```

**Data corruption**

---

## What Went Wrong? 

This line:

```java
totalEnergy = totalEnergy + units;
```

Is **NOT atomic**.

It expands to:

1. Read `totalEnergy`
2. Add `units`
3. Write back result

Two threads can interleave like this:

```
Thread A reads totalEnergy = 100
Thread B reads totalEnergy = 100
Thread A writes 101
Thread B writes 101  (lost update)
```

---

# This Is Why We Need Synchronization

> **Thread synchronization ensures that only one thread accesses a critical section at a time.**

---

# Solution 1: `synchronized` Method

```java
class EnergyCounter {
    int totalEnergy = 0;

    synchronized void addEnergy(int units) {
        totalEnergy = totalEnergy + units;
    }
}
```

---

### Output 

```
2000
```

---

## What `synchronized` Does Internally

* Acquires a **monitor lock**
* Blocks other threads
* Guarantees:

    * Mutual exclusion
    * Visibility
    * Happens-before relationship

---

# Solution 2: `synchronized` Block 

```java
void addEnergy(int units) {
    synchronized (this) {
        totalEnergy = totalEnergy + units;
    }
}
```

Use when:

* Only part of method needs locking
* Performance matters

---

# Solution 3: Using `AtomicInteger` 

```java
class EnergyCounter {
    AtomicInteger totalEnergy = new AtomicInteger(0);

    void addEnergy(int units) {
        totalEnergy.addAndGet(units);
    }
}
```

---

# Why Synchronization Is CRITICAL in Real Systems

### Without synchronization:

* Wrong billing
* Incorrect metrics
* Missed alerts
* Financial loss
* System instability

### With synchronization:

* Data consistency
* Predictable behavior
* Correct analytics




### Can synchronization cause issues?

Yes:

* Deadlocks
* Reduced throughput
* Increased latency
* Complex code

That’s why **lock scope must be minimal**.

