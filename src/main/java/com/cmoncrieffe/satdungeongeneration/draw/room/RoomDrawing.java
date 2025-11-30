package com.cmoncrieffe.satdungeongeneration.draw.room;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonBounds;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomDirection;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomShape;
import lombok.Getter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;


public abstract class RoomDrawing {
    private static final int PADDING = 0;
    protected static final int SIZE_MULTIPLIER = 60;

    protected final RoomShape roomShape;
    protected final DungeonBounds bounds;
    protected final RoomDrawing parent;
    protected final int exitNumber;

    @Getter protected Map<Integer, RoomDirection> directions = new HashMap<>();
    @Getter protected Shape shape;
    protected double height;
    protected double width;
    protected double x;
    protected double y;

    RoomDrawing(RoomShape roomShape, DungeonBounds bounds, RoomDrawing parent,  int exitNumber) {
        this.roomShape = roomShape;
        this.bounds = bounds;
        this.parent = parent;
        this.exitNumber = exitNumber;
        create();
    }

    abstract void createShape();

    private void create() {
        boolean created = true;
        int tries = 0;
        do {
            tries++;
            if (tries == 10) {
                created = false;
                break;
            }
            createShape();
        } while (!bounds.isWithinBounds(x, y, width, height) || !bounds.isValid(shape));

        if (!created)
            shape = null;
    }

    protected void setY(Rectangle2D parent, RoomDirection roomDirection) {
        y = switch (roomDirection) {
            case UP_LEFT, UP_CENTER, UP_RIGHT -> parent.getY() - height - PADDING;
            case DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT -> parent.getY() + parent.getHeight() + PADDING;
            case LEFT_UP, RIGHT_UP -> parent.getY() - (height / 2);
            case LEFT_DOWN, RIGHT_DOWN -> parent.getY() + (parent.getHeight() / 2);
            default ->  parent.getY();
        };
    }

    protected void setX(Rectangle2D parent, RoomDirection roomDirection) {
        x = switch (roomDirection) {
            case LEFT_UP, LEFT_CENTER, LEFT_DOWN -> parent.getX() - width - PADDING;
            case RIGHT_UP, RIGHT_CENTER, RIGHT_DOWN -> parent.getX() + parent.getWidth() + PADDING;
            case UP_LEFT, DOWN_LEFT -> parent.getX() - (width / 2);
            case UP_RIGHT, DOWN_RIGHT -> parent.getX() + (parent.getWidth() / 2);
            default ->  parent.getX();
        };
    }

    protected void generateExits() {
        directions.clear();
        RoomDirection roomDirection = parent.directions.get(exitNumber);
        int exits = DiceRoller.roll1Based(roomShape.getExits());
        int count = 0;
        int tries = 0;
        while (count < exits && tries++ < 10) {
            RoomDirection childRoomDirection = RoomDirection.random();
            if (RoomDirection.getOppositeDirections(roomDirection).contains(childRoomDirection))
                continue;
            if (directions.containsValue(childRoomDirection))
                continue;
            directions.put(count, childRoomDirection);
            count++;
        }
    }
}
