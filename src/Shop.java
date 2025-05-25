import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Shop {
    private static ArrayList<Item> shopItems = new ArrayList<>();
    private static boolean itemsLoaded = false;

    public void loadItems(String shop) {
        shopItems.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(shop))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                Item item = new Item(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]),
                        ItemType.valueOf(parts[5])
                );
                shopItems.add(item);
            }
            itemsLoaded = true;

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
        Collections.sort(shopItems);


        displayItems();
    }

    public static ArrayList<Item> getShopItems() {
        return shopItems;
    }

    public void displayItems() {
        for (int i = 0; i < shopItems.size(); i++) {
            switch (shopItems.get(i).getItemType()) {
                case WEAPON:
                    System.out.print("I: " + i + ", " + shopItems.get(i).toStringWeaponAdv());
                    break;
                case POTION:
                    System.out.print("I: " + i + ", " + shopItems.get(i).toStringHealingAdv());
                    break;
                case TROPHY,VALUABLE:
                    System.out.print("I: " + i + ", " + shopItems.get(i).toStringTrophyS());
                    break;
                default:
                    System.out.println("Error: Unknown item type");
            }
        }
        System.out.println();
    }
}
