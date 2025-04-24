
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;


public class Konzole {
    private boolean exit = false;
    private HashMap<String, Command> prikazy = new HashMap<>();
    public static String souborPrikazu = "src/TextSoubory/souborPrikazu.txt";
    Mapa svet = new Mapa();

    public void inicializace(){
        prikazy.put("pohyb",new Pohyb());
        prikazy.put("konec",new Konec());
        prikazy.put("prevoz",new Prevoz());
        prikazy.put("vstup",new Vstup());
        prikazy.put("travel",new FastTravel());
        prikazy.put("kostky", new Kostky());
        prikazy.put("lov",new Lov());
        prikazy.put("krast",new Krast());
        prikazy.put("souboj",new Souboj());
        prikazy.put("obchodovat",new Obchodovat());
        prikazy.put("profil",new Profil());
        prikazy.put("batoh",new Batoh());

    }

    private Scanner scanner = new Scanner(System.in);

    private void proved(){
        System.out.println("NAPIŠTE CO CHCETE DĚLAT:");
        String prikaz = scanner.nextLine();
        prikaz = prikaz.trim();
        prikaz = prikaz.toLowerCase();
        ulozPrikaz(prikaz);
        if(prikazy.containsKey(prikaz)){
            System.out.println(">> ");
            prikazy.get(prikaz).execute();
            exit = prikazy.get(prikaz).exit();
        }else{
            System.out.println(">> Nedefinovany prikaz");
        }
    }


    public void start(){
        //svet.nacistMapuTros();
        svet.nacistMapuTros();

        inicializace();
        System.out.println("Vítejte ve hře Ztracený Hraničář :-D");
        System.out.println("Pro přehled příkazů napište: prikazy");
        System.out.println("Nacházíš se na lokaci\uD83D\uDCCD\uD83D\uDDFA\uFE0F: " + svet.getCurrentPosition1().getName().toUpperCase());
        try{
            resetSouboru();
            do{
                proved();
            }while(!exit);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void ulozPrikaz(String prikaz){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(souborPrikazu,true))){
            bw.write(prikaz);
            bw.newLine();
        }catch(Exception e){

        }
    }

    private void resetSouboru(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(souborPrikazu,false))){
        }catch(Exception e){
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
