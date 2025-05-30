package Commands;

import Additional.Item;
import Additional.ItemType;
import Additional.Player;
import World.Command;
import World.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * class for collecting herbs
 */
public class Grab extends Command {

    private Random random = new Random();
    private ArrayList<Item> herbs = new ArrayList<>();
    private ArrayList<Item> collected = new ArrayList<>();
    Backpack backpack = new Backpack();

    private static boolean herbsLoaded = false;
    /**
     * method to run a command and set up herbs and collect them
     */
    @Override
    public void execute() {
        loadHerbs();
        collect();
    }

    /**
     * method to load all herbs from the file
     */
    public void loadHerbs() {
        if (!herbsLoaded) {
            herbs.clear();
            try (BufferedReader br = new BufferedReader(new FileReader("Herbs.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");

                    Item item = new Item(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), ItemType.valueOf(parts[3]));
                    herbs.add(item);
                }

                herbsLoaded = true;

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
            selectHerbs();
        }
    }

    /**
     * method to display all herbs loaded from the file
     */
    public void displayAllHerbs() {
        for (int i = 0; i < herbs.size(); i++) {
            System.out.print(herbs.get(i).toStringByType());
        }
        System.out.println("");
    }

    /**
     * method to display all collectable herbs in the current location
     */
    public void displayCollectableHerbs() {
        for (int i = 0; i < collected.size(); i++) {
            System.out.println(collected.get(i).toStringByType());
        }
        System.out.println("");
    }

    /**
     * method to collect a random herb from the current location
     */
    public void collect() {
        if (!collected.isEmpty()) {
            int randomIndex = random.nextInt(collected.size());
            int sizeBefore = Backpack.getBackpack().size();
            backpack.addItem(collected.get(randomIndex));
            int sizeAfter = Backpack.getBackpack().size();

            if (sizeBefore != sizeAfter) {
                Player.setStrengthXP(Player.getStrengthXP() + 1);
                System.out.println("Strength xp + 1, Current strength xp: " + Player.getStrengthXP());
                Player.levelUp();
                collected.remove(randomIndex);
                System.out.println(Map.getCurrentLocationName().toUpperCase() + " contains: ");
                displayCollectableHerbs();
            } else {
                System.out.println("NO SPACE");
            }

        } else {
            System.out.println("NOTHING TO COLLECT");
        }
    }

    /**
     * method to select the herbs that can be collected in the current location
     */
    public void selectHerbs() {
        String herbName = "";

        switch (Map.getCurrentLocationName()) {
            case "Meadow":
                herbName = "Marigold";
                break;
            case "Forest":
                herbName = "Nettle";
                break;
            case "HuntingSpot":
                herbName = "Valerian";
                break;
            case "Ruins":
                herbName = "Mint";
                break;
            default:
                System.out.println("No herbs here");
                return;
        }

        collected.clear();
        for (Item item : herbs) {
            if (item.getName().equals(herbName)) {
                collected.add(item);
            }
        }

        displayCollectableHerbs();

    }

    public static boolean areHerbsLoaded() {
        return herbsLoaded;
    }

    public static void setHerbsLoaded(boolean loaded) {
        Grab.herbsLoaded = loaded;
    }

    public ArrayList<Item> getHerbs() {
        return herbs;
    }

    public ArrayList<Item> getCollected() {
        return collected;
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
