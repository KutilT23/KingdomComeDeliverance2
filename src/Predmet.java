public class Predmet implements Comparable<Predmet>{
    private String nazev;
    private int cena;
    private int sila;
    private int leceni;

    public Predmet(String nazev, int cena, int sila, int leceni) {
        this.nazev = nazev;
        this.cena = cena;
        this.sila = sila;
        this.leceni = leceni;
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

    public void setLeceni(int leceni) {
        this.leceni = leceni;
    }

    String RESET = "\u001B[0m";
    String CERNA = "\u001B[30m";
    String CERVENA = "\u001B[91m";
    String ZELENA = "\u001B[92m";
    String ZLUTA = "\u001B[93m";
    String MODRA = "\u001B[94m";
    String FIALOVA = "\u001B[95m";
    String CYAN = "\u001B[36m";
    String BILA = "\u001B[37m";
    @Override
    public String toString() {
       //return "\nNÁZEV: "  + FIALOVA+ nazev+RESET+ ",CENA: " +ZLUTA +  cena + RESET + ",SILA: " + CERVENA + sila + RESET + ",LECENI: " + ZELENA + leceni + RESET;
        return "\nNÁZEV: "  + FIALOVA+ nazev+RESET+ ",SILA: " + CERVENA + sila + RESET +",CENA: " +ZLUTA +  cena + RESET + ",LECENI: " + ZELENA + leceni + RESET;

    }

    @Override
    public int compareTo(Predmet o) {
    return Integer.compare( o.getCena(),this.getCena());
    }
}
