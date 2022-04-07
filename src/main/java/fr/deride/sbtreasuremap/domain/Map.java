package fr.deride.sbtreasuremap.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Map {
    private int length;
    private int width;

    public Map(int l, int w) {
        this.length = l;
        this.width = w;
    }
    public Map() {
    }
}
