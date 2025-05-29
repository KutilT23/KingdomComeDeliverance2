package Commands;

import World.Command;

/**
 * class for using items in the backpack
 */
public class Use extends Command {
    /**
     * method to run a command
     */
    @Override
    public void execute() {
        Backpack backpack = new Backpack();
        backpack.use();
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
