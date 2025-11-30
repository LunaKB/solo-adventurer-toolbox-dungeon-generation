package com.cmoncrieffe.satdungeongeneration.draw.dungeon;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.dice.DiceSize;
import com.cmoncrieffe.satdungeongeneration.draw.room.*;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomShape;
import com.cmoncrieffe.satdungeongeneration.draw.room.tree_node.DrawingNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class DungeonDrawing {
    private final DungeonBounds bounds;
    private final boolean isDebug;

    public DungeonDrawing(DungeonSize dungeonSize) {
        bounds = new DungeonBounds(dungeonSize, 1000, 1000);
        isDebug = Boolean.parseBoolean(System.getenv("DEBUG_DEV"));
    }

    public byte[] get() {
        BufferedImage image = new BufferedImage(bounds.getImageWidth(), bounds.getImageHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!isDebug) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, bounds.getImageWidth(), bounds.getImageHeight());
        }

        draw(graphics);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }

    private void draw(Graphics2D graphics) {
        bounds.setRootDrawingNode(new DrawingNode(new Entrance(bounds)));

        int count = 1;
        int nodeLevel = 0;
        while (count <= bounds.getMaxRooms()) {
            List<DrawingNode> nodes = DrawingNode.getNodesAtLevel(bounds.getRootDrawingNode(), nodeLevel);
            if (nodes.isEmpty())
                break;

            for (DrawingNode parentNode : nodes) {
                for (Integer key : parentNode.getData().getDirections().keySet()) {
                    RoomDrawing child = RoomDrawingFactory.getDrawing(getRoomShape(), bounds, parentNode.getData(), key);
                    if (child.getShape() != null) {
                        DrawingNode childNode = new DrawingNode(child);
                        parentNode.getChildren().add(childNode);

                        count++;
                        if (count >= bounds.getMaxRooms())
                            break;
                    }
                }
            }
            nodeLevel++;
        }

        draw(graphics, bounds.getRootDrawingNode());

        if (isDebug) {
            graphics.setColor(Color.WHITE);
            graphics.fill(bounds.getRootDrawingNode().getData().getShape());
        }
    }

    private RoomShape getRoomShape() {
        int roll = DiceRoller.roll1Based(DiceSize.D20);
        return switch (roll) {
            case 1, 2, 16, 17 -> RoomShape.RECTANGLE1;
            case 3, 4, 18 -> RoomShape.SQUARE1;
            case 5, 6, 19 -> RoomShape.SQUARE2;
            case 7, 8, 20 -> RoomShape.SQUARE3;
            case 9, 10 -> RoomShape.RECTANGLE2;
            case 11, 12 -> RoomShape.RECTANGLE3;
            case 13, 14 -> RoomShape.CIRCLE;
            default -> RoomShape.TRIANGLE;
        };
    }

    private void draw(Graphics2D graphics, DrawingNode drawingNode) {
        RoomDrawing roomDrawing = drawingNode.getData();

        graphics.setColor(getColor());
        graphics.fill(roomDrawing.getShape());
        graphics.setColor(getBorderColor());
        graphics.draw(roomDrawing.getShape());

        for (DrawingNode child : drawingNode.getChildren())
            draw(graphics, child);
    }

    private Color getColor() {
        if (isDebug) {
            Random random = new Random();
            return new Color(
                    random.nextFloat(),
                    random.nextFloat(),
                    random.nextFloat()
            );
        } else
            return Color.GRAY;
    }

    private Color getBorderColor() {
        if (isDebug)
            return Color.WHITE;
        else
            return Color.BLACK;
    }
}
