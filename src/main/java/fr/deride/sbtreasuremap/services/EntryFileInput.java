package fr.deride.sbtreasuremap.services;

import fr.deride.sbtreasuremap.domain.Adventurer;
import fr.deride.sbtreasuremap.domain.Map;
import fr.deride.sbtreasuremap.domain.Mountain;
import fr.deride.sbtreasuremap.domain.Treasure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntryFileInput {
    /**
     * read a text file
     * delete all line beginning with #
     * @return list of String
     */
    public static List<String> readFile() {
        String file = "C:\\Projet\\sb-treasure-map\\src\\main\\resources\\test.txt";
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            list = stream.filter(line -> !line.startsWith("#"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return  list;
    }

    /**
     * get the Map size from the input file
     * @param lineList input file
     * @return a new Map
     */
    public static Map getMap(List<String> lineList) {
        Map map = new Map();
        lineList.forEach(line ->{
            if (line.startsWith("C")) {
                String[] split = line.split(" ");
                map.setWidth(Integer.parseInt(split[2]));
                map.setLength(Integer.parseInt(split[4]));
            }
        });
        return map;
    }

    /**
     * get the mountain coordinate from the input file
     * @param lineList input file
     * @return a list of Mountain
     */
    public static List<Mountain> getMountains(List<String> lineList) {
        List<Mountain>mountains = new ArrayList<>();
        lineList.forEach(line ->{
            if (line.startsWith("M")) {
                Mountain mountain = new Mountain();
                String[] split = line.split(" ");
                mountain.setXPosition(Integer.parseInt(split[2]));
                mountain.setYPosition(Integer.parseInt(split[4]));
                mountains.add(mountain);
            }
        });
        return mountains;
    }

    /**
     * get the treasures coordinate and amount from the input file
     * @param lineList input file
     * @return a list of Treasure
     */
    public static List<Treasure> getTreasures(List<String> lineList) {
        List<Treasure>treasures = new ArrayList<>();
        lineList.forEach(line ->{
            if (line.startsWith("T")) {
                Treasure treasure = new Treasure();
                String[] split = line.split(" ");
                treasure.setXPosition(Integer.parseInt(split[2]));
                treasure.setYPosition(Integer.parseInt(split[4]));
                treasure.setAmount(Integer.parseInt(split[6]));
                treasures.add(treasure);
            }
        });
        return treasures;
    }

    /**
     * get the adventurers coordinate name, position, direction and movement from the input file
     * @param lineList input file
     * @return  a list of Adventurer
     */
    public static List<Adventurer> getAdventurers(List<String> lineList) {
        List<Adventurer>adventurers = new ArrayList<>();
        lineList.forEach(line ->{
            Adventurer adventurer = new Adventurer();
            if (line.startsWith("A")) {
                String[] split = line.split(" ");
                adventurer.setName(split[2]);
                adventurer.setXPosition(Integer.parseInt(split[4]));
                adventurer.setYPosition(Integer.parseInt(split[6]));
                adventurer.setDirection(split[8]);
                adventurer.setMovementsOrder(split[10]);
                adventurers.add(adventurer);
            }
        });
        return adventurers;
    }
}
