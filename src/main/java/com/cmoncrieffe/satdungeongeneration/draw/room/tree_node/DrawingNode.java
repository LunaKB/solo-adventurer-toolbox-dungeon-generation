package com.cmoncrieffe.satdungeongeneration.draw.room.tree_node;

import com.cmoncrieffe.satdungeongeneration.draw.room.RoomDrawing;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DrawingNode {
    private RoomDrawing data;
    private List<DrawingNode> children = new ArrayList<>();

    public DrawingNode(RoomDrawing roomDrawing) {
        data = roomDrawing;
    }

    public static List<DrawingNode> getNodesAtLevel(DrawingNode drawingNode, int level) {
        if (level == 0)
            return List.of(drawingNode);
        else
            return getNodesAtLevel(drawingNode, level, 1);
    }

    private static List<DrawingNode> getNodesAtLevel(DrawingNode drawingNode, int targetLevel, int currentLevel) {
        if (currentLevel == targetLevel)
            return drawingNode.getChildren();
        else {
            List<DrawingNode> children = new ArrayList<>();
            for (DrawingNode child : drawingNode.getChildren()) {
                children.addAll(getNodesAtLevel(child, targetLevel, currentLevel + 1));
            }
            return children;
        }
    }
}
