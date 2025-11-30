package com.cmoncrieffe.satdungeongeneration.draw.dungeon;

import com.cmoncrieffe.satdungeongeneration.draw.room.tree_node.DrawingNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
public class DungeonBounds {
    private static final int PADDING = 20;

    private final int imageWidth;
    private final int imageHeight;
    private final int maxRooms;

    @Setter
    private DrawingNode rootDrawingNode;

    public DungeonBounds(DungeonSize dungeonSize, int imageWidth, int imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.maxRooms = DungeonSize.getDungeonRooms(dungeonSize);
        System.out.printf("CLM ----------%d Rooms------------------\n", maxRooms);
    }

    public double getTop() {
        return PADDING;
    }

    public double getBottom() {
        return imageHeight - PADDING;
    }

    public double getLeft() {
        return PADDING;
    }

    public double getRight() {
        return imageWidth - PADDING;
    }

    public boolean isWithinBounds(double x, double y, double width, double height) {
        if (y < getTop()) return false;
        if (y > getBottom() || y + height > getBottom()) return false;
        if (x < getLeft()) return false;
        if (x > getRight() || x + width > getRight()) return false;
        return true;
    }

    public boolean isValid(Shape shape) {
        boolean intersects = intersects(shape, rootDrawingNode);
        return !intersects;
    }

    private boolean intersects(Shape shape, DrawingNode drawingNode) {
        if (drawingNode == null) return false;

        boolean intersects = false;
        if (drawingNode.getData().getShape().intersects(shape.getBounds2D()))
            intersects = true;
        else {
            for (DrawingNode child : drawingNode.getChildren()) {
                intersects = intersects(shape, child);
                if (intersects) break;
            }
        }
        return intersects;
    }
}
