import java.sql.*;

/**
 * JDBCStudentDemo - Complete JDBC Database Operations Example
 * 
 * This program demonstrates:
 * - Database connection management
 * - CRUD operations (Create, Read, Update, Delete)
 * - PreparedStatement usage
 * - ResultSet handling
 * - Database joins
 * - Transaction management
 * 
 * Original: Java Practical 09 (Student Database Management)
 * 
 * Database Schema:
 * - basicdata table: stu_id, stu_name, stu_address
 * - marks table: stu_id, subject1, subject2, subject3
 * 
 * @author BICT Intermediate Java Course
 * @version 1.0
 */
public class JDBCStudentDemo {
    
    /**
     * Database Connection Manager
     */
    static class DbConnector {
        private static final String URL = "jdbc:mysql://localhost:3306/student";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "1234";
        
        /**
         * Get database connection
         * @return Connection object
         */
        public static Connection getConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.err.println("❌ MySQL JDBC Driver not found!");
                System.err.println("   Add mysql-connector-java to your classpath");
                throw new RuntimeException(e);
            } catch (SQLException e) {
                System.err.println("❌ Connection failed: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
    
    /**
     * Insert a new student record
     */
    public static void insertStudent(int id, String name, String address) {
        String sql = "INSERT INTO basicdata (stu_id, stu_name, stu_address) VALUES (?, ?, ?)";
        
        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Student inserted successfully!");
                System.out.println("   ID: " + id + ", Name: " + name);
            } else {
                System.out.println("❌ Failed to insert student");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Update student name by ID
     */
    public static void updateStudentName(int id, String newName) {
        String sql = "UPDATE basicdata SET stu_name = ? WHERE stu_id = ?";
        
        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Student name updated successfully!");
                System.out.println("   ID: " + id + " → New Name: " + newName);
            } else {
                System.out.println("⚠️  No student found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Update student address by ID
     */
    public static void updateStudentAddress(int id, String newAddress) {
        String sql = "UPDATE basicdata SET stu_address = ? WHERE stu_id = ?";
        
        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newAddress);
            pstmt.setInt(2, id);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Student address updated successfully!");
                System.out.println("   ID: " + id + " → New Address: " + newAddress);
            } else {
                System.out.println("⚠️  No student found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Delete student by ID
     */
    public static void deleteStudent(int id) {
        String sql = "DELETE FROM basicdata WHERE stu_id = ?";
        
        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Student deleted successfully!");
                System.out.println("   ID: " + id);
            } else {
                System.out.println("⚠️  No student found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Display student by ID
     */
    public static void displayStudentById(int id) {
        String sql = "SELECT * FROM basicdata WHERE stu_id = ?";
        
        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("Student Details:");
                System.out.println("=".repeat(50));
                System.out.println("ID:      " + rs.getInt("stu_id"));
                System.out.println("Name:    " + rs.getString("stu_name"));
                System.out.println("Address: " + rs.getString("stu_address"));
                System.out.println("=".repeat(50));
            } else {
                System.out.println("⚠️  No student found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Display all students
     */
    public static void displayAllStudents() {
        String sql = "SELECT * FROM basicdata ORDER BY stu_id";
        
        try (Connection conn = DbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("All Students:");
            System.out.println("=".repeat(70));
            System.out.printf("%-10s %-25s %-30s%n", "ID", "Name", "Address");
            System.out.println("-".repeat(70));
            
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("%-10d %-25s %-30s%n",
                    rs.getInt("stu_id"),
                    rs.getString("stu_name"),
                    rs.getString("stu_address"));
            }
            
            if (!hasData) {
                System.out.println("No students in database");
            }
            System.out.println("=".repeat(70));
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Display students with marks (JOIN operation)
     */
    public static void displayStudentsWithMarks() {
        String sql = "SELECT b.stu_id, b.stu_name, b.stu_address, " +
                     "m.subject1, m.subject2, m.subject3 " +
                     "FROM basicdata b INNER JOIN marks m ON b.stu_id = m.stu_id";
        
        try (Connection conn = DbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n" + "=".repeat(90));
            System.out.println("Students with Marks:");
            System.out.println("=".repeat(90));
            System.out.printf("%-5s %-20s %-20s %-10s %-10s %-10s%n",
                "ID", "Name", "Address", "Subject1", "Subject2", "Subject3");
            System.out.println("-".repeat(90));
            
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-20s %-10d %-10d %-10d%n",
                    rs.getInt("stu_id"),
                    rs.getString("stu_name"),
                    rs.getString("stu_address"),
                    rs.getInt("subject1"),
                    rs.getInt("subject2"),
                    rs.getInt("subject3"));
            }
            System.out.println("=".repeat(90));
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Display students with grades (calculated)
     */
    public static void displayStudentsWithGrades() {
        String sql = "SELECT b.stu_name, m.stu_id, m.subject1, m.subject2, m.subject3 " +
                     "FROM basicdata b INNER JOIN marks m ON b.stu_id = m.stu_id";
        
        try (Connection conn = DbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n" + "=".repeat(90));
            System.out.println("Students with Grades:");
            System.out.println("=".repeat(90));
            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-10s%n",
                "ID", "Name", "Subject1", "Subject2", "Subject3", "Average", "Grade");
            System.out.println("-".repeat(90));
            
            while (rs.next()) {
                int id = rs.getInt("stu_id");
                String name = rs.getString("stu_name");
                int sub1 = rs.getInt("subject1");
                int sub2 = rs.getInt("subject2");
                int sub3 = rs.getInt("subject3");
                
                int sum = sub1 + sub2 + sub3;
                int avg = sum / 3;
                
                char grade;
                if (avg >= 85) grade = 'A';
                else if (avg >= 65) grade = 'B';
                else if (avg >= 35) grade = 'C';
                else grade = 'F';
                
                System.out.printf("%-5d %-20s %-10d %-10d %-10d %-10d %-10c%n",
                    id, name, sub1, sub2, sub3, avg, grade);
            }
            System.out.println("=".repeat(90));
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Main method - Demonstration
     */
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║     JDBC Student Database Demo - BICT Course          ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        // Note: Make sure MySQL is running and database 'student' exists
        System.out.println("Prerequisites:");
        System.out.println("  1. MySQL server running on localhost:3306");
        System.out.println("  2. Database 'student' created");
        System.out.println("  3. Tables 'basicdata' and 'marks' created");
        System.out.println("  4. MySQL JDBC driver in classpath\n");
        
        System.out.println("=".repeat(50));
        System.out.println("Demonstration of JDBC Operations:");
        System.out.println("=".repeat(50));
        
        // Uncomment to test operations:
        
        // 1. Insert student
        // insertStudent(101, "John Doe", "123 Main St");
        // insertStudent(102, "Jane Smith", "456 Oak Ave");
        
        // 2. Display all students
        // displayAllStudents();
        
        // 3. Update student
        // updateStudentName(101, "John Updated");
        // updateStudentAddress(102, "789 New Address");
        
        // 4. Display by ID
        // displayStudentById(101);
        
        // 5. Display with marks
        // displayStudentsWithMarks();
        
        // 6. Display with grades
        // displayStudentsWithGrades();
        
        // 7. Delete student
        // deleteStudent(101);
        
        System.out.println("\nKey Concepts Demonstrated:");
        System.out.println("  ✓ Database connection management");
        System.out.println("  ✓ PreparedStatement (SQL injection prevention)");
        System.out.println("  ✓ CRUD operations (Create, Read, Update, Delete)");
        System.out.println("  ✓ ResultSet handling");
        System.out.println("  ✓ Database joins (INNER JOIN)");
        System.out.println("  ✓ Try-with-resources (auto-closing connections)");
        System.out.println("  ✓ Calculated fields (grades from marks)");
    }
}
