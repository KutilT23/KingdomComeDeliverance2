
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;



public class Mapa{
    static private HashMap<Integer, Lokace> svet = new HashMap<>();
    private static int start = 0;
    private static int currentPosition = start;
    public static boolean maMapu = false;

    public boolean nacistMapu() {
        try (BufferedReader br = new BufferedReader(new FileReader("MapKing.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(";");
                Lokace location = new Lokace(
                        lines[1],
                        Integer.parseInt(lines[0]),
                        Arrays.copyOfRange(lines, 2, 6)
                );
                svet.put(Integer.valueOf(lines[0]), location);
            }

            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public Lokace getCurrentPosition1(){
        return svet.get(currentPosition);
    }

    static public int getCurrentPosition() {
        return currentPosition;
    }

    public static void setCurrentPosition(int currentPosition) {
        Mapa.currentPosition = currentPosition;
    }

    public HashMap<Integer, Lokace> getSvet() {
        return svet;
    }

    public String pohyb(String smer) {
        int dirIndex;

        switch (smer.toLowerCase()) {
            case "s":
                dirIndex = 0;
                break;
            case "j":
                dirIndex = 1;
                break;
            case "v":
                dirIndex = 2;
                break;
            case "z":
                dirIndex = 3;
                break;
            default:
                return "Neplatný směr! Použijte Sever, Jih, Východ nebo Západ.";
        }


        int newPosition = svet.get(currentPosition).getLocations()[dirIndex];
        if (newPosition == -1) {
            return "Tímto směrem nelze jít⛔.";
        }

            currentPosition = newPosition;

            return "Přesunuli jste se na lokaci:  " + svet.get(currentPosition).getName().toUpperCase();
        }


}