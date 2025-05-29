package UnitTests;

import Additional.Region;
import Commands.Steal;
import World.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StealTest {

    @Test
    void loadCitizens() {
        Map map = new Map();
        map.loadTrosMap();
        Steal steal = new Steal();
        steal.loadCitizens();
        assertEquals(true,Steal.isCitizensloaded());
        Map.setRegion(Region.KUTTENBERG);
        steal.loadCitizens();
        assertEquals("Silver Ring",steal.getCitizens().get(5).getPockets().get(1).getName());
    }

    @Test
    void isInTown() {
        Map map = new Map();
        Steal steal = new Steal();
        map.loadTrosMap();
        Map.setCurrentPosition(1);
        assertEquals(false,Steal.isInTown());
        map.loadKutMap();
        Map.setCurrentPosition(4);
        steal.checkIfInTown();
        assertEquals(true,Steal.isInTown());
    }
}