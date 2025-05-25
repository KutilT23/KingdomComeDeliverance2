import java.util.ArrayList;
import java.util.Scanner;

public class Backpack extends Command {

    private static ArrayList<Item> backpack = new ArrayList<>();
    private static int capacity = 30 + Player.getStrength() * 5;
    private static int currentCapacity = capacity;
    Scanner sc = new Scanner(System.in);

    @Override
    public void execute() {
        chooseOption();
        System.out.println("CURRENT CAPACITY: " + getCurrentCapacity());
    }

    @Override
    public boolean exit() {
        return false;
    }

    public void chooseOption() {
        System.out.println(">> PRINT, USE");
        String answer = sc.next();
        answer = answer.toLowerCase();
        switch (answer) {
            case "print":
                printBackpack();
                break;
            case "use":
                if (!backpack.isEmpty()) {
                    printBackpack();
                    int index = 0;
                    String indexInput = sc.next();
                    while (!indexInput.matches("[0-9]+") || Integer.parseInt(indexInput) >= Backpack.getBackpack().size()) {
                        System.out.println("Invalid input >> INDEX:");
                        indexInput = sc.next();
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
                                    System.out.println(item.toStringItemUse());
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
                        case TROPHY:
                            System.out.println("Cannot be used");
                            break;
                        default:
                            System.out.println("Error");
                    }
                } else {
                    System.out.println("NOTHING TO USE");
                }
                break;
            default:
                System.out.println("Invalid option");
                chooseOption();
        }
    }

    public void addItem(Item item) {
        if (getCurrentCapacity() - item.getWeight() >= 0) {
            backpack.add(item);
            System.out.println("Item: " + item.getName() + " added to backpack");
            setCurrentCapacity(getCurrentCapacity() - item.getWeight());
        } else {
            System.out.println("Backpack is full");
        }
    }

    public void printBackpack() {
        if (!backpack.isEmpty()) {
            System.out.println("BACKPACK:");
            for (int i = 0; i < backpack.size(); i++) {
                switch (backpack.get(i).getItemType()) {
                    case WEAPON:
                        System.out.print("  I: " + i + ", " + backpack.get(i).toStringWeaponAdv());
                        break;
                    case POTION:
                        System.out.print("  I: " + i + ", " + backpack.get(i).toStringHealingAdv());
                        break;
                    case TROPHY:
                        if (backpack.get(i).isStolen()) {
                            System.out.print("  I: " + i + ", " + backpack.get(i).toStringTrophyAdv());
                        }else{
                            System.out.print("  I: " + i + ", " + backpack.get(i).toStringTrophyS());
                        }
                        break;
                    default:
                        System.out.println("Error");
                }
            }
            System.out.println("");
        } else {
            System.out.println("Backpack is empty");
        }
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
}
