import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hunt extends Command {
    @Override
    public void execute() {
        if (Map.getCurrentLocationName().equals("HuntingSpot")) {
            loadAnimals();
            hunt();
        } else {
            System.out.println("You are not in a hunting ground");
        }
    }

    @Override
    public boolean exit() {
        return false;
    }

    private Random random = new Random();
    private ArrayList<Animal> huntingList = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    Backpack backpack = new Backpack();

    private static boolean animalsLoaded = false;

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
            selectAnimal();
            animalsLoaded = true;
        }
    }

    public void displayAllAnimalLoot() {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("ANIMAL: " + animals.get(i).getName() + ",HP: " + animals.get(i).getHealth() +
                    ",LOOT: " + animals.get(i).getLoot().get(0).toStringTrophy() +
                    animals.get(i).getLoot().get(1).toStringTrophy() +
                    animals.get(i).getLoot().get(2).toStringTrophy());
        }
        System.out.println("");
    }

    public void displayHuntLoot() {
        for (int i = 0; i < huntingList.size(); i++) {
            System.out.println("ANIMAL: " + huntingList.get(i).getName() + ",HP: " + huntingList.get(i).getHealth() +
                    ",LOOT: ");
            for (int j = 0; j < huntingList.get(i).getLoot().size(); j++) {

                switch (huntingList.get(i).getLoot().get(j).getItemType() ) {
                    case WEAPON:
                        System.out.print(huntingList.get(i).getLoot().get(j).toStringWeaponAdv());
                        break;
                    case POTION:
                        System.out.print(huntingList.get(i).getLoot().get(j).toStringHealingAdv());
                        break;
                    case TROPHY:
                        System.out.print(huntingList.get(i).getLoot().get(j).toStringTrophyS());

                        break;
                    default:
                        System.out.println("Error");
                }
            }
        }
        System.out.println("");
    }

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

    public void hunt() {
        if (!huntingList.isEmpty()) {
            int index = random.nextInt(huntingList.size());
            Animal target = huntingList.get(index);
            displayAnimal(target);

            if (Player.getStrength() * 10 > target.getHealth()) {
                System.out.println("You hunted: " + target.getName());
                backpack.addItem(target.getLoot().get(random.nextInt(3)));
                huntingList.remove(index);
            } else {
                System.out.println("You lost: " + target.getName());
                huntingList.remove(index);
            }
        } else {
            System.out.println("No animals left in the hunting spot");
        }
    }
    public void displayAnimal(Animal target){
        System.out.println("ANIMAL: " + target.getName() + ",HP: " + target.getHealth() +
                ",LOOT: ");
        for (int i = 0; i < target.getLoot().size(); i++) {
            switch (target.getLoot().get(i).getItemType() ) {
                case WEAPON:
                    System.out.print("  I: " + i + ", " + target.getLoot().get(i).toStringWeaponAdv());
                    break;
                case POTION:
                    System.out.print("  I: " + i + ", " + target.getLoot().get(i).toStringHealingAdv());
                    break;
                case TROPHY:
                    System.out.print("  I: " + i + ", " + target.getLoot().get(i).toStringTrophyS());

                    break;
                default:
                    System.out.println("Error");
            }
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
