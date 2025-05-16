import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random rd = new Random();
        /*
        for (int i = 0; i < 50; i++) {
            int number = rd.nextInt(4);
            System.out.println(number);
        }
        */

        Console console = new Console();
        Backpack backpack = new Backpack();

        backpack.addItem(new Item("Potion", 20, 0, 1, 50, ItemType.POTION));
        backpack.addItem(new Item("Sword", 20, 30, 3, 0, ItemType.WEAPON));
        backpack.addItem(new Item("Meat", 20, 30, 1, 0, ItemType.TROPHY));
        backpack.addItem(new Item("Potion", 20, 0, 1, 50, ItemType.POTION));
        backpack.addItem(new Item("Sword", 20, 30, 3, 0, ItemType.WEAPON));
        backpack.addItem(new Item("Meat", 20, 30, 1, 0, ItemType.TROPHY));

        console.start();

        // TODO: implement herbs
        // TODO: start loading people into cities and enemies into enemy camps

        /*
        Item item = new Item("something", 20, 20, ItemType.PREY);
        item.setStolen(true);
        System.out.println(item);
        */
    }
}
