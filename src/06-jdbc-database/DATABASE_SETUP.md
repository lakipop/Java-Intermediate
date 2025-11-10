# Database Setup for JDBC Student Demo

## Prerequisites
- MySQL Server installed and running
- MySQL JDBC Driver (mysql-connector-java)

## Step 1: Create Database

```sql
CREATE DATABASE student;
USE student;
```

## Step 2: Create Tables

### Basic Data Table
```sql
CREATE TABLE basicdata (
    stu_id INT PRIMARY KEY,
    stu_name VARCHAR(100) NOT NULL,
    stu_address VARCHAR(200)
);
```

### Marks Table
```sql
CREATE TABLE marks (
    stu_id INT PRIMARY KEY,
    subject1 INT NOT NULL,
    subject2 INT NOT NULL,
    subject3 INT NOT NULL,
    FOREIGN KEY (stu_id) REFERENCES basicdata(stu_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
```

## Step 3: Insert Sample Data

```sql
-- Insert students
INSERT INTO basicdata VALUES (1, 'Alice Johnson', 'Colombo');
INSERT INTO basicdata VALUES (2, 'Bob Williams', 'Kandy');
INSERT INTO basicdata VALUES (3, 'Carol Davis', 'Galle');

-- Insert marks
INSERT INTO marks VALUES (1, 85, 90, 88);
INSERT INTO marks VALUES (2, 72, 68, 75);
INSERT INTO marks VALUES (3, 95, 92, 97);
```

## Step 4: Add MySQL JDBC Driver

### Download
- Download from: https://dev.mysql.com/downloads/connector/j/
- Or use Maven:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Add to Classpath
- Place `mysql-connector-java-x.x.xx.jar` in your project's lib folder
- Add to classpath when compiling/running

## Step 5: Update Connection Details (if needed)

In `JDBCStudentDemo.java`, modify the DbConnector class:

```java
private static final String URL = "jdbc:mysql://localhost:3306/student";
private static final String USERNAME = "root";
private static final String PASSWORD = "1234";
```

## Grading System
- **A**: Average >= 85
- **B**: Average >= 65
- **C**: Average >= 35
- **F**: Average < 35

## Original Source
**Java Practical 09** - Student Database Management System
- Demonstrates complete JDBC CRUD operations
- Implements PreparedStatement for security
- Uses database joins for complex queries
- Includes grade calculation logic
