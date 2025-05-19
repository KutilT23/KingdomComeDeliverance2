import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Steal extends Command{
    @Override
    public void execute() {
        loadCitizens();
        steal();
    }
    private static boolean citizensloaded = false;
    private ArrayList<Citizen> citizens = new ArrayList<>();
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    public void loadCitizens() {

        if (!citizensloaded) {
            citizens.clear();
            try (BufferedReader br = new BufferedReader(new FileReader("Citizens.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");

                    Citizen citizen = new Citizen(parts[0],
                            new Item(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),ItemType.valueOf(parts[4])), //herbs
                            new Item(parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[7]),ItemType.valueOf(parts[8])), // ring,necklace
                            new Item(parts[9], Integer.parseInt(parts[10]), Integer.parseInt(parts[11]),ItemType.valueOf(parts[12])), //food
                            new Item(parts[13], Integer.parseInt(parts[14]))); //money
                    citizens.add(citizen);
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
            citizensloaded = true;
            //displayCitizens();

        }
    }
    public void displayCitizens() {
        for (int i = 0; i < citizens.size(); i++) {
            System.out.println("CITIZEN: " + citizens.get(i).getName() + ",POCKETS: \n" +
                    citizens.get(i).getPockets().get(0).toString4Test() +
                    citizens.get(i).getPockets().get(1).toString4Test() +
                    citizens.get(i).getPockets().get(2).toString4Test() +
                    citizens.get(i).getPockets().get(3).toStringMoney());
        }
        System.out.println("");
    }
    public void displayCitizen(Citizen citizen) {
            System.out.println("CITIZEN: " + citizen.getName() + ",POCKETS: \n" +
                    citizen.getPockets().get(0).toString4Test() +
                    citizen.getPockets().get(1).toString4Test() +
                    citizen.getPockets().get(2).toString4Test() +
                    citizen.getPockets().get(3).toStringMoney());

        System.out.println("");
    }

    @Override
    public boolean exit() {
        return false;
    }
    public void steal() {
        int number = random.nextInt(citizens.size());
        Citizen citizen = citizens.get(number);
        displayCitizen(citizen);


    }

    public static boolean isCitizensloaded() {
        return citizensloaded;
    }

    public static void setCitizensloaded(boolean citizensloaded) {
        Steal.citizensloaded = citizensloaded;
    }
}
