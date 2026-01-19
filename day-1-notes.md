# Day 1 – Java & OOP Fundamentals

**Trainer:** Ramanuj Das 
**Focus:** Core OOP concepts, clean coding, and hands-on library system design

***

## Table of Contents
1. Java Syntax & Best Practices
2. OOP Core Concepts
3. Clean Code Principles
4. Hands-on Lab Exercises
5. Key Takeaways & Next Steps

***


## 1. Java Syntax & Best Practices Refresher

### 2.1 Class Structure & Naming Conventions

**Basic Structure:**
```java
public class ClassName {
    // Fields (state)
    private String field;
    
    // Constructor
    public ClassName(String field) {
        this.field = field;
    }
    
    // Methods (behavior)
    public void doSomething() {
        System.out.println("Doing something");
    }
}
```

**Naming Rules (follow religiously):**
- **Classes:** `PascalCase` → `Book`, `StudentManager`, `PaymentService`
- **Methods & variables:** `camelCase` → `getName()`, `studentCount`, `isActive`
- **Constants:** `UPPER_SNAKE_CASE` → `MAX_ATTEMPTS = 3`
- **Packages:** lowercase → `com.example.library.model`

**Why it matters:**
- Code is read 10x more than written
- Consistency makes teams 2x faster
- IDE refactoring relies on naming patterns

***

### 2.2 Best Practices: Write Code Others Will Love

#### Principle 1: Single Responsibility (SRP)
**One class = one reason to change**

```java
// BAD: Multiple responsibilities
public class Student {
    private String name;
    
    public void printStudentDetails() {
        System.out.println(name);
    }
    
    public void saveToDatabase() {
        // DB code here
    }
    
    public void sendEmail() {
        // Email code here
    }
}

// GOOD: Single responsibility each
public class Student {
    private String name;
    public String getName() { return name; }
}

public class StudentRepository {
    public void save(Student student) { /* DB */ }
}

public class StudentNotificationService {
    public void notifyStudent(Student student) { /* Email */ }
}
```

#### Principle 2: Intention-Revealing Names
```java
// BAD: Cryptic names
public int c(int a, int b) {
    int x = a + b;
    return x * 2;
}

// GOOD: Clear intent
public int calculateTotalPayment(int baseSalary, int bonus) {
    int grossAmount = baseSalary + bonus;
    return grossAmount * 2;  // or just: return (baseSalary + bonus) * 2
}
```

#### Principle 3: Keep Methods Small
```java
// BAD: 50 lines in one method
public void processOrder(Order order) {
    // validate...
    // calculate...
    // update DB...
    // send email...
    // log metrics...
}

// GOOD: Broken into focused methods
public void processOrder(Order order) {
    validateOrder(order);
    calculateTotals(order);
    saveOrder(order);
    notifyCustomer(order);
}
```

#### Principle 4: Avoid Magic Numbers
```java
// BAD
if (age >= 18) { /* voter */ }
if (attempts > 3) { /* lock */ }

// GOOD
private static final int VOTING_AGE = 18;
private static final int MAX_LOGIN_ATTEMPTS = 3;

if (age >= VOTING_AGE) { /* voter */ }
if (attempts > MAX_LOGIN_ATTEMPTS) { /* lock */ }
```

#### Principle 5: Meaningful Comments (Not Every Line!)
```java
// BAD: Over-commenting
int age = 25;  // set age to 25
age++;  // increment age

// GOOD: Comment the WHY, not the WHAT
// Users must be 18+ to vote; increment for next year's eligibility check
int age = 25;
age++;
```

***

### 2.3 Understanding the Main Method

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

**Why `public static void main(String[] args)`?**
- `public` = JVM can access it
- `static` = no object needed to call it
- `void` = no return value
- `String[] args` = command-line arguments

**Usage:**
```bash
java HelloWorld
java HelloWorld arg1 arg2
```

***

## 3. Object-Oriented Programming (OOP) Core Concepts

### 3.1 Classes & Objects (The Foundation)

**Concept:**
- **Class** = Blueprint/template (like a car design)
- **Object** = Actual instance (like the car you drive)

**Example:**
```java
public class Book {
    // Fields = state (data)
    private String title;
    private String author;
    private int pages;
    
    // Constructor = initialization
    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
    
    // Methods = behavior (what it can do)
    public String getTitle() {
        return title;
    }
    
    public void display() {
        System.out.println(title + " by " + author);
    }
}
```

**Usage:**
```java
// Create objects (instances)
Book book1 = new Book("Clean Code", "Robert Martin", 464);
Book book2 = new Book("Effective Java", "Joshua Bloch", 416);

// Use them
book1.display();  // Output: Clean Code by Robert Martin
```

**Key Insight:**
- Each object has its own state
- Multiple objects can use the same class blueprint

***

### 3.2 Encapsulation (Hide Internals, Expose Behavior)

**Core Idea:**
- Make fields `private` (hide implementation)
- Provide `public` methods to access/modify (control access)
- This lets you validate data and maintain invariants

**Real-World Analogy:**
- ATM machine: You can't directly touch the internal money. You use the interface (buttons) to withdraw.
- The ATM validates your request before allowing withdrawal.

**Example:**
```java
public class BankAccount {
    private String accountNumber;
    private double balance;
    private static final double MIN_BALANCE = 100.0;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Encapsulation: getter (read-only)
    public double getBalance() {
        return balance;
    }
    
    // Encapsulation: controlled withdrawal
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance - MIN_BALANCE) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
        System.out.println("Withdrew: " + amount);
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        this.balance += amount;
        System.out.println("Deposited: " + amount);
    }
}
```

**Why This Works:**
```java
BankAccount account = new BankAccount("ACC123", 5000);
account.deposit(1000);        // Works: balance = 6000
account.withdraw(500);         // Works: balance = 5500
account.withdraw(5500);        // Throws exception: violates MIN_BALANCE

// Cannot do this (field is private):
// account.balance = -999999;   // Compile error
```

**Benefits:**
- Protects data integrity
- Changes internal code without breaking users
- Easier to debug (controlled access points)

***

### 3.3 Inheritance (IS-A Relationship)

**Concept:**
- Child class inherits fields/methods from parent
- Allows code reuse and relationship modeling
- Use `extends` keyword

**Real-World Example:**
```
LibraryItem (Parent)
  ├── Book
  ├── DVD
  └── Magazine
```

**Code:**
```java
// Parent class
public abstract class LibraryItem {
    private String id;
    private String title;
    private int yearPublished;
    
    public LibraryItem(String id, String title, int yearPublished) {
        this.id = id;
        this.title = title;
        this.yearPublished = yearPublished;
    }
    
    public String getTitle() { return title; }
    public int getYearPublished() { return yearPublished; }
    
    // Abstract method (child must implement)
    public abstract int getMaxLoanDays();
}

// Child class 1
public class Book extends LibraryItem {
    private String isbn;
    
    public Book(String id, String title, int year, String isbn) {
        super(id, title, year);  // Call parent constructor
        this.isbn = isbn;
    }
    
    @Override
    public int getMaxLoanDays() {
        return 14;  // Books can be borrowed for 14 days
    }
}

// Child class 2
public class DVD extends LibraryItem {
    private int durationMinutes;
    
    public DVD(String id, String title, int year, int duration) {
        super(id, title, year);
        this.durationMinutes = duration;
    }
    
    @Override
    public int getMaxLoanDays() {
        return 3;  // DVDs: 3 days only
    }
}
```

**Usage:**
```java
Book book = new Book("B1", "Clean Code", 2008, "ISBN123");
DVD dvd = new DVD("D1", "Inception", 2010, 148);

System.out.println(book.getMaxLoanDays());  // 14
System.out.println(dvd.getMaxLoanDays());   // 3
System.out.println(book.getTitle());        // Clean Code (inherited)
```

**Key Points:**
- `super()` calls parent constructor
- `@Override` marks overridden methods (documentation + compiler check)
- Child has all parent methods + its own

***

### 3.4 Polymorphism (Many Forms)

**Concept:**
- Same method call, different behavior depending on object type
- Enables flexible, extensible code

**Real Example:**
```java
public void printBorrowInfo(LibraryItem item) {
    System.out.println("Item: " + item.getTitle());
    System.out.println("Max borrow days: " + item.getMaxLoanDays());
}

// This method works with ANY LibraryItem subclass
Book book = new Book("B1", "Clean Code", 2008, "ISBN");
DVD dvd = new DVD("D1", "Inception", 2010, 148);

printBorrowInfo(book);  // Calls Book's getMaxLoanDays() → 14
printBorrowInfo(dvd);   // Calls DVD's getMaxLoanDays() → 3
```

**Why This Matters:**
- Write once, work with many types
- Add new types without changing existing code
- This is how Spring Boot wires dependencies!

***

### 3.5 Abstraction (Interfaces & Abstract Classes)

**Abstract Classes:**
- Cannot instantiate directly
- Can have fields + implemented methods + abstract methods
- Child classes must implement abstract methods
- Use `abstract` keyword

```java
public abstract class LibraryItem {
    public abstract int getMaxLoanDays();  // No implementation
    
    public void printInfo() {  // Can have implementation
        System.out.println("This is a library item");
    }
}

// Cannot do this:
// LibraryItem item = new LibraryItem();  // Compile error

// Must create concrete subclass:
public class Book extends LibraryItem {
    @Override
    public int getMaxLoanDays() {
        return 14;
    }
}

Book book = new Book(...);  // Works
```

**Interfaces:**
- Pure contract (what methods must exist)
- No state (fields); only method signatures
- A class can implement multiple interfaces
- Use `interface` keyword and `implements`

```java
public interface Notifiable {
    void sendNotification(String message);
    String getNotificationChannel();
}

public class EmailNotifier implements Notifiable {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email: " + message);
    }
    
    @Override
    public String getNotificationChannel() {
        return "email";
    }
}

public class SmsNotifier implements Notifiable {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS: " + message);
    }
    
    @Override
    public String getNotificationChannel() {
        return "sms";
    }
}
```

**Comparing Abstract Classes vs Interfaces:**

| Aspect | Abstract Class | Interface |
|--------|---|---|
| **Instantiate?** | No | No |
| **State (fields)** | Yes | No (only constants) |
| **Constructor** | Yes | No |
| **Multiple implementation** | No (single inheritance) | Yes |
| **Use case** | Shared code + contract | Pure contract |

**Real Pattern (Dependency Injection):**
```java
public class NotificationService {
    private Notifiable notifier;
    
    public NotificationService(Notifiable notifier) {
        this.notifier = notifier;  // Can be any Notifiable
    }
    
    public void alertUser(String message) {
        notifier.sendNotification(message);
    }
}

// Usage: inject different notifiers
NotificationService service1 = new NotificationService(new EmailNotifier());
NotificationService service2 = new NotificationService(new SmsNotifier());

service1.alertUser("Welcome!");  // Sends email
service2.alertUser("Welcome!");  // Sends SMS
```

This is **exactly** how Spring Boot works (we'll see it on Day 4).

***

### 3.6 Constructors & Initialization

**Purpose:**
- Initialize object in valid state
- Ensure required fields are set

```java
public class Student {
    private String id;
    private String name;
    private String email;
    private boolean active;
    
    // Constructor 1: Minimal
    public Student(String id, String name) {
        this(id, name, "", true);  // Delegate to full constructor
    }
    
    // Constructor 2: Full
    public Student(String id, String name, String email, boolean active) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
}
```

**Constructor Overloading:**
```java
// Different constructors, same class name
new Student("S1", "Alice");
new Student("S1", "Alice", "alice@example.com", true);
```

**Key Points:**
- Constructor name = class name
- No return type
- Can have multiple constructors (overloading)
- Use `this()` to delegate between constructors

***

## 4. Clean Code & Design Principles

### 4.1 SOLID Principles (Quick Overview)

**S: Single Responsibility**
- One class = one reason to change
- Example: `StudentRepository` for DB, `StudentValidator` for validation

**O: Open/Closed**
- Open for extension, closed for modification
- Example: Use interfaces so you can add new types without changing existing code

**L: Liskov Substitution**
- Child can replace parent without breaking code
- Example: Any `LibraryItem` subclass works wherever parent is expected

**I: Interface Segregation**
- Don't force classes to implement methods they don't need
- Bad: `Animal` interface with `fly()` (not all animals fly)
- Good: Separate `Flyable` interface

**D: Dependency Inversion**
- Depend on abstractions, not concrete classes
- Example: Inject `Notifiable` interface, not `EmailNotifier` class

We'll see these in action throughout the training.

***

### 4.2 DRY (Don't Repeat Yourself)

```java
// BAD: Validation duplicated
public void registerStudent(String email) {
    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Invalid email");
    }
    // register...
}

public void updateStudentEmail(String email) {
    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Invalid email");
    }
    // update...
}

// GOOD: Extract to single method
private void validateEmail(String email) {
    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Invalid email");
    }
}

public void registerStudent(String email) {
    validateEmail(email);
    // register...
}

public void updateStudentEmail(String email) {
    validateEmail(email);
    // update...
}
```

***

### 4.3 YAGNI (You Aren't Gonna Need It)

```java
// BAD: Over-engineering for future use
public class Book {
    public int getMaxLoanDays() { return 14; }
    public int getMaxLoanDaysForPremium() { return 21; }
    public int getMaxLoanDaysForStudent() { return 7; }
    public int getMaxLoanDaysForFaculty() { return 28; }
    // ... and 10 more variations
}

// GOOD: Start simple, extend when needed
public class Book {
    public int getMaxLoanDays() { return 14; }
}

// If needed later:
public class PremiumBook extends Book {
    @Override
    public int getMaxLoanDays() { return 21; }
}
```

***

## 5. Hands-on Lab Exercises

### Lab 1: Student Class

**Task:**
Create a `Student` class with encapsulation and validation.

**Requirements:**
- Fields: `id` (String), `name` (String), `email` (String), `enrollmentDate` (LocalDate)
- Constructor: initialize all fields with validation
    - ID must not be empty
    - Email must contain '@'
    - Enrollment date must not be in future
- Methods:
    - `getName()` (getter)
    - `getEmail()` (getter)
    - `updateEmail(String newEmail)` (with validation)

**Starter Code:**
```java
import java.time.LocalDate;

public class Student {
    private String id;
    private String name;
    private String email;
    private LocalDate enrollmentDate;
    
    public Student(String id, String name, String email, LocalDate enrollmentDate) {
        // TODO: Validate and initialize
    }
    
    public String getName() {
        // TODO: Return name
    }
    
    public void updateEmail(String newEmail) {
        // TODO: Validate and update email
    }
}
```

**Test Your Code:**
```java
public class Main {
    public static void main(String[] args) {
        Student student = new Student("S001", "Alice", "alice@example.com", LocalDate.now());
        System.out.println("Name: " + student.getName());
        
        student.updateEmail("alice.new@example.com");
        System.out.println("Updated email");
        
        // This should throw exception:
        // student.updateEmail("invalid-email");
    }
}
```

**Solution Walkthrough:**
```java
public class Student {
    private String id;
    private String name;
    private String email;
    private LocalDate enrollmentDate;
    
    public Student(String id, String name, String email, LocalDate enrollmentDate) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (enrollmentDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Enrollment date cannot be in future");
        }
        
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void updateEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = newEmail;
    }
}
```

***

### Lab 2: Library Item Hierarchy (Inheritance + Polymorphism)

**Task:**
Model a library with different item types using inheritance.

**Requirements:**
- Abstract class: `LibraryItem` with:
    - Fields: `id`, `title`, `yearPublished`
    - Getter methods
    - Abstract method: `getMaxLoanDays()`

- Concrete classes:
    - `Book` (max loan: 14 days)
    - `DVD` (max loan: 3 days)
    - `Magazine` (max loan: 7 days)

**Code:**
```java
public abstract class LibraryItem {
    private String id;
    private String title;
    private int yearPublished;
    
    public LibraryItem(String id, String title, int yearPublished) {
        this.id = id;
        this.title = title;
        this.yearPublished = yearPublished;
    }
    
    public String getTitle() { return title; }
    public int getYearPublished() { return yearPublished; }
    
    public abstract int getMaxLoanDays();
}

public class Book extends LibraryItem {
    public Book(String id, String title, int yearPublished) {
        super(id, title, yearPublished);
    }
    
    @Override
    public int getMaxLoanDays() {
        return 14;
    }
}

public class DVD extends LibraryItem {
    public DVD(String id, String title, int yearPublished) {
        super(id, title, yearPublished);
    }
    
    @Override
    public int getMaxLoanDays() {
        return 3;
    }
}

public class Magazine extends LibraryItem {
    public Magazine(String id, String title, int yearPublished) {
        super(id, title, yearPublished);
    }
    
    @Override
    public int getMaxLoanDays() {
        return 7;
    }
}
```

**Test Polymorphism:**
```java
public class Main {
    public static void main(String[] args) {
        LibraryItem book = new Book("B1", "Clean Code", 2008);
        LibraryItem dvd = new DVD("D1", "Inception", 2010);
        LibraryItem mag = new Magazine("M1", "Tech Weekly", 2024);
        
        printInfo(book);   // Max days: 14
        printInfo(dvd);    // Max days: 3
        printInfo(mag);    // Max days: 7
    }
    
    public static void printInfo(LibraryItem item) {
        System.out.println("Title: " + item.getTitle());
        System.out.println("Max loan days: " + item.getMaxLoanDays());
        System.out.println();
    }
}
```

***

### Lab 3: Mini Library System (Everything Together)

**Task:**
Build a simple library system combining all concepts.

**Classes Needed:**
1. `LibraryItem` (abstract) - Already done
2. `Member` class
3. `Library` class (manages members and items)

**Code Skeleton:**
```java
public class Member {
    private String memberId;
    private String name;
    private int maxBooksAllowed;
    
    public Member(String memberId, String name, int maxBooksAllowed) {
        this.memberId = memberId;
        this.name = name;
        this.maxBooksAllowed = maxBooksAllowed;
    }
    
    public String getName() { return name; }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }
}

public class Library {
    private String libraryName;
    private List<Member> members = new ArrayList<>();
    private List<LibraryItem> items = new ArrayList<>();
    
    public Library(String libraryName) {
        this.libraryName = libraryName;
    }
    
    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member " + member.getName() + " added");
    }
    
    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Item added");
    }
    
    public void displayItems() {
        System.out.println("=== " + libraryName + " ===");
        for (LibraryItem item : items) {
            System.out.println("- " + item.getTitle() + " (" + item.getMaxLoanDays() + " days)");
        }
    }
}
```

**Test:**
```java
public class Main {
    public static void main(String[] args) {
        Library lib = new Library("City Library");
        
        lib.addMember(new Member("M1", "Alice", 5));
        lib.addMember(new Member("M2", "Bob", 3));
        
        lib.addItem(new Book("B1", "Clean Code", 2008));
        lib.addItem(new DVD("D1", "Inception", 2010));
        lib.addItem(new Magazine("M1", "Tech Weekly", 2024));
        
        lib.displayItems();
    }
}
```

***

## 6. Key Takeaways & Next Steps

### What You Learned Today

1. **OOP Fundamentals:**
    - Classes & objects = blueprints & instances
    - Encapsulation = hide implementation, control access
    - Inheritance = reuse code & model relationships
    - Polymorphism = flexible, extensible code
    - Abstraction = define contracts with interfaces/abstract classes

2. **Best Practices:**
    - Single Responsibility: One class = one job
    - Clear naming: Code is read more than written
    - Small methods: Each does one thing well
    - No magic numbers: Use constants
    - Avoid duplication: Extract common code

3. **Hands-on Skills:**
    - Write validation in constructors
    - Use `super()` to call parent constructors
    - Use `@Override` for clarity
    - Test polymorphism with different types

### Common Mistakes to Avoid

- **Public fields:** Use `private` fields with getters/setters
- **God classes:** If your class name contains "Manager/Helper/Utility", it probably has too many jobs
- **Deep inheritance:** 3+ levels usually indicates poor design
- **Ignoring validation:** Always validate in constructors
- **Overloading constructors:** Use builder pattern for >3 parameters (we'll see this later)

### Tomorrow (Day 2): Exception Handling, Collections & Streams

- How to throw and catch exceptions properly
- List, Set, Map internals and when to use each
- Lambda functions and Streams API
- Real data processing patterns

### Self-Study Before Tomorrow

1. **Review:** Inheritance/polymorphism example codes
2. **Practice:** Build a small hierarchy (e.g., Shape → Circle, Rectangle)
3. **Read:** Java naming conventions guide (5 min)
4. **Optional:** Watch 1 video on "Why encapsulation matters"

### Questions & Support

- Post questions in Slack/WhatsApp group anytime
- Office hours: Tomorrow before start (15 mins)

***

## Appendix: Quick Reference

### Access Modifiers
```java
public       // Accessible everywhere
protected    // Accessible in same package + subclasses
(default)    // Accessible in same package only
private      // Accessible only in this class
```

### Keyword Cheat Sheet
```java
class         // Define a class
abstract      // Cannot instantiate
interface     // Pure contract
extends       // Inherit from class
implements    // Implement interface
super         // Call parent method/constructor
this          // Refer to current object
final         // Cannot override/change
static        // Belongs to class, not instance
@Override     // Mark overridden methods
```

### Common Object Methods
```java
toString()    // String representation
equals()      // Compare objects
hashCode()    // Hash for collections
getClass()    // Get object's class
```

***

**Next Session:** Day 2 - Collections & Streams

**Happy Coding!** 