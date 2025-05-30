package Commands;

import Additional.Item;
import Additional.Player;
import World.Command;
import World.Map;

import java.util.ArrayList;

/**
 * class for printing the player profile
 */
public class Profile extends Command {
    Backpack backpack = new Backpack();
    /**
     * method to run a command and print the player profile
     */
    @Override
    public void execute() {
        printStats();
    }

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
    String MAGENTA = "\u001B[35m";

    /**
     * method to print the player stats.
     * used the internet to find out how printf works and how to
     */
    public void printStats() {

        String[] stats = {
                "     STRENGTH:    " + RED + Player.getStrength() + RESET + ", XP: " + RED + Player.getStrengthXP() + RESET,
                "     DAMAGE:      " + MAGENTA + Player.getDamage() + RESET,
                "     HEALTH:      " + GREEN + Player.getHealth() + RESET,
                "     GROSCHEN:    " + YELLOW + Player.getMoney() + RESET,
                "     REPUTATION:  " + PURPLE + Player.getReputation() + RESET,
                "     TALK:        " + ORANGE + Player.getTalk() + RESET+ ", XP:  " + ORANGE + Player.getTalkXP() + RESET

        };

        ArrayList<Item> backpack = Backpack.getBackpack();
        ArrayList<String> leftColumn = new ArrayList<>();
        ArrayList<String> printedItems = new ArrayList<>();

        for (int i = 0; i < backpack.size(); i++) {
            Item currentItem = backpack.get(i);

            String emoji = "";
            if (currentItem.isStolen()) {
                emoji = "\uD83D\uDD90\uFE0F ";
            }else{
                emoji = "   ";
            }

            String itemStr = emoji + currentItem.toStringByType();

            if (!printedItems.contains(itemStr)) {
                int count = 1;


                for (int j = i + 1; j < backpack.size(); j++) {
                    Item compareItem = backpack.get(j);
                    boolean sameType = compareItem.toStringByType().equals(currentItem.toStringByType());
                    boolean sameStolenStatus = compareItem.isStolen() == currentItem.isStolen();

                    if (sameType && sameStolenStatus) {
                        count++;
                    }
                }

                leftColumn.add(count + "x " + itemStr);
                printedItems.add(itemStr);
            }
        }

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println(BLUE + "BACKPACK:                                                           STATS:" + RESET);
        System.out.println("-------------------------------------------------------------------------------------------");

        int max = Math.max(leftColumn.size(), stats.length);
        for (int i = 0; i < max; i++) {
            String left = "";
            if (i < leftColumn.size()) {
                left = leftColumn.get(i);
            }

            String right = "";
            if (i < stats.length) {
                right = stats[i];
            }

            System.out.printf("%-63s%s%n", left, right);
        }

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.print("CURRENT BACKPACK CAPACITY: " + ORANGE + Backpack.getCurrentCapacity() + RESET);
        System.out.print(", BACKPACK VALUE: " + YELLOW+ Backpack.countValue() + RESET + " GROSCHEN, ");
        System.out.println("REGION: " + PURPLE + Map.getRegion() + RESET);
        System.out.println("EQUIPPED ITEM: ");
        System.out.print("----> ");
        showEquippedItem();
    }

    /**
     * method to print the equipped item
     */
    public void showEquippedItem() {
        boolean foundEquipped = false;

        for (Item item : Backpack.getBackpack()) {
            if (item.isInUse()) {
                System.out.print(item.toStringByTypeBackpack());
                System.out.println("");
                foundEquipped = true;
            }
        }
        if (!foundEquipped) {
            System.out.println("NO ITEM IS EQUIPPED");
        }
    }
    /**
     * method to terminate the program if the return value is true
     * @return
     */
    @Override
    public boolean exit() {
        return false;
    }
}
