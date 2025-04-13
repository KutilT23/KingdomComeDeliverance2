public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Mapa mapa = new Mapa();
        mapa.nacistMapu();
        System.out.println(mapa.getSvet());
        Mapa.setCurrentPosition(20);
        System.out.println(mapa.getCurrentPosition1());
        mapa.pohyb("s");
        System.out.println(mapa.getCurrentPosition1());
    }
}