import java.util.Scanner;

/**
 * InterfaceResizableDemo - Advanced Interface Implementation Example
 * 
 * This program demonstrates:
 * - Multiple interface implementation
 * - Interface inheritance (extends)
 * - Method overriding
 * - Interactive user input with validation
 * - Percentage-based transformations
 * 
 * Original: TG1010 Practical 07 (Resizable Circle)
 * 
 * @author BICT Intermediate Java Course
 * @version 1.0
 */
public class InterfaceResizableDemo {
    
    /**
     * Interface for geometric objects
     */
    interface GeometricObject {
        double getArea();
        double getPerimeter();
    }
    
    /**
     * Interface for resizable objects
     */
    interface Resizable {
        void resize(int percent);
    }
    
    /**
     * Circle class implementing GeometricObject
     */
    static class Circle implements GeometricObject {
        protected double radius;
        
        public Circle(double radius) {
            if (radius < 0) {
                throw new IllegalArgumentException("Radius cannot be negative");
            }
            this.radius = radius;
        }
        
        @Override
        public double getArea() {
            return Math.PI * radius * radius;
        }
        
        @Override
        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }
        
        public double getRadius() {
            return radius;
        }
        
        @Override
        public String toString() {
            return String.format("Circle [radius=%.2f, area=%.2f, perimeter=%.2f]",
                radius, getArea(), getPerimeter());
        }
    }
    
    /**
     * ResizableCircle - extends Circle and implements Resizable
     */
    static class ResizableCircle extends Circle implements Resizable {
        
        public ResizableCircle(double radius) {
            super(radius);
        }
        
        @Override
        public void resize(int percent) {
            // Validate percent range
            if (percent < -100 || percent > 100) {
                throw new IllegalArgumentException(
                    "Percent must be between -100 and +100");
            }
            
            // Calculate new radius
            radius = radius * (1 + percent / 100.0);
            
            // Ensure radius doesn't become negative
            if (radius < 0) {
                radius = 0;
            }
        }
        
        @Override
        public String toString() {
            return String.format("ResizableCircle [radius=%.2f, area=%.2f, perimeter=%.2f]",
                radius, getArea(), getPerimeter());
        }
    }
    
    /**
     * Main method - Interactive demonstration
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║     Interface & Inheritance Demo - BICT Course        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        // Get initial radius with validation
        double radius = getValidRadius(scanner);
        
        // Create circles
        Circle normalCircle = new Circle(radius);
        ResizableCircle resizableCircle = new ResizableCircle(radius);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Initial State:");
        System.out.println("=".repeat(60));
        System.out.println("Normal Circle:     " + normalCircle);
        System.out.println("Resizable Circle:  " + resizableCircle);
        
        // Demonstrate resizing with multiple operations
        demonstrateResizing(resizableCircle, scanner);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Final State:");
        System.out.println("=".repeat(60));
        System.out.println("Normal Circle:     " + normalCircle + " (unchanged)");
        System.out.println("Resizable Circle:  " + resizableCircle + " (modified)");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Key Concepts Demonstrated:");
        System.out.println("=".repeat(60));
        System.out.println("  ✓ Interface definition (GeometricObject, Resizable)");
        System.out.println("  ✓ Interface implementation");
        System.out.println("  ✓ Multiple interface implementation");
        System.out.println("  ✓ Inheritance with interfaces (extends + implements)");
        System.out.println("  ✓ Method overriding");
        System.out.println("  ✓ Input validation");
        System.out.println("  ✓ Polymorphic behavior");
        System.out.println("=".repeat(60));
        
        scanner.close();
    }
    
    /**
     * Get valid radius from user (must be >= 0)
     */
    private static double getValidRadius(Scanner scanner) {
        double radius;
        while (true) {
            try {
                System.out.print("Enter initial radius (>= 0): ");
                radius = scanner.nextDouble();
                
                if (radius < 0) {
                    System.out.println("❌ Error: Radius cannot be negative. Try again.");
                    continue;
                }
                
                break;
            } catch (Exception e) {
                System.out.println("❌ Error: Invalid input. Enter a valid number.");
                scanner.nextLine(); // Clear buffer
            }
        }
        return radius;
    }
    
    /**
     * Get valid percentage from user (-100 to +100)
     */
    private static int getValidPercent(Scanner scanner) {
        int percent;
        while (true) {
            try {
                System.out.print("Enter resize percentage (-100 to +100): ");
                percent = scanner.nextInt();
                
                if (percent < -100 || percent > 100) {
                    System.out.println("❌ Error: Percentage must be between -100 and +100. Try again.");
                    continue;
                }
                
                break;
            } catch (Exception e) {
                System.out.println("❌ Error: Invalid input. Enter a valid integer.");
                scanner.nextLine(); // Clear buffer
            }
        }
        return percent;
    }
    
    /**
     * Demonstrate multiple resize operations
     */
    private static void demonstrateResizing(ResizableCircle circle, Scanner scanner) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Resize Operations:");
        System.out.println("=".repeat(60));
        System.out.println("  +100% = Double the size");
        System.out.println("  -50%  = Reduce to half size");
        System.out.println("  -100% = Reduce to zero (minimum)");
        System.out.println("=".repeat(60));
        
        // First resize
        int percent1 = getValidPercent(scanner);
        circle.resize(percent1);
        System.out.println("After " + percent1 + "% resize: " + circle);
        
        // Ask for another resize
        System.out.print("\nPerform another resize? (y/n): ");
        String response = scanner.next();
        
        if (response.equalsIgnoreCase("y")) {
            int percent2 = getValidPercent(scanner);
            circle.resize(percent2);
            System.out.println("After " + percent2 + "% resize: " + circle);
        }
    }
}
