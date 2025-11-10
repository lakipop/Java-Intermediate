import java.io.*;

/**
 * ExternalizableSerializationDemo - Externalizable Interface Implementation
 *
 * Demonstrates:
 * - Externalizable interface for custom serialization
 * - Complete control over serialization process
 * - writeExternal() and readExternal() methods
 * - Inheritance with Externalizable
 *
 * Original: Java-Design-Patterns/ExternalizableDemo/
 * 
 * @author BICT Practicals Collection
 * @version 1.0
 */

/**
 * User - Base Class with Externalizable Support
 * Parent class for custom serialization
 */
class User implements Externalizable {
    protected String username;
    protected String password;
    
    // Required no-arg constructor for Externalizable
    public User() {
        System.out.println("🔧 User no-arg constructor called");
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("✅ User created: " + username);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("📝 Writing User data...");
        out.writeUTF(username);
        out.writeUTF(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("📖 Reading User data...");
        username = in.readUTF();
        password = in.readUTF();
    }
    
    @Override
    public String toString() {
        return "User{username='" + username + "', password='***'}";
    }
}

/**
 * AdminUser - Extended Class with Externalizable
 * Demonstrates inheritance with custom serialization
 */
class AdminUser extends User {
    private String email;
    private String phone;

    // Required no-arg constructor
    public AdminUser() {
        super();
        System.out.println("🔧 AdminUser no-arg constructor called");
    }

    public AdminUser(String username, String password, String email, String phone) {
        super(username, password);
        this.email = email;
        this.phone = phone;
        System.out.println("✅ AdminUser created with email: " + email);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("📝 Writing AdminUser data...");
        super.writeExternal(out); // Call parent's writeExternal
        out.writeUTF(email);
        out.writeUTF(phone);
        System.out.println("✅ AdminUser data written successfully");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("📖 Reading AdminUser data...");
        super.readExternal(in); // Call parent's readExternal
        email = in.readUTF();
        phone = in.readUTF();
        System.out.println("✅ AdminUser data read successfully");
    }
    
    @Override
    public String toString() {
        return "AdminUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

/**
 * Main Demo Class
 */
public class ExternalizableSerializationDemo {
    
    private static final String FILE_NAME = "admin_user.ser";
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   EXTERNALIZABLE SERIALIZATION DEMO    ║");
        System.out.println("║   Custom Serialization Control         ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Create AdminUser object
        System.out.println("🎯 STEP 1: Creating AdminUser Object\n");
        AdminUser adminUser = new AdminUser("lakin_admin", "securePass123", "admin@example.com", "0771234567");
        System.out.println("\n📦 Original Object: " + adminUser);
        
        System.out.println("\n" + "─".repeat(50));
        
        // Serialize (Write to file)
        System.out.println("\n🎯 STEP 2: Serializing AdminUser to File\n");
        serializeAdminUser(adminUser);
        
        System.out.println("\n" + "─".repeat(50));
        
        // Deserialize (Read from file)
        System.out.println("\n🎯 STEP 3: Deserializing AdminUser from File\n");
        AdminUser deserializedUser = deserializeAdminUser();
        
        if (deserializedUser != null) {
            System.out.println("\n📦 Deserialized Object: " + deserializedUser);
            
            System.out.println("\n" + "─".repeat(50));
            System.out.println("\n✅ Verification:");
            System.out.println("   Original Username: " + adminUser.username);
            System.out.println("   Deserialized Username: " + deserializedUser.username);
            System.out.println("   Match: " + adminUser.username.equals(deserializedUser.username));
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("✅ Externalizable Demo Completed!");
        System.out.println("=".repeat(50));
        
        // Key Concepts
        System.out.println("\n📚 KEY CONCEPTS:");
        System.out.println("• Externalizable: Full control over serialization");
        System.out.println("• writeExternal(): Custom write logic");
        System.out.println("• readExternal(): Custom read logic");
        System.out.println("• No-arg constructor: REQUIRED for Externalizable");
        
        System.out.println("\n💡 EXTERNALIZABLE vs SERIALIZABLE:");
        System.out.println("✓ Externalizable: Manual control, better performance");
        System.out.println("✓ Serializable: Automatic, easier but less control");
        System.out.println("✓ Externalizable: Must implement both methods");
        System.out.println("✓ Externalizable: Requires no-arg constructor");
    }
    
    /**
     * Serialize AdminUser object to file
     */
    private static void serializeAdminUser(AdminUser user) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(user);
            System.out.println("\n💾 Object saved to: " + FILE_NAME);
            
        } catch (IOException e) {
            System.err.println("❌ Serialization failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Deserialize AdminUser object from file
     */
    private static AdminUser deserializeAdminUser() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            AdminUser user = (AdminUser) ois.readObject();
            System.out.println("\n📂 Object loaded from: " + FILE_NAME);
            return user;
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Deserialization failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
