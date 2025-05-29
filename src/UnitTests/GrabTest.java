package UnitTests;

import Commands.Grab;
import World.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrabTest {

    @Test
    void loadHerbs() {
        Grab grab = new Grab();
        grab.loadHerbs();
        assertEquals(16,grab.getHerbs().size());
    }

    @Test
    void displayCollectableHerbs() {
        Grab grab = new Grab();
        grab.loadHerbs();
        Map map = new Map();
        map.loadTrosMap();
        map.setCurrentPosition(1);
        grab.selectHerbs();
        assertEquals("Mint",grab.getCollected().get(0).getName());
        map.setCurrentPosition(0);
        grab.selectHerbs();
        assertEquals("Nettle",grab.getCollected().get(3).getName());

    }
}