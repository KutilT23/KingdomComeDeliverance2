package Commands;

import World.Command;

/**
 * class for showing the items in the store
 */
public class Show extends Command {
    /**
     * method to run a command and show the items in the store
     */
    @Override
    public void execute() {
        Trade trade = new Trade();
         trade.show();
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
