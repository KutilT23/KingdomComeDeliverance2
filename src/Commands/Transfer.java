package Commands;

import Additional.Player;
import World.Command;
import World.Map;

import java.util.Scanner;

/**
 * class for transferring the player to another region
 */
public class Transfer extends Command {
    /**
     * method to run a command and transfer the player to another region
     */
    @Override
    public void execute() {
       transfer();

    }

    /**
     * method to transfer the player to another region
     */
    public void transfer(){
        Map world = new Map();
        Scanner sc = new Scanner(System.in);

        if (Map.getCurrentLocationName().equals("Ferryman")) {
            System.out.println("The price of the ferry is 200 groschen. Do you want to pay? (y/n)");
            String answer = sc.nextLine().trim().toLowerCase();

            while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                System.out.println("Not valid answer.");
                answer = sc.nextLine().trim().toLowerCase();
            }

            if (answer.equalsIgnoreCase("y")) {
                if (Player.getMoney() >= 200) {
                    Player.setMoney(Player.getMoney() - 200);
                    System.out.println("You paid 200 groschen to transfer to another region.");
                    System.out.print("Traveling to another region in  ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print( 5 - i + "...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Interrupted while waiting.");
                            return;
                        }
                    }
                    System.out.println();
                    switch (Map.getRegion()) {
                        case TROSKY:
                            System.out.println("Traveled from Trosecko to Kutnohorsko");
                            world.loadKutMap();
                            Map.setCurrentPosition(96);
                            break;

                        case KUTTENBERG:
                            System.out.println("Traveled from Kutnohorsko to Trosecko");
                            world.loadTrosMap();
                            Map.setCurrentPosition(54);
                            break;

                        default:
                            System.out.println("Error: Unknown region");
                    }
                } else {
                    System.out.println("You don't have enough groschen to pay for the ferry.");
                }
            } else {
                System.out.println("You declined to pay. Travel cancelled.");
            }
        } else {
            System.out.println("You are not at the ferryman's location.");
        }
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
