import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class BombDefusalGame extends JFrame {
    private static final Random random = new Random();
    private Timer gameTimer;
    private int timeLeft = 60;
    private boolean gameOver = false;
    private boolean bombDefused = false;
    
    // Game components
    private String[] wireColors = {"RED", "BLUE", "GREEN", "YELLOW", "BLACK", "WHITE"};
    private Color[] wireColorObjects = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.WHITE};
    private boolean[] wiresCut = new boolean[4];
    private String[] bombWires = new String[4];
    private Color[] bombWireColors = new Color[4];
    private String correctWireSequence;
    private String keypadCode;
    private boolean wiresDefused = false;
    private boolean keypadDefused = false;
    private String enteredCode = "";
    
    // UI Components
    private JLabel timerLabel;
    private JLabel statusLabel;
    private JPanel wirePanel;
    private JPanel keypadPanel;
    private JLabel keypadDisplay;
    private JButton[] wireButtons;
    private JButton[] numberButtons;
    private JButton clearButton;
    private JButton enterButton;
    private JButton manualButton;
    private JProgressBar progressBar;
    private JLabel wireStatusLabel;
    private JLabel keypadStatusLabel;
    
    public BombDefusalGame() {
        initializeGame();
        setupUI();
        startTimer();
    }
    
    private void initializeGame() {
        // Generate random wires
        for (int i = 0; i < 4; i++) {
            int colorIndex = random.nextInt(wireColors.length);
            bombWires[i] = wireColors[colorIndex];
            bombWireColors[i] = wireColorObjects[colorIndex];
        }
        
        // Generate correct wire sequence
        List<String> sequence = new ArrayList<>();
        Set<Integer> positions = new HashSet<>();
        int numWiresToCut = 2 + random.nextInt(2);
        
        while (positions.size() < numWiresToCut) {
            positions.add(random.nextInt(4));
        }
        
        for (int pos : positions) {
            sequence.add(bombWires[pos]);
        }
        correctWireSequence = String.join(", ", sequence);
        
        // Generate keypad code
        keypadCode = String.format("%04d", random.nextInt(10000));
    }
    
    private void setupUI() {
        setTitle("💣 BOMB DEFUSAL GAME 💣");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        
        // Create main panels
        createTopPanel();
        createCenterPanel();
        createBottomPanel();
        
        // Style the window
        getContentPane().setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Timer display
        timerLabel = new JLabel("TIME: 60", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        timerLabel.setForeground(Color.GREEN);
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.BLACK);
        
        // Progress bar
        progressBar = new JProgressBar(0, 60);
        progressBar.setValue(60);
        progressBar.setStringPainted(true);
        progressBar.setString("60 seconds remaining");
        progressBar.setForeground(Color.GREEN);
        progressBar.setBackground(Color.BLACK);
        
        // Status label
        statusLabel = new JLabel("DEFUSE BOTH SYSTEMS TO SAVE THE CITY!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        statusLabel.setForeground(Color.YELLOW);
        
        topPanel.add(timerLabel, BorderLayout.NORTH);
        topPanel.add(progressBar, BorderLayout.CENTER);
        topPanel.add(statusLabel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
    }
    
    private void createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Wire panel
        createWirePanel();
        
        // Keypad panel
        createKeypadPanel();
        
        centerPanel.add(wirePanel);
        centerPanel.add(keypadPanel);
        
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private void createWirePanel() {
        wirePanel = new JPanel(new BorderLayout());
        wirePanel.setBackground(Color.DARK_GRAY);
        wirePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2), 
            "WIRE SYSTEM", 
            0, 0, 
            new Font("Dialog", Font.BOLD, 14), 
            Color.WHITE
        ));
        
        // Wire status
        wireStatusLabel = new JLabel("❌ ACTIVE", SwingConstants.CENTER);
        wireStatusLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        wireStatusLabel.setForeground(Color.RED);
        
        // Wire buttons
        JPanel wiresGrid = new JPanel(new GridLayout(4, 1, 5, 5));
        wiresGrid.setBackground(Color.DARK_GRAY);
        
        wireButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            final int wireIndex = i;
            wireButtons[i] = new JButton("WIRE " + (i + 1) + ": " + bombWires[i]);
            wireButtons[i].setFont(new Font("Monospaced", Font.BOLD, 12));
            wireButtons[i].setBackground(bombWireColors[i]);
            wireButtons[i].setForeground(Color.WHITE);
            wireButtons[i].setBorder(BorderFactory.createRaisedBorderBorder());
            wireButtons[i].addActionListener(e -> cutWire(wireIndex));
            wiresGrid.add(wireButtons[i]);
        }
        
        wirePanel.add(wireStatusLabel, BorderLayout.NORTH);
        wirePanel.add(wiresGrid, BorderLayout.CENTER);
    }
    
    private void createKeypadPanel() {
        keypadPanel = new JPanel(new BorderLayout());
        keypadPanel.setBackground(Color.DARK_GRAY);
        keypadPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2), 
            "KEYPAD SYSTEM", 
            0, 0, 
            new Font("Dialog", Font.BOLD, 14), 
            Color.WHITE
        ));
        
        // Keypad status
        keypadStatusLabel = new JLabel("❌ ACTIVE", SwingConstants.CENTER);
        keypadStatusLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        keypadStatusLabel.setForeground(Color.RED);
        
        // Display
        keypadDisplay = new JLabel("____", SwingConstants.CENTER);
        keypadDisplay.setFont(new Font("Monospaced", Font.BOLD, 20));
        keypadDisplay.setForeground(Color.GREEN);
        keypadDisplay.setBackground(Color.BLACK);
        keypadDisplay.setOpaque(true);
        keypadDisplay.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        
        // Number pad
        JPanel numberPad = new JPanel(new GridLayout(4, 3, 2, 2));
        numberPad.setBackground(Color.DARK_GRAY);
        
        numberButtons = new JButton[10];
        for (int i = 1; i <= 9; i++) {
            final int num = i;
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Monospaced", Font.BOLD, 16));
            numberButtons[i].setBackground(Color.LIGHT_GRAY);
            numberButtons[i].addActionListener(e -> addDigit(num));
            numberPad.add(numberButtons[i]);
        }
        
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Dialog", Font.BOLD, 10));
        clearButton.setBackground(Color.ORANGE);
        clearButton.addActionListener(e -> clearCode());
        
        numberButtons[0] = new JButton("0");
        numberButtons[0].setFont(new Font("Monospaced", Font.BOLD, 16));
        numberButtons[0].setBackground(Color.LIGHT_GRAY);
        numberButtons[0].addActionListener(e -> addDigit(0));
        
        enterButton = new JButton("ENTER");
        enterButton.setFont(new Font("Dialog", Font.BOLD, 10));
        enterButton.setBackground(Color.GREEN);
        enterButton.addActionListener(e -> enterCode());
        
        numberPad.add(clearButton);
        numberPad.add(numberButtons[0]);
        numberPad.add(enterButton);
        
        keypadPanel.add(keypadStatusLabel, BorderLayout.NORTH);
        keypadPanel.add(keypadDisplay, BorderLayout.CENTER);
        keypadPanel.add(numberPad, BorderLayout.SOUTH);
    }
    
    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.BLACK);
        
        manualButton = new JButton("📖 DEFUSAL MANUAL");
        manualButton.setFont(new Font("Dialog", Font.BOLD, 12));
        manualButton.setBackground(Color.BLUE);
        manualButton.setForeground(Color.WHITE);
        manualButton.addActionListener(e -> showManual());
        
        JButton quitButton = new JButton("💀 GIVE UP");
        quitButton.setFont(new Font("Dialog", Font.BOLD, 12));
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        quitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to give up?\nThe bomb will explode!", 
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                explodeBomb();
            }
        });
        
        bottomPanel.add(manualButton);
        bottomPanel.add(quitButton);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void startTimer() {
        gameTimer = new Timer(true);
        gameTimer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (!gameOver) {
                        timeLeft--;
                        updateTimer();
                        
                        if (timeLeft <= 0) {
                            explodeBomb();
                        }
                    }
                });
            }
        }, 1000, 1000);
    }
    
    private void updateTimer() {
        timerLabel.setText("TIME: " + timeLeft);
        progressBar.setValue(timeLeft);
        progressBar.setString(timeLeft + " seconds remaining");
        
        if (timeLeft <= 10) {
            timerLabel.setForeground(Color.RED);
            progressBar.setForeground(Color.RED);
            if (timeLeft % 2 == 0) {
                timerLabel.setBackground(Color.RED);
                statusLabel.setText("⚠️ CRITICAL TIME WARNING! ⚠️");
            } else {
                timerLabel.setBackground(Color.BLACK);
                statusLabel.setText("HURRY UP! TIME IS RUNNING OUT!");
            }
        } else if (timeLeft <= 20) {
            timerLabel.setForeground(Color.YELLOW);
            progressBar.setForeground(Color.YELLOW);
        }
    }
    
    private void cutWire(int wireIndex) {
        if (gameOver || wiresDefused || wiresCut[wireIndex]) return;
        
        wiresCut[wireIndex] = true;
        wireButtons[wireIndex].setText("✂️ CUT: " + bombWires[wireIndex]);
        wireButtons[wireIndex].setBackground(Color.GRAY);
        wireButtons[wireIndex].setEnabled(false);
        
        checkWireSequence();
    }
    
    private void checkWireSequence() {
        List<String> cutWires = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (wiresCut[i]) {
                cutWires.add(bombWires[i]);
            }
        }
        
        String currentSequence = String.join(", ", cutWires);
        
        if (currentSequence.equals(correctWireSequence)) {
            wiresDefused = true;
            wireStatusLabel.setText("✅ DEFUSED");
            wireStatusLabel.setForeground(Color.GREEN);
            statusLabel.setText("WIRE SYSTEM NEUTRALIZED!");
            
            // Disable remaining wire buttons
            for (int i = 0; i < 4; i++) {
                if (!wiresCut[i]) {
                    wireButtons[i].setEnabled(false);
                }
            }
            
            checkVictory();
        } else if (cutWires.size() >= correctWireSequence.split(", ").length) {
            explodeBomb();
        }
    }
    
    private void addDigit(int digit) {
        if (gameOver || keypadDefused || enteredCode.length() >= 4) return;
        
        enteredCode += digit;
        updateKeypadDisplay();
    }
    
    private void clearCode() {
        if (gameOver || keypadDefused) return;
        
        enteredCode = "";
        updateKeypadDisplay();
    }
    
    private void updateKeypadDisplay() {
        String display = enteredCode;
        while (display.length() < 4) {
            display += "_";
        }
        keypadDisplay.setText(display);
    }
    
    private void enterCode() {
        if (gameOver || keypadDefused || enteredCode.length() != 4) return;
        
        if (enteredCode.equals(keypadCode)) {
            keypadDefused = true;
            keypadStatusLabel.setText("✅ DEFUSED");
            keypadStatusLabel.setForeground(Color.GREEN);
            keypadDisplay.setText("PASS");
            keypadDisplay.setForeground(Color.GREEN);
            statusLabel.setText("KEYPAD SYSTEM NEUTRALIZED!");
            
            // Disable keypad
            for (JButton btn : numberButtons) {
                if (btn != null) btn.setEnabled(false);
            }
            clearButton.setEnabled(false);
            enterButton.setEnabled(false);
            
            checkVictory();
        } else {
            keypadDisplay.setText("FAIL");
            keypadDisplay.setForeground(Color.RED);
            timeLeft -= 3;
            statusLabel.setText("WRONG CODE! -3 SECONDS PENALTY!");
            
            // Flash the display
            Timer flashTimer = new Timer(true);
            flashTimer.schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> {
                        enteredCode = "";
                        updateKeypadDisplay();
                        keypadDisplay.setForeground(Color.GREEN);
                    });
                }
            }, 1000);
        }
    }
    
    private void checkVictory() {
        if (wiresDefused && keypadDefused) {
            defuseBomb();
        }
    }
    
    private void defuseBomb() {
        gameOver = true;
        bombDefused = true;
        gameTimer.cancel();
        
        timerLabel.setText("DEFUSED!");
        timerLabel.setForeground(Color.GREEN);
        timerLabel.setBackground(Color.BLACK);
        statusLabel.setText("🎉 BOMB SUCCESSFULLY DEFUSED! 🎉");
        
        JOptionPane.showMessageDialog(
            this,
            "CONGRATULATIONS!\n\n" +
            "You successfully defused the bomb with " + timeLeft + " seconds remaining!\n" +
            "Both systems were neutralized and the city is safe!\n\n" +
            "SOLUTION:\n" +
            "Wire sequence: " + correctWireSequence + "\n" +
            "Keypad code: " + keypadCode,
            "MISSION ACCOMPLISHED!",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Would you like to play again?",
            "Play Again?",
            JOptionPane.YES_NO_OPTION
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new BombDefusalGame();
        } else {
            System.exit(0);
        }
    }
    
    private void explodeBomb() {
        gameOver = true;
        gameTimer.cancel();
        
        timerLabel.setText("BOOM!");
        timerLabel.setForeground(Color.RED);
        timerLabel.setBackground(Color.YELLOW);
        statusLabel.setText("💥 THE BOMB EXPLODED! 💥");
        
        // Disable all buttons
        for (JButton btn : wireButtons) {
            btn.setEnabled(false);
        }
        for (JButton btn : numberButtons) {
            if (btn != null) btn.setEnabled(false);
        }
        clearButton.setEnabled(false);
        enterButton.setEnabled(false);
        
        JOptionPane.showMessageDialog(
            this,
            "GAME OVER!\n\n" +
            "The bomb exploded! You failed to defuse it in time.\n\n" +
            "SOLUTION:\n" +
            "Wire sequence was: " + correctWireSequence + "\n" +
            "Keypad code was: " + keypadCode + "\n\n" +
            "Better luck next time, bomb disposal expert!",
            "MISSION FAILED",
            JOptionPane.ERROR_MESSAGE
        );
        
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Would you like to try again?",
            "Try Again?",
            JOptionPane.YES_NO_OPTION
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new BombDefusalGame();
        } else {
            System.exit(0);
        }
    }
    
    private void showManual() {
        String manual = "BOMB DEFUSAL MANUAL\n\n" +
            "OBJECTIVE:\n" +
            "Defuse both the Wire System and Keypad System before time runs out!\n\n" +
            "WIRE SYSTEM:\n" +
            "• Cut the correct wires in any order\n" +
            "• Cutting wrong wires will detonate the bomb\n" +
            "• Look for patterns in wire colors\n" +
            "• If colors repeat, you may need to cut multiple wires\n\n" +
            "KEYPAD SYSTEM:\n" +
            "• Enter the correct 4-digit code (0000-9999)\n" +
            "• Wrong codes cause 3-second time penalties\n" +
            "• Use CLEAR to reset your input\n" +
            "• Press ENTER to submit your code\n\n" +
            "CURRENT WIRE COLORS:\n";
        
        Map<String, Integer> colorCount = new HashMap<>();
        for (String wire : bombWires) {
            colorCount.put(wire, colorCount.getOrDefault(wire, 0) + 1);
        }
        
        for (String color : colorCount.keySet()) {
            manual += "• " + color + " (" + colorCount.get(color) + " wire" + 
                     (colorCount.get(color) > 1 ? "s" : "") + ")\n";
        }
        
        manual += "\nHINTS:\n" +
            "• Primary colors (RED, BLUE, GREEN) are often important\n" +
            "• Try common codes: 0000, 1234, 9999\n" +
            "• Duplicate colored wires may both need cutting";
        
        JTextArea textArea = new JTextArea(manual);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Defusal Manual",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            } catch (Exception e) {
                // Use default look and feel
            }
            new BombDefusalGame();
        });
    }
}