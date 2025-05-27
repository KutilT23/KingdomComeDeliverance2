
import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private boolean exit = false;
    private HashMap<String, Command> commands = new HashMap<>();
    Map world = new Map();

    public void initialize() {
        commands.put("move", new Move());
        commands.put("end", new End());
        commands.put("transfer", new Transfer());
        commands.put("enter", new Enter());
        commands.put("travel", new FastTravel());
        commands.put("dice", new Dice());
        commands.put("hunt", new Hunt());
        commands.put("steal", new Steal());
        commands.put("fight", new Fight());
        commands.put("trade", new Trade());
        commands.put("profile", new Profile());
        commands.put("backpack", new Backpack());
        commands.put("harvest", new Grab());
        commands.put("exit", new Exit());
        commands.put("talk", new Talk());
    }

    private Scanner scanner = new Scanner(System.in);

    private void executeCommand() {
        System.out.println("TYPE WHAT YOU WANT TO DO:");
        String command = scanner.nextLine();
        command = command.trim().toLowerCase();
        if (commands.containsKey(command)) {
            System.out.println(">> ");
            commands.get(command).execute();
            exit = commands.get(command).exit();
        } else {
            System.out.println(">> Undefined command");
        }
    }

    public void start() {
        System.out.println("Welcome to Kingdom Come Deliverance 2");
        world.loadTrosMap();

        initialize();
        System.out.println("You are located at 📍🗺️: " + world.getCurrentLocation().getName().toUpperCase());
        do {
            executeCommand();
        } while (!exit);
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}

