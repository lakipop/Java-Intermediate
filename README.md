# 🟡 Java Intermediate Complete

> **Intermediate-Level Java Programming** - GUI, Collections, JDBC & Advanced OOP

---

## 📋 Overview

This repository contains **Intermediate-level Java practicals** covering GUI development, database operations, collections, and advanced OOP concepts.

**Prerequisites:** Java-Foundation-Complete (or equivalent knowledge)  
**Duration:** 3-4 weeks  
**Skill Level:** 🟡 Intermediate

---

## 📚 Content Structure

```
src/
├── 01-advanced-oop/          # Advanced OOP patterns
├── 02-interfaces/            # Interface basics
├── 03-serialization/         # Object serialization
├── 04-multithreading-basics/ # Basic threading concepts
├── 05-file-handling/         # Advanced file operations
├── 05-interfaces/            # More interface patterns
├── 06-collections/           # Collections Framework
├── 06-jdbc-database/         # ⭐ NEW! Database operations
├── 07-interfaces-advanced/   # ⭐ NEW! Advanced interface patterns
└── GUI folders/              # Swing GUI development
```

---

## 🎯 Topics Covered

### 01 - Advanced OOP
- ✅ Abstract classes and methods
- ✅ Interface implementation
- ✅ Multiple inheritance via interfaces
- ✅ Polymorphism advanced patterns
- ✅ Design principles (SOLID basics)

**Key Practicals:** Shape Hierarchy, Payment Systems, Vehicle Abstraction

---

### 02 & 05 - Interfaces
- ✅ Interface definition and implementation
- ✅ Multiple interface implementation
- ✅ Default and static methods (Java 8+)
- ✅ Functional interfaces
- ✅ Interface inheritance

**Key Practicals:** Drawable Shapes, Comparable Objects, Custom Interfaces

---

### 03 - Serialization
- ✅ Object serialization
- ✅ Deserialization
- ✅ Serializable interface
- ✅ transient keyword
- ✅ ObjectInputStream/ObjectOutputStream

**Key Practicals:** Object Persistence, Student Serialization

---

### 04 - Multithreading Basics
- ✅ Thread creation (extends Thread, implements Runnable)
- ✅ Thread lifecycle
- ✅ Basic synchronization
- ✅ Thread priorities

**Key Practicals:** Simple Thread Demo, Concurrent Counting

---

### 05 - File Handling (Advanced)
- ✅ File class operations
- ✅ Path and Paths
- ✅ Files utility class
- ✅ Directory traversal
- ✅ File filtering

**Key Practicals:** Directory Scanner, File Searcher

---

### 06 - Collections Framework
- ✅ List (ArrayList, LinkedList)
- ✅ Set (HashSet, TreeSet)
- ✅ Map (HashMap, TreeMap)
- ✅ Queue and Deque
- ✅ Iterators
- ✅ Comparators and Comparable
- ✅ Collections utility methods

**Key Practicals:** Student List Management, Map Operations, Set Operations

---

### ⭐ 06 - JDBC Database (NEW!)
- ✅ **MySQL connection management**
- ✅ **CRUD operations (Create, Read, Update, Delete)**
- ✅ **PreparedStatement (SQL injection prevention)**
- ✅ **ResultSet handling**
- ✅ **Database joins (INNER JOIN)**
- ✅ **Transaction management**
- ✅ **Calculated fields (grade calculations)**

**Files:**
- `JDBCStudentDemo.java` - Complete CRUD operations
- `DATABASE_SETUP.md` - MySQL setup instructions

**Features:**
- Insert student records
- Update student information (name, address)
- Delete students by ID
- Display all students
- Display students with marks (JOIN operation)
- Display students with calculated grades (A/B/C/F)

---

### ⭐ 07 - Interfaces Advanced (NEW!)
- ✅ **Multiple interface implementation**
- ✅ **Interface inheritance (extends)**
- ✅ **Interactive input validation**
- ✅ **Percentage-based transformations**
- ✅ **Polymorphic behavior**

**Files:**
- `InterfaceResizableDemo.java` - Advanced interface patterns

**Features:**
- GeometricObject interface (getArea, getPerimeter)
- Resizable interface (resize method)
- Circle class with geometric calculations
- ResizableCircle with dynamic resizing (-100% to +100%)
- Interactive Scanner-based demo with validation

---

### GUI Development (Multiple Folders)
- ✅ Swing components (JFrame, JPanel, JButton, JTextField, etc.)
- ✅ Layout managers (FlowLayout, BorderLayout, GridLayout)
- ✅ Event handling (ActionListener, MouseListener)
- ✅ Custom components
- ✅ MVC pattern basics

**Key Practicals:** Calculator GUI, Event Demo, Form Builder

---

## 🚀 Quick Start

### Basic Usage

```bash
# Navigate to any topic
cd src/06-collections

# Compile
javac CollectionsDemo.java

# Run
java CollectionsDemo
```

### JDBC Setup (MySQL Required)

```bash
# 1. Install MySQL Server
# 2. Create database
mysql -u root -p
CREATE DATABASE student;
USE student;

# 3. Run setup script
# See: src/06-jdbc-database/DATABASE_SETUP.md

# 4. Add MySQL JDBC driver to classpath
# Download: mysql-connector-java-8.x.xx.jar

# 5. Compile with driver
javac -cp .:mysql-connector-java-8.x.xx.jar JDBCStudentDemo.java

# 6. Run
java -cp .:mysql-connector-java-8.x.xx.jar JDBCStudentDemo
```

**For detailed MySQL setup, see:** `src/06-jdbc-database/DATABASE_SETUP.md`

---

## 💡 Learning Tips

1. **Prerequisite Check:** Ensure you understand Foundation concepts first
2. **Hands-On Practice:** GUI and JDBC are best learned by doing
3. **Database First:** Set up MySQL before trying JDBC examples
4. **Experiment with Collections:** Try different data structures for same problem
5. **Build Mini-Projects:** Combine GUI + Collections + JDBC for complete apps

---

## 🎓 What You'll Learn

By completing this repository, you will:
- ✅ Build graphical user interfaces with Swing
- ✅ Connect Java applications to MySQL databases
- ✅ Perform database CRUD operations securely
- ✅ Use Collections Framework effectively
- ✅ Implement advanced OOP patterns
- ✅ Handle events in GUI applications
- ✅ Serialize and persist objects
- ✅ Work with threads (basics)

---

## 🔗 Related Repositories

**⬅️ Previous:** [Java-Foundation-Complete](../Java-Foundation-Complete/)  
**➡️ Next:** [Java-Advanced-Complete](../Java-Advanced-Complete/)

---

## 🛠️ Requirements

### Software
- **JDK 8+** (JDK 11+ recommended)
- **MySQL Server** (for JDBC practicals)
- **IDE:** IntelliJ IDEA, Eclipse, VS Code, or NetBeans
- **MySQL JDBC Driver** (mysql-connector-java-8.x.xx.jar)

### MySQL Installation

**Windows:**
```
1. Download MySQL Installer
2. Install MySQL Server + Workbench
3. Set root password
4. Start MySQL service
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

**Mac:**
```bash
brew install mysql
brew services start mysql
```

### Verify Installation
```bash
mysql --version
# Output: mysql  Ver 8.x.xx for ...
```

---

## 📊 Progress Tracking

- [ ] 01-advanced-oop (5+ practicals)
- [ ] 02-interfaces (4+ practicals)
- [ ] 03-serialization (2+ practicals)
- [ ] 04-multithreading-basics (3+ practicals)
- [ ] 05-file-handling (3+ practicals)
- [ ] 06-collections (5+ practicals)
- [ ] ⭐ 06-jdbc-database (1 comprehensive demo)
- [ ] ⭐ 07-interfaces-advanced (1 interactive demo)
- [ ] GUI folders (8+ practicals)

**Total:** 30+ practical exercises

---

## 🎯 Project Ideas

Build complete applications combining concepts:

1. **Student Management System**
   - GUI: Swing forms for input
   - Database: MySQL with JDBC
   - Collections: Store/manage student lists
   - File: Export to CSV

2. **Library Management**
   - GUI: Book search, member management
   - Database: Books, members, transactions
   - Collections: Sorting and filtering
   - Serialization: Backup data

3. **Simple Banking App**
   - GUI: Account operations
   - Database: Store accounts, transactions
   - Threads: Handle multiple operations
   - Collections: Transaction history

4. **Task Manager**
   - GUI: Task list, priorities
   - Database: Task persistence
   - Collections: ArrayList for tasks
   - File: Export tasks

---

## 💻 JDBC Quick Reference

### Connection
```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/student", 
    "root", 
    "password"
);
```

### Insert
```java
String sql = "INSERT INTO students (id, name) VALUES (?, ?)";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, 101);
pstmt.setString(2, "John Doe");
pstmt.executeUpdate();
```

### Query
```java
String sql = "SELECT * FROM students";
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
    System.out.println(rs.getString("name"));
}
```

**Full examples in:** `src/06-jdbc-database/JDBCStudentDemo.java`

---

## 📜 License

Educational use only. BICT Java Intermediate Course.

---

## 🌟 Highlights

### ⭐ New in November 2025

**JDBC Module (06-jdbc-database/):**
- Complete student database management
- 5 CRUD operations in one demo
- PreparedStatement best practices
- Database joins and grade calculations
- Professional error handling
- Complete MySQL setup guide

**Advanced Interfaces (07-interfaces-advanced/):**
- Interactive interface demonstration
- Input validation patterns
- Multiple interface implementation
- Resizable geometric shapes
- Scanner-based user interaction

---

**Happy Coding! 🚀**

*Part of the BICT Complete Java Course Collection*
