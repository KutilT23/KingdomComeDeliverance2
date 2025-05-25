public class Exit extends Command {
    @Override
    public void execute() {
        Map map = new Map();
        if(Enter.isInsideTown()&&Map.getCurrentLocationName().equalsIgnoreCase("entrance")){
            System.out.println("Leaving town...");
            switch (Map.getRegion()) {
                case TROSECKO:
                    map.loadTrosMap();
                    Map.setCurrentPosition(Enter.getTownLocation());
                    System.out.println("Your current position is: " + Map.getCurrentLocationName());
                    break;
                case KUTNOHORSKO:
                    map.loadKutMap();
                    Map.setCurrentPosition(Enter.getTownLocation());
                    System.out.println("Your current position is: " + Map.getCurrentLocationName());
                    break;
                default:
                    System.out.println("error: unknown region");
            }
            Enter.setInsideTown(false);
        }else{
            System.out.println("You are not in a town exit");
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
