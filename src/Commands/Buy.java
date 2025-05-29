package Commands;

import World.Command;

/**
 * class for buying items from the store
 */
public class Buy extends Command {
    /**
     * method to run a command and buy items from the store
     */
    @Override
    public void execute() {
        Trade trade = new Trade();
        trade.buy();
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
