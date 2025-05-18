import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fight extends Command{
    private static boolean enemiesLoaded = false;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    private boolean enemyDead = false;
    Backpack backpack = new Backpack();
    @Override
    public void execute() {
        loadEnemies();
        setEnemyDead(false);
        fight();
    }

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
                    enemy.setHealth(enemy.getHealth()-Player.getStrength());
                    System.out.println("You attacked for " + Player.getStrength() + " damage, Enemy health: " + enemy.getHealth());
                }else{
                    System.out.println("Attack blocked");
                }
                break;
            case "down":
                if(numberA!=1){
                    enemy.setHealth(enemy.getHealth()-Player.getStrength());
                    System.out.println("You attacked for " + Player.getStrength() + " damage, Enemy health: " + enemy.getHealth());

                }else{
                    System.out.println("Attack blocked");
                }
                break;
            case "left":

                if(numberA!=2){
                    enemy.setHealth(enemy.getHealth()-Player.getStrength());
                    System.out.println("You attacked for " + Player.getStrength() + " damage, Enemy health: " + enemy.getHealth());

                }else{
                    System.out.println("Attack blocked");
                }
                break;
            case "right":
                if(numberA!=3){
                    enemy.setHealth(enemy.getHealth()-Player.getStrength());
                    System.out.println("You attacked for " + Player.getStrength() + " damage, Enemy health: " + enemy.getHealth());

                }else{
                    System.out.println("Attack blocked");
                }
                break;
            default:
                System.out.println("Wrong answer");


        }
         if (enemy.getHealth() <= 0) {
            setEnemyDead(true);
            System.out.println("Enemy defeated!");
            backpack.addItem(enemy.getDrop().get(random.nextInt(3)));
            enemies.remove(enemy);
        }
    }
    public void block(Enemy enemy){
        String answer = "";
        while (true) {
            System.out.println("From which direction do you want to block?>> up,down,left,right");
            answer = sc.next().toLowerCase();
            if (answer.equals("up") || answer.equals("down") || answer.equals("left") || answer.equals("right")) {
                break;
            } else {
                System.out.println("Wrong answer, try again.");
            }
        }
        int numberB = random.nextInt(4);
        switch (answer.toLowerCase()){
            case "up":

                if(numberB!=0){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());
                }else{
                    System.out.println("Attack blocked");
                }
                break;
            case "down":

                if(numberB!=1){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());

                }else{
                    System.out.println("Attack blocked");
                }
                break;
            case "left":

                if(numberB!=2){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());

                }else{
                    System.out.println("Attack blocked");
                }
                break;
            case "right":

                if(numberB!=3){
                    Player.setHealth(Player.getHealth()-enemy.getDamage());
                    System.out.println("Enemy attacked for " + enemy.getDamage() + " damage, Player health: " + Player.getHealth());

                }else{
                    System.out.println("Attack blocked");
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
    public void loadEnemies() {

            if (!enemiesLoaded) {
                enemies.clear();
                try (BufferedReader br = new BufferedReader(new FileReader("Enemies.txt"))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(";");

                        Enemy enemy = new Enemy(
                                parts[0],
                                Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),
                                new Item(parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),Integer.parseInt(parts[6]),Integer.parseInt(parts[7]), ItemType.valueOf(parts[8])),
                                new Item(parts[9], Integer.parseInt(parts[10]), Integer.parseInt(parts[11]),Integer.parseInt(parts[12]),Integer.parseInt(parts[13]), ItemType.valueOf(parts[14])),
                                new Item(parts[15], Integer.parseInt(parts[16]), Integer.parseInt(parts[17]),ItemType.valueOf(parts[18]))
                        );
                        enemies.add(enemy);
                    }

                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
                displayEnemies();
                enemiesLoaded = true;

        }
    }
    public void displayEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("ENEMY: " + enemies.get(i).getName() + ",HP: "
                    + enemies.get(i).getHealth() + ",DROP: \n" +
                    enemies.get(i).getDrop().get(0).toString3Test() +
                    enemies.get(i).getDrop().get(1).toString2Test() +
                    enemies.get(i).getDrop().get(2).toString4Test());
        }
        System.out.println("");
    }
    public void displayEnemy(Enemy enemy) {
            System.out.println("ENEMY: " + enemy.getName() + ",HP: "
                    + enemy.getHealth() + ",DROP: \n" +
                    enemy.getDrop().get(0).toString3Test() +
                    enemy.getDrop().get(1).toString2Test() +
                    enemy.getDrop().get(2).toString4Test());

        System.out.println("");
    }


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
