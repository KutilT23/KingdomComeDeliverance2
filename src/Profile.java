public class Profile extends Command {
    Backpack backpack = new Backpack();

    @Override
    public void execute() {
        printStats();
        /*
        System.out.println("STATS: \nSTRENGTH: " + Player.getStrength() + ", HEALTH: " + Player.getHealth() + ", MONEY: " + Player.getMoney()
                + "\nREPUTATION: " + Player.getReputation() + ", ELOQUENCE: " + Player.getEloquence());
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
                "    STRENGTH:    " + RED + Player.getStrength() + RESET,
                "    HEALTH:      " + GREEN + Player.getHealth() + RESET,
                "    MONEY:       " + YELLOW + Player.getMoney() + RESET,
                "    REPUTATION:  " + PURPLE + Player.getReputation() + RESET,
                "    TALK:        " + ORANGE + Player.getTalk() + RESET
        };

        System.out.println("-----------------------------------------------------------------------");
        System.out.println(BLUE + "BACKPACK:                                              STATS:" + RESET);
        System.out.println("-----------------------------------------------------------------------");

        int max = Math.max(Backpack.getBackpack().size(), stats.length);
        for (int i = 0; i < max; i++) {
            String leftPart = "";
            if (i < Backpack.getBackpack().size()) {
                leftPart = Backpack.getBackpack().get(i).toStringByType();
            }

            String rightPart;
            if (i < stats.length) {
                rightPart = stats[i];
            } else {
                rightPart = "";
            }



            System.out.printf("%-50s %s%n", leftPart, rightPart);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("CURRENT BACKPACK CAPACITY: " + RED + Backpack.getCurrentCapacity() + RESET);

        // Print equipped item
        System.out.println("EQUIPPED ITEM: ");
        showEquippedItem();
    }

    public void showEquippedItem() {
        boolean foundEquipped = false;

        for (Item item : Backpack.getBackpack()) {
            if (item.isInUse()) {
                System.out.print(item.toString5Test());
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
