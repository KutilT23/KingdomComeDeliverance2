import java.util.ArrayList;

public class Citizen {

    private String name;
    private ArrayList<Item> pockets = new ArrayList<>();

    public Citizen(String name, Item item, Item item1, Item item2, Item item3) {
        this.name = name;
        this.pockets.add(item);
        this.pockets.add(item1);
        this.pockets.add(item2);
        this.pockets.add(item3);
    }
    @Override
    public String toString() {
        return "\nNAME: " + name + " ,POCKETS: " + pockets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getPockets() {
        return pockets;
    }

    public void setPockets(ArrayList<Item> pockets) {
        this.pockets = pockets;
    }
}
