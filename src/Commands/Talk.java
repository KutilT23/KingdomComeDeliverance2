package Commands;

import Additional.Citizen;
import Additional.Player;
import World.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * class for talking to citizens
 */
public class Talk extends Command {

    private boolean citizensTextLoaded = false;
    private ArrayList<Citizen> citizens = new ArrayList<>();

    /**
     * method to load all citizens from the file
     */
    public void loadCitizens() {

        if (!citizensTextLoaded) {
            citizens.clear();
            try (BufferedReader br = new BufferedReader(new FileReader("Citizens.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");

                    Citizen citizen = new Citizen(parts[0],parts[15]);
                    citizens.add(citizen);
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
            citizensTextLoaded = true;
            //displayCitizens();

        }
    }

    /**
     * method to talk to a random citizen and ask him some questions
     */
    public void talk(){
        if(!citizens.isEmpty()){
            Random rd = new Random();
            Scanner sc = new Scanner(System.in);
            int randomText = rd.nextInt(citizens.size());
            System.out.println("You talk to: " + citizens.get(randomText).getName());
            System.out.println("What do you want to ask him?: >> " +
                    "\n1)Where can i buy and sell my items?, " +
                    "\n2)How can i get rich fast?, " +
                    "\n3)What are all those different locations on the map for?" );
            String answer = sc.nextLine().trim();
            while (!answer.matches("\\d+") || Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > 3) {
                System.out.println("Invalid input >> NUMBER:");
                answer = sc.nextLine().trim();
            }
            int chosen = Integer.parseInt(answer);

            switch (citizens.get(randomText).getText()){
                case "Aye, I know of it, and I shall tell thee.",
                     "I have heard the tidings, and can tell thee.",
                     "That I do know, and will speak plain to thee.":
                    switch (chosen){
                        case 1:
                            System.out.println("You can buy and sell items in towns like this," +
                                    " but each shop sells and buys something different, for example:" +
                                    "\nTavern - Sells: Alcohol(Healing), Buys: Nothing" +
                                    "\nArmorer - Sells: Weapons, Buys: Weapons" +
                                    "\nHunter - Sells: Animal drops, Buys: Animal drops" +
                                    "\nMerchant - Sells: Food,Valuables, Buys: Food,Valuables" +
                                    "\nHerbalist - Sells: Herbs, Buys: Herbs" +
                                    "\nPotionMaker - Sells: Potions(Healing), Buys: (Healing) items");
                            break;
                        case 2:
                            System.out.println("You can get rich fast by " +
                                    "stealing from citizens(risk of being caught), playing dice in Tavern(gamble)" +
                                    " or farm your way trough with fighting enemies, hunting animals " +
                                    "and harvesting herbs.");
                            break;
                        case 3:
                            System.out.println("In different locations you can do different actions, for example:" +
                                    "\nRuins,Meadow,Forest.HuntingSpot - harvesting herbs" +
                                    "\nEnemysCamp - fight enemies" +
                                    "\nPathway - no action here, just guides your way to towns or Ferryman" +
                                    "\nHuntingSpot - hunting animals" +
                                    "\nAnd then there are towns which when you enter, you can sell your items" +
                                    " buy something, talk to citizens and more...");
                            break;
                        default:
                            System.out.println("Invalid number.");
                    }
                    Player.setTalkXP(Player.getTalkXP()+3);
                    System.out.println("Talking xp + 3, Current talk xp: " + Player.getTalkXP());
                    Player.levelUp();
                    break;
                case "Naught do I know, yet I wish thee well upon thy way.",
                     "I wot not, good sir, but God grant thee fair fortune.":

                    Player.setTalkXP(Player.getTalkXP()+1);
                    System.out.println(citizens.get(randomText).getName() + " says: " + citizens.get(randomText).getText());
                    System.out.println("Citizen does not know the answer.");
                    System.out.println("Talking xp + 1, Current talk xp: " + Player.getTalkXP());
                    Player.levelUp();
                    break;
                case "Mind thy own business and be gone.",
                     "Be gone, meddler, and trouble me no more.":
                    System.out.println(citizens.get(randomText).getName() + " says: " + citizens.get(randomText).getText());
                    System.out.println("Citizen does not want to talk to you.");
                    break;

            }
        }else{
            System.out.println("No one to talk to.");
        }

    }
    /**
     * method to run a command and set up citizens and talk to them
     */
    @Override
    public void execute() {
        if(Enter.isInsideTown()){
            loadCitizens();
            talk();
        }else{
            System.out.println("You are not in a town.");
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

