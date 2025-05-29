package World;

import Additional.Region;
import Commands.FastTravel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * class for the maps of the game
 */
public class Map {
    private static HashMap<Integer, Location> regions = new HashMap<>();

    private static int start = 36;//36
    private static int currentPosition = start;
    private static Region region;

    /**
     * method to load the troskyMap from the file
     * @return
     */
    public boolean loadTrosMap() {
        regions.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("TroskyMap.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                Location location = new Location(
                        parts[1], Integer.parseInt(parts[0]),
                        Arrays.copyOfRange(parts, 2, 6));
                regions.put(Integer.valueOf(parts[0]), location);
                region = Region.TROSKY;
                FastTravel.getCitiesT().add("troskowitz");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * method to load the KuttenbergMap from the file
     * @return
     */
    public boolean loadKutMap() {
        regions.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("KuttenbergMap.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                Location location = new Location(
                        parts[1], Integer.parseInt(parts[0]),
                        Arrays.copyOfRange(parts, 2, 6));
                regions.put(Integer.valueOf(parts[0]), location);
                region = Region.KUTTENBERG;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * method to load the townMap from the file
     * @return
     */
    public boolean loadTown() {
        try (BufferedReader br = new BufferedReader(new FileReader("TownMap.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                Location location = new Location(
                        parts[1], Integer.parseInt(parts[0]),
                        Arrays.copyOfRange(parts, 2, 6));
                regions.put(Integer.valueOf(parts[0]), location);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Region getRegion() {
        return region;
    }

    public static void setRegion(Region region) {
        Map.region = region;
    }

    public static Location getCurrentLocation() {
        return regions.get(currentPosition);
    }

    public static String getCurrentLocationName() {
        Location currentLoc = getCurrentLocation();
        if (currentLoc != null) {
            return currentLoc.getName();
        } else {
            return "World.Location not found.";
        }
    }

    public static int getCurrentPosition() {
        return currentPosition;
    }

    public static void setCurrentPosition(int newPosition) {
        Map.currentPosition = newPosition;
    }

    public HashMap<Integer, Location> getRegions() {
        return regions;
    }

    /**
     * method to move the player to a new location
     * @param direction
     * @return
     */
    public String move(String direction) {

        int dirIndex;

        switch (direction.toLowerCase()) {
            case "n":  // North
                dirIndex = 0;
                break;
            case "s":  // South
                dirIndex = 1;
                break;
            case "e":  // East
                dirIndex = 2;
                break;
            case "w":  // West
                dirIndex = 3;
                break;
            default:
                return "Invalid direction! Commands.Use North, South, East or West.";
        }

        int newPosition = regions.get(currentPosition).getLocations()[dirIndex];
        if (newPosition == -1) {
            return "You can't go this way ⛔.";
        }

        currentPosition = newPosition;

        // Add locations to fast travel if first time visited
        switch (getCurrentLocationName().toLowerCase()) {
            case "troskycastle":
                if (FastTravel.getCitiesT().add("troskycastle")) {
                    System.out.println("HradTrosky added to Commands.FastTravel");
                }
                break;
            case "troskowitz":
                if (FastTravel.getCitiesT().add("troskowitz")) {
                    System.out.println("Troskovice added to Commands.FastTravel");
                }
                break;
            case "semine":
                if (FastTravel.getCitiesT().add("semine")) {
                    System.out.println("Semine added to Commands.FastTravel");
                }
                break;
            case "devilsden":
                if (FastTravel.getCitiesK().add("devilsden")) {
                    System.out.println("DevilsDen added to Commands.FastTravel");
                }
                break;
            case "kuttenberg":
                if (FastTravel.getCitiesK().add("kuttenberg")) {
                    System.out.println("Kuttenberg added to Commands.FastTravel");
                }
                break;
            case "suchdol":
                if (FastTravel.getCitiesK().add("suchdol")) {
                    System.out.println("Suchdol added to Commands.FastTravel");
                }
                break;
            default:

        }

        return "You moved to location: " + regions.get(currentPosition).getName().toUpperCase();
    }

    /**
     * method to get the name of the new location in each direction
     * @param direction
     * @return
     */
    public String locationMoveName(String direction) {

        int dirIndex;

        switch (direction.toLowerCase()) {
            case "n":
                dirIndex = 0;
                break;
            case "s":
                dirIndex = 1;
                break;
            case "e":
                dirIndex = 2;
                break;
            case "w":
                dirIndex = 3;
                break;
            default:
                return "error";
        }

        int newPosition = regions.get(currentPosition).getLocations()[dirIndex];
        if (newPosition == -1) {
            return "You can't go this way ⛔.";
        }

        return regions.get(newPosition).getName().toUpperCase() + "\uD83D\uDCCD\uD83D\uDDFA\uFE0F";
    }
}
