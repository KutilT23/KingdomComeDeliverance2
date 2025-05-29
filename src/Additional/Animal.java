package Additional;

import java.util.ArrayList;

/**
 * class for animals
 */
public class Animal {
    private String name;
    private int health;
    private Item item;
    private ArrayList<Item> loot = new ArrayList<>();

    public Animal(String name, int health, Item item, Item item1, Item item2) {
        this.name = name;
        this.health = health;
        this.loot.add(item);
        this.loot.add(item1);
        this.loot.add(item2);
    }

    @Override
    public String toString() {
        return "\nANIMAL: " + name + " ,HEALTH: " + health + ",LOOT: " + loot;
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

    public ArrayList<Item> getLoot() {
        return loot;
    }

    public void setLoot(ArrayList<Item> loot) {
        this.loot = loot;
    }
}
