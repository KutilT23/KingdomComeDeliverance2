package Commands;

import Additional.Enemy;
import Additional.ItemType;
import Additional.Player;
import World.Command;
import World.Map;
import Additional.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * class for fighting enemies
 */
public class Fight extends Command {
    private static boolean enemiesLoaded = false;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    private boolean enemyDead = false;
    Backpack backpack = new Backpack();
    /**
     * method to run a command and set up enemies and fight them
     */
    @Override
    public void execute() {
        if (Map.getCurrentLocationName().equals("EnemyCamp")) {
            loadEnemies();
            setEnemyDead(false);
            fight();
        }else{
            System.out.println("You are not in Enemy Camp");
        }

    }

    /**
     * method to fight enemies
     */
    public void fight() {
        if (!enemies.isEmpty()) {
            Enemy enemy = enemies.get(random.nextInt(enemies.size()));
            displayEnemy(enemy);
            while (!Player.isIsDead()&&!isEnemyDead()){
                attack(enemy);
                if (isEnemyDead()) {
                    break;
                }
                System.out.println("Enemy turn");
                block(enemy);

            }
        }else{
            System.out.println("No enemies left");;
        }

    }

    /**
     * method to attack an enemy
     * @param enemy
     */
    public void attack(Enemy enemy){
        String answer = "";
        while (true) {
            System.out.println("From which direction do you want to attack?>> up,down,left,right");
            answer = sc.next().toLowerCase();
            if (answer.equals("up") || answer.equals("down") || answer.equals("left") || answer.equals("right")) {
                break;
            } else {
                System.out.println("Wrong answer, try again.");
            }
        }
        int numberA = random.nextInt(4);
        switch (answer){
            case "up":
                if(numberA!=0){
                    enemy.setHealth(enemy.getHealth()- Player.getDamage());
                    if(enemy.getHealth()>0){
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }else{
                        enemy.setHealth(0);
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }
                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }else{
                    System.out.println("Enemy blocked");
                }
                break;
            case "down":
                if(numberA!=1){
                    enemy.setHealth(enemy.getHealth()- Player.getDamage());
                    if(enemy.getHealth()>0){
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }else{
                        enemy.setHealth(0);
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }
                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }else{
                    System.out.println("Enemy blocked");
                }
                break;
            case "left":

                if(numberA!=2){
                    enemy.setHealth(enemy.getHealth()- Player.getDamage());
                    if(enemy.getHealth()>0){
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }else{
                        enemy.setHealth(0);
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }
                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }else{
                    System.out.println("Enemy blocked");
                }
                break;
            case "right":
                if(numberA!=3){
                    enemy.setHealth(enemy.getHealth()- Player.getDamage());
                    if(enemy.getHealth()>0){
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }else{
                        enemy.setHealth(0);
                        System.out.println("You attacked for " + Player.getDamage() + " damage, Enemy health: " + enemy.getHealth());
                    }

                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }else{
                    System.out.println("Enemy blocked");
                }
                break;
            default:
                System.out.println("Wrong answer");


        }
         if (enemy.getHealth() <= 0) {
            setEnemyDead(true);
            System.out.println("Enemy defeated!");
             System.out.println("Player strenght XP: " + Player.getStrenghtXP());
            Player.levelUp();
            Item itemToGet = enemy.getDrop().get(random.nextInt(3));
            if (itemToGet.getItemType() == null) {
                System.out.println("You got: " + itemToGet.getPrice() + " groschen");
                Player.setMoney(Player.getMoney()+itemToGet.getPrice());
                System.out.println("You now have: " + Player.getMoney() + " groschen");
            }else{
                backpack.addItem(itemToGet);
            }
            enemies.remove(enemy);
        }
    }

    /**
     * method to block an attack from an enemy
     * @param enemy
     */
    public void block(Enemy enemy){
        String answer = "";
        int numberB = random.nextInt(4);
        String direction = "";
        switch (numberB){
            case 0:
                direction = "above";
                break;
            case 1:
                direction = "below";
                break;
            case 2:
                direction = "the left";
                break;
            case 3:
                direction = "the right";
                break;
            default:
                System.out.println("Error");
        }
        System.out.println("Enemy attacks from: " + direction);
        while (true) {
            System.out.println("From which direction do you want to block?>> up,down,left,right");
            answer = sc.next().toLowerCase();
            if (answer.equals("up") || answer.equals("down") || answer.equals("left") || answer.equals("right")) {
                break;
            } else {
                System.out.println("Wrong answer, try again.");
            }
        }

        switch (answer.toLowerCase()){
            case "up":

                if(numberB!=0){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    if(Player.getHealth()>0){
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }else{
                        Player.setHealth(0);
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }
                }else{
                    System.out.println("Attack blocked");
                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }

                break;
            case "down":

                if(numberB!=1){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    if(Player.getHealth()>0){
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }else{
                        Player.setHealth(0);
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }

                }else{
                    System.out.println("Attack blocked");
                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }

                break;
            case "left":

                if(numberB!=2){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    if(Player.getHealth()>0){
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }else{
                        Player.setHealth(0);
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }

                }else{
                    System.out.println("Attack blocked");
                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }

                break;
            case "right":

                if(numberB!=3){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    if(Player.getHealth()>0){
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }else{
                        Player.setHealth(0);
                        System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                    }

                }else{
                    System.out.println("Attack blocked");
                    Player.setStrenghtXP(Player.getStrenghtXP()+2);
                }

                break;
            default:
                System.out.println("Wrong answer");
                block(enemy);
        }
        if (Player.getHealth() <= 0) {
            Player.setIsDead(true);
            System.out.println("You have been defeated.");

        }
    }

    /**
     * method to load enemies from a file
     */
    public void loadEnemies() {
            if (!enemiesLoaded) {
                String difficulty = "";
                switch (Map.getRegion()){
                    case TROSKY:
                        difficulty = "Enemies.txt";
                        break;
                    case KUTTENBERG:
                        difficulty = "Enemies2.txt";
                        break;
                    default:
                        System.out.println("Error");
                }
                enemies.clear();
                try (BufferedReader br = new BufferedReader(new FileReader(difficulty))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(";");

                        Enemy enemy = new Enemy(
                                parts[0],
                                Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),
                                new Item(parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),Integer.parseInt(parts[6]),Integer.parseInt(parts[7]), ItemType.valueOf(parts[8])),
                                new Item(parts[9], Integer.parseInt(parts[10]), Integer.parseInt(parts[11]),Integer.parseInt(parts[12]),Integer.parseInt(parts[13]), ItemType.valueOf(parts[14])),
                                new Item(parts[15], Integer.parseInt(parts[16]))
                        );
                        enemies.add(enemy);
                    }

                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
                //displayEnemies();
                enemiesLoaded = true;

        }
    }

    /**
     * method to display all enemies in the location
     */
    public void displayEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("ENEMY: " + enemies.get(i).getName() + ",HP: " + enemies.get(i).getHealth() +
                    ",DROP: ");
            for (int j = 0; j < enemies.get(i).getDrop().size(); j++) {
                if (enemies.get(i).getDrop().get(j).getItemType() == null) {
                    System.out.println(enemies.get(i).getDrop().get(j).toStringMoney());
                } else {
                    System.out.println(enemies.get(i).getDrop().get(j).toStringByType());
                }
            }
        }
        System.out.println("");
    }

    /**
     * method to display the current enemy
     * @param enemy
     */
    public void displayEnemy(Enemy enemy) {
            System.out.println("ENEMY: " + enemy.getName() +" ,DAMAGE: " +enemy.getDamage() + " ,HP: "
                    + enemy.getHealth() + " ,DROPS:");
        for (int i = 0; i < enemy.getDrop().size(); i++) {
            if (enemy.getDrop().get(i).getItemType() == null) {
                System.out.println(enemy.getDrop().get(i).toStringMoney());
            } else {
                System.out.println(enemy.getDrop().get(i).toStringByType());
            }
        }
        System.out.println("");
    }

    /**
     * method to terminate the program if the return value is true
     * @return
     */
    @Override
    public boolean exit() {
        return Player.isIsDead();
    }

    public static boolean isEnemiesLoaded() {
        return enemiesLoaded;
    }

    public static void setEnemiesLoaded(boolean enemiesLoaded) {
        Fight.enemiesLoaded = enemiesLoaded;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public boolean isEnemyDead() {
        return enemyDead;
    }

    public void setEnemyDead(boolean enemyDead) {
        this.enemyDead = enemyDead;
    }
}
