package Commands;

import World.Command;

/**
 * class for ending the game
 */
public class End extends Command {
    /**
     * method to run a command and The END message
     */
    @Override
    public void execute() {
        System.out.println("END...");
    }
    /**
     * method to terminate the program if the return value is true
     * @return
     */
    @Override
    public boolean exit() {
        return true;
    }
}
