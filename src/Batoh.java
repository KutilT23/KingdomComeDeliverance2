import java.util.ArrayList;

public class Batoh extends Command{

    private static ArrayList<Predmet>batoh = new ArrayList<>();
    private static int kapacita = 30 + Hrac.getSila()*5;
    private static int AktualniKapacita = kapacita;
    @Override
    public void execute() {
        vypisBatoh();
        System.out.println(getAktualniKapacita());
    }

    @Override
    public boolean exit() {
        return false;
    }


    public void  pridatPredmet(Predmet predmet){
        if(getAktualniKapacita() - predmet.getVaha()>0){
            batoh.add(predmet);
            System.out.println("Předmět: " + predmet.getNazev() + " přidán do batohu");
            setAktualniKapacita(getAktualniKapacita()-predmet.getVaha());
        }
    }
    public void vypisBatoh(){
        if(!batoh.isEmpty()){
            System.out.println("Batoh: ");
            for (int i = 0; i < batoh.size(); i++) {
                switch (batoh.get(i).getTypPredmetu()){
                    case ZBRAN:
                        System.out.print(batoh.get(i).toString2());
                        break;
                    case LEKTVAR:
                        System.out.print(batoh.get(i).toString3());
                        break;
                    case ULOVEK:
                        System.out.print(batoh.get(i).toString4());
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
