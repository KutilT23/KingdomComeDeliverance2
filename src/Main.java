import java.util.Random;

public class Main {
    public static void main(String[] args) {


        Console console = new Console();
        Backpack backpack = new Backpack();

        backpack.addItem(new Item("Marigold Potion", 20, 0, 1, 50, ItemType.POTION));
        backpack.addItem(new Item("Long Sword", 20, 50, 3, 0, ItemType.WEAPON));
        backpack.addItem(new Item("Mace", 20, 10, 3, 0, ItemType.WEAPON));
       // backpack.addItem(new Item("Meat", 20, 30, 1, 0, ItemType.TROPHY));
        backpack.addItem(new Item("Marigold Potion", 20, 0, 1, 50, ItemType.POTION));
        backpack.addItem(new Item("Sword", 20, 30, 3, 0, ItemType.WEAPON));
        backpack.addItem(new Item("Silver Necklace", 20, 30, 1, 0, ItemType.VALUABLE));



        //backpack.addItem(new Item("Bread", 20, 30, 3, 0, ItemType.WEAPON));
        //backpack.addItem(new Item("Amulet", 20, 30, 1, 0, ItemType.TROPHY));
        console.start();


    }
}
