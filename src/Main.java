public class Main {
    public static void main(String[] args) {
       // Konzole k = new Konzole();
     //   k.start();
        Mapa mapa = new Mapa();
        mapa.nacistMapu();
        FastTravel.getMestaT().add("troskovice");
        FastTravel fastTravel = new FastTravel();
        fastTravel.TravelFast();
        System.out.println(Mapa.getNazevAktualniLokace());
        Mapa.setCurrentPosition(50);
        System.out.println(Mapa.getNazevAktualniLokace());




    }
}