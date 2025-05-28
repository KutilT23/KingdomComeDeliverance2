
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
    public void initializeAdmin() {
        commands.put("mo", new Move());
        commands.put("tran", new Transfer());
        commands.put("ent", new Enter());
        commands.put("trav", new FastTravel());
        commands.put("di", new Dice());
        commands.put("hu", new Hunt());
        commands.put("st", new Steal());
        commands.put("fi", new Fight());
        commands.put("tra", new Trade());
        commands.put("prof", new Profile());
        commands.put("back", new Backpack());
        commands.put("harv", new Grab());
        commands.put("ex", new Exit());
        commands.put("ta", new Talk());
    }

    private Scanner scanner = new Scanner(System.in);
    private boolean adminLoaded = false;

    private void executeCommand() {

        System.out.println("TYPE WHAT YOU WANT TO DO:");
        String command = scanner.nextLine();
        command = command.trim().toLowerCase();
        if (commands.containsKey(command)) {
            System.out.println(">> ");
            commands.get(command).execute();
            exit = commands.get(command).exit();
        } else if (command.equals("admin")&&!adminLoaded) {
           initializeAdmin();
           adminLoaded = true;
            //System.out.println(getCommands());
        }else{
            System.out.println(">> Undefined command");
        }
    }

    public void start() {
        System.out.println("Welcome to Kingdom Come Deliverance 2");
        System.out.println("Audentes fortuna iuvat.");
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

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}

