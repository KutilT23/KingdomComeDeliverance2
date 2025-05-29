package Commands;

import Additional.Region;
import World.Command;
import World.Map;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * class for fast traveling to a city
 */
public class FastTravel extends Command {

    private static TreeSet<String> citiesT = new TreeSet<>();
    private static TreeSet<String> citiesK = new TreeSet<>();
    private boolean hasCityT = false;
    private boolean hasCityK = false;
    Scanner sc = new Scanner(System.in);

    public static TreeSet<String> getCitiesT() {
        return citiesT;
    }

    public static TreeSet<String> getCitiesK() {
        return citiesK;
    }

    /**
     * method to wait for the player to travel to a city
     * @param name
     */
    public void travelWait(String name){
        System.out.print("Traveling to: " + name + " in ");
        for (int i = 0; i < 5; i++) {
            System.out.print( 5 - i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted while waiting.");
                return;
            }
        }
        System.out.println();
    }

    /**
     * method to fast travel to a city
     */
    public void fastTravel() {
        if (Map.getRegion().equals(Region.TROSKY)) {
            System.out.println("Which city do you want to travel to?: ");
            System.out.println(getCitiesT());
            String name = sc.next().toLowerCase();
            if (citiesT.contains(name)) {
                travelWait(name);
                switch (name) {
                    case "troskycastle":
                        Map.setCurrentPosition(13);
                        System.out.println("Moved to: " + Map.getCurrentLocationName());
                        break;
                    case "troskowitz":
                        Map.setCurrentPosition(36);
                        System.out.println("Moved to: " + Map.getCurrentLocationName());
                        break;
                    case "semine":
                        Map.setCurrentPosition(49);
                        System.out.println("Moved to: " + Map.getCurrentLocationName());
                        break;
                    default:
                        System.out.println("City not discovered");
                }
            } else {
                System.out.println("City not discovered");
            }
        }

        if (Map.getRegion().equals(Region.KUTTENBERG)) {
            if (citiesK.isEmpty()) {
                System.out.println("No city discovered in Kutnohorsko yet");
            } else {
                System.out.println("Which city do you want to travel to?: ");
                System.out.println(getCitiesK());
                String name = sc.next().toLowerCase();
                if (citiesK.contains(name)) {
                    travelWait(name);
                    switch (name) {
                        case "devilsden":
                            Map.setCurrentPosition(68);
                            System.out.println("Moved to: " + Map.getCurrentLocationName());
                            break;
                        case "suchdol":
                            Map.setCurrentPosition(98);
                            System.out.println("Moved to: " + Map.getCurrentLocationName());
                            break;
                        case "kuttenberg":
                            Map.setCurrentPosition(110);
                            System.out.println("Moved to: " + Map.getCurrentLocationName());
                            break;
                        default:
                            System.out.println("City not discovered");
                    }
                } else {
                    System.out.println("City not discovered");
                }
            }
        }
    }
    /**
     * method to run a command and fast travel to a city
     */
    @Override
    public void execute() {
        fastTravel();
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
