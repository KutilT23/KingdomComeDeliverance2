public class Main {
    public static void main(String[] args) {
        Konzole k = new Konzole();
        k.start();
        Mapa mapa  = new Mapa();
        System.out.println(mapa.getTrosecko());
        mapa.nacistMapuTros();
        System.out.println(mapa.getTrosecko());
        System.out.println(Mapa.getCurrentPosition());
        System.out.println(mapa.getKutnohorsko());






    }
}