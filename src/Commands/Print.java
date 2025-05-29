package Commands;

import World.Command;

/**
 * class for printing the backpack
 */
public class Print extends Command {
    /**
     * method to run a command and print the backpack
     */
    @Override
    public void execute() {
        Backpack backpack = new Backpack();
        backpack.printBackpack();
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
