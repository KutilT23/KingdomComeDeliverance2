package UnitTests;

import Additional.Region;
import Commands.Hunt;
import World.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuntTest {

    @Test
    void loadAnimals() {
        Hunt hunt = new Hunt();
        hunt.loadAnimals();
        System.out.println(hunt.getAnimals());
        assertEquals(8,hunt.getAnimals().size());

    }

    @Test
    void areAnimalsLoaded() {
        Hunt hunt = new Hunt();
        Hunt.setAnimalsLoaded(false);
        assertFalse(hunt.areAnimalsLoaded());
        hunt.loadAnimals();
        assertTrue(hunt.areAnimalsLoaded());
    }
}