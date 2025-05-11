public class Hrac {
     private static int sila = 10;
     private static int zivoty = 100;
     private static int vyrecnost = 5;
     private static int reputace = 50;
     private static int penize = 10; // pak 0

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

    public static int getReputace() {
        return reputace;
    }

    public static void setReputace(int reputace) {
        Hrac.reputace = reputace;
    }

    public static int getPenize() {
        return penize;
    }

    public static void setPenize(int penize) {
        Hrac.penize = penize;
    }
}
