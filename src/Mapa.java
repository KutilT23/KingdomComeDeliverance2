
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;



public class Mapa{
    static private HashMap<Integer, Lokace> svet = new HashMap<>();
    private static int start = 36;
    private static int currentPosition = start;
    private static Region region;
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
                region = Region.TROSECKO;
                FastTravel.getMestaT().add("troskovice");
            }

            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public static Region getRegion() {
        return region;
    }

    public static Lokace getCurrentPosition1(){
        return svet.get(currentPosition);
    }

    public static String getNazevAktualniLokace() {
        Lokace aktualniLokace = getCurrentPosition1();
        if (aktualniLokace != null) {
            return aktualniLokace.getName();
        } else {
            return "Lokace nenalezena.";
        }
    }
    public static int getIDAktualniLokace() {
        Lokace aktualniLokace = getCurrentPosition1();
        if (aktualniLokace != null) {
            return aktualniLokace.getID();
        } else {
            return -1;
        }
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
        System.out.println(Mapa.getNazevAktualniLokace());
        switch (Mapa.getNazevAktualniLokace().toLowerCase()) {
            case "hradtrosky":
                System.out.println("HradTrosky přidáno do FastTravel");
                FastTravel.getMestaT().add("hradtrosky");
                break;
            case "troskovice":
                System.out.println("Troskovice přidáno do FastTravel");
                FastTravel.getMestaT().add("troskovice");
                break;
            case "semín":
                System.out.println("Semín přidáno do FastTravel");
                FastTravel.getMestaT().add("semín");
                break;
            default:
                System.out.println("test");
        }


            return "Přesunuli jste se na lokaci:  " + svet.get(currentPosition).getName().toUpperCase();
        }


}