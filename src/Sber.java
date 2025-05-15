import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Sber extends Command {

    private Random rd = new Random();
    private ArrayList<Predmet>bylinky = new ArrayList<>();
    private ArrayList<Predmet>sbirani = new ArrayList<>();
    Batoh batoh = new Batoh();

    private static boolean kytkyNacteny = false;

    @Override
    public void execute() {
        nacistBylinky();
        sbirat();
    }
    public void nacistBylinky(){
        if(!kytkyNacteny){
        bylinky.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("Bylinky.txt"))) {
            String radek;

            while ((radek = br.readLine()) != null) {
                String[] lines = radek.split(";");

                Predmet predmet = new Predmet(lines[0],Integer.parseInt(lines[1]),Integer.parseInt(lines[2]),TypPredmetu.valueOf(lines[3]));
                bylinky.add(predmet);

            }
            kytkyNacteny = true;


        } catch (IOException e) {
            System.out.println("Chyba při práci se souborem: " + e.getMessage());
        }
        vyberBylin();

        }
    }
    public void getBylinkyNacteny(){
        for (int i = 0; i < bylinky.size(); i++) {
            System.out.print(bylinky.get(i).toStringBylinky());
        }
        System.out.println("");
    }
    public void getBylinkySber(){
        for (int i = 0; i < sbirani.size(); i++) {
            System.out.print(sbirani.get(i).toStringBylinky());
        }
        System.out.println("");
    }
    public void sbirat(){
        if(!sbirani.isEmpty()){
           // System.out.println(Mapa.getNazevAktualniLokace().toUpperCase() + " obsahuje: ");
          //  getBylinkySber();
            int nahoda = rd.nextInt(sbirani.size());
            int velikost = Batoh.getBatoh().size();
                batoh.pridatPredmet(sbirani.get(nahoda));
                int velikost2 = Batoh.getBatoh().size();
                if(velikost!=velikost2){
                    sbirani.remove(nahoda);
                    System.out.println(Mapa.getNazevAktualniLokace().toUpperCase() + " obsahuje: ");
                    getBylinkySber();
                }else{
                    System.out.println("NEMÁŠ MÍSTO");
                }

        }else{
            System.out.println("NENÍ CO SBÍRAT");
        }
    }
    public void vyberBylin(){
        switch (Mapa.getNazevAktualniLokace()){
            case "Louka":
                sbirani.clear();
                for(Predmet predmet : bylinky){
                    if(predmet.getNazev().equals("Měsíček")){
                        sbirani.add(predmet);
                    }
                }

                break;
            case "Les":
                sbirani.clear();
                for(Predmet predmet : bylinky){
                    if(predmet.getNazev().equals("Kopřiva")){
                        sbirani.add(predmet);
                    }
                }
                getBylinkySber();
                break;
            case "Loviště":
                sbirani.clear();
                for(Predmet predmet : bylinky){
                    if(predmet.getNazev().equals("Kozlík")){
                        sbirani.add(predmet);
                    }
                }
                getBylinkySber();
                break;
            case "Zřícenina":
                sbirani.clear();
                for(Predmet predmet : bylinky){
                    if(predmet.getNazev().equals("Máta")){
                        sbirani.add(predmet);
                    }
                }
                getBylinkySber();
                break;

            default:
                System.out.println("Zde bylinky nejsou");
        }
    }

    public static boolean isKytkyNacteny() {
        return kytkyNacteny;
    }

    public static void setKytkyNacteny(boolean kytkyNacteny) {
        Sber.kytkyNacteny = kytkyNacteny;
    }

    public ArrayList<Predmet> getBylinky() {
        return bylinky;
    }

    public ArrayList<Predmet> getSbirani() {
        return sbirani;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
