## What is Unit Testing?

**Unit Testing** is the practice of testing the **smallest testable unit of code**—usually a **method or class**—in isolation to ensure it behaves as expected.

Key idea:

* Test **one unit**
* Test it **independently**
* Test it **automatically**
* Test it **frequently**

In Java, the de-facto standard framework is **JUnit**, and the modern version is **JUnit 5**.

---

## Why Unit Testing is Important

From an enterprise / microservices perspective:

* Prevents **regressions** when code changes
* Enables **safe refactoring**
* Acts as **living documentation**
* Mandatory for **CI/CD pipelines**
* Improves **design quality** (testable code = better code)

In large systems (banking, energy, e-commerce), **no unit tests = no production deploy**.

---

## JUnit 5 Architecture 

JUnit 5 is **not a single jar**, it’s a platform:

| Component          | Purpose                                   |
| ------------------ | ----------------------------------------- |
| **JUnit Platform** | Test engine launcher (IDE, Maven, Gradle) |
| **JUnit Jupiter**  | API + Engine for writing tests            |
| **JUnit Vintage**  | For running JUnit 3/4 tests               |


## Basic JUnit 5 Test Example

### Production Code

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```

### Test Code

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        Calculator calculator = new Calculator();
        int result = calculator.add(10, 20);
        assertEquals(30, result);
    }
}
```

Key points:

* Test class name ends with `Test`
* `@Test` marks a test method
* Assertions validate expected vs actual

---

## Common JUnit 5 Annotations

| Annotation     | Purpose                    |
| -------------- | -------------------------- |
| `@Test`        | Marks a test method        |
| `@BeforeEach`  | Runs before each test      |
| `@AfterEach`   | Runs after each test       |
| `@BeforeAll`   | Runs once before all tests |
| `@AfterAll`    | Runs once after all tests  |
| `@DisplayName` | Custom readable test name  |
| `@Disabled`    | Skip a test                |

### Example

```java
@BeforeEach
void setup() {
    calculator = new Calculator();
}
```

---

## Assertions in JUnit 5

Assertions are the **heart of unit tests**.

```java
assertEquals(expected, actual);
assertNotEquals(a, b);
assertTrue(condition);
assertFalse(condition);
assertNull(value);
assertNotNull(value);
```

### Exception Testing

```java
assertThrows(IllegalArgumentException.class, () -> {
    service.withdraw(-100);
});
```

---

## Parameterized Tests 

Avoid duplicate test methods.

```java
@ParameterizedTest
@CsvSource({
    "1,2,3",
    "5,5,10",
    "10,20,30"
})
void shouldAddNumbers(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
```

This is heavily used in **data-driven testing**.

---

## Testing with Setup & Teardown

```java
class UserServiceTest {

    UserService service;

    @BeforeEach
    void init() {
        service = new UserService();
    }

    @AfterEach
    void cleanup() {
        // release resources
    }
}
```

---

## Unit Testing vs Integration Testing

![Image](https://i0.wp.com/theembeddedkit.io/wp-content/uploads/2023/11/The-Pyramid-of-test-Automated-testing.png)

| Aspect       | Unit Test           | Integration Test    |
| ------------ | ------------------- | ------------------- |
| Scope        | Single class/method | Multiple components |
| Speed        | Very fast           | Slower              |
| Dependencies | Mocked              | Real                |
| Tools        | JUnit + Mockito     | JUnit + Spring Test |

---

## Unit Testing with Dependencies (Mockito + JUnit 5)

Real systems rarely have isolated classes.

```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    PaymentGateway gateway;

    @InjectMocks
    OrderService service;

    @Test
    void shouldPlaceOrderSuccessfully() {
        when(gateway.pay()).thenReturn(true);
        assertTrue(service.placeOrder());
    }
}
```

JUnit tests **behavior**, Mockito handles **collaborators**.

---

## Best Practices

* One test should test **one behavior**
* Test names should read like **sentences**
* Keep tests **independent**
* Tests should be **repeatable**
* Follow **AAA pattern**
  **Arrange → Act → Assert**
* Use **mocks** wisely

## Common Mistakes

* Writing tests after bugs appear
* Overusing mocks
* No assertions (yes, it happens)
* Huge test methods

---

## Where Unit Testing Fits in Real Projects

![Image](https://testsigma.com/blog/wp-content/uploads/testing-pyramid.png)

Typical flow:

1. Developer writes code
2. Writes unit tests
3. Pushes to Git
4. CI runs tests
5. Build passes → Deploy

If unit tests fail → pipeline stops.

---


