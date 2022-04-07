package fr.deride.sbtreasuremap.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Adventurer {
    private String       name;
    private int          yPosition;
    private int          xPosition;
    private String       direction;
    private String       movementsOrder;
    private int          treasure;

    public Adventurer(String name, int y, int x, String d, String movements, int treasure) {
        this.name = name;
        this.yPosition = y;
        this.xPosition = x;
        this.direction = d;
        this.movementsOrder = movements;
        this.treasure = 0;
    }
    public Adventurer() {
    }
}
