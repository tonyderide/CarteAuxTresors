package fr.deride.sbtreasuremap.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Treasure {
    private int xPosition;
    private int yPosition;
    private int amount;

    public Treasure(int x, int y, int a) {
        this.xPosition = x;
        this.yPosition = y;
        this.amount = a;
    }
    public Treasure() {
    }
}
