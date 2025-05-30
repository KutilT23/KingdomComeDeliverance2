package Commands;

import Additional.Animal;
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
 * class for hunting animals
 */
public class Hunt extends Command {
    /**
     * method to run a command and set up animals and hunt them
     */
    @Override
    public void execute() {
        if (Map.getCurrentLocationName().equals("HuntingSpot")) {
            loadAnimals();
            hunt();
        } else {
            System.out.println("You are not in a hunting ground");
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

    private Random random = new Random();
    private ArrayList<Animal> huntingList = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    Backpack backpack = new Backpack();

    private static boolean animalsLoaded = false;

    /**
     * method to load all animals from the file
     */
    public void loadAnimals() {
        if (!animalsLoaded) {
            animals.clear();
            try (BufferedReader br = new BufferedReader(new FileReader("Animals.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");

                    Animal animal = new Animal(
                            parts[0],
                            Integer.parseInt(parts[1]),
                            new Item(parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), ItemType.valueOf(parts[5])),
                            new Item(parts[6], Integer.parseInt(parts[7]), Integer.parseInt(parts[8]), ItemType.valueOf(parts[9])),
                            new Item(parts[10], Integer.parseInt(parts[11]), Integer.parseInt(parts[12]), ItemType.valueOf(parts[13]))
                    );
                    animals.add(animal);
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
            //displayAllAnimalLoot();
            selectAnimal();
            animalsLoaded = true;
        }
    }

    /**
     * method to display all animals loaded from the file and their loot
     */
    public void displayAllAnimalLoot() {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("ANIMAL: " + animals.get(i).getName() + ", LOOT: ");
            for (int j = 0; j < animals.get(i).getLoot().size(); j++) {
                    System.out.println(animals.get(i).getLoot().get(j).toStringByType());
            }
        }
        System.out.println("");
    }

    /**
     * method to select the animals that can be hunted in the current location
     */
    public void selectAnimal() {
        int choice = random.nextInt(2);
        huntingList.clear();

        switch (choice) {
            case 0:
                for (Animal animal : animals) {
                    if (animal.getName().equals("Rabbit")) {
                        huntingList.add(animal);
                    }
                }
                break;
            case 1:
                for (Animal animal : animals) {
                    if (animal.getName().equals("Deer")) {
                        huntingList.add(animal);
                    }
                }
                break;
            default:
                System.out.println("Error");
        }

        //displayHuntLoot();
    }

    /**
     * method to hunt an animal from the current location
     */
    public void hunt() {
        if (!huntingList.isEmpty()) {
            int index = random.nextInt(huntingList.size());
            Animal target = huntingList.get(index);
            displayAnimal(target);

            if (Player.getDamage() * 5 > target.getHealth()) {
                System.out.println("You hunted: " + target.getName());
                backpack.addItem(target.getLoot().get(random.nextInt(3)));
                Player.setStrengthXP(Player.getStrengthXP() + 2);
                System.out.println("Strength xp + 2, Current strength xp: " + Player.getStrengthXP());
                Player.levelUp();
                huntingList.remove(index);
            } else {
                System.out.println("You lost: " + target.getName());
                huntingList.remove(index);
            }
        } else {
            System.out.println("No animals left in the hunting spot");
        }
    }

    /**
     * method to display current hunted animal and its loot
     * @param target
     */
    public void displayAnimal(Animal target){
        System.out.println("ANIMAL: " + target.getName() + ",HP: " + target.getHealth() +
                ",LOOT: ");
        for (int i = 0; i < target.getLoot().size(); i++) {
            System.out.println("  I: " + i + ", " + target.getLoot().get(i).toStringByType());
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Animal> getHuntingList() {
        return huntingList;
    }

    public static boolean areAnimalsLoaded() {
        return animalsLoaded;
    }

    public static void setAnimalsLoaded(boolean loaded) {
        Hunt.animalsLoaded = loaded;
    }
}
