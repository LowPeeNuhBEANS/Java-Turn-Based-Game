import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class turnBased {
    private static Stack<String> items = new Stack<>();
    private static Random r = new Random();
    private static int playerHp = 100, enemyHp = 100;
    private static String username;
    private static String enemyName;
    private static Stack<Integer> playerHpHistory = new Stack<>();
    private static Stack<Integer> enemyHpHistory = new Stack<>();
    private static boolean enemyStunned = false;

    private JFrame frame;
    private JLabel playerHpLabel;
    private JLabel enemyHpLabel;
    private JTextArea actionLog;
    private JTextField inputField;
    
    }
    
    class TurnBasedGameUI {
        private JFrame frame;
        private JLabel playerHpLabel;
        private JLabel enemyHpLabel;
        private JTextArea actionLog;
        private JTextField inputField;
    
        public TurnBasedGameUI() {
            initializeUI();
            initializeGame();
        }
    
        private void initializeUI() {
        frame = new JFrame("Turn Based Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        playerHpLabel = new JLabel("Player HP: " + turnBased.playerHp);
        enemyHpLabel = new JLabel("Enemy HP: " + turnBased.enemyHp);
        actionLog = new JTextArea();
        actionLog.setEditable(false);
        inputField = new JTextField(20);

        JPanel buttonPanel = new JPanel();
        JButton attackButton = new JButton("Attack");
        JButton packButton = new JButton("Pack");
        JButton stunButton = new JButton("Stun");
        JButton undoButton = new JButton("Undo");
        JButton exitButton = new JButton("Exit");

        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("attack");
            }
        });

        packButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("pack");
            }
        });

        stunButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("stun");
            }
        });

        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("undo");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(attackButton);
        buttonPanel.add(packButton);
        buttonPanel.add(stunButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(exitButton);

        frame.add(playerHpLabel, BorderLayout.NORTH);
        frame.add(enemyHpLabel, BorderLayout.CENTER);
        frame.add(new JScrollPane(actionLog), BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    private void initializeGame() {
        // Initialize items and enemy name
        items.add("Sting");
        items.add("Mighty Pula");
        items.add("Sandugo Sandals");
        items.add("Marlboro Black");
        items.add("Tanduay Lapad");

        enemyName = "Enemy " + (r.nextInt(100) + 1); // Random enemy name
        actionLog.append("Welcome to the game! Your enemy is " + enemyName + "\n");
    }

    private void performAction(String action) {
        switch (action.toLowerCase()) {
            case "attack":
                int damage = (int) (Math.random() * 30) + 1;
                enemyHp -= damage;
                actionLog.append("You attacked " + enemyName + " for " + damage + " damage!\n");
                break;
            case "pack":
                actionLog.append("You checked your inventory.\n");
                break;
            case "stun":
                if (Math.random() < 0.33) {
                    enemyStunned = true;
                    actionLog.append(enemyName + " is stunned!\n");
                } else {
                    actionLog.append(enemyName + " resisted the stun!\n");
                }
                break;
            case "undo":
                actionLog.append("Undo action performed.\n");
                break;
            default:
                actionLog.append("Invalid action!\n");
                return;
        }

        updateHealthLabels();
        checkGameOver();
    }

    private void updateHealthLabels() {
        playerHpLabel.setText("Player HP: " + playerHp);
        enemyHpLabel.setText("Enemy HP: " + enemyHp);
    }

    private void checkGameOver() {
        if (playerHp <= 0) {
            actionLog.append("You have been defeated!\n");
            resetGame();
        } else if (enemyHp <= 0) {
            actionLog.append("You have defeated " + enemyName + "!\n");
            resetGame();
        }
    }

    private void resetGame() {
        playerHp = 100;
        enemyHp = 100;
        enemyName = "Enemy " + (r.nextInt(100) + 1);
        actionLog.append("Game reset. Your new enemy is " + enemyName + "\n");
        updateHealthLabels();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TurnBasedGameUI());
    }
}