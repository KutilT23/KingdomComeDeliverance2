import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Obchod {
    private static ArrayList<Predmet> obchod = new ArrayList<>();
    private static boolean predmetyNacteny = false;

    public void nacistItemy(){
        try (BufferedReader br = new BufferedReader(new FileReader("Obchod.txt"))) {
            String radek;

            while ((radek = br.readLine()) != null&&!predmetyNacteny) {
                String[] lines = radek.split(";");

                Predmet predmet = new Predmet(lines[0],Integer.parseInt(lines[1]),Integer.parseInt(lines[2]),Integer.parseInt(lines[3]),Integer.parseInt(lines[4]),TypPredmetu.valueOf(lines[5]));
                obchod.add(predmet);

            }
            predmetyNacteny = true;


        } catch (IOException e) {
            System.out.println("Chyba při práci se souborem: " + e.getMessage());
        }
        Collections.sort(obchod);
        getPredmety();
    }

    public static ArrayList<Predmet> getObchod() {
        return obchod;
    }
    public void getPredmety(){
        for (int i = 0; i < obchod.size(); i++) {
            switch (obchod.get(i).getTypPredmetu()){
                case ZBRAN:
                    System.out.print("I: " + i +", " +  obchod.get(i).toString2Test());
                    break;
                case LEKTVAR:
                    System.out.print("I: " + i + ", " + obchod.get(i).toString3Test());
                    break;
                default:
                    System.out.println("Chyba");
            }
        }
        System.out.println("");
    }
}
