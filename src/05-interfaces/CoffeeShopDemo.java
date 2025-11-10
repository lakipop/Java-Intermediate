/**
 * CoffeeShopDemo - Interface and Multi-Threading Demonstration
 * 
 * This program demonstrates:
 * - Implementing Runnable interface
 * - Multiple threads running concurrently
 * - Thread coordination
 * - Real-world simulation (coffee shop events)
 * - Observer-like pattern
 * 
 * Original: MyInterCoffeeDemo practical
 * 
 * @author BICT Intermediate Java Course
 * @version 1.0
 */

/**
 * CoffeeEvent - Represents a timed event in coffee making process
 */
class CoffeeEvent implements Runnable {
    private int delaySeconds;
    private String message;
    
    public CoffeeEvent(int delaySeconds, String message) {
        this.delaySeconds = delaySeconds;
        this.message = message;
    }
    
    @Override
    public void run() {
        try {
            // Wait for specified delay
            Thread.sleep(delaySeconds * 1000);
            
            // Display the event message
            System.out.println("⏰ [T+" + delaySeconds + "s] " + message);
            
        } catch (InterruptedException e) {
            System.err.println("Event interrupted: " + e.getMessage());
        }
    }
}

public class CoffeeShopDemo {
    
    /**
     * Simulate coffee making process with multiple concurrent events
     */
    public static void simulateCoffeeMaking() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║  Coffee Shop - Concurrent Events Demo     ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        
        System.out.println("☕ Starting coffee making process...\n");
        
        // Create coffee making events
        CoffeeEvent[] events = {
            new CoffeeEvent(2, "✓ Heating water..."),
            new CoffeeEvent(4, "✓ Grinding coffee beans..."),
            new CoffeeEvent(6, "✓ Preparing cup..."),
            new CoffeeEvent(8, "✓ Adding coffee powder..."),
            new CoffeeEvent(10, "✓ Pouring hot water..."),
            new CoffeeEvent(12, "✓ Adding sugar..."),
            new CoffeeEvent(14, "✓ Adding milk..."),
            new CoffeeEvent(16, "✓ Stirring..."),
            new CoffeeEvent(18, "✓ Final touches..."),
            new CoffeeEvent(20, "🎉 Coffee is ready! Enjoy!")
        };
        
        // Start all events as separate threads
        Thread[] threads = new Thread[events.length];
        
        for (int i = 0; i < events.length; i++) {
            threads[i] = new Thread(events[i]);
            threads[i].setName("CoffeeEvent-" + (i + 1));
            threads[i].start();
        }
        
        // Main thread continues
        System.out.println("📋 All events scheduled.");
        System.out.println("⏳ Processing coffee order...\n");
        System.out.println("=".repeat(50));
        
        // Wait for all events to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Process interrupted: " + e.getMessage());
        }
        
        System.out.println("=".repeat(50));
        System.out.println("\n✅ Coffee making process completed!");
    }
    
    /**
     * Demonstrate customer orders with multiple threads
     */
    public static void simulateMultipleOrders() {
        System.out.println("\n\n╔════════════════════════════════════════════╗");
        System.out.println("║     Multiple Customer Orders Demo         ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        
        // Customer order threads
        Runnable customer1 = () -> {
            System.out.println("👤 Customer 1: Ordering Espresso...");
            try {
                Thread.sleep(3000);
                System.out.println("☕ Customer 1: Espresso ready!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        Runnable customer2 = () -> {
            System.out.println("👤 Customer 2: Ordering Cappuccino...");
            try {
                Thread.sleep(4000);
                System.out.println("☕ Customer 2: Cappuccino ready!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        Runnable customer3 = () -> {
            System.out.println("👤 Customer 3: Ordering Latte...");
            try {
                Thread.sleep(5000);
                System.out.println("☕ Customer 3: Latte ready!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        // Start all customer threads
        Thread t1 = new Thread(customer1, "Customer-1");
        Thread t2 = new Thread(customer2, "Customer-2");
        Thread t3 = new Thread(customer3, "Customer-3");
        
        t1.start();
        t2.start();
        t3.start();
        
        // Wait for all orders to complete
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n✅ All customer orders completed!");
    }
    
    /**
     * Main method demonstrating interface and threading
     */
    public static void main(String[] args) {
        System.out.println("\n🌟 Coffee Shop Simulation");
        System.out.println("Demonstrating: Runnable Interface & Multi-Threading\n");
        
        // Demo 1: Single coffee making process
        simulateCoffeeMaking();
        
        // Demo 2: Multiple customer orders
        simulateMultipleOrders();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Coffee Shop Demo Completed!");
        System.out.println("Key Concepts Demonstrated:");
        System.out.println("  ✓ Runnable interface implementation");
        System.out.println("  ✓ Multiple threads running concurrently");
        System.out.println("  ✓ Thread.sleep() for timing");
        System.out.println("  ✓ Thread.join() for synchronization");
        System.out.println("  ✓ Lambda expressions for Runnable");
        System.out.println("=".repeat(50));
    }
}
