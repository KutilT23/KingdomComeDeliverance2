public class Prevoz extends Command{
    @Override
    public void execute() {
        Mapa svet = new Mapa();
        if(Mapa.getNazevAktualniLokace().equals("Převozník")){
            switch (Mapa.getRegion()){
                case TROSECKO:
                    System.out.println("Cestuješ z trosecko do kutnohorska");
                    svet.nacistMapuKut();
                    Mapa.setCurrentPosition(96);
                    break;
                case KUTNOHORSKO:
                    System.out.println("Cestuješ z kutnohorska do trosecka");
                    svet.nacistMapuTros();
                    Mapa.setCurrentPosition(54);
                    break;
                default:
                    System.out.println("Chyba");


            }
        }else{
            System.out.println("Nejsi u převozníka");
        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
