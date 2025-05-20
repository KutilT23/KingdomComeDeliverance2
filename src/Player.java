public class Player {
    private static int strength = 50;
    private static int health = 100;
    private static int reputation = 50;
    private static int money = 100; // later 0
    private static boolean isDead = false;
    public static int getStrength() {
        return strength;
    }

    public static void setStrength(int strength) {
        Player.strength = strength;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        if (health > 100) {
            System.out.println("Cannot have more than 100");
            Player.health = 100;
        } else {
            Player.health = health;
        }
    }


    public static int getReputation() {
        return reputation;
    }

    public static void setReputation(int reputation) {
        Player.reputation = reputation;
    }

    public static boolean isIsDead() {
        return isDead;
    }

    public static void setIsDead(boolean isDead) {
        Player.isDead = isDead;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Player.money = money;
    }
}
