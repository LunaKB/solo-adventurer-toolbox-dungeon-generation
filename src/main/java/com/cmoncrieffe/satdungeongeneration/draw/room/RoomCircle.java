package com.cmoncrieffe.satdungeongeneration.draw.room;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonBounds;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomDirection;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomShape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class RoomCircle extends RoomDrawing {
    public RoomCircle(RoomShape roomShape, DungeonBounds bounds, RoomDrawing parent, int exitNumber) {
        super(roomShape, bounds, parent, exitNumber);
    }

    @Override
    void createShape() {
        int size = DiceRoller.roll1Based(roomShape.getDimensions().get(0)) + roomShape.getDimensionModifiers().get(0);
        height = SIZE_MULTIPLIER * size;
        width = SIZE_MULTIPLIER * size;

        RoomDirection roomDirection = parent.directions.get(exitNumber);
        Rectangle2D parentBounds = parent.getShape().getBounds2D();
        setY(parentBounds, roomDirection);
        setX(parentBounds, roomDirection);
        shape = new Ellipse2D.Double(x, y, width, height);

        generateExits();
    }
}
