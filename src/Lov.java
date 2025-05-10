import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Lov extends Command{
    @Override
    public void execute() {
        if(!Mapa.getNazevAktualniLokace().equals("Loviště")){ // bacha na ! potom pryč
            nacistZver();
            lovit();
        }else{
            System.out.println("Nejsi v lovisti");
        }

    }

    @Override
    public boolean exit() {
        return false;
    }

    private Random rd = new Random();
    private ArrayList<Zvire> lov = new ArrayList<>();

    private ArrayList<Zvire> zver = new ArrayList<>();
    Batoh batoh = new Batoh();

    private static boolean lovNacteny = false;
    public void nacistZver(){
        if(!lovNacteny){
            zver.clear();
            try (BufferedReader br = new BufferedReader(new FileReader("Zver.txt"))) {
                String radek;

                while ((radek = br.readLine()) != null) {
                    String[] lines = radek.split(";");

                    Zvire zvire = new Zvire(lines[0],Integer.parseInt(lines[1]),new Predmet(lines[2],Integer.parseInt(lines[3]),Integer.parseInt(lines[4]),
                    TypPredmetu.valueOf(lines[5])),new Predmet(lines[6],Integer.parseInt(lines[7]),Integer.parseInt(lines[8]),TypPredmetu.valueOf(lines[9])),
                    new Predmet(lines[10],Integer.parseInt(lines[11]),Integer.parseInt(lines[12]),TypPredmetu.valueOf(lines[13])));
                    //zvire.getKorist().get(0).setJeKradeny(true); až budu okrádat lidi
                    zver.add(zvire);

                }

            } catch (IOException e) {
                System.out.println("Chyba při práci se souborem: " + e.getMessage());
            }
            vyberZvere();
            lovNacteny = true;
        }

    }
    public void getPredmety(){
        for (int i = 0; i < zver.size(); i++) {
            System.out.println("ZVÍŘE: " + zver.get(i).getJmeno() + ",ŽIVOTY: " + zver.get(i).getZivoty()+ ",KOŘIST: " + zver.get(i).getKorist().get(0).toString4() +
            zver.get(i).getKorist().get(1).toString4() + zver.get(i).getKorist().get(2).toString4());
        }
        System.out.println("");
    }
    public void getPredmetyLov(){
        for (int i = 0; i < lov.size(); i++) {
            System.out.println("ZVÍŘE: " + lov.get(i).getJmeno() + ",ŽIVOTY: " + lov.get(i).getZivoty()+ ",KOŘIST: " + lov.get(i).getKorist().get(0).toString4() +
                    lov.get(i).getKorist().get(1).toString4() + lov.get(i).getKorist().get(2).toString4());
        }
        System.out.println("");
    }

    public void vyberZvere(){
        int cislo = rd.nextInt(2);
        switch (cislo){
            case 0:
                lov.clear();
                for (int i = 0; i < zver.size(); i++) {
                    if(zver.get(i).getJmeno().equals("Kralik")){
                        lov.add(zver.get(i));
                    }
                }
                break;
            case 1:
                lov.clear();
                for (int i = 0; i < zver.size(); i++) {
                    if(zver.get(i).getJmeno().equals("Jelen")){
                        lov.add(zver.get(i));
                    }
                }
                break;
            default:
                System.out.println("Chyba");
        }
        getPredmetyLov();
    }
    public void lovit(){
        if(!lov.isEmpty()){
        int nahoda = rd.nextInt(lov.size());
            getLov().get(nahoda);
            System.out.println("ZVÍŘE: " + lov.get(nahoda).getJmeno() + ",ŽIVOTY: " + lov.get(nahoda).getZivoty()+ ",KOŘIST: " + lov.get(nahoda).getKorist().get(0).toString4() +
                lov.get(nahoda).getKorist().get(1).toString4() + lov.get(nahoda).getKorist().get(2).toString4());
            if(Hrac.getSila()*10 > lov.get(nahoda).getZivoty()){
                System.out.println("Ulovil si: " + lov.get(nahoda).getJmeno());
                batoh.pridatPredmet(lov.get(nahoda).getKorist().get(rd.nextInt(3)));

                lov.remove(nahoda);
            }else{
                System.out.println("Ztratil si: " + lov.get(nahoda).getJmeno());
                lov.remove(nahoda);
            }
        }else{
            System.out.println("V lovišti už nejsou zvířata");
        }
    }

    public ArrayList<Zvire> getZver() {
        return zver;
    }

    public ArrayList<Zvire> getLov() {
        return lov;
    }

    public static boolean isLovNacteny() {
        return lovNacteny;
    }

    public static void setLovNacteny(boolean lovNacteny) {
        Lov.lovNacteny = lovNacteny;
    }
}
