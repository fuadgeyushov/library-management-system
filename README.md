# File-Based Robust Library Management System

## 👤 Student Information
* **Name / Surname:** Fuad Geyushov
* **Institution:** Azerbaijan Technical University (AzTU)
* **Major:** Computer Engineering
* **Submitted To:** Hasan Naghiyev
* **Date:** May 2026

---

## 🚀 Project Overview
This production-grade Core Java application serves as a fully independent **File-Based Library Management System**. Built strictly from scratch, the system manages three dynamic subscription levels (`Basic`, `Silver`, `Gold`) and tracks continuous states across multiple library item classes (`Book`, `Magazine`, `Thesis`). Data persistence is handled via structural text-based streams instead of localized relational engine tools.

## 🛠️ Advanced Technical Architecture (Grading Matrix Adherence)

### 1. Object-Oriented Programming (OOP) Structure (25 Points)
* **Inheritance & Hierarchy:** Utilizes unified polymorphic patterns mapping distinct business layers.
* **Abstraction:** Implements abstract base structures (`LibraryItem` and `Member`) alongside decoupled contractual behavioral boundaries defined by the `Borrowable` interface.

### 2. Encapsulation & Robust Validations (10 Points)
* Strict access isolation is enforced by marking all state fields across models as `private`. 
* Setter chains run dynamic parameter evaluations (e.g., boundary array validations like `type < 1 || type > 3`) preventing logical errors before runtime objects save states.

### 3. Collections & Generics Framework (15 Points)
* Implements high-performance memory configurations (`HashMap<String, T>`) inside data engines to ensure immediate lookup speeds.
* Eliminates boilerplate duplication by establishing type-safe execution wrapper scopes through custom Java Generics (`SearchResult<T>`).

### 4. Custom Exception Handling Layer (10 Points)
* Business model constraints do not rely on raw print triggers. The core engine safely fires targeted runtime exceptions like `BorrowLimitExceededException` or `ItemNotAvailableException` which are caught upper-level in `Main.java` to secure crash-free operation.

---

## 📂 Project Directory Structure
```text
src/
├── main/
│   └── Main.java                 # Interactive Console Dashboard Loop
├── services/
│   └── Library.java              # Main Core Business Logic Engine
├── models/
│   ├── item/                     # Abstract LibraryItem, Book, Magazine, Thesis
│   └── member/                   # Abstract Member, Basic, Silver, Gold Tiers
├── interfaces/
│   └── Borrowable.java           # Interface for Issuing Items
├── exceptions/
│   ├── BorrowLimitExceededException.java
│   └── ItemNotAvailableException.java
└── utils/
    ├── FileHandler.java          # BufferedReader/PrintWriter Stream Pipeline
    └── SearchResult.java         # Type-safe Java Generic Wrapper
