import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Lov extends Command{
    @Override
    public void execute() {
    nacistZver();
    }

    @Override
    public boolean exit() {
        return false;
    }
    private ArrayList<Zvire> zver = new ArrayList<>();
    public void nacistZver(){
        try (BufferedReader br = new BufferedReader(new FileReader("Zver.txt"))) {
            String radek;

            while ((radek = br.readLine()) != null) {
                String[] lines = radek.split(";");

                Zvire zvire = new Zvire(lines[0],Integer.parseInt(lines[1]),new Predmet(lines[2],Integer.parseInt(lines[3]),Integer.parseInt(lines[4])),new Predmet(lines[5],Integer.parseInt(lines[6]),Integer.parseInt(lines[7])),new Predmet(lines[8],Integer.parseInt(lines[9]),Integer.parseInt(lines[10])));
                zver.add(zvire);


            }

        } catch (IOException e) {
            System.out.println("Chyba při práci se souborem: " + e.getMessage());
        }
        getPredmety();
    }
    public void getPredmety(){
        for (int i = 0; i < zver.size(); i++) {
            System.out.println("ZVÍŘE: " + zver.get(i).getJmeno() + ",ŽIVOTY: " + zver.get(i).getZivoty()+ ",KOŘIST: " + zver.get(i).getKorist().get(0).toString4() +
            zver.get(i).getKorist().get(1).toString4() + zver.get(i).getKorist().get(2).toString4());
        }
        System.out.println("");
    }

    public ArrayList<Zvire> getZver() {
        return zver;
    }

}
