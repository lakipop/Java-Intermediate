import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * BasicGUIDemo - Introduction to Java Swing
 * 
 * This program demonstrates:
 * - JFrame creation and configuration
 * - Basic components (JLabel, JButton, JTextField)
 * - Event handling
 * - Layout managers
 * - Simple user interaction
 * 
 * @author BICT Intermediate Java Course
 * @version 1.0
 */
public class BasicGUIDemo extends JFrame implements ActionListener {
    
    // GUI Components
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton greetButton;
    private JButton clearButton;
    private JTextArea outputArea;
    
    /**
     * Constructor - Initialize GUI
     */
    public BasicGUIDemo() {
        // Frame settings
        setTitle("Basic GUI Demo - BICT Java Course");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen
        
        // Create components
        initializeComponents();
        
        // Set layout and add components
        setupLayout();
        
        // Make visible
        setVisible(true);
    }
    
    /**
     * Initialize all GUI components
     */
    private void initializeComponents() {
        // Title label
        titleLabel = new JLabel("Welcome to Java GUI Programming");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(0, 102, 204));
        
        // Name label and text field
        nameLabel = new JLabel("Enter your name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Buttons
        greetButton = new JButton("Greet Me");
        greetButton.setFont(new Font("Arial", Font.BOLD, 14));
        greetButton.setBackground(new Color(76, 175, 80));
        greetButton.setForeground(Color.WHITE);
        greetButton.setFocusPainted(false);
        greetButton.addActionListener(this);
        
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(244, 67, 54));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(this);
        
        // Output area
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputArea.setBackground(new Color(245, 245, 245));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
    
    /**
     * Setup layout and add components
     */
    private void setupLayout() {
        // Main panel with BorderLayout
        setLayout(new BorderLayout(10, 10));
        
        // Top panel for title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);
        
        // Center panel for input
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        centerPanel.add(greetButton);
        centerPanel.add(clearButton);
        add(centerPanel, BorderLayout.CENTER);
        
        // Bottom panel for output
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(450, 150));
        bottomPanel.add(scrollPane);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Handle button clicks
     * 
     * @param e ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == greetButton) {
            handleGreetButton();
        } else if (e.getSource() == clearButton) {
            handleClearButton();
        }
    }
    
    /**
     * Handle Greet button click
     */
    private void handleGreetButton() {
        String name = nameField.getText().trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter your name!",
                "Input Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Add greeting to output area
        String greeting = "Hello, " + name + "! Welcome to Java GUI Programming.\n";
        outputArea.append(greeting);
        outputArea.append("Current Time: " + java.time.LocalTime.now() + "\n");
        outputArea.append("-".repeat(50) + "\n");
        
        // Show dialog
        JOptionPane.showMessageDialog(this,
            "Hello, " + name + "!\nWelcome to Java GUI Programming!",
            "Greeting",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Handle Clear button click
     */
    private void handleClearButton() {
        nameField.setText("");
        outputArea.setText("");
        nameField.requestFocus();
    }
    
    /**
     * Main method to run the application
     */
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BasicGUIDemo();
            }
        });
    }
}
