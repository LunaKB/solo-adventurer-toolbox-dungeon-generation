package com.cmoncrieffe.satdungeongeneration.draw.room.properties;

import com.cmoncrieffe.dice.DiceSize;
import lombok.Getter;

import java.util.List;

@Getter
public enum RoomShape {
    RECTANGLE1(2, List.of(DiceSize.D4, DiceSize.D4), List.of(0, 0), DiceSize.D6),
    SQUARE1(4, List.of(DiceSize.D4), List.of(1), DiceSize.D4),
    SQUARE2(6, List.of(DiceSize.D6), List.of(1), DiceSize.D6),
    SQUARE3(8, List.of(DiceSize.D8), List.of(1), DiceSize.D8),
    RECTANGLE2(10, List.of(DiceSize.D4, DiceSize.D8), List.of(1, 1), DiceSize.D6),
    RECTANGLE3(12, List.of(DiceSize.D6, DiceSize.D6), List.of(1, 2), DiceSize.D6),
    CIRCLE(14, List.of(DiceSize.D4), List.of(2),  DiceSize.D4),
    TRIANGLE(15, List.of(DiceSize.D6), List.of(2), DiceSize.D4)
    ;

    private final List<DiceSize> dimensions;
    private final List<Integer> dimensionModifiers;
    private final DiceSize exits;
    private final int range;
    private RoomShape(int range, List<DiceSize> dimensions, List<Integer> dimensionModifiers, DiceSize exits) {
        this.range = range;
        this.dimensions = dimensions;
        this.dimensionModifiers = dimensionModifiers;
        this.exits = exits;
    }
}
