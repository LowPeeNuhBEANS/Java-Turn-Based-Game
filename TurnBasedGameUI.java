import java.awt.*;
import java.util.Random;
import java.util.Stack;
import javax.swing.*;

public class TurnBasedGameUI {
    private static Stack<String> items = new Stack<>();
    private static Random r = new Random();
    private static int playerHp = 100, enemyHp = 100;
    private static String enemyName;
    private static Stack<Integer> playerHpHistory = new Stack<>();
    private static Stack<Integer> enemyHpHistory = new Stack<>();
    private static boolean enemyStunned = false;

    private JFrame frame;
    private JLabel playerHpLabel;
    private JLabel enemyHpLabel;
    private JTextArea actionLog;

    public TurnBasedGameUI() {
        initializeUI();
        initializeGame();
    }

    private void initializeUI() {
        frame = new JFrame("Turn Based Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());

        // Top panel for HP
        JPanel hpPanel = new JPanel(new GridLayout(1, 2));
        playerHpLabel = new JLabel("Player HP: " + playerHp);
        enemyHpLabel = new JLabel("Enemy HP: " + enemyHp);
        hpPanel.add(playerHpLabel);
        hpPanel.add(enemyHpLabel);

        // Action log in center
        actionLog = new JTextArea();
        actionLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(actionLog);

        // Button panel at bottom
        JPanel buttonPanel = new JPanel();
        JButton attackButton = new JButton("Attack");
        JButton packButton = new JButton("Pack");
        JButton stunButton = new JButton("Stun");
        JButton undoButton = new JButton("Undo");
        JButton exitButton = new JButton("Exit");

        attackButton.addActionListener(e -> performAction("attack"));
        packButton.addActionListener(e -> performAction("pack"));
        stunButton.addActionListener(e -> performAction("stun"));
        undoButton.addActionListener(e -> performAction("undo"));
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(attackButton);
        buttonPanel.add(packButton);
        buttonPanel.add(stunButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(exitButton);

        frame.add(hpPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void initializeGame() {
        items.clear();
        items.add("Sting");
        items.add("Mighty Pula");
        items.add("Sandugo Sandals");
        items.add("Marlboro Black");
        items.add("Tanduay Lapad");

        playerHp = 100;
        enemyHp = 100;
        enemyName = "Enemy " + (r.nextInt(100) + 1);
        actionLog.setText("Welcome to the game! Your enemy is " + enemyName + "\n");
        updateHealthLabels();
    }

    private void performAction(String action) {
        if (playerHp <= 0 || enemyHp <= 0) return;

        switch (action.toLowerCase()) {
            case "attack":
                playerHpHistory.push(playerHp);
                enemyHpHistory.push(enemyHp);
                int damage = (int) (Math.random() * 30) + 1;
                enemyHp -= damage;
                actionLog.append("You attacked " + enemyName + " for " + damage + " damage!\n");
                break;
            case "pack":
                if (items.isEmpty()) {
                    actionLog.append("Your pack is empty!\n");
                } else {
                    StringBuilder sb = new StringBuilder("Your inventory:\n");
                    for (int i = items.size() - 1; i >= 0; i--) {
                        sb.append("- ").append(items.get(i)).append("\n");
                    }
                    actionLog.append(sb.toString());
                    // Simulate using an item
                    String used = items.pop();
                    int heal = 0;
                    if (used.equalsIgnoreCase("Sting")) heal = 10;
                    else if (used.equalsIgnoreCase("Mighty Pula")) heal = 15;
                    else if (used.equalsIgnoreCase("Sandugo Sandals")) heal = 5;
                    else if (used.equalsIgnoreCase("Marlboro Black")) heal = -10;
                    else if (used.equalsIgnoreCase("Tanduay Lapad")) heal = -20;
                    playerHp += heal;
                    actionLog.append("You used " + used + " and " + (heal >= 0 ? "healed " : "lost ") + Math.abs(heal) + " HP.\n");
                }
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
                if (!playerHpHistory.isEmpty() && !enemyHpHistory.isEmpty()) {
                    playerHp = playerHpHistory.pop();
                    enemyHp = enemyHpHistory.pop();
                    actionLog.append("Undo successful!\n");
                } else {
                    actionLog.append("Nothing to undo!\n");
                }
                updateHealthLabels();
                return; // Skip enemy turn on undo
            default:
                actionLog.append("Invalid action!\n");
                return;
        }

        updateHealthLabels();
        checkGameOver();

        if (playerHp > 0 && enemyHp > 0) {
            enemyTurn();
        }
    }

    private void enemyTurn() {
        if (enemyStunned) {
            actionLog.append(enemyName + " is stunned and skips a turn!\n");
            enemyStunned = false;
            return;
        }
        int enemyDamage = (int) (Math.random() * 20) + 1;
        playerHp -= enemyDamage;
        actionLog.append(enemyName + " attacked you for " + enemyDamage + " damage!\n");
        updateHealthLabels();
        checkGameOver();
    }

    // ...existing code...
private void updateHealthLabels() {
    playerHpLabel.setText("Player HP: " + Math.max(playerHp, 0) + " " + getHealthBar(playerHp));
    enemyHpLabel.setText("Enemy HP: " + Math.max(enemyHp, 0) + " " + getHealthBar(enemyHp));
}
// ...existing code...
    private String getHealthBar(int hp) {
    int totalBars = 20;
    int bars = Math.max(0, Math.min(totalBars, (int) Math.round((hp / 100.0) * totalBars)));
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < bars; i++) sb.append("|");
    for (int i = bars; i < totalBars; i++) sb.append(" ");
    sb.append("]");
    return sb.toString();
}

    private void checkGameOver() {
        if (playerHp <= 0) {
            actionLog.append("You have been defeated!\n");
            int result = JOptionPane.showConfirmDialog(frame, "You lost! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        } else if (enemyHp <= 0) {
            actionLog.append("You have defeated " + enemyName + "!\n");
            int result = JOptionPane.showConfirmDialog(frame, "You won! Play again?", "Victory", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void resetGame() {
        initializeGame();
        playerHpHistory.clear();
        enemyHpHistory.clear();
        enemyStunned = false;
    }

    public static void main(String[] args) {
        TurnBasedGameUI game = new TurnBasedGameUI();
        game.frame.setVisible(true);
    }
}