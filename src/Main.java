public class Main {
    public static void main(String[] args) {
        Konzole k = new Konzole();
        k.start();
        Mapa mapa = new Mapa();
        System.out.println(mapa.getNazevAktualniLokace());
        Mapa.setCurrentPosition(50);
        System.out.println(mapa.getNazevAktualniLokace());



    }
}