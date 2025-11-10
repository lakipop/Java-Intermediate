import java.io.*;

/**
 * SerializationDemo - Complete Serialization Example
 * 
 * This program demonstrates:
 * - Object serialization (writing objects to files)
 * - Object deserialization (reading objects from files)
 * - Serializable interface
 * - Proper file handling with user home directory
 * - Error handling for I/O operations
 * 
 * Original: MySerializationDemo + MyDeSerializationDemo practicals
 * 
 * @author BICT Intermediate Java Course
 * @version 1.0
 */

/**
 * Student class that can be serialized
 */
class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Version control for serialization
    
    private int id;
    private String name;
    private int age;
    private double gpa;
    
    public Student(int id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    
    @Override
    public String toString() {
        return String.format("Student[ID=%d, Name=%s, Age=%d, GPA=%.2f]", 
                           id, name, age, gpa);
    }
}

public class SerializationDemo {
    
    /**
     * Serialize (save) a student object to file
     */
    public static void serializeStudent(Student student, String filename) {
        System.out.println("=== Serializing Student Object ===");
        System.out.println("Object to serialize: " + student);
        
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(student);
            System.out.println("✓ Student object serialized successfully!");
            System.out.println("  Saved to: " + filename);
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: File path not accessible - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }
    
    /**
     * Deserialize (load) a student object from file
     */
    public static Student deserializeStudent(String filename) {
        System.out.println("\n=== Deserializing Student Object ===");
        System.out.println("Reading from: " + filename);
        
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Student student = (Student) ois.readObject();
            System.out.println("✓ Student object deserialized successfully!");
            return student;
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
            System.err.println("Please run serialization first!");
        } catch (IOException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Student class not found - " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Serialize multiple students to file
     */
    public static void serializeMultipleStudents(Student[] students, String filename) {
        System.out.println("\n=== Serializing Multiple Students ===");
        System.out.println("Number of students: " + students.length);
        
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(students);
            System.out.println("✓ All students serialized successfully!");
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Deserialize multiple students from file
     */
    public static Student[] deserializeMultipleStudents(String filename) {
        System.out.println("\n=== Deserializing Multiple Students ===");
        
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Student[] students = (Student[]) ois.readObject();
            System.out.println("✓ Deserialized " + students.length + " students");
            return students;
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Main method demonstrating serialization and deserialization
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║  Serialization & Deserialization Demo ║");
        System.out.println("╚═══════════════════════════════════════╝\n");
        
        // Use user's home directory for file storage
        String userHome = System.getProperty("user.home");
        String singleFile = userHome + File.separator + "student.ser";
        String multipleFile = userHome + File.separator + "students.ser";
        
        // Example 1: Serialize single student
        Student student1 = new Student(1, "Anura Perera", 20, 3.75);
        serializeStudent(student1, singleFile);
        
        // Example 2: Deserialize single student
        Student loadedStudent = deserializeStudent(singleFile);
        if (loadedStudent != null) {
            System.out.println("Loaded student: " + loadedStudent);
        }
        
        System.out.println("\n" + "=".repeat(60));
        
        // Example 3: Serialize multiple students
        Student[] students = {
            new Student(1, "Anura Perera", 20, 3.75),
            new Student(2, "Kasun Silva", 21, 3.60),
            new Student(3, "Nimal Fernando", 19, 3.90),
            new Student(4, "Saman Kumara", 22, 3.45)
        };
        
        serializeMultipleStudents(students, multipleFile);
        
        // Example 4: Deserialize multiple students
        Student[] loadedStudents = deserializeMultipleStudents(multipleFile);
        if (loadedStudents != null) {
            System.out.println("\nLoaded Students:");
            for (Student s : loadedStudents) {
                System.out.println("  " + s);
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Serialization Demo Completed!");
        System.out.println("Files created in: " + userHome);
        System.out.println("  - student.ser (single object)");
        System.out.println("  - students.ser (array of objects)");
    }
}
