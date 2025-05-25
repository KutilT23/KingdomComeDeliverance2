import java.util.Scanner;

public class Trade extends Command {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void execute() {
        if (Enter.isInsideTown()) {
            choice();
        } else {
            System.out.println("You are not in a town.");
        }
    }

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
                    if(Backpack.getBackpack().get(sellIndex).isStolen()){
                        Player.setReputation(Player.getReputation()-5);
                        System.out.println("You sold a stolen item and lost 5 reputation points.");
                    }else{
                        Player.setReputation(Player.getReputation()+5);
                        System.out.println("You sold an item and gained 5 reputation points.");
                    }
                    Player.setMoney(Player.getMoney() + Backpack.getBackpack().get(sellIndex).getPrice());
                    Backpack.setCurrentCapacity(Backpack.getCurrentCapacity() + Backpack.getBackpack().get(sellIndex).getWeight());

                    if (Backpack.getBackpack().get(sellIndex).isInUse()) {
                        Player.setDamage(Player.getDamage()-Backpack.getBackpack().get(sellIndex).getDamage());
                    }
                    System.out.println("You gained " + Backpack.getBackpack().get(sellIndex).getPrice() + " gold coins.");
                    System.out.println("Your new balance: " + Player.getMoney());
                    Backpack.getBackpack().remove(sellIndex);


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
                        System.out.println("You bought an item and gained 5 reputation points.");
                        Player.setReputation(Player.getReputation()+5);
                        System.out.println("You lost " + itemToBuy.getPrice() + " gold coins.");
                        System.out.println("Your new balance: " + Player.getMoney());
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

    @Override
    public boolean exit() {
        return false;
    }
}
