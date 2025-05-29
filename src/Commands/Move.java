package Commands;

import World.Command;
import World.Map;

import java.util.Scanner;

/**
 * class for moving the player to a new location
 */
public class Move extends Command {
    private Map worldMap = new Map();
    private Scanner scanner = new Scanner(System.in);
    /**
     * method to run a command and move the player
     */
    @Override
    public void execute() {
        movePlayer();
    }

    /**
     * method to move the player to a new location
     */
    public void movePlayer(){
        System.out.println("You are currently at location\uD83D\uDCCD\uD83D\uDDFA\uFE0F: " + Map.getCurrentLocationName());
        System.out.println("-------------------------------------");

        showPossibleDirections();

        System.out.println("\"Choose the direction you want to go\uD83E\uDDED:\"");
        String direction = scanner.next();
        direction = direction.toLowerCase();

        while (!direction.equals("n") && !direction.equals("s") && !direction.equals("w") && !direction.equals("e")) {
            System.out.println("Invalid direction\uD83E\uDDED");
            direction = scanner.next();
            direction = direction.toLowerCase();
        }

        System.out.println(worldMap.move(direction));
        Hunt.setAnimalsLoaded(false);
        Grab.setHerbsLoaded(false);
        Fight.setEnemiesLoaded(false);
    }

    /**
     * method to show the possible directions to move the player
     */
    public void showPossibleDirections() {
        System.out.println("n - North  ⬆\uFE0F: " + worldMap.locationMoveName("n") +
                "\ns - South  ⬇\uFE0F: " + worldMap.locationMoveName("s") +
                "\nw - West   ⬅\uFE0F: " + worldMap.locationMoveName("w") +
                "\ne - East   ➡\uFE0F: " + worldMap.locationMoveName("e"));
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
