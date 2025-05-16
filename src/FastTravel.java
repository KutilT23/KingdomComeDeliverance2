import java.util.Scanner;
import java.util.TreeSet;

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

    public void fastTravel() {
        if (Map.getRegion().equals(Region.TROSECKO)) {
            System.out.println("Which city do you want to travel to?: ");
            System.out.println(getCitiesT());
            String name = sc.next().toLowerCase();
            if (citiesT.contains(name)) {
                switch (name) {
                    case "troskycastle":
                        Map.setCurrentPosition(13);
                        System.out.println("Moved to: " + Map.getCurrentLocationName());
                        break;
                    case "troskowitz":
                        Map.setCurrentPosition(36);
                        System.out.println("Moved to: " + Map.getCurrentLocationName());
                        break;
                    case "semin":
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

        if (Map.getRegion().equals(Region.KUTNOHORSKO)) {
            if (citiesK.isEmpty()) {
                System.out.println("No city discovered in Kutnohorsko yet");
            } else {
                System.out.println("Which city do you want to travel to?: ");
                System.out.println(getCitiesK());
                String name = sc.next().toLowerCase();
                if (citiesK.contains(name)) {
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

    @Override
    public void execute() {
        fastTravel();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
