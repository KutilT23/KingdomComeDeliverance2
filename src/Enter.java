public class Enter extends Command{

    private static boolean insideTown = false;
    private static int townLocation = Map.getCurrentPosition();

    @Override
    public void execute() {

        Map map = new Map();
        Steal.checkIfInTown();
        if(Steal.isInTown()&&!Enter.isInsideTown()){
                System.out.println("Entering town...");
                townLocation = Map.getCurrentPosition();
                map.loadTown();
                Map.setCurrentPosition(4);
                insideTown = true;

        }else{
            System.out.println("You are not in a town entrance");
        }

    }

    @Override
    public boolean exit() {
        return false;
    }

    public static boolean isInsideTown() {
        return insideTown;
    }

    public static void setInsideTown(boolean insideTown) {
        Enter.insideTown = insideTown;
    }

    public static int getTownLocation() {
        return townLocation;
    }

    public static void setTownLocation(int townLocation) {
        Enter.townLocation = townLocation;
    }
}
