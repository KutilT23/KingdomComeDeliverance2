package Commands;

import World.Command;
import World.Console;

import java.util.Set;

/**
 * class for printing commands
 */
public class Help extends Command {
    /**
     * method to run a command and print usable commands
     */
    @Override
    public void execute() {
        Set<String> keys = Console.getCommands().keySet();
        int count = 0;
        for (String key : keys) {
            System.out.print(key + ", ");
            count++;

            if (count % 4 == 0) {
                System.out.println();
            }
        }
        System.out.println();

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
