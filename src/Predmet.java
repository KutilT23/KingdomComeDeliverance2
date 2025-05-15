public class Predmet implements Comparable<Predmet>{
    private String nazev;
    private int cena;
    private int sila;
    private int leceni;
    private int vaha;
    private TypPredmetu typPredmetu;
    private boolean kradeny = false;
    private boolean pouzivany = false;


    public Predmet(String nazev, int cena, int sila, int vaha, int leceni, TypPredmetu typPredmetu) {
        this.nazev = nazev;
        this.cena = cena;
        this.sila = sila;
        this.vaha = vaha;
        this.leceni = leceni;
        this.typPredmetu = typPredmetu;
    }

    public Predmet(String nazev, int cena, int vaha, TypPredmetu typPredmetu) {
        this.nazev = nazev;
        this.cena = cena;
        this.vaha = vaha;
        this.typPredmetu = typPredmetu;
    }
    public Predmet(String nazev, int cena, int vaha, TypPredmetu typPredmetu,boolean kradeny) {
        this.nazev = nazev;
        this.cena = cena;
        this.vaha = vaha;
        this.typPredmetu = typPredmetu;
        this.kradeny = kradeny;
    }

    public Predmet(Predmet p) {
    }

    public Predmet() {

    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public boolean isPouzivany() {
        return pouzivany;
    }

    public void setPouzivany(boolean pouzivany) {
        this.pouzivany = pouzivany;
    }

    public int getLeceni() {
        return leceni;
    }

    public boolean isKradeny() {
        return kradeny;
    }

    public void setKradeny(boolean kradeny) {
        this.kradeny = kradeny;
    }

    public void setLeceni(int leceni) {
        this.leceni = leceni;
    }

    public int getVaha() {
        return vaha;
    }

    public void setVaha(int vaha) {
        this.vaha = vaha;
    }

    public TypPredmetu getTypPredmetu() {
        return typPredmetu;
    }

    public void setTypPredmetu(TypPredmetu typPredmetu) {
        this.typPredmetu = typPredmetu;
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
    String ORANZOVA = "\u001B[38;5;208m";
    @Override
    public String toString() {
        return "\nNÁZEV: "  + FIALOVA + nazev+RESET+ ",SILA: " + CERVENA + sila + RESET +",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET+ ",LECENI: " + ZELENA + leceni + RESET + kradeny;

    }

    public String toString2() {
        return "\nNÁZEV: "  + FIALOVA+ nazev+RESET+ ",SILA: " + CERVENA + sila + RESET +",CENA: " +ZLUTA +  cena + RESET+",VAHA: " +MODRA +  vaha + RESET;

    }
    public String toString2Test() {
        return "NÁZEV: "  + FIALOVA+ nazev+RESET+ ",SILA: " + CERVENA + sila + RESET +",CENA: " +ZLUTA +  cena + RESET+",VAHA: " +MODRA +  vaha + RESET + "\n";

    }

    public String toString5Test() {
            return "NÁZEV: "  + FIALOVA+ nazev+RESET+ ",SILA: " + CERVENA + sila + RESET +",CENA: " +ZLUTA +  cena + RESET+",VAHA: " +MODRA +  vaha + RESET +",POUZIVANY: " + ZELENA + pouzivany + RESET + "\n";


    }

    public String toString3() {
        return "\nNÁZEV: "  + FIALOVA+ nazev+RESET+ ",LECENI: " + ZELENA + leceni + RESET +",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET;

    }
    public String toString3Test() {
        return "NÁZEV: "  + FIALOVA+ nazev+RESET+ ",LECENI: " + ZELENA + leceni + RESET +",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET + "\n";

    }
    public String toString4() {
        return "\nNÁZEV: "  + FIALOVA+ nazev+RESET+ ",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET;

    }
    public String toString4Test() {
        return "NÁZEV: "  + FIALOVA+ nazev+RESET+ ",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET + "\n";

    }
    public String toStringBylinky() {
        return "NÁZEV: "  + ZELENA+ nazev+RESET+ ",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET + "\n";

    }

    public String toStringJmeno() {
        return "\nNÁZEV: "  + FIALOVA+ nazev+RESET;
    }
    public String toStringDleTypu() {
        String text = "";

        switch (typPredmetu) {
            case ZBRAN:
                text = String.format("NÁZEV: \u001B[95m%-8s\u001B[0m SILA: \u001B[91m  %-3d\u001B[0m CENA: \u001B[38;5;220m%-3d\u001B[0m VAHA: \u001B[38;5;208m%-2d\u001B[0m",
                         nazev,sila, cena, vaha);
                break;
            case LEKTVAR:
                text = String.format("NÁZEV: \u001B[95m%-8s\u001B[0m LÉČENÍ: \u001B[92m%-3d\u001B[0m CENA: \u001B[38;5;220m%-3d\u001B[0m VAHA: \u001B[38;5;208m%-2d\u001B[0m",
                        nazev, leceni, cena, vaha);
                break;
            case ULOVEK:
                text = String.format("NÁZEV: \u001B[95m%-8s\u001B[0m             CENA: \u001B[38;5;220m%-3d\u001B[0m VAHA: \u001B[38;5;208m%-2d\u001B[0m",
                        nazev, cena, vaha);
                break;
            default:
                text = "chyba"; //obarvit horní názvy a čisla atd
        }

        return text;
    }


    public String toStringTest() {
        return "Predmet{" +
                "nazev='" + nazev + '\'' +
                ", cena=" + cena +
                ", sila=" + sila +
                ", leceni=" + leceni +
                ", vaha=" + vaha +
                ", typPredmetu=" + typPredmetu +
                '}';
    }

    @Override
    public int compareTo(Predmet o) {
    return Integer.compare( o.getCena(),this.getCena());
    }
}
