import java.util.ArrayList;

public class Profil extends Command{
Batoh batoh = new Batoh();
    @Override
    public void execute() {
        vypisStatistik();
/*
        System.out.println("STATISTIKY: \nSÍLA: " + Hrac.getSila() + ", ŽIVOTY: " + Hrac.getZivoty() + ", PENÍZE: " + Hrac.getPenize()
                + "\nREPUTACE: " + Hrac.getReputace()+ ", VYŘEČNOST: " + Hrac.getVyrecnost()) ;
        System.out.print("POUŽÍVANÝ PŘEDMĚT: ");
        test();
        System.out.println("KAPACITA BATOHU: " + Batoh.getAktualniKapacita());
        batoh.vypisBatoh();

 */
    }
    String RESET = "\u001B[0m";
    String CERNA = "\u001B[30m";
    String CERVENA = "\u001B[91m";
    String ZELENA = "\u001B[92m";
    String ZLUTA = "\u001B[38;5;220m"; ;
    String MODRA = "\u001B[38;5;33m";
    String CYAN = "\u001B[95m";
    String FIALOVA =  "\u001B[38;5;135m";
    String BILA = "\u001B[37m";
    public void vypisStatistik() {

        String[] statistiky = {
                "SÍLA:       " + CERVENA + Hrac.getSila() + RESET,
                "ŽIVOTY:     " + ZELENA + Hrac.getZivoty() + RESET,
                "PENÍZE:     " + ZLUTA + Hrac.getPenize() + RESET,
                "REPUTACE:   " + FIALOVA + Hrac.getReputace() + RESET,
                "VYŘEČNOST:  " + FIALOVA + Hrac.getVyrecnost() + RESET
        };

        System.out.println("-------------------------------------------------------------------");
        System.out.println("BATOH:                                             STATISTIKY:");
        System.out.println("-------------------------------------------------------------------");

        int max = Math.max(Batoh.getBatoh().size(), statistiky.length);
        for (int i = 0; i < max; i++) {
            String levaCast = "";
            if (i < Batoh.getBatoh().size()) {
                levaCast = Batoh.getBatoh().get(i).toStringDleTypu(); // použiješ svůj výpis předmětu
            }

            String pravaCast = (i < statistiky.length) ? statistiky[i] : "";

            System.out.printf("%-50s %s%n", levaCast, pravaCast);
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println("KAPACITA BATOHU: " + Batoh.getAktualniKapacita());

        // Výpis používaného předmětu
        System.out.print("POUŽÍVANÝ PŘEDMĚT:");
        test();

        /*
        System.out.println("--------------------------------------------------------------------");
        System.out.println("  BATOH:");
        System.out.println("--------------------------------------------------------------------");

        batoh.vypisBatoh();

        System.out.println("--------------------------------------------------------------------");
        System.out.println("  STATISTIKY:");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("  SÍLA:       " + CERVENA +  Hrac.getSila() + RESET);
        System.out.println("  ŽIVOTY:     " + ZELENA + Hrac.getZivoty() + RESET);
        System.out.println("  PENÍZE:     " + ZLUTA + Hrac.getPenize() + RESET);
        System.out.println("  REPUTACE:   " + FIALOVA + Hrac.getReputace() + RESET);
        System.out.println("  VYŘEČNOST:  " + MODRA + Hrac.getVyrecnost() + RESET);
        System.out.println("--------------------------------------------------------------------");

        System.out.println("KAPACITA BATOHU: " + Batoh.getAktualniKapacita());

        System.out.print("POUŽÍVANÝ PŘEDMĚT: ");
        test();

         */
    }

        public void test(){
        boolean naselPouzivany = false;

        for (Predmet p : Batoh.getBatoh()) {
            if (p.isPouzivany()) {
                System.out.print(p.toString5Test());
                naselPouzivany = true;
            }
        }
        if (!naselPouzivany) {
            System.out.println("ŽÁDNÝ PŘEDMĚT SE NEPOUŽÍVÁ");
        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
