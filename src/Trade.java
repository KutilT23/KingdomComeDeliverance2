import java.util.ArrayList;
import java.util.Scanner;

public class Trade extends Command {

    private Scanner sc = new Scanner(System.in);
    private ArrayList<Item>saleableItems  = new ArrayList<>();

    @Override
    public void execute() {
        if (Enter.isInsideTown()&&(Map.getCurrentLocationName().equalsIgnoreCase("herbalist")||
                Map.getCurrentLocationName().equalsIgnoreCase("merchant")||
                Map.getCurrentLocationName().equalsIgnoreCase("armorer")||
                Map.getCurrentLocationName().equalsIgnoreCase("hunter")||
                Map.getCurrentLocationName().equalsIgnoreCase("tavern")||
                Map.getCurrentLocationName().equalsIgnoreCase("potionmaker"))) {
            choice();
        } else {
            System.out.println("You are not in a shop.");
        }
    }

    public void choice() {
        Shop shop = new Shop();
        Backpack backpack = new Backpack();

        System.out.println(">> SELL, BUY or SHOW?");
        String answer = sc.next().toLowerCase();

        switch (answer) {
            case "sell":
                boolean isSaleable = false;
                saleableItems.clear();
                switch (Map.getCurrentLocationName().toLowerCase()){
                    case "herbalist":
                        for (Item item : Backpack.getBackpack()) {
                            if (item.getItemType() == ItemType.HERB) {
                                saleableItems.add(item);
                            }
                        }
                        isSaleable = true;
                        break;
                    case "merchant" :
                        for (Item item : Backpack.getBackpack()) {
                            if (item.getItemType() == ItemType.VALUABLE) {
                                saleableItems.add(item);
                            }
                        }
                        isSaleable = true;
                        break;
                    case "armorer" :
                        for (Item item : Backpack.getBackpack()) {
                            if(item.getItemType()==ItemType.WEAPON){
                                saleableItems.add(item);
                            }
                        }
                        isSaleable = true;
                        break;
                    case "hunter" :
                        for (Item item : Backpack.getBackpack()) {
                            if (item.getItemType() == ItemType.TROPHY) {
                                saleableItems.add(item);
                            }
                        }
                        isSaleable = true;
                        break;
                    case "potionmaker" :
                        for (Item item : Backpack.getBackpack()) {
                            if (item.getItemType() == ItemType.POTION) {
                                saleableItems.add(item);
                            }
                        }
                        isSaleable = true;
                        break;
                    case "tavern" :
                        isSaleable = false;
                        break;
                }

                if (!saleableItems.isEmpty()&&isSaleable) {
                    printSaleableItems();
                    System.out.println("Which item do you want to sell? >> INDEX:");
                    String input = sc.next();
                    while (!input.matches("\\d+") || Integer.parseInt(input) >= saleableItems.size()) {
                        System.out.println("Invalid input >> INDEX:");
                        input = sc.next();
                    }
                    int sellIndex = Integer.parseInt(input);
                    Item saleableItem = saleableItems.get(sellIndex);
                    if (saleableItem.isStolen()) {
                        Player.setReputation(Player.getReputation() - 5);
                        System.out.println("You sold a stolen item and lost 5 reputation points.");
                    } else {
                        Player.setReputation(Player.getReputation() + 5);
                        System.out.println("You sold an item and gained 5 reputation points.");
                    }

                    Player.setMoney(Player.getMoney() + saleableItem.getPrice());
                    Backpack.setCurrentCapacity(Backpack.getCurrentCapacity() + saleableItem.getWeight());

                    if (saleableItem.isInUse()) {
                        Player.setDamage(Player.getDamage() - saleableItem.getDamage());
                    }
                    System.out.println("You sold: " + saleableItem.getName() + " for " + saleableItem.getPrice() + " groschen." );
                    System.out.println("Your new balance: " + Player.getMoney());
                    Backpack.getBackpack().remove(saleableItem);


                } else {
                    System.out.println("Cannot sell items.");
                }
                break;

            case "buy":
                if (Player.getMoney() > 0&&Player.getReputation()>=40) {
                    System.out.println("Money: " + Player.getMoney());
                    String shopName = "";

                    switch (Map.getCurrentLocationName().toLowerCase()){
                        case "herbalist":
                            shopName = "Herbalist.txt";
                            break;
                        case "merchant" :
                            shopName = "Merchant.txt";
                            break;
                        case "armorer" :
                            shopName = "Armorer.txt";
                            break;
                        case "hunter" :
                            shopName = "Hunter.txt";
                            break;
                        case "potionmaker" :
                            shopName = "PotionMaker.txt";
                            break;
                        case "tavern" :
                            shopName = "Tavern.txt";
                            break;
                        default:
                            System.out.println("Error: Unknown shop.");
                            return;

                    }
                    shop.loadItems(shopName);
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

                        System.out.println("You bought an item and gained 5 reputation points.");
                        Player.setReputation(Player.getReputation()+5);
                        System.out.println("You bought: " +itemToBuy.getName() + " for " + itemToBuy.getPrice() + " groschen.");
                        System.out.println("Your new balance: " + Player.getMoney());
                        if(itemToBuy.getPrice()%2==0){
                            itemToBuy.setPrice(itemToBuy.getPrice()/2);
                        }
                        Backpack.getBackpack().add(itemToBuy);
                    } else {
                        System.out.println("You don't have enough money.");
                    }
                } else {
                    System.out.println("You have no money/bad reputation.");
                }
                break;
            case "show":
                String shopName = "";

                switch (Map.getCurrentLocationName().toLowerCase()) {
                    case "herbalist":
                        shopName = "Herbalist.txt";
                        break;
                    case "merchant":
                        shopName = "Merchant.txt";
                        break;
                    case "armorer":
                        shopName = "Armorer.txt";
                        break;
                    case "hunter":
                        shopName = "Hunter.txt";
                        break;
                    case "potionmaker":
                        shopName = "PotionMaker.txt";
                        break;
                    case "tavern":
                        shopName = "Tavern.txt";
                        break;
                    default:
                        System.out.println("Error: Unknown shop.");
                        return;
                }
                shop.loadItems(shopName);

                break;

            default:
                System.out.println("Invalid option.");
                choice();
        }
    }
    public void printSaleableItems(){
        if (!saleableItems.isEmpty()) {
            System.out.println("SALEABLE ITEMS:");
            for (int i = 0; i < saleableItems.size(); i++) {
                System.out.println("  I: " + i + ", " + saleableItems.get(i).toStringByTypeBackpack());
                }
        } else {
            System.out.println("Saleable items are empty.");
        }
    }

    public ArrayList<Item> getSaleableItems() {
        return saleableItems;
    }

    public void setSaleableItems(ArrayList<Item> saleableItems) {
        this.saleableItems = saleableItems;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
