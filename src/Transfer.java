public class Transfer extends Command {

    @Override
    public void execute() {
        Map world = new Map();

        if (Map.getCurrentLocationName().equals("Ferryman")) {
            switch (Map.getRegion()) {
                case TROSECKO:
                    System.out.println("Traveling from Trosecko to Kutnohorsko");
                    world.loadKutMap();
                    Map.setCurrentPosition(96);
                    break;

                case KUTNOHORSKO:
                    System.out.println("Traveling from Kutnohorsko to Trosecko");
                    world.loadTrosMap();
                    Map.setCurrentPosition(54);
                    break;

                default:
                    System.out.println("Error: Unknown region");
            }
        } else {
            System.out.println("You are not at the ferryman's location.");
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
