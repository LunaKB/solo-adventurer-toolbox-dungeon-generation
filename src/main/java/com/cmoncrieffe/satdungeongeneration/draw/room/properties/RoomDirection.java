package com.cmoncrieffe.satdungeongeneration.draw.room.properties;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.dice.DiceSize;

import java.util.List;

public enum RoomDirection {
    UP_LEFT, UP_CENTER, UP_RIGHT,
    DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT,
    LEFT_UP, LEFT_CENTER, LEFT_DOWN,
    RIGHT_UP, RIGHT_CENTER, RIGHT_DOWN;

    public static RoomDirection random() {
        return switch(DiceRoller.roll1Based(DiceSize.D12)) {
            case 1 -> UP_LEFT;
            case 2 -> UP_CENTER;
            case 3 -> UP_RIGHT;
            case 4 -> DOWN_LEFT;
            case 5 -> DOWN_CENTER;
            case 6 -> DOWN_RIGHT;
            case 7 -> LEFT_UP;
            case 8 -> LEFT_CENTER;
            case 9 -> LEFT_DOWN;
            case 10 -> RIGHT_UP;
            case 11 -> RIGHT_CENTER;
            default -> RIGHT_DOWN;
        };
    }

    public static List<RoomDirection> getOppositeDirections(RoomDirection roomDirection) {
        return switch (roomDirection) {
            case UP_LEFT, UP_CENTER, UP_RIGHT -> List.of(DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT);
            case DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT -> List.of(UP_LEFT, UP_CENTER, UP_RIGHT);
            case LEFT_UP, LEFT_CENTER, LEFT_DOWN -> List.of(RIGHT_UP, RIGHT_CENTER, RIGHT_DOWN);
            default -> List.of(LEFT_UP, LEFT_CENTER, LEFT_DOWN);
        };
    }
}
