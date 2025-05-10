public class Predmet implements Comparable<Predmet>{
    private String nazev;
    private int cena;
    private int sila;
    private int leceni;
    private int vaha;
    private TypPredmetu typPredmetu;
    private boolean jeKradeny = false;


    public Predmet(String nazev, int cena, int sila, int vaha, TypPredmetu typPredmetu, int leceni) {
        this.nazev = nazev;
        this.cena = cena;
        this.sila = sila;
        this.vaha = vaha;
        this.typPredmetu = typPredmetu;
        this.leceni = leceni;
    }

    public Predmet(String nazev, int cena, int vaha, TypPredmetu typPredmetu) {
        this.nazev = nazev;
        this.cena = cena;
        this.vaha = vaha;
        this.typPredmetu = typPredmetu;
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

    public int getLeceni() {
        return leceni;
    }

    public boolean isJeKradeny() {
        return jeKradeny;
    }

    public void setJeKradeny(boolean jeKradeny) {
        this.jeKradeny = jeKradeny;
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
    String FIALOVA = "\u001B[95m";
    String CYAN ="\u001B[38;5;177m";
    String BILA = "\u001B[37m";
    @Override
    public String toString() {
        return "\nNÁZEV: "  + CYAN + nazev+RESET+ ",SILA: " + CERVENA + sila + RESET +",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET+ ",LECENI: " + ZELENA + leceni + RESET + jeKradeny;

    }

    public String toString2() {
        return "\nNÁZEV: "  + CYAN+ nazev+RESET+ ",SILA: " + CERVENA + sila + RESET +",CENA: " +ZLUTA +  cena + RESET+",VAHA: " +MODRA +  vaha + RESET;

    }

    public String toString3() {
        return "\nNÁZEV: "  + CYAN+ nazev+RESET+ ",LECENI: " + ZELENA + leceni + RESET +",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET;

    }
    public String toString4() {
        return "\nNÁZEV: "  + CYAN+ nazev+RESET+ ",CENA: " +ZLUTA +  cena + RESET +",VAHA: " +MODRA +  vaha + RESET;

    }

    public String toStringJmeno() {
        return "\nNÁZEV: "  + CYAN+ nazev+RESET;
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
