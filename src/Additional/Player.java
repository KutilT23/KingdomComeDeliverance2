package Additional;

/**
 * class for the player
 */
public class Player {
    private static int strength = 10;
    private static int damage = strength;
    private static int health = 100;
    private static int reputation = 50;
    private static int money = 3000; // later 0
    private static int talk = 5;
    private static int talkXP = 0;
    private static int strenghtXP = 0;
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

    /**
     * method to level up the player
     */
    public static void levelUp() {

        while (getTalkXP() >= 10) {
            setTalkXP(getTalkXP() - 10);
            setTalk(getTalk() + 1);
            System.out.println("You leveled up your talk skill! New Commands.Talk level: " + getTalk());
        }
        while (getStrenghtXP() >= 10) {
            setStrenghtXP(getStrenghtXP() - 10);
            setStrength(getStrength() + 1);
            setDamage(getDamage()+1);
            System.out.println("You leveled up your strength! New Strength level: " + getStrength());

        }

    }

    public static int getTalkXP() {
        return talkXP;
    }

    public static void setTalkXP(int talkXP) {
        Player.talkXP = talkXP;
    }

    public static int getStrenghtXP() {
        return strenghtXP;
    }

    public static void setStrenghtXP(int strenghtXP) {
        Player.strenghtXP = strenghtXP;
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

    public static int getTalk() {
        return talk;
    }

    public static void setTalk(int talk) {
        Player.talk = talk;
    }

    public static int getDamage() {
        return damage;
    }

    public static void setDamage(int damage) {
        Player.damage = damage;
    }
}
