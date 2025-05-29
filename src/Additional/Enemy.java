package Additional;

import java.util.ArrayList;

/**
 * class for enemies
 */
public class Enemy {
    private String name;
    private int health;
    private int damage;
    private ArrayList<Item> drop = new ArrayList<>();

    public Enemy(String name, int health,int damage, Item item, Item item1, Item item2) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.drop.add(item);
        this.drop.add(item1);
        this.drop.add(item2);
    }

    @Override
    public String toString() {
        return "\nNAME: " + name + " ,DAMAGE: " +damage+ " ,HEALTH: " + health + ",DROP: " + drop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArrayList<Item> getDrop() {
        return drop;
    }

    public void setDrop(ArrayList<Item> drop) {
        this.drop = drop;
    }
}
