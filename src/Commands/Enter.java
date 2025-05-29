package Commands;

import World.Command;
import World.Map;

/**
 * class for entering a town
 */
public class Enter extends Command {

    private static boolean insideTown = false;
    private static int townLocation = Map.getCurrentPosition();
    /**
     * method to run a command and enter a town
     */
    @Override
    public void execute() {

        Map map = new Map();
        Steal.checkIfInTown();
        if(Steal.isInTown()&&!Enter.isInsideTown()){
                System.out.println("Entering town...");
                townLocation = Map.getCurrentPosition();
                map.loadTown();
                Map.setCurrentPosition(4);
                insideTown = true;

        }else{
            System.out.println("You are not in a town entrance");
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

    public static boolean isInsideTown() {
        return insideTown;
    }

    public static void setInsideTown(boolean insideTown) {
        Enter.insideTown = insideTown;
    }

    public static int getTownLocation() {
        return townLocation;
    }

    public static void setTownLocation(int townLocation) {
        Enter.townLocation = townLocation;
    }
}
