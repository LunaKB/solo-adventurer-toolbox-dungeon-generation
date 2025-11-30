package com.cmoncrieffe.satdungeongeneration.draw.room;

import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonBounds;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomShape;

public class RoomDrawingFactory {
    public static RoomDrawing getDrawing(RoomShape roomShape, DungeonBounds bounds, RoomDrawing parent, int exitNumber) {
        return switch (roomShape) {
            case SQUARE1,
                 SQUARE2,
                 SQUARE3,
                 RECTANGLE1,
                 RECTANGLE2,
                 RECTANGLE3 -> new RoomSquare(roomShape, bounds, parent, exitNumber);
            case CIRCLE -> new RoomCircle(roomShape, bounds, parent, exitNumber);
            case TRIANGLE -> new RoomTriangle(roomShape, bounds, parent, exitNumber);
        };
    }
}
