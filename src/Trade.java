import java.util.Scanner;

public class Trade extends Command {

    private boolean inTown = false;
    private Scanner sc = new Scanner(System.in);

    @Override
    public void execute() {
        checkIfInTown();
        if (isInTown()) {
            choice();
        } else {
            System.out.println("You are not in a town.");
        }
    }


    public void checkIfInTown() {
        String location = Map.getCurrentLocationName();
        if (!location.equalsIgnoreCase("Fishing Spot")
                && !location.equalsIgnoreCase("Meadow")
                && !location.equalsIgnoreCase("Forest")
                && !location.equalsIgnoreCase("Ruins")
                && !location.equalsIgnoreCase("Enemy Camp")
                && !location.equalsIgnoreCase("Ferry")
                && !location.equalsIgnoreCase("Road")) {
            setInTown(true);
        } else {
            setInTown(false);
        }
    }

    // Allows player to buy or sell items
    public void choice() {
        Shop shop = new Shop();
        Backpack backpack = new Backpack();

        System.out.println(">> SELL or BUY?");
        String answer = sc.next().toLowerCase();

        switch (answer) {
            case "sell":
                if (!Backpack.getBackpack().isEmpty()) {
                    backpack.printBackpack();
                    System.out.println("Which item do you want to sell? >> INDEX:");
                    String input = sc.next();
                    while (!input.matches("\\d+") || Integer.parseInt(input) >= Backpack.getBackpack().size()) {
                        System.out.println("Invalid input >> INDEX:");
                        input = sc.next();
                    }
                    int sellIndex = Integer.parseInt(input);

                    Player.setMoney(Player.getMoney() + Backpack.getBackpack().get(sellIndex).getPrice());
                    Backpack.setCurrentCapacity(Backpack.getCurrentCapacity() + Backpack.getBackpack().get(sellIndex).getWeight());

                    if (Backpack.getBackpack().get(sellIndex).isInUse()) {
                        Player.setStrength(10);  // Reset strength if used item sold
                    }
                    Backpack.getBackpack().remove(sellIndex);
                    backpack.printBackpack();
                    System.out.println("Money: " + Player.getMoney());
                } else {
                    System.out.println("You have nothing to sell.");
                }
                break;

            case "buy":
                if (Player.getMoney() > 0) {
                    System.out.println("Money: " + Player.getMoney());
                    shop.loadItems();
                    System.out.println("Which item do you want to buy? >> INDEX:");
                    String input = sc.next();
                    while (!input.matches("\\d+") || Integer.parseInt(input) >= Shop.getShopItems().size()) {
                        System.out.println("Invalid input >> INDEX:");
                        input = sc.next();
                    }
                    int buyIndex = Integer.parseInt(input);
                    Item itemToBuy = Shop.getShopItems().get(buyIndex);

                    if (Player.getMoney() >= itemToBuy.getPrice()) {
                        Player.setMoney(Player.getMoney() - itemToBuy.getPrice());
                        Backpack.setCurrentCapacity(Backpack.getCurrentCapacity() - itemToBuy.getWeight());
                        Backpack.getBackpack().add(itemToBuy);
                        backpack.printBackpack();
                        System.out.println("Money: " + Player.getMoney());
                    } else {
                        System.out.println("You don't have enough money.");
                    }
                } else {
                    System.out.println("You have no money.");
                }
                break;

            default:
                System.out.println("Invalid option.");
                choice();
        }
    }

    public boolean isInTown() {
        return inTown;
    }

    public void setInTown(boolean inTown) {
        this.inTown = inTown;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
