import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Steal extends Command{
    @Override
    public void execute() {
        if (Enter.isInsideTown()) {
            loadCitizens();
            steal();
        } else {
            System.out.println("You are not in a town.");
        }

    }
    public static void checkIfInTown() {
        String location = Map.getCurrentLocationName();
        if (!location.equalsIgnoreCase("HuntingSpot")
                && !location.equalsIgnoreCase("Meadow")
                && !location.equalsIgnoreCase("Forest")
                && !location.equalsIgnoreCase("Ruins")
                && !location.equalsIgnoreCase("EnemyCamp")
                && !location.equalsIgnoreCase("Ferryman")
                && !location.equalsIgnoreCase("Pathway")) {
            setInTown(true);
        } else {
            setInTown(false);
        }
    }
    private static boolean citizensloaded = false;
    private ArrayList<Citizen> citizens = new ArrayList<>();
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    private Backpack backpack = new Backpack();
    private static boolean inTown = false;
    public void loadCitizens() {

        if (!citizensloaded) {
            citizens.clear();
            try (BufferedReader br = new BufferedReader(new FileReader("Citizens.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");

                    Citizen citizen = new Citizen(parts[0],
                            new Item(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),ItemType.valueOf(parts[4])), //herbs
                            new Item(parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[7]),ItemType.valueOf(parts[8])), // ring,necklace
                            new Item(parts[9], Integer.parseInt(parts[10]), Integer.parseInt(parts[11]),ItemType.valueOf(parts[12])), //food
                            new Item(parts[13], Integer.parseInt(parts[14]))); //money
                    citizens.add(citizen);
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
            citizensloaded = true;
            //displayCitizens();

        }
    }
    public void displayCitizens() {
        for (int i = 0; i < citizens.size(); i++) {
            System.out.println("ANIMAL: " + citizens.get(i).getName() + ",DROP: ");
            for (int j = 0; j < citizens.get(i).getPockets().size(); j++) {
                if (citizens.get(i).getPockets().get(j).getItemType() == null) {
                    System.out.println(citizens.get(i).getPockets().get(j).toStringMoney());
                }else{
                    switch (citizens.get(i).getPockets().get(j).getItemType() ) {
                        case WEAPON:
                            System.out.print(citizens.get(i).getPockets().get(j).toStringWeaponAdv());
                            break;
                        case POTION:
                            System.out.print(citizens.get(i).getPockets().get(j).toStringHealingAdv());
                            break;
                        case TROPHY,VALUABLE,HERB:
                            System.out.print(citizens.get(i).getPockets().get(j).toStringTrophyS());

                            break;
                        default:
                            System.out.println("Error");
                    }
                }

            }
        }
        System.out.println("");
    }
    public void printPockets(Citizen citizen){
        System.out.println("You are stealing from: " + citizen.getName() + ", \nPOCKETS:");
            for (int i = 0; i < citizen.getPockets().size(); i++) {
                if (citizen.getPockets().get(i).getItemType() == null) {
                    System.out.println("  I: " + i + ", " + citizen.getPockets().get(i).toStringMoney());
                } else {
                    switch (citizen.getPockets().get(i).getItemType()) {
                        case WEAPON:
                            System.out.print("  I: " + i + ", " + citizen.getPockets().get(i).toStringWeaponAdv());
                            break;
                        case POTION:
                            System.out.print("  I: " + i + ", " + citizen.getPockets().get(i).toStringHealingAdv());
                            break;
                        case TROPHY,VALUABLE,HERB:
                            System.out.print("  I: " + i + ", " + citizen.getPockets().get(i).toStringTrophyS());
                            break;
                        default:
                            System.out.println("  I: " + i + ", Unknown item type");
                    }
                }
            }
            System.out.println("");
    }
    @Override
    public boolean exit() {
        return false;
    }
    public boolean guard() {
        int chance = random.nextInt(100);
        int charisma = chance + Player.getReputation() / 5;
       // System.out.println("Chance: " + chance + ", Reputation bonus: " + Player.getReputation() / 5);
        //System.out.println("Charisma: " + charisma);

        if (charisma < 50) {
            System.out.println("You were caught by a guard while trying to rob a citizen.");

            boolean validChoice = false;
            boolean haveStolen = false;

            while (!validChoice) {
                System.out.println("What are you gonna do? >> \n talk - try to talk your way out of the situation \n search - let him search your backpack");

                String answer = sc.next().toLowerCase();

                switch (answer) {
                    case "talk":
                        validChoice = true;
                        int talkNeed;
                        switch (Map.getRegion()) {
                            case TROSECKO:
                                talkNeed = 5;
                                break;
                            case KUTNOHORSKO:
                                talkNeed = 7;
                                break;
                            default:
                                System.out.println("Error: Unknown region.");
                                return false;
                        }

                        if (Player.getTalk() >= talkNeed) {
                            System.out.println("You talked to the guard and he let you through.");
                            return true;
                        } else {
                            System.out.println("Your talk skill is too low.");
                            ArrayList<Item> toRemove = new ArrayList<>();
                            for (Item item : Backpack.getBackpack()) {
                                if (item.isStolen()) {
                                    toRemove.add(item);
                                    haveStolen = true;
                                }
                            }
                            Backpack.getBackpack().removeAll(toRemove);

                            if (haveStolen) {
                                System.out.println("Guard took all your stolen items + you lost 10 reputation points.");
                                Player.setReputation(Player.getReputation() - 10);
                            } else {
                                System.out.println("You are lucky this time. Guard didn't find anything stolen.");
                            }
                            return false;
                        }

                    case "search":
                        validChoice = true;
                        ArrayList<Item> stolenItems = new ArrayList<>();
                        for (Item item : Backpack.getBackpack()) {
                            if (item.isStolen()) {
                                stolenItems.add(item);
                                haveStolen = true;
                            }
                        }

                        Backpack.getBackpack().removeAll(stolenItems);

                        if (haveStolen) {
                            System.out.println("Guard found stolen items! He took them and you lost 10 reputation points.");
                            Player.setReputation(Player.getReputation() - 10);
                            return false;
                        } else {
                            System.out.println("Guard searched your backpack and found nothing. You are free to go.");
                            return true;
                        }

                    default:
                        System.out.println("Invalid choice. Type either talk or search.");
                }
            }
        }
        return true;
    }
    public void steal() {

        if (!citizens.isEmpty()) {

        int number = random.nextInt(citizens.size());
        Citizen citizen = citizens.get(number);
            if(citizen.getPockets().isEmpty()){
                citizens.remove(citizen);
            }
        printPockets(citizen);
        System.out.println("What do you want to steal?");
        String input = sc.next();
            while (!input.matches("\\d+") || Integer.parseInt(input) >= citizen.getPockets().size()) {
            System.out.println("Invalid input >> INDEX:");
            input = sc.next();
            }
            int stealIndex = Integer.parseInt(input);

            if (citizen.getPockets().get(stealIndex).getItemType() == null) {
            Player.setMoney(Player.getMoney()+ citizen.getPockets().get(stealIndex).getPrice());
            System.out.println("You stole " + citizen.getPockets().get(stealIndex).getPrice() + " groschen");
            System.out.println("Your new balance: " + Player.getMoney());
            }else{
            citizen.getPockets().get(stealIndex).setStolen(true);
            backpack.addItem(citizen.getPockets().get(stealIndex));

            }
            citizen.getPockets().remove(stealIndex);

            if (citizen.getPockets().isEmpty()) {
                citizens.remove(citizen);
            }
            if(!guard()){
                System.out.println("Guard caught you.");
            }

        }else{
            System.out.println("There are no citizens in the town.");
        }

    }

    public static boolean isCitizensloaded() {
        return citizensloaded;
    }

    public static void setCitizensloaded(boolean citizensloaded) {
        Steal.citizensloaded = citizensloaded;
    }

    public static boolean isInTown() {
        return inTown;
    }

    public static void setInTown(boolean inTown) {
        Steal.inTown = inTown;
    }
}
