package fr.deride.sbtreasuremap.services;

import fr.deride.sbtreasuremap.domain.Adventurer;
import fr.deride.sbtreasuremap.domain.Map;
import fr.deride.sbtreasuremap.domain.Mountain;
import fr.deride.sbtreasuremap.domain.Treasure;

import java.util.ArrayList;
import java.util.List;

public class SimulateRoute {
    private final String NORTH = "N";
    private final String SOUTH = "S";
    private final String EAST = "E";
    private final String WEST = "W";

    Adventurer advance( Adventurer adventurer,List<Mountain> mountains, List<Treasure> treasures) {
        switch (adventurer.getDirection()) {
            case NORTH:
                for (Mountain mountain : mountains) {
                    if(mountain.getXPosition() == adventurer.getXPosition() && mountain.getYPosition() == adventurer.getYPosition()-1) {
                        return adventurer;
                    }else {
                        //recalculate the position of the adventurer
                        adventurer.setYPosition(adventurer.getYPosition() - 1);
                        //test if the adventurer is on a treasure
                        advanceOnTreasures(adventurer, treasures);
                        return adventurer;
                    }
                }
            case EAST:
                for (Mountain mountain : mountains) {
                    if(mountain.getXPosition() == adventurer.getXPosition()+1 && mountain.getYPosition() == adventurer.getYPosition()) {
                        return adventurer;
                    }else {
                        //recalculate the position of the adventurer
                        adventurer.setXPosition(adventurer.getXPosition() + 1);
                        advanceOnTreasures(adventurer, treasures);
                        return adventurer;
                    }
                }

            case SOUTH:
                for (Mountain mountain : mountains) {
                    if (mountain.getXPosition() == adventurer.getXPosition() && mountain.getYPosition() == adventurer.getYPosition()+1) {
                        return adventurer;
                    }else {
                        //recalculate the position of the adventurer
                        adventurer.setYPosition(adventurer.getYPosition() + 1);
                        advanceOnTreasures(adventurer, treasures);
                        return adventurer;
                    }

                }
            case WEST:
                for (Mountain mountain : mountains) {
                    if (mountain.getXPosition() == adventurer.getXPosition()-1 && mountain.getYPosition() == adventurer.getYPosition()) {
                        return adventurer;
                    }else {
                        //recalculate the position of the adventurer
                        adventurer.setXPosition(adventurer.getXPosition() - 1);
                        advanceOnTreasures(adventurer, treasures);
                        return adventurer;
                    }

                }
            default:
                throw new IllegalStateException("Unexpected value: " + adventurer.getDirection());
        }
    }

    Adventurer turnLeft( Adventurer adventurer) {
        switch(adventurer.getDirection()) {
            case NORTH:
                adventurer.setDirection(WEST);
                break;
            case EAST:
                adventurer.setDirection(NORTH);
                break;
            case SOUTH:
                adventurer.setDirection(EAST);
                break;
            case WEST:
                adventurer.setDirection(SOUTH);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + adventurer.getDirection());
        }
        return adventurer;
    }

    Adventurer turnRight( Adventurer adventurer) {
        switch(adventurer.getDirection()) {
            case NORTH:
                adventurer.setDirection(EAST);
                break;
            case EAST:
                adventurer.setDirection(SOUTH);
                break;
            case SOUTH:
                adventurer.setDirection(WEST);
                break;
            case WEST:
                adventurer.setDirection(NORTH);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + adventurer.getDirection());
        }
        return adventurer;
    }

    public List<String> run(Map map, List<Mountain> mountains, List<Treasure> treasures, List<Adventurer> adventurers) {
        //for each adventurer
        for (Adventurer adventurer : adventurers) {
            char[] movements = adventurer.getMovementsOrder().toCharArray();
            //for each movement
            for (char movement : movements) {
                switch (movement) {
                    case 'A':
                        adventurer = advance( adventurer, mountains, treasures);
                        break;
                    case 'D':
                        adventurer = turnRight(adventurer);
                        break;
                    case 'G':
                        adventurer = turnLeft(adventurer);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + movement);
                }
            }
        }
        List<String> simulatedLines = new ArrayList<>();
        simulatedLines.add("C - " + map.getWidth() + " - " + map.getLength());
        for (Mountain mountain : mountains){
            simulatedLines.add("M - " + mountain.getXPosition() + " - " + mountain.getYPosition());
        }
        for (Treasure treasure : treasures){
            simulatedLines.add("T - " + treasure.getXPosition() + " - " + treasure.getYPosition() + " - " + treasure.getAmount());
        }
        for (Adventurer adventurer: adventurers){
            simulatedLines.add("A - " + adventurer.getName() + " - " + adventurer.getXPosition() + " - " +
                                        adventurer.getYPosition() + " - " + adventurer.getDirection() + " - " +
                                        adventurer.getTreasure());
        }
        return simulatedLines;
    }

    private void advanceOnTreasures(Adventurer adventurer, List<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            if (treasure.getXPosition() == adventurer.getXPosition() && treasure.getYPosition() == adventurer.getYPosition()) {
                treasure.setAmount(treasure.getAmount() - 1);
                adventurer.setTreasure(adventurer.getTreasure()+1);
            }
            if (treasure.getAmount() == 0) {
                treasures.remove(treasure);
            }
        }
    }

}
