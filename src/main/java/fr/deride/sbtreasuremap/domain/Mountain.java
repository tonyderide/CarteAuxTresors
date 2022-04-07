package fr.deride.sbtreasuremap.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mountain {
    private int yPosition;
    private int xPosition;

    public Mountain(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }
    public Mountain() {
    }
}
