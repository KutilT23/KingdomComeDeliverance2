public class Hrac {
     private static int sila = 10;
     private static int zivoty = 100;
     private static int vyrecnost = 5;
     private static int dojem = 5;
     private static int kapacita = 80 + sila*5;

    public static int getSila() {
        return sila;
    }

    public static void setSila(int sila) {
        Hrac.sila = sila;
    }

    public static int getZivoty() {
        return zivoty;
    }

    public static void setZivoty(int zivoty) {
        Hrac.zivoty = zivoty;
    }

    public static int getVyrecnost() {
        return vyrecnost;
    }

    public static void setVyrecnost(int vyrecnost) {
        Hrac.vyrecnost = vyrecnost;
    }

    public static int getDojem() {
        return dojem;
    }

    public static void setDojem(int dojem) {
        Hrac.dojem = dojem;
    }

    public static int getKapacita() {
        return kapacita;
    }

    public static void setKapacita(int kapacita) {
        Hrac.kapacita = kapacita;
    }
}
