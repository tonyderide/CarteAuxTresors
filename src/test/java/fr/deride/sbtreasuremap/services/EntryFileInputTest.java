package fr.deride.sbtreasuremap.services;

import fr.deride.sbtreasuremap.domain.Adventurer;
import fr.deride.sbtreasuremap.domain.Map;
import fr.deride.sbtreasuremap.domain.Mountain;
import fr.deride.sbtreasuremap.domain.Treasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.accessibility.AccessibleRelationSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntryFileInputTest {
    List<String> fileLine = Arrays.asList("C - 3 - 4",
            "M - 1 - 0",
            "M - 2 - 1",
            "T - 0 - 3 - 2",
            "T - 1 - 3 - 3",
            "A - LARA - 1 - 1 - S - AADADAGGA");

    @Test
    void readFile() throws IOException {
        assertLinesMatch(fileLine, EntryFileInput.readFile());
    }

    @Test
    void getMap() {
        Map map = new Map(3,4);
        map.setWidth(3);
        map.setLength(4);
        assertEquals(map.toString(), EntryFileInput.getMap(fileLine).toString());
    }

    @Test
    void getMountains() {
        Mountain mountain1 = new Mountain(1, 0);
        Mountain mountain2 = new Mountain(2, 1);
        List<Mountain> mountains = Arrays.asList(mountain1, mountain2);
        assertEquals(mountains.toString(), EntryFileInput.getMountains(fileLine).toString());
    }

    @Test
    void getTreasures() {
        Treasure treasure1 = new Treasure(0, 3, 2);
        Treasure treasure2 = new Treasure(1, 3, 3);
        List<Treasure> treasures = Arrays.asList(treasure1, treasure2);
        assertEquals(treasures.toString(), EntryFileInput.getTreasures(fileLine).toString());
    }

    @Test
    void getAdventurer() {
        Adventurer adventurer = new Adventurer("LARA", 1, 1, "S", "AADADAGGA",0);
        List<Adventurer> adventurers = Collections.singletonList(adventurer);
        assertEquals(adventurers.toString(), EntryFileInput.getAdventurers(fileLine).toString());
    }
}