package Commands;

import World.Command;
import World.Map;

/**
 * class for exiting the town
 */
public class Exit extends Command {
    /**
     * method to run a command and exit town
     */
    @Override
    public void execute() {
        Map map = new Map();
        if(Enter.isInsideTown()&& Map.getCurrentLocationName().equalsIgnoreCase("entrance")){
            System.out.println("Leaving town...");
            switch (Map.getRegion()) {
                case TROSKY:
                    map.loadTrosMap();
                    Map.setCurrentPosition(Enter.getTownLocation());
                    System.out.println("Your current position is: " + Map.getCurrentLocationName());
                    break;
                case KUTTENBERG:
                    map.loadKutMap();
                    Map.setCurrentPosition(Enter.getTownLocation());
                    System.out.println("Your current position is: " + Map.getCurrentLocationName());
                    break;
                default:
                    System.out.println("error: unknown region");
            }
            Enter.setInsideTown(false);
            Steal.setCitizensloaded(false);
        }else{
            System.out.println("You are not in a town exit");
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
}
