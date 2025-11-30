package com.cmoncrieffe.satdungeongeneration.draw.room;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonBounds;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomDirection;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomShape;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class RoomTriangle extends RoomDrawing {
    public RoomTriangle(RoomShape roomShape, DungeonBounds bounds, RoomDrawing parent, int exitNumber) {
        super(roomShape, bounds, parent, exitNumber);
    }

    @Override
    void createShape() {
        Rectangle2D parentBounds = parent.getShape().getBounds2D();
        RoomDirection roomDirection = parent.directions.get(exitNumber);

        int dimension1 = DiceRoller.roll1Based(roomShape.getDimensions().get(0)) + roomShape.getDimensionModifiers().get(0);
        switch (roomDirection) {
            case UP_LEFT:
            case UP_CENTER:
            case UP_RIGHT:
            case DOWN_LEFT:
            case DOWN_CENTER:
            case DOWN_RIGHT:
                height = 10 * dimension1;
                width = parentBounds.getWidth();
                break;
            default:
                height = parentBounds.getHeight();
                width = 10 * dimension1;
                break;
        }

        setY(parentBounds, roomDirection);
        setX(parentBounds, roomDirection);

        shape = getPath(roomDirection);;
    }

    private Path2D.Double getPath(RoomDirection roomDirection) {
        Path2D.Double path = new Path2D.Double();
        switch (roomDirection) {
            case UP_CENTER:
                path.moveTo(x + (width / 2), y);
                path.lineTo(x, y + height);
                path.lineTo(x + width , y + height);
                path.closePath();
                break;
            case DOWN_CENTER:
                path.moveTo(x + (width / 2), y + height);
                path.lineTo(x, y);
                path.lineTo(x + width , y);
                path.closePath();
                break;
            case LEFT_CENTER:
                path.moveTo(x, y + (height / 2));
                path.lineTo(x + width, y + height);
                path.lineTo(x + width, y);
                path.closePath();
                break;
            case RIGHT_CENTER:
                path.moveTo(x, y);
                path.lineTo(x, y + height);
                path.lineTo(x + width, y + (height / 2));
                break;
            case UP_LEFT:
            case LEFT_UP:
                path.moveTo(x, y + height);
                path.lineTo(x + width, y + height);
                path.lineTo(x + width, y);
                path.closePath();
                break;
            case UP_RIGHT:
            case RIGHT_UP:
                path.moveTo(x, y + height);
                path.lineTo(x + width, y + height);
                path.lineTo(x, y);
                path.closePath();
                break;
            case DOWN_LEFT:
            case LEFT_DOWN:
                path.moveTo(x, y);
                path.lineTo(x + width, y);
                path.lineTo(x + width, y + height);
                path.closePath();
                break;
            case DOWN_RIGHT:
            case RIGHT_DOWN:
                path.moveTo(x, y);
                path.lineTo(x + width, y);
                path.lineTo(x, y + height);
                path.closePath();
                break;
        }
        return path;
    }
}
