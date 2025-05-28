public class Item implements Comparable<Item> {
    private String name;
    private int price;
    private int damage;
    private int healing;
    private int weight;
    private ItemType itemType;
    private boolean stolen = false;
    private boolean inUse = false;

    public Item(String name, int price, int damage, int weight, int healing, ItemType itemType) {
        this.name = name;
        this.price = price;
        this.damage = damage;
        this.weight = weight;
        this.healing = healing;
        this.itemType = itemType;
    }

    public Item(String name, int price, int weight, ItemType itemType) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.itemType = itemType;
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, int price, int weight, ItemType itemType, boolean stolen) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.itemType = itemType;
        this.stolen = stolen;
    }

    public Item() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public int getHealing() { return healing; }
    public void setHealing(int healing) { this.healing = healing; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public ItemType getItemType() { return itemType; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }

    public boolean isStolen() { return stolen; }
    public void setStolen(boolean stolen) { this.stolen = stolen; }

    public boolean isInUse() { return inUse; }
    public void setInUse(boolean inUse) { this.inUse = inUse; }

    // ANSI Colors
    String RESET = "\u001B[0m";
    String BLACK = "\u001B[30m";
    String RED = "\u001B[91m";
    String GREEN = "\u001B[92m";
    String YELLOW = "\u001B[38;5;220m";
    String BLUE = "\u001B[38;5;33m";
    String CYAN = "\u001B[95m";
    String PURPLE = "\u001B[38;5;135m";
    String WHITE = "\u001B[37m";
    String ORANGE = "\u001B[38;5;208m";

    @Override
    public String toString() {
        return "\nNAME: " + CYAN + name + RESET +
                ", DAMAGE: " + RED + damage + RESET +
                ", PRICE: " + YELLOW + price + RESET +
                ", WEIGHT: " + ORANGE + weight + RESET +
                ", HEALING: " + GREEN + healing + RESET +
                ", STOLEN: " + stolen;
    }
    public String toStringMoney() {
        return "NAME: " + CYAN + name + RESET +
                "                         PRICE: " + YELLOW + price + RESET;
    }
    public String toStringByType() {
        String text = "";

        switch (itemType) {
            case WEAPON:
                text = String.format("NAME: \u001B[95m%-15.15s\u001B[0m DAMAGE:   \u001B[91m%-3d\u001B[0m PRICE: \u001B[38;5;220m%-3d\u001B[0m WEIGHT: \u001B[38;5;208m%-2d\u001B[0m",
                        name, damage, price, weight);
                break;
            case POTION:
                text = String.format("NAME: \u001B[95m%-15.15s\u001B[0m HEALING: \u001B[92m %-3d\u001B[0m PRICE: \u001B[38;5;220m%-3d\u001B[0m WEIGHT: \u001B[38;5;208m%-2d\u001B[0m",
                        name, healing, price, weight);
                break;
            case TROPHY, VALUABLE,HERB:
                text = String.format("NAME: \u001B[95m%-15.15s\u001B[0m               PRICE: \u001B[38;5;220m%-3d\u001B[0m WEIGHT: \u001B[38;5;208m%-2d\u001B[0m",
                        name, price, weight);
                break;
            default:
                text = "Error";
        }

        return text;
    }
    public String toStringByTypeBackpack() {
        String text = "";

        switch (itemType) {
            case WEAPON:
                text = String.format("NAME: \u001B[95m%-15.15s\u001B[0m DAMAGE:   \u001B[91m%-3d\u001B[0m PRICE: \u001B[38;5;220m%-3d\u001B[0m WEIGHT: \u001B[38;5;208m%-2d\u001B[0m",
                        name, damage, price, weight);
                break;
            case POTION:
                text = String.format("NAME: \u001B[95m%-15.15s\u001B[0m HEALING: \u001B[92m %-3d\u001B[0m PRICE: \u001B[38;5;220m%-3d\u001B[0m WEIGHT: \u001B[38;5;208m%-2d\u001B[0m",
                        name, healing, price, weight);
                break;
            case TROPHY, VALUABLE,HERB:

                text = String.format("NAME: \u001B[95m%-15.15s\u001B[0m               PRICE: \u001B[38;5;220m%-3d\u001B[0m WEIGHT: \u001B[38;5;208m%-2d\u001B[0m",
                        name, price, weight);
                break;
            default:
                text = "Error";
        }
        if (inUse) {
            text += String.format(" USING: \u001B[92m%s\u001B[0m", true);
        }
        if (stolen) {
            text += String.format(" STOLEN: \u001B[92m%s\u001B[0m", true);
        }

        return text;
    }


    @Override
    public int compareTo(Item o) {
        return Integer.compare(o.getPrice(), this.getPrice());
    }
}
