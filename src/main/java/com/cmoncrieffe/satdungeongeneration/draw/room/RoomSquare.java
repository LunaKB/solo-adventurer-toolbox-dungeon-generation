package com.cmoncrieffe.satdungeongeneration.draw.room;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.dice.DiceSize;
import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonBounds;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomDirection;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomShape;

import java.awt.geom.Rectangle2D;

public class RoomSquare extends RoomDrawing {
    public RoomSquare(RoomShape roomShape, DungeonBounds bounds, RoomDrawing parent, int exitNumber) {
        super(roomShape, bounds, parent, exitNumber);
    }

    @Override
    void createShape() {
        if (roomShape == RoomShape.SQUARE1) {
            int size = DiceRoller.roll1Based(roomShape.getDimensions().get(0)) + roomShape.getDimensionModifiers().get(0);
            height = SIZE_MULTIPLIER * size;
            width = SIZE_MULTIPLIER * size;
        } else {
            int dimension1 = DiceRoller.roll1Based(roomShape.getDimensions().get(0)) + roomShape.getDimensionModifiers().get(0);
            int dimension2 = DiceRoller.roll1Based(roomShape.getDimensions().get(0)) + roomShape.getDimensionModifiers().get(0);
            if (DiceRoller.roll0Based(DiceSize.D100) % 2 == 0) {
                height = SIZE_MULTIPLIER * dimension1;
                width = SIZE_MULTIPLIER * dimension2;
            } else {
                height = SIZE_MULTIPLIER * dimension2;
                width = SIZE_MULTIPLIER * dimension1;
            }
        }

        RoomDirection roomDirection = parent.directions.get(exitNumber);
        Rectangle2D parentBounds = parent.getShape().getBounds2D();
        setY(parentBounds, roomDirection);
        setX(parentBounds, roomDirection);
        this.shape = new Rectangle2D.Double(x, y, width, height);
        generateExits();
    }
}
