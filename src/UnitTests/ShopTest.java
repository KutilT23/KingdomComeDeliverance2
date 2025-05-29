package UnitTests;

import Additional.Shop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    void loadItems() {
        Shop shop = new Shop();
        shop.loadItems("Armorer.txt");
        assertEquals(5, Shop.getShopItems().size());
        assertEquals("Long Sword", Shop.getShopItems().get(0).getName());
    }
}