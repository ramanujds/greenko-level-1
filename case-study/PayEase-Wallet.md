## Case Study – “PayEase Wallet” 

**Context:**  
You are building the backend domain model and core logic for **PayEase**, a digital wallet app similar to Paytm/PhonePe. Users can register, load money, transfer to other users, and view transaction history.

**Scope**
- Pure Java (no Spring yet).
- Focus on modeling, correctness, readability, and modern Java features.

***

## Day 1 – OOP & Clean Code Focus

### User Stories (Day 1)

1. **US-1: Register Wallet User (Basic Profile)**
    - As a user, I want to have a wallet account with my basic details so that I can use the app.
    - Fields: `userId`, `fullName`, `email`, `phoneNumber`.

2. **US-2: Create Wallet & Check Balance**
    - As a user, I want a wallet linked to my profile so that I can see my balance.
    - Wallet fields: `walletId`, `owner` (user), `balance`.

3. **US-3: Deposit Money (Happy Path Only)**
    - As a user, I want to deposit money into my wallet so that I can spend later.

***

### Day 1 – Todos

**Model Layer (Classes & Relationships):**

- [ ] Create class `User`
    - Fields: `userId`, `fullName`, `email`, `phoneNumber`
    - Constructor with validation:
        - `userId` not empty
        - `email` contains `@`
        - `phoneNumber` non-empty
    - Encapsulate fields (`private` + getters).
- [ ] Create class `Wallet`
    - Fields: `walletId`, `owner` (`User`), `balance` (double or BigDecimal for later)
    - Constructor: set initial balance to 0.
    - Methods:
        - `getBalance()`
        - `deposit(double amount)` – no negative check yet (we’ll enhance on Day 2).
- [ ] Create `Main` or `Demo` class
    - Hardcode a `User` and associated `Wallet`
    - Call `deposit()` and print new balance.

**Hints (Day 1):**

- Use **encapsulation**: no public fields.
- Use **constructor** to enforce invariants (e.g., never allow `null` `userId`).
- Use **Single Responsibility**:
    - `User` = identity + contact.
    - `Wallet` = money handling.
- Optional: Create `UserService` just to separate creation logic from `main`.

***

## Day 2 – Exceptions, Collections & Streams Focus

### New Requirements for Day 2

4. **US-4: Validate Money Operations**
    - As a user, I must not be able to deposit or send negative or zero amounts.
    - As a user, I must not be able to send more money than my balance.

5. **US-5: Transfer Money Between Wallets**
    - As a user, I want to transfer money from my wallet to another user's wallet.

6. **US-6: View My Transaction History**
    - As a user, I want to see a list of all my transactions (date, amount, type, counterparty).

***

### Day 2 – Todos

**1) Exception Handling & Validation**

- [ ] Create custom exception classes:
    - `InvalidAmountException extends RuntimeException`
    - `InsufficientBalanceException extends RuntimeException`
- [ ] Enhance `Wallet.deposit(double amount)`:
    - If `amount <= 0` → throw `InvalidAmountException`.
- [ ] Add method `Wallet.withdraw(double amount)`:
    - If `amount <= 0` → throw `InvalidAmountException`.
    - If `amount > balance` → throw `InsufficientBalanceException`.

**2) Transaction Modeling**

- [ ] Create `enum TransactionType { DEPOSIT, TRANSFER_IN, TRANSFER_OUT }`
- [ ] Create `Transaction` class:
    - Fields:
        - `transactionId` (String/UUID)
        - `timestamp` (e.g., `LocalDateTime`)
        - `type` (TransactionType)
        - `amount`
        - `fromWalletId`
        - `toWalletId`
- [ ] Each `Wallet` should keep its own transaction list:
    - `private List<Transaction> transactions = new ArrayList<>();`
    - Add a method `addTransaction(Transaction txn)` used internally from deposit/withdraw/transfer methods.

**3) Transfer Money Logic**

- [ ] In a service class `WalletService` (new), implement:
  ```java
  public void transfer(Wallet from, Wallet to, double amount) {
      // validate & update both balances
      // add appropriate Transaction entries to both wallets
  }
  ```
- [ ] Transaction creation rules:
    - For sender:
        - Type: `TRANSFER_OUT`
        - `fromWalletId` = sender, `toWalletId` = receiver
    - For receiver:
        - Type: `TRANSFER_IN`
        - `fromWalletId` = sender, `toWalletId` = receiver

**4) Collections & Queries (Prep for Streams)**

- [ ] In `Wallet`, add method:
  ```java
  public List<Transaction> getTransactions() {
      return Collections.unmodifiableList(transactions);
  }
  ```
- [ ] Create a `TransactionReportService` class with methods:
    - `List<Transaction> getAllDeposits(Wallet wallet)`
    - `List<Transaction> getTransactionsAboveAmount(Wallet wallet, double threshold)`
    - For now, implement with basic `for` loops. You’ll refactor to Streams on Day 3.

**Hints (Day 2):**

- Use **custom exceptions** instead of generic `RuntimeException` to make error handling clearer.
- Use `List<Transaction>` to store history; this will become a **Stream source** on Day 3.
- Keep **business rules in service classes**, not in `main()`.

***

## Day 3 – Modern Java (17 & 21) Focus

Now enhance model and logic using modern Java features (records, pattern matching, streams).

### New Requirements for Day 3

7. **US-7: Generate Transaction Summary for User**
    - As a user, I want to see:
        - Total number of transactions
        - Total amount sent
        - Total amount received
        - Largest transaction
        - List of last 5 transactions

8. **US-8: Support “Lightweight DTO View”**
    - As client (mobile/web), I only need read-only projections of `Transaction` with limited fields.

9. **US-9: Use Modern Java for Maintainable Code**
    - Use records for simple data carriers where appropriate.
    - Use Stream API for data processing and summaries.

***

### Day 3 – Todos

**1) Introduce Records (Java 17)**

- [ ] Convert simple DTO-style classes into records where appropriate:
    - If your `Transaction` is **immutable** and has only data + trivial logic, consider:
      ```java
      public record Transaction(
          String transactionId,
          LocalDateTime timestamp,
          TransactionType type,
          double amount,
          String fromWalletId,
          String toWalletId
      ) {}
      ```
    - Or use a separate **view**:
      ```java
      public record TransactionView(
          LocalDateTime timestamp,
          TransactionType type,
          double amount
      ) {}
      ```
- [ ] Create a record `UserSummary`:
  ```java
  public record UserSummary(
      String userId,
      String fullName,
      int totalTransactions,
      double totalSent,
      double totalReceived,
      double maxTransactionAmount
  ) {}
  ```

**Hints (Records):**
- Good for **read-only** models / response DTOs.
- Keep entities (if mutable) as normal classes for now.

***

**2) Refactor Collections Logic Using Streams**

In `TransactionReportService`:

- [ ] Implement using Streams:

  ```java
  public List<Transaction> getAllDeposits(Wallet wallet) {
      return wallet.getTransactions().stream()
          .filter(tx -> tx.type() == TransactionType.DEPOSIT)
          .toList();
  }

  public List<Transaction> getTransactionsAboveAmount(Wallet wallet, double threshold) {
      return wallet.getTransactions().stream()
          .filter(tx -> tx.amount() > threshold)
          .toList();
  }
  ```

- [ ] Add methods:

  ```java
  public double getTotalSent(Wallet wallet) {
      return wallet.getTransactions().stream()
          .filter(tx -> tx.type() == TransactionType.TRANSFER_OUT)
          .mapToDouble(Transaction::amount)
          .sum();
  }

  public double getTotalReceived(Wallet wallet) {
      return wallet.getTransactions().stream()
          .filter(tx -> tx.type() == TransactionType.TRANSFER_IN || tx.type() == TransactionType.DEPOSIT)
          .mapToDouble(Transaction::amount)
          .sum();
  }

  public Optional<Transaction> getMaxTransaction(Wallet wallet) {
      return wallet.getTransactions().stream()
          .max(Comparator.comparingDouble(Transaction::amount));
  }

  public List<Transaction> getLastNTransactions(Wallet wallet, int n) {
      List<Transaction> txns = wallet.getTransactions();
      return txns.stream()
          .sorted(Comparator.comparing(Transaction::timestamp).reversed())
          .limit(n)
          .toList();
  }
  ```

**Hints (Streams):**
- Use `filter` for conditions.
- Use `map`, `mapToDouble` for transformation.
- Use `sum`, `max`, `limit`, `sorted` for aggregation & ordering.
- Keep logic **declarative**, not manual loops.

***

**3) Pattern Matching / Switch Expressions (Java 17)**

- [ ] Implement a utility method to format transaction messages:

  ```java
  public String formatTransactionMessage(Transaction tx) {
      return switch (tx.type()) {
          case DEPOSIT      -> "Deposit of " + tx.amount() + " into wallet " + tx.toWalletId();
          case TRANSFER_IN  -> "Received " + tx.amount() + " from wallet " + tx.fromWalletId();
          case TRANSFER_OUT -> "Sent " + tx.amount() + " to wallet " + tx.toWalletId();
      };
  }
  ```

- [ ] Optionally, use pattern matching for `instanceof` if you have a base type and subtypes 

***

### 4) Optional: Virtual Threads Concept (Java 21) – Concept Only for Now


- Concept: Each transfer could run in its own virtual thread where operations like DB calls don’t block physical threads. Useful when building high-throughput payment systems.
- This links nicely to: “Why Java is still heavily used in fintech in 2026”.

***





