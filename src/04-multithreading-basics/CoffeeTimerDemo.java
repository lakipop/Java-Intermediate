/**
 * CoffeeTimerDemo - Basic Multithreading Example
 * 
 * This program demonstrates:
 * - Implementing Runnable interface
 * - Creating and starting threads
 * - Thread.sleep() for time delays
 * - Sequential processing in a thread
 * - Real-world simulation (making coffee)
 * 
 * Original: MyBasicCoffeeDemo practical
 * 
 * @author BICT Intermediate Java Course
 * @version 1.0
 */
public class CoffeeTimerDemo {
    
    /**
     * Timer class that implements Runnable for threading
     */
    static class CoffeeTimer implements Runnable {
        
        @Override
        public void run() {
            System.out.println("=== Coffee Making Process Started ===\n");
            
            for (int i = 20; i >= 0; i--) {
                System.out.print("T minus " + i + " seconds");
                
                // Add ingredients at specific times
                if (i == 16) {
                    System.out.print(" → Adding Coffee...!!!");
                }
                
                if (i == 13) {
                    System.out.print(" → Adding Sugar...!!!");
                }
                
                if (i == 11) {
                    System.out.print(" → Adding Warm Water...!!!");
                }
                
                if (i == 5) {
                    System.out.print(" → Stirring...!!!");
                }
                
                if (i == 0) {
                    System.out.print(" → ☕ Serving Coffee...!!!");
                }
                
                try {
                    Thread.sleep(1000); // Wait 1 second
                } catch (InterruptedException e) {
                    System.err.println("Timer interrupted: " + e.getMessage());
                    return;
                }
                
                System.out.println();
            }
            
            System.out.println("\n=== Coffee is Ready! Enjoy! ===");
        }
    }
    
    /**
     * Main method to start the coffee making process
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║  Coffee Timer - Threading Demo    ║");
        System.out.println("╚════════════════════════════════════╝\n");
        
        // Create timer instance
        CoffeeTimer timer = new CoffeeTimer();
        
        // Create and start thread
        Thread coffeeThread = new Thread(timer);
        coffeeThread.setName("CoffeeMaker");
        
        System.out.println("Starting coffee making thread...");
        System.out.println("Thread name: " + coffeeThread.getName());
        System.out.println();
        
        coffeeThread.start();
        
        // Main thread continues
        System.out.println("[Main thread]: Coffee thread is running in background\n");
        
        // Wait for coffee thread to complete
        try {
            coffeeThread.join();
            System.out.println("\n[Main thread]: Coffee making complete!");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
        }
    }
}
