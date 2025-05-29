package Commands;

import World.Command;

/**
 * class for selling items
 */
public class Sell extends Command {
    /**
     * method to run a command and sell items
     */
    @Override
    public void execute() {
        Trade trade = new Trade();
        trade.sell();
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
