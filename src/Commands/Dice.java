package Commands;

import Additional.Player;
import World.Command;
import World.Map;

import java.util.*;

/**
 * class for dice game
 */
public class Dice extends Command {

    private Random rd = new Random();
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Integer> dice = new ArrayList<>();
    private ArrayList<Integer> guessDice = new ArrayList<>();
    /**
     * method to run a command and dice game
     */
    @Override
    public void execute() {
        if(Enter.isInsideTown()&& Map.getCurrentLocationName().equalsIgnoreCase("tavern")){
            dice();
        }else{
            System.out.println("You are not in a tavern");
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

    /**
     * method to play a dice game
     */
    public void dice(){
        if(Player.getMoney()>0){
            System.out.println("How much money do you want to bet? ");
            System.out.println("You have " + Player.getMoney() + " groschen");
            String bet = sc.next();
            while (!bet.matches("\\d+") || Integer.parseInt(bet) > Player.getMoney() || Integer.parseInt(bet) < 1) {
                System.out.println("Invalid input >> INDEX:");
                bet = sc.next();
            }
            int betMoney = Integer.parseInt(bet);
            int aiBetMoney = rd.nextInt(betMoney)+1;
            dice.clear();
            guessDice.clear();
            System.out.println("Take a guess which dice you rolled: ");
            for (int i = 0; i < 6; i++) {
                int diceRoll = rd.nextInt(6) + 1;
                dice.add(diceRoll);
                System.out.print((i + 1) + ": ");
                String guess = sc.next();
                while (!guess.matches("\\d+") || Integer.parseInt(guess) > 6 || Integer.parseInt(guess) < 1) {
                    System.out.println("Invalid input >> INDEX:");
                    guess = sc.next();
                }
                int guessIndex = Integer.parseInt(guess);
                guessDice.add(guessIndex);

            }
            int correctPlayerGuesses = compareDice(guessDice, dice);
            System.out.println(guessDice);
            System.out.println(dice);
            System.out.println("AI turn");
            dice.clear();
            guessDice.clear();
            for (int i = 0; i < 6; i++) {
                int diceRoll = rd.nextInt(6) + 1;
                dice.add(diceRoll);
                int guessAIDice = rd.nextInt(6) + 1;
                guessDice.add(guessAIDice);
            }
            int correctAIGuesses = compareDice(guessDice, dice);
            System.out.println(guessDice);
            System.out.println(dice);

            System.out.println("Correct guesses: " + correctPlayerGuesses);
            System.out.println("Correct AI guesses: " + correctAIGuesses);

            if (correctPlayerGuesses > correctAIGuesses) {
                System.out.println("🎉 You won! " + aiBetMoney + " groschen");
                Player.setMoney(Player.getMoney() + aiBetMoney);
            } else if (correctPlayerGuesses < correctAIGuesses) {
                System.out.println("💻 AI won! Better luck next time! You lost " + betMoney + " groschen");
                Player.setMoney(Player.getMoney() - betMoney);
            } else {
                System.out.println("🤝 Draw!");
            }
        }else{
            System.out.println("You don't have enough money to bet!");
        }



    }

    /**
     * method to compare the guess dice with the real dice
     * @param guessDice
     * @param dice
     * @return
     */
    public int compareDice(ArrayList<Integer> guessDice, ArrayList<Integer> dice){
    ArrayList<Integer> copyDice = new ArrayList<>(dice);
    int correctGuesses = 0;
    for(Integer guess : guessDice){
        if(copyDice.contains(guess)){
            correctGuesses++;
            copyDice.remove(guess);
        }
    }
    return correctGuesses;
    }
}
