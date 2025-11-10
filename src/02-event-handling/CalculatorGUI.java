import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CalculatorGUI - Simple Calculator with GUI
 * 
 * This program demonstrates:
 * - GridLayout for button arrangement
 * - Action listeners for multiple buttons
 * - TextField for display
 * - Basic calculator operations
 * - Event-driven programming
 * 
 * @author BICT Intermediate Java Course
 * @version 1.0
 */
public class CalculatorGUI extends JFrame implements ActionListener {
    
    // GUI Components
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton equalsButton, clearButton, deleteButton, dotButton;
    
    // Calculator state
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    
    /**
     * Constructor - Initialize calculator GUI
     */
    public CalculatorGUI() {
        // Frame settings
        setTitle("Calculator - BICT Java Course");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Initialize components
        initializeComponents();
        
        // Setup layout
        setupLayout();
        
        setVisible(true);
    }
    
    /**
     * Initialize all components
     */
    private void initializeComponents() {
        // Display field
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(Color.WHITE);
        displayField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        // Number buttons (0-9)
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].setFocusPainted(false);
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(new Color(240, 240, 240));
        }
        
        // Operator buttons
        addButton = createOperatorButton("+", new Color(255, 152, 0));
        subButton = createOperatorButton("-", new Color(255, 152, 0));
        mulButton = createOperatorButton("*", new Color(255, 152, 0));
        divButton = createOperatorButton("/", new Color(255, 152, 0));
        
        // Special buttons
        equalsButton = createOperatorButton("=", new Color(76, 175, 80));
        clearButton = createOperatorButton("C", new Color(244, 67, 54));
        deleteButton = createOperatorButton("Del", new Color(244, 67, 54));
        dotButton = createOperatorButton(".", new Color(33, 150, 243));
    }
    
    /**
     * Create an operator button with styling
     */
    private JButton createOperatorButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.addActionListener(this);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        return button;
    }
    
    /**
     * Setup layout and add components
     */
    private void setupLayout() {
        setLayout(new BorderLayout(5, 5));
        
        // Display panel (top)
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(displayField);
        add(displayPanel, BorderLayout.NORTH);
        
        // Button panel (center)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Row 1: Clear, Delete, /, *
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(divButton);
        buttonPanel.add(mulButton);
        
        // Row 2: 7, 8, 9, -
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(subButton);
        
        // Row 3: 4, 5, 6, +
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(addButton);
        
        // Row 4: 1, 2, 3, =
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(equalsButton);
        
        // Row 5: 0, ., empty, empty
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(dotButton);
        buttonPanel.add(new JLabel());  // Empty space
        buttonPanel.add(new JLabel());  // Empty space
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    /**
     * Handle button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                displayField.setText(displayField.getText() + i);
                return;
            }
        }
        
        // Dot button
        if (e.getSource() == dotButton) {
            if (!displayField.getText().contains(".")) {
                displayField.setText(displayField.getText() + ".");
            }
            return;
        }
        
        // Clear button
        if (e.getSource() == clearButton) {
            displayField.setText("");
            num1 = num2 = result = 0;
            return;
        }
        
        // Delete button
        if (e.getSource() == deleteButton) {
            String text = displayField.getText();
            if (text.length() > 0) {
                displayField.setText(text.substring(0, text.length() - 1));
            }
            return;
        }
        
        // Operator buttons
        if (e.getSource() == addButton) {
            handleOperator('+');
        } else if (e.getSource() == subButton) {
            handleOperator('-');
        } else if (e.getSource() == mulButton) {
            handleOperator('*');
        } else if (e.getSource() == divButton) {
            handleOperator('/');
        } else if (e.getSource() == equalsButton) {
            calculateResult();
        }
    }
    
    /**
     * Handle operator button click
     */
    private void handleOperator(char op) {
        try {
            num1 = Double.parseDouble(displayField.getText());
            operator = op;
            displayField.setText("");
        } catch (NumberFormatException e) {
            displayField.setText("Error");
        }
    }
    
    /**
     * Calculate and display result
     */
    private void calculateResult() {
        try {
            num2 = Double.parseDouble(displayField.getText());
            
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        displayField.setText("Cannot divide by 0");
                        return;
                    }
                    break;
            }
            
            // Display result
            if (result == (long) result) {
                displayField.setText(String.valueOf((long) result));
            } else {
                displayField.setText(String.valueOf(result));
            }
            
            num1 = result;
            
        } catch (NumberFormatException e) {
            displayField.setText("Error");
        }
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorGUI();
            }
        });
    }
}
