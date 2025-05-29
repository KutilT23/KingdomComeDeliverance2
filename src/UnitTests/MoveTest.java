package UnitTests;

import World.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MoveTest {

    @Test
    void movePlayer() {
        Map map = new Map();
        map.loadTrosMap();
        Map.setCurrentPosition(36);
        System.out.println(Map.getCurrentLocationName());
        map.move("s");
        Assertions.assertEquals("Pathway", Map.getCurrentLocationName());
    }
    @Test
    void showPossibleDirections() {
        Map map = new Map();
        map.loadTrosMap();
        Map.setCurrentPosition(36);
        System.out.println(map.locationMoveName("n"));
        Assertions.assertEquals("PATHWAY\uD83D\uDCCD\uD83D\uDDFA\uFE0F", map.locationMoveName("n"));
    }
}