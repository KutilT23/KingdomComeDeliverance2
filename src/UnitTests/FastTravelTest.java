package UnitTests;

import Commands.FastTravel;
import World.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastTravelTest {

    @Test
    void fastTravel() {
        Map map = new Map();
        map.loadTrosMap();
        Map.setCurrentPosition(49);
        assertEquals("Semine", Map.getCurrentLocationName());
    }
}