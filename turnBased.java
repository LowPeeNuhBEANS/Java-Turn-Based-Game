import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class turnBased {

    private static Stack<String> items = new Stack<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int[] shields = {1, 1, 1, 1, 1};
    private static Random r = new Random();
    private static int playerHp = 100, enemyHp = 100;

    public static void main(String[] args) {
    //Item list
        items.add("Sting");
        items.add("Mighty Pula");
        items.add("Sandugo Sandals");
        items.add("Marlboro Black");
        items.add("Tanduay Lapad");

        Stack<Integer> playerHpHistory = new Stack<>();
        Stack<Integer> enemyHpHistory = new Stack<>();
        LinkedList<String> oppFirstName = new LinkedList<>();
        LinkedList<String> oppLastName = new LinkedList<>();

        //Opponent's first names
        oppFirstName.add("Jhon");
        oppFirstName.add("Miguel");
        oppFirstName.add("Alex");
        oppFirstName.add("Charlie");
        oppFirstName.add("Dodong");
        oppFirstName.add("Mark");
        oppFirstName.add("Kevin");
        oppFirstName.add("Michael");
        oppFirstName.add("Gusion");
        oppFirstName.add("Albert");
        oppFirstName.add("Godwin");
        oppFirstName.add("Vincent");
        oppFirstName.add("Angelo");
        oppFirstName.add("Jipoy");
        oppFirstName.add("Calvin");
        oppFirstName.add("Cathy");
        oppFirstName.add("Ramon");
        oppFirstName.add("Mohammed");
        oppFirstName.add("Abdul");
        oppFirstName.add("Ameer");
        oppFirstName.add("Jonel");
        oppFirstName.add("Toto");
        oppFirstName.add("Jilbert");
        oppFirstName.add("Jasper");
        oppFirstName.add("Sarah");
        oppFirstName.add("Inday");
        oppFirstName.add("Marjorie");
        oppFirstName.add("Diday");
        oppFirstName.add("Tata");
        oppFirstName.add("Maribik");
        oppFirstName.add("Julius");
        oppFirstName.add("Junjun");
        oppFirstName.add("Tonton");
        oppFirstName.add("Bentong");
        oppFirstName.add("CTFU");
        oppFirstName.add("Dondon");
        oppFirstName.add("Omar");
        oppFirstName.add("Paolo");
        oppFirstName.add("Rigel");
        oppFirstName.add("Samsam");
        oppFirstName.add("Tuping");
        oppFirstName.add("Oscar");
        oppFirstName.add("Biboy");
        oppFirstName.add("Ginging");
        oppFirstName.add("Marbin");
        oppFirstName.add("Jerick");
        oppFirstName.add("Junsoy");
        oppFirstName.add("Justin");
        oppFirstName.add("Roberto");
        oppFirstName.add("Danilo");

        //Opponent's last names
        oppLastName.add("Acacia");
        oppLastName.add("Agdao");
        oppLastName.add("Marapangi");
        oppLastName.add("Angliongto");
        oppLastName.add("Bago Aplaya");
        oppLastName.add("Bago Gallera");
        oppLastName.add("Bago Oshiro");
        oppLastName.add("Baguio");
        oppLastName.add("Baliok");
        oppLastName.add("Baracatan");
        oppLastName.add("Bangkerohan");
        oppLastName.add("Malagamot");
        oppLastName.add("Binugao");
        oppLastName.add("Bucana");
        oppLastName.add("Buda");
        oppLastName.add("Buhangin");
        oppLastName.add("Bunawan");
        oppLastName.add("Cabantian");
        oppLastName.add("Calinan");
        oppLastName.add("Callawa");
        oppLastName.add("Camus");
        oppLastName.add("Catalunan Pequeño");
        oppLastName.add("Catigan");
        oppLastName.add("Crossing Bayabas");
        oppLastName.add("Dacudao");
        oppLastName.add("Bacaca");
        oppLastName.add("Datu Salumay");
        oppLastName.add("Quirino");
        oppLastName.add("Tagakpan");
        oppLastName.add("Gatungan");
        oppLastName.add("Ilang");
        oppLastName.add("Indangan");
        oppLastName.add("Lacson");
        oppLastName.add("Langub");
        oppLastName.add("Lasang");
        oppLastName.add("Los Amigos");
        oppLastName.add("El Rio");
        oppLastName.add("Magtuod");
        oppLastName.add("Malagos");
        oppLastName.add("Aplaya");
        oppLastName.add("Biao");
        oppLastName.add("Crossing");
        oppLastName.add("Pangi");
        oppLastName.add("Mintal");
        oppLastName.add("Sandawa");
        oppLastName.add("Tuganay");
        oppLastName.add("Tibungco");
        oppLastName.add("Talomo");
        oppLastName.add("Tamayong");



        boolean enemyStunned = false;


        //Replayability

        while (true) {

            System.out.println("+-----------------------------------+");
            System.out.println("|                                   |");
            System.out.println("|   SUMBAGAY SA KADALANAN SA DAVAO  |");
            System.out.println("|         [A Turn Based Game]       |");
            System.out.println("|                                   |");
            System.out.println("+-----------------------------------+");

            //Player name
            System.out.print("Enter your name: ");
            String username = scanner.nextLine();

            //Enemy name randomizer
            String enemyName = oppFirstName.get(r.nextInt(oppFirstName.size())) + " " + oppLastName.get(r.nextInt(oppLastName.size()));

            while (playerHp > 0 && enemyHp > 0) {
                System.out.println("\n" + username + " HP: " + playerHp + " " + getHealthBar(playerHp));
                System.out.println(enemyName + " HP: " + enemyHp + " " + getHealthBar(enemyHp)); // Enemy name
                System.out.println("Choose your action: attack / pack / stun / undo (type exit to exit)");
                String action = scanner.nextLine();

                if (action.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting game. Goodbye!");
                    break;
                }

                if (action.equalsIgnoreCase("attack")) {
                    playerHpHistory.push(playerHp);
                    enemyHpHistory.push(enemyHp);
                    int damage = (int) (Math.random() * 30) + 1;
                    enemyHp -= damage;
                    System.out.println("Napaksitan ug " + damage + " damage si " + enemyName + "!");
                } else if (action.equalsIgnoreCase("pack")) {
                    inventory();
                } else if (action.equalsIgnoreCase("stun")) {

                    if (Math.random() < 0.33) {
                        enemyStunned = true;
                        System.out.println("Nalipong si" + enemyName + " oi! Wa kabalo unsay buhaton");
                    } else {
                        System.out.println("Wa na stun si " + enemyName);
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
                    System.out.println("Di na pwede, usaba ra gud!");
                    continue;
                }

                // Enemy's turn
                int rando = r.nextInt(4 - 1 + 1) + 1;

                if (enemyHp > 0) {
                    if (enemyStunned) {
                        System.out.println("Ikaw na pod.");

                        enemyStunned = false; // Reset stun
                    } else if (rando == 4) {
                        checkFood(2);
                    } else {
                        int enemyDamage = (int) (Math.random() * 20) + 1;
                        playerHp -= enemyDamage;
                        System.out.println("Nadunggaban ka ni "+ enemyName + " ug " + enemyDamage + " damage!");

                        // Backtrack logic
                        if (enemyHp < 50) {
                            if (Math.random() < 0.5) {
                                int passiveHeal = (int) (Math.random() * 20) + 1;
                                enemyHp += passiveHeal;
                                System.out.println("Naloadan ug GoSurf50 si " + enemyName + " nadunggagan ug " + enemyHp + " HP.");
                            }

                        }
                        if (playerHp <= 0) {
                            System.out.println("DAOG SI "+ enemyName);
                            System.out.println("Mu-usab ka o di? (yes/no): ");
                            String restart = scanner.nextLine();
                            if (!restart.equalsIgnoreCase("yes")) {
                                System.out.println("Lamat sa pagdula!");
                                break;
                            }
                        }
                        if (enemyHp <= 0) {
                            System.out.println("DAOG SI "+ username);
                            System.out.println("Mu-usab ka o di? (yes/no): ");
                            String restart = scanner.nextLine();
                            if (!restart.equalsIgnoreCase("yes")) {
                                System.out.println("Lamat sa pagdula!");
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
        int bars = Math.max(0, (int) Math.round(hp / 5.0));
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < bars; i++) {
            bar.append("|");
        }
        for (int i = bars; i < totalBars; i++) {
            bar.append(" ");
        }
        bar.append("]");
        return bar.toString();
    }

        static void inventory() {
            System.out.println("Nitanaw ka sa imong Jansport bag nga color blue. Here are your options:");
            System.out.println(">>Consume(eat a health item)   >>Use Shield [5]");
            System.out.print("Pilii daw? ");

            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("use shield")) {
            useShield();
            } else if (choice.equalsIgnoreCase("view") || choice.equalsIgnoreCase("view inventory")) {
            if (items.isEmpty()) {
            System.out.println("Way unod imong bag oi.");
            } else {
            System.out.println("Your inventory contains (top item will be used first):");
            for (int i = items.size() - 1; i >= 0; i--) {
                if (i == items.size() - 1) {
                    System.out.println("- " + items.peek() + " (next to use)");
                } else {
                    System.out.println("- " + items.get(i));
                }
            }
        }
    } else {
        checkFood(1);
    }
}

    static void checkFood(int target) {
        if (items.empty() && target == 1) {
            System.out.println("There are no more items in your pack.");
            return;
        } else if (items.empty() && target == 2) {
            System.out.println("The enemy tried to steal from your pack, but it was empty!");
            return;
        }
    
        
        int heal;
        String food = items.pop();

        if (food.equalsIgnoreCase("cake")) {
            heal = 50;
        } else if (food.equalsIgnoreCase("candy")) {
            heal = 5;
        } else if (food.equalsIgnoreCase("cheese")) {
            heal = 25;
        } else if (food.equalsIgnoreCase("gatorade")) {
            heal = 30;
        } else {
            heal = -20;
        }

        if (target == 1) {
            playerHp += heal;
            System.out.println("You consumed " + food + " that added " + heal + " to your health. Your health is now " + playerHp);
        } else {
            enemyHp += heal;
            System.out.println("The enemy stole from your pack! He consumed " + food + ". His health is now " + enemyHp);
        }
    }
    

    static void useShield() {
        System.out.println("shield used");
    }
}

        
            