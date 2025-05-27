/*Player skills and abilities:
Offensive:
Passive - Every 4th attack gives guaranteed Crit
Active:  - Stun (1/3 chances to land) for balancing purposes. DONE!
- use healing item (inventory)

Defensive:
Active: (Item) Using shield gains + defensive status while returning 25% of the damage to the opponent 
(skips the turn) Disable's the enemy's crit chance.
if player's hp is below 50% this defensive skill shows up. CD: 3 or 4 turns


Opponent skills and abilities:
Offensive: Basic Attack DONE!
Passive: Burn (turn-ticked base) 

Defensive:
Passive: Backtrack DONE!
Active: Item Stealing (USE IMMEDIATELY)*/


import java.util.Scanner;
import java.util.Stack;

public class TurnBasedGame {
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> playerHpHistory = new Stack<>();
        Stack<Integer> enemyHpHistory = new Stack<>();
        int playerHp = 100;
        int enemyHp = 100;
        boolean enemyStunned = false;

    //Replayability [Would you like to play again?]
    while(true){
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your enemy's name: ");
        String enemyName = scanner.nextLine();

        System.out.println("Welcome to " + username + " VS. " + enemyName + " THE FIGHT OF THE CENTURY!");

        while (playerHp > 0 && enemyHp > 0) {
            System.out.println("\n" + username + " HP: " + playerHp + " " + getHealthBar(playerHp));
            System.out.println(enemyName + " HP: " + enemyHp + " " + getHealthBar(enemyHp));
            System.out.println("Choose your action: attack / heal / stun / undo (type exit to exit)");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("exit")) {
                System.out.println("Exiting game. Goodbye!");
                break;
            }

            if (action.equalsIgnoreCase("attack")) {
                playerHpHistory.push(playerHp);
                enemyHpHistory.push(enemyHp);
                int damage = (int)(Math.random() * 30) + 1;
                enemyHp -= damage;
                System.out.println("You attacked the enemy for " + damage + " damage!");
            } else if (action.equalsIgnoreCase("heal")) {
                playerHpHistory.push(playerHp);
                enemyHpHistory.push(enemyHp);
                int heal = (int)(Math.random() * 30) + 1;
                playerHp += heal;
                System.out.println("You healed yourself for " + heal + " HP!");
            } else if (action.equalsIgnoreCase("stun")) {
            
                if (Math.random() < 0.33) {
                        enemyStunned = true;
                        System.out.println(enemyName + " has been successfully stunned!");
                    }
                else { 
                    System.out.println("Stun failed! " + enemyName + " is not stunned.");
                }
            } else if (action.equalsIgnoreCase("undo")) {
                if (!playerHpHistory.isEmpty() && !enemyHpHistory.isEmpty()) {
                    playerHp = playerHpHistory.pop();
                    enemyHp = enemyHpHistory.pop();
                    System.out.println("Undo successful!");
                } else {
                    System.out.println("Nothing to undo!");
                }
                continue;
            } else {
                System.out.println("Invalid action. Try again.");
                continue;
            }

            // Enemy's turn
            if (enemyHp > 0) {
                if (enemyStunned) {
                    System.out.println("Enemy is stunned and skips their turn!");
                    
                enemyStunned = false; // Reset stun
            } else {
                int enemyDamage = (int)(Math.random() * 20) + 1;
                playerHp -= enemyDamage;
                System.out.println(enemyName + " attacks you for " + enemyDamage + " damage!");

                // Backtrack logic
            if (enemyHp < 50) {
                    if (Math.random() < 0.5) {
                        int passiveHeal = (int)(Math.random() * 20) + 1;
                        enemyHp += passiveHeal;
                        System.out.println("Backtrack activated! " + enemyName + " restored " + enemyHp + " HP.");
                    } 
                
            }
            if (playerHp <= 0) {
                System.out.println(enemyName + " wins!");
                System.out.print("Do you want to restart the game? (yes/no): ");
                String restart = scanner.nextLine();
                    if (!restart.equalsIgnoreCase("yes")) {
                    System.out.println("Thanks for playing!");
                    break;
                    }
            }
            if (enemyHp <= 0) {
                System.out.println(username + " wins!");
                System.out.print("Do you want to restart the game? (yes/no): ");
                String restart = scanner.nextLine();
                    if (!restart.equalsIgnoreCase("yes")) {
                    System.out.println("Thanks for playing!");
                    break;
                    }                
                }
            }
        }
    }
}
}
    // Health bar representation
    static String getHealthBar(int hp) {
        int totalBars = 20;
        int bars = Math.max(0, (int)Math.round(hp / 5.0));
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < bars; i++) bar.append("|");
        for (int i = bars; i < totalBars; i++) bar.append(" ");
        bar.append("]");
        return bar.toString();
    }
}