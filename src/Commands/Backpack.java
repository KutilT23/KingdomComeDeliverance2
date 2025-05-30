package Commands;
import Additional.Item;
import Additional.ItemType;
import Additional.Player;
import World.Command;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class for the backpack
 */
public class Backpack extends Command {

    private static ArrayList<Item> backpack = new ArrayList<>();
    private static int capacity = 30 + Player.getStrength() * 5;
    private static int currentCapacity = capacity;
    private static int backpackValue = 0;
    Scanner sc = new Scanner(System.in);

    /**
     * method to run a command and choose what the player wants to do with the backpack
     */
    @Override
    public void execute() {
        chooseOption();
        System.out.println("CURRENT CAPACITY: " + getCurrentCapacity());
    }

    /**
     * method to terminate the program if the return value is true
     * @return
     */
    @Override
    public boolean exit() {
        return false;
    }

    /**
     * method for choosing the option what the player wants to do with the backpack
     */
    public void chooseOption() {
        System.out.println(">> PRINT, USE");
        String answer = sc.nextLine().trim();
        answer = answer.toLowerCase();
        switch (answer) {
            case "print":
                printBackpack();
                break;
            case "use":
                use();
                break;
            default:
                System.out.println("Invalid option");
                chooseOption();
        }
    }

    /**
     * method to add an item to the backpack if the capacity is not full
     * @param item
     */
    public void addItem(Item item) {
        if (getCurrentCapacity() - item.getWeight() >= 0) {
            backpack.add(item);
            System.out.println("Item: " + item.getName() + " added to backpack");
            setCurrentCapacity(getCurrentCapacity() - item.getWeight());
        } else {
            System.out.println("Backpack is full");
        }
    }

    /**
     * method to use an item from the backpack
     */
    public void use(){
        if (!backpack.isEmpty()) {
            printBackpack();
            System.out.println("Which item do you want to use? >> INDEX:");
            int index = 0;
            String indexInput = sc.nextLine().trim();
            while (!indexInput.matches("[0-9]+") || Integer.parseInt(indexInput) >= Backpack.getBackpack().size()) {
                System.out.println("Invalid input >> INDEX:");
                indexInput = sc.nextLine().trim();
            }
            index = Integer.parseInt(indexInput);
            switch (backpack.get(index).getItemType()) {
                case WEAPON:
                    for (Item item : backpack) {
                        if (item.isInUse()) {
                            Player.setDamage(Player.getDamage() - item.getDamage());
                            break;
                        }
                    }
                    for (Item item : backpack) {
                        item.setInUse(false);
                    }
                    backpack.get(index).setInUse(true);
                    for (Item item : backpack) {
                        if (item.isInUse()) {
                            Player.setDamage(Player.getDamage() + item.getDamage());
                            System.out.println("------> "+item.toStringByTypeBackpack());
                            // break;
                        }
                    }
                    System.out.println("CURRENT DAMAGE: " + Player.getDamage());
                    break;
                case POTION:
                    if (Player.getHealth() != 100) {
                        System.out.println("Using potion");
                        Player.setHealth(Player.getHealth() + backpack.get(index).getHealing());
                        backpack.remove(index);
                        System.out.println("YOUR HEALTH: " + Player.getHealth());
                    } else {
                        System.out.println("YOU ALREADY HAVE MAXIMUM HEALTH");
                    }
                    break;
                case TROPHY, VALUABLE, HERB:
                    System.out.println("Cannot be used");
                    break;
                default:
                    System.out.println("Error");
            }
        } else {
            System.out.println("NOTHING TO USE");
        }

    }

    /**
     * method to print the backpack
     */
    public void printBackpack() {
        if (!backpack.isEmpty()) {
            System.out.println("BACKPACK:");
            for (int i = 0; i < backpack.size(); i++) {
                System.out.println("  I: " + i + ", " + backpack.get(i).toStringByTypeBackpack());
            }
            System.out.println();
        } else {
            System.out.println("Backpack is empty");
        }
    }

    /**
     * method to calculate the value of the backpack and the money in the player's inventory
     * @return
     */
    public static int countValue(){
        backpackValue = 0;
        for(Item item:backpack){
            backpackValue+= item.getPrice();
        }
        backpackValue = backpackValue + Player.getMoney();
        return backpackValue;
    }

    public static ArrayList<Item> getBackpack() {
        return backpack;
    }

    public static int getCurrentCapacity() {
        return currentCapacity;
    }

    public static void setCurrentCapacity(int currentCapacity) {
        Backpack.currentCapacity = currentCapacity;
    }

    public static int getBackpackValue() {
        return backpackValue;
    }

    public static void setBackpackValue(int backpackValue) {
        Backpack.backpackValue = backpackValue;
    }
}
