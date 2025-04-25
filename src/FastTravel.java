import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class FastTravel extends Command{


    private static TreeSet<String> mestaT = new TreeSet<>();
    private static TreeSet<String> mestaK = new TreeSet<>();
    private boolean maMestoT = false;
    private boolean maMestoK = false;
    Scanner sc = new Scanner(System.in);

    public static TreeSet<String> getMestaT() {
        return mestaT;
    }

    public static TreeSet<String> getMestaK() {
        return mestaK;
    }
    public void TravelFast(){
        if(Mapa.getRegion().equals(Region.TROSECKO)){
            System.out.println("Do kterého města chcete cestovat?: ");
            System.out.println(getMestaT());
            String jmeno = sc.next().toLowerCase();
                if(mestaT.contains(jmeno)){
                    switch (jmeno){
                        case "hradtrosky":
                            Mapa.setCurrentPosition(13);
                            System.out.println("Přesunuto na : " + Mapa.getNazevAktualniLokace());
                            break;
                        case "troskovice":
                            Mapa.setCurrentPosition(36);
                            System.out.println("Přesunuto na : " + Mapa.getNazevAktualniLokace());
                            break;
                        case "semín":
                            Mapa.setCurrentPosition(49);
                            System.out.println("Přesunuto na : " + Mapa.getNazevAktualniLokace());
                            break;
                        default:
                            System.out.println("Město neobjeveno");
                    }
                }else{
                    System.out.println("Město neobjeveno");
                }

        }


        if (Mapa.getRegion().equals(Region.KUTNOHORSKO)){
            if(mestaK.isEmpty()){
                System.out.println("Dosud nebylo objeveno město v Kutnohorsku");
            }else{

                System.out.println("Do kterého města chcete cestovat?: ");
                System.out.println(getMestaK());
                String jmeno = sc.next().toLowerCase();
                if (mestaK.contains(jmeno)){
                    switch (jmeno){
                        case "čertovka":
                            Mapa.setCurrentPosition(68);
                            System.out.println("Přesunuto na : " + Mapa.getNazevAktualniLokace());
                            break;
                        case "suchdol":
                            Mapa.setCurrentPosition(98);
                            System.out.println("Přesunuto na : " + Mapa.getNazevAktualniLokace());
                            break;
                        case "kutnáhora":
                            Mapa.setCurrentPosition(110);
                            System.out.println("Přesunuto na : " + Mapa.getNazevAktualniLokace());
                            break;
                        default:
                            System.out.println("Město neobjeveno");
                    }
                }else{
                    System.out.println("Město neobjeveno");
                }

            }


        }

    }

    @Override
    public void execute() {
    TravelFast();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
