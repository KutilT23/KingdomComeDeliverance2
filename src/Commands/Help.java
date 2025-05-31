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
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[91m";
    public static final String GREEN = "\u001B[92m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    @Override
    public void execute() {
        System.out.println(
                PURPLE + "\n--- Anytime Commands ---" + RESET + "\n" +
                        GREEN + "backpack" + RESET + "   - Shows actions with your backpack.\n" +
                        GREEN + "profile" + RESET + "    - Display player's backpack and stats.\n" +
                        GREEN + "help" + RESET + "       - Display information about all commands.\n" +
                        GREEN + "end" + RESET + "        - Ends the game.\n" +
                        GREEN + "move" + RESET + "       - Moves the player to a different location (north, south, east, west).\n" +
                        GREEN + "travel" + RESET + "     - Fast travel to a known town.\n" +

                        PURPLE + "\n--- Town Entrance Command ---" + RESET + "\n" +
                        GREEN + "enter" + RESET + "      - Enter a town. Only usable when standing on a town entrance tile.\n" +
                        "             Example: Current location: TROSKOWITZ\n" +

                        PURPLE + "\n--- Town-Only Commands ---" + RESET + "\n" +
                        GREEN + "talk" + RESET + "       - Talk to NPCs.\n" +
                        GREEN + "dice" + RESET + "       - Play a dice game. Only in Tavern.\n" +
                        GREEN + "trade" + RESET + "      - Enters shop and player decides what to do. Only on shop locations.\n" +
                        "             Example: Current location: ARMORER\n" +
                        GREEN + "steal" + RESET + "      - Try to steal something from a citizen, at risk of being caught.\n" +
                        GREEN + "exit" + RESET + "       - Exit the town. Only at the Entrance inside the town.\n" +

                        PURPLE + "\n--- Special Location Commands ---" + RESET + "\n" +
                        GREEN + "harvest" + RESET + "    - Collect herbs in Meadow, Forest, Ruins, or Hunting spot.\n" +
                        GREEN + "hunt" + RESET + "       - Attempt to hunt animals in Hunting Spots.\n" +
                        GREEN + "fight" + RESET + "      - Fight enemies in Enemy Camps.\n" +
                        GREEN + "transfer" + RESET + "   - Transfer player to another region (Map). Only at Ferryman location.\n" +

                        PURPLE + "\n--- Admin-Only Commands ---" + RESET + "\n" +
                        GREEN + "show" + RESET + "       - Display merchant inventory. (Requires admin mode)\n" +
                        GREEN + "sell" + RESET + "       - Sell items from inventory for groschen. (Requires admin mode)\n" +
                        GREEN + "buy" + RESET + "        - Purchase items using available groschen. (Requires admin mode)\n" +
                        GREEN + "use" + RESET + "        - Use an item from your inventory. (Requires admin mode)\n" +
                        GREEN + "print" + RESET + "      - Display detailed contents of your backpack. (Requires admin mode)\n" +
                        "  These admin commands are quick commands for faster gameplay and easier management.\n" +

                        PURPLE + "\nNote:\n" + RESET +
                        "Type '" + RED + "admin" + RESET + "' once to unlock admin mode and enable these quick commands."
        );
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
