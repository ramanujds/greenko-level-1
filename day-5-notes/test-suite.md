## What is a Test Suite?

A **Test Suite** is a **collection of test classes or test methods** that are **grouped together** and executed as a **single unit**.

In simple words:

> A test suite lets you say:
> “Run *these* tests together.”

### Why do we need Test Suites?

In real projects you may want to:

* Run only **unit tests**
* Run only **fast smoke tests**
* Skip slow or unstable tests
* Run tests **module-wise** or **feature-wise**
* Trigger different suites in **CI/CD pipelines**

---

## Example Scenario (Real World)

Imagine a project with:

* Authentication tests
* Order tests
* Payment tests

You may want:

* A **smoke suite** before every deploy
* A **full regression suite** at night

That’s where **test suites** come in.

---

## Test Suites in JUnit 5 (JUnit Jupiter)

Important:

* JUnit 5 **does not use `@RunWith`** (that was JUnit 4)
* JUnit 5 uses **JUnit Platform Suite API**

---

## Creating a Simple Test Suite in JUnit 5

### Step 1: Your Test Classes

```java
class UserServiceTest {
    @Test
    void createUserTest() {}
}

class OrderServiceTest {
    @Test
    void placeOrderTest() {}
}
```

---

### Step 2: Create a Test Suite Class

```java
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    UserServiceTest.class,
    OrderServiceTest.class
})
public class ApplicationTestSuite {
}
```

That’s it!
Running `ApplicationTestSuite` will run **both test classes together**.

---

## Test Suite Using Packages (Very Common)

Instead of listing classes one by one, you can run **all tests in a package**.

```java
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.myapp.service")
public class ServiceLayerTestSuite {
}
```

This will run **all tests inside `com.myapp.service` package**.

---

## Test Suite Using Tags (Best Practice)

### Step 1: Tag Your Tests

```java
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class LoginTest {

    @Test
    void loginShouldWork() {}
}
```

```java
@Tag("regression")
class PaymentTest {

    @Test
    void paymentShouldSucceed() {}
}
```

---

### Step 2: Create Tag-Based Test Suite

```java
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags("smoke")
public class SmokeTestSuite {
}
```

Only tests tagged with `"smoke"` will run.

---

## Combining Multiple Conditions

```java
@Suite
@SelectPackages("com.myapp")
@IncludeTags({"smoke", "fast"})
public class FastSmokeSuite {
}
```

---

## Running Test Suites via Maven (CI Friendly)

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.2.5</version>
  <configuration>
    <groups>smoke</groups>
  </configuration>
</plugin>
```

Or via command line:

```bash
mvn test -Dgroups=smoke
```

---

## Common Mistakes

* Creating too many suites without purpose
* Hardcoding class lists instead of packages/tags
* Mixing unit & integration tests in same suite
* Forgetting tags in new tests
* Not documenting the purpose of each suite