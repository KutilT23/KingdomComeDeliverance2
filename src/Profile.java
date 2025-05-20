import java.util.ArrayList;

public class Profile extends Command {
    Backpack backpack = new Backpack();

    @Override
    public void execute() {
        printStats();
            /*
        System.out.println("STATS: \nSTRENGTH: " + Player.getStrength() + ", HEALTH: " + Player.getHealth() + ", MONEY: " + Player.getMoney()
                + "\nREPUTATION: " + Player.getReputation() + ", TALK: " + Player.getTalk());
        System.out.print("EQUIPPED ITEM: ");
        showEquippedItem();
        System.out.println("BACKPACK CAPACITY:" + Backpack.getCurrentCapacity());
        backpack.printBackpack();


             */
    }

    // ANSI color codes for console text styling
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

    public void printStats() {

        String[] stats = {
                "     STRENGTH:    " + RED + Player.getStrength() + RESET,
                "     HEALTH:      " + GREEN + Player.getHealth() + RESET,
                "     MONEY:       " + YELLOW + Player.getMoney() + RESET,
                "     REPUTATION:  " + PURPLE + Player.getReputation() + RESET
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

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(BLUE + "BACKPACK:                                                    STATS:" + RESET);
        System.out.println("-----------------------------------------------------------------------------");

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

            System.out.printf("%-56s%s%n", left, right);
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("CURRENT BACKPACK CAPACITY: " + RED + Backpack.getCurrentCapacity() + RESET);
        System.out.println("EQUIPPED ITEM: ");
        showEquippedItem();
    }

    public void showEquippedItem() {
        boolean foundEquipped = false;

        for (Item item : Backpack.getBackpack()) {
            if (item.isInUse()) {
                System.out.print(item.toStringItemUse());
                foundEquipped = true;
            }
        }
        if (!foundEquipped) {
            System.out.println("NO ITEM IS EQUIPPED");
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
