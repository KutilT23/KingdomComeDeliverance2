import java.util.ArrayList;
import java.util.Scanner;

public class Batoh extends Command{

    private static ArrayList<Predmet>batoh = new ArrayList<>();
    private static int kapacita = 30 + Hrac.getSila()*5;
    private static int AktualniKapacita = kapacita;
    Scanner sc = new Scanner(System.in);


    @Override
    public void execute() {
        volba();
        System.out.println("AKTUÁLNÍ KAPACITA: " + getAktualniKapacita());
    }

    @Override
    public boolean exit() {
        return false;
    }
    public void volba(){
        System.out.println(">> VYPIS, POUZIT");
        String odpoved = sc.next();
        odpoved = odpoved.toLowerCase();
        switch (odpoved){
            case "vypis":
                vypisBatoh();
                break;
            case "pouzit":
                if(!batoh.isEmpty()){
                    vypisBatoh();
                    int index = 0;
                    String index1 = sc.next();
                    while (!index1.matches("[0-9]+")||Integer.parseInt(index1)>= Batoh.getBatoh().size()){
                        System.out.println("Chybný vstup >> INDEX:");
                        index1 = sc.next();
                    }
                    index = Integer.parseInt(index1);
                    switch (batoh.get(index).getTypPredmetu()){
                        case ZBRAN :
                            Hrac.setSila(10);
                            for(Predmet predmet : batoh){
                                predmet.setPouzivany(false);
                            }
                            batoh.get(index).setPouzivany(true);
                            for (Predmet predmet : batoh) {
                                if(predmet.isPouzivany()){
                                    Hrac.setSila(Hrac.getSila()+predmet.getSila());
                                    System.out.println(predmet.toString5Test());

                                   // break;
                                }
                            }
                            System.out.println("AKTUÁLNÍ SÍLA: "+Hrac.getSila());
                            break;
                        case LEKTVAR:
                            if(Hrac.getZivoty() !=100){
                                System.out.println("Používáš lektvar");
                                Hrac.setZivoty(Hrac.getZivoty() + batoh.get(index).getLeceni());
                                batoh.remove(index);
                                System.out.println("TVOJE ŽIVOTY: " + Hrac.getZivoty());
                            }else{
                                System.out.println("UŽ MÁŠ MAXIMÁLNÍ ŽIVOTY");
                            }
                            break;
                        case ULOVEK:
                            System.out.println("Nelze použít");
                            break;
                        default:
                            System.out.println("Chyba");
                    }
                }else{
                    System.out.println("NENÍ CO POUŽÍT");
                }

                break;
            default:
                System.out.println("Není možnost");
                volba();
        }
    }

    public void pridatPredmet(Predmet predmet){
        if(getAktualniKapacita() - predmet.getVaha()>=0){
            batoh.add(predmet);
            System.out.println("Předmět: " + predmet.getNazev() + " přidán do batohu");
            setAktualniKapacita(getAktualniKapacita()-predmet.getVaha());
        }else{
            System.out.println("Batoh je plný");
        }
    }
    public void vypisBatoh(){
        if(!batoh.isEmpty()){
            for (int i = 0; i < batoh.size(); i++) {
                switch (batoh.get(i).getTypPredmetu()){
                    case ZBRAN:
                        System.out.print("  I: " + i +", " + batoh.get(i).toString2Test());
                        break;
                    case LEKTVAR:
                        System.out.print("  I: " + i +", " + batoh.get(i).toString3Test());
                        break;
                    case ULOVEK:
                        System.out.print("  I: " + i +", " + batoh.get(i).toString4Test());
                        break;
                    default:
                        System.out.println("Chyba");
                }
            }
            System.out.println("");

        }else{
            System.out.println("Batoh je prázdný");
        }


    }
    public static ArrayList<Predmet> getBatoh() {
        return batoh;
    }

    public static int getAktualniKapacita() {
        return AktualniKapacita;
    }

    public static void setAktualniKapacita(int aktualniKapacita) {
        AktualniKapacita = aktualniKapacita;
    }
}
