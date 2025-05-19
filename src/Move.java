import java.util.Scanner;

public class Move extends Command {
    private Map worldMap = new Map();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
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
        Steal.setCitizensloaded(false);
    }

    public void showPossibleDirections() {
        System.out.println("n - North  ⬆\uFE0F: " + worldMap.testMove("n") +
                "\ns - South  ⬇\uFE0F: " + worldMap.testMove("s") +
                "\nw - West   ⬅\uFE0F: " + worldMap.testMove("w") +
                "\ne - East   ➡\uFE0F: " + worldMap.testMove("e"));
    }

    @Override
    public boolean exit() {
        return false;
    }
}
