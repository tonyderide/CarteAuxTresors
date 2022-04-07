package fr.deride.sbtreasuremap.services;

import fr.deride.sbtreasuremap.domain.Adventurer;
import fr.deride.sbtreasuremap.domain.Map;
import fr.deride.sbtreasuremap.domain.Mountain;
import fr.deride.sbtreasuremap.domain.Treasure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapCreation {
    /**
     * create the map as a list of list of string with "." for empty space
     * @param mapCoordinates
     * @return
     */
    public List<List<String>> createMap(Map mapCoordinates,List<Mountain> mountains, List<Treasure>treasures, List<Adventurer>adventurers) {
        List<List<String>> createdMap = new ArrayList<>();
        for (int i = 0; i < mapCoordinates.getLength(); i++) {
            List<String> line = new ArrayList<>();
            for (int j = 0; j < mapCoordinates.getWidth(); j++) {
                line.add(".");
            }
            createdMap.add(line);
        }
        addMountainsOnMap( createdMap, mountains );
        addTreasuresOnMap( createdMap, treasures);
        addAdventurersOnMap( createdMap, adventurers);
        displayMapInput(createdMap);
        return createdMap;
    }

    /**
     * add the mountain to the created map
     * @param createdMap
     * @param mountains
     */
    public void addMountainsOnMap( List<List<String>> createdMap, List<Mountain> mountains ) {
        for (Mountain mountain : mountains) {
            createdMap.get(mountain.getYPosition()).set(mountain.getXPosition(), "M");
        }
    }
    /**
     * add the treasure to the created map
     */
    public void addTreasuresOnMap(List<List<String>> createdMap, List<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            createdMap.get(treasure.getYPosition()).set(treasure.getXPosition(), "T"+treasure.getAmount());
        }
    }

    public void addAdventurersOnMap(List<List<String>> createdMap, List<Adventurer> adventurers) {
        for (Adventurer adventurer : adventurers) {
            createdMap.get(adventurer.getXPosition()).set(adventurer.getYPosition(), "A");
        }

    }

    public void displayMapInput(List<List<String>> createdMap) {
        for (List<String> line : createdMap) {
            for (String character : line) {
                if (character.charAt(0) == 'T') {
                    System.out.print(character+" ");
                } else{
                    System.out.print(character+"  ");
                }
            }
            System.out.println();
        }
    }

    public void displayMapOutput(List<String> outputLines) {
        for (String outputLine : outputLines){
                if (outputLine.charAt(0) == 'T') {
                    System.out.print(outputLine+" ");
                } else{
                    System.out.print(outputLine+"  ");
                }
            System.out.println();
        }
    }
}
