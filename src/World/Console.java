package World;

import Commands.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * class for console
 */
public class Console {
    private boolean exit = false;
    private HashMap<String, Command> commands = new HashMap<>();
    Map world = new Map();

    /**
     * method to initialize the commands
     */
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

    /**
     * method to initialize the admin commands(for lazy people)
     */
    public void initializeAdmin() {
        commands.put("use",new Use()); //use item from a backpack
        commands.put("print",new Print()); //print backpack
        commands.put("sell",new Sell()); //sell item from a backpack
        commands.put("buy",new Buy()); //buy item from a shop
        commands.put("show",new Show()); //show all items in a shop

    }

    private Scanner scanner = new Scanner(System.in);
    private boolean adminLoaded = false;

    /**
     * method to execute the command
     */
    private void executeCommand() {

        System.out.println("TYPE WHAT YOU WANT TO DO:");
        String command = scanner.nextLine();
        command = command.trim().toLowerCase();
        if (commands.containsKey(command)) {
            System.out.println(">> ");
            commands.get(command).execute();
            exit = commands.get(command).exit();
        } else if (command.equals("admin")&&!adminLoaded) {
            System.out.println("Bonus commands loaded");
           initializeAdmin();
           adminLoaded = true;
            //System.out.println(getCommands());
        }else{
            System.out.println(">> Undefined command");
        }
    }

    /**
     * method to start the game
     */
    public void start() {
        System.out.println("Welcome to Kingdom Come Deliverance 2");
        System.out.println("Audentes fortuna iuvat.");
        world.loadTrosMap();

        initialize();
        System.out.println("You are located at 📍🗺️: " + Map.getCurrentLocationName().toUpperCase());
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

