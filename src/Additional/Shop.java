package Additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * class for setting up the shop and loading items from the file
 */
public class Shop {
    private static ArrayList<Item> shopItems = new ArrayList<>();

    /**
     * method to load items from the file and sort them by price
     * @param shop
     */
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

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
        Collections.sort(shopItems);


        displayItems();
    }

    public static ArrayList<Item> getShopItems() {
        return shopItems;
    }

    /**
     * method to display all items loaded from the file
     */
    public void displayItems() {
        for (int i = 0; i < shopItems.size(); i++) {
                System.out.println("I: " + i + ", " + shopItems.get(i).toStringByType());
        }
        System.out.println("");
    }
}
