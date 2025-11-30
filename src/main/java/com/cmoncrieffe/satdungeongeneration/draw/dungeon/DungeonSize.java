package com.cmoncrieffe.satdungeongeneration.draw.dungeon;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.dice.DiceSize;

public enum DungeonSize {
    EXTRA_SMALL,
    SMALL,
    MEDIUM,
    LARGE,
    EXTRA_LARGE,
    RANDOM;

    public static int getDungeonRooms(DungeonSize size) {
        return switch (size) {
            case EXTRA_SMALL -> DiceRoller.roll1Based(DiceSize.D4) + 2;
            case SMALL -> DiceRoller.roll1Based(DiceSize.D6) + 4;
            case MEDIUM -> DiceRoller.roll1Based(DiceSize.D4, 4).stream().mapToInt(Integer::intValue).sum() + 6;
            case LARGE -> DiceRoller.roll1Based(DiceSize.D6, 5).stream().mapToInt(Integer::intValue).sum() + 12;
            case EXTRA_LARGE -> DiceRoller.roll1Based(DiceSize.D6, 10).stream().mapToInt(Integer::intValue).sum() + 24;
            case RANDOM -> getDungeonRooms(getDungeonSize());
        };
    }

    private static DungeonSize getDungeonSize() {
        int roll = DiceRoller.roll1Based(DiceSize.D20);
        if (roll <= 3)
            return EXTRA_SMALL;
        else if (roll <= 8)
            return SMALL;
        else if (roll <= 16)
            return MEDIUM;
        else if (roll <= 18)
            return LARGE;
        else
            return EXTRA_LARGE;
    }
}
