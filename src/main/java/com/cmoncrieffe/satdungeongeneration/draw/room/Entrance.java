package com.cmoncrieffe.satdungeongeneration.draw.room;

import com.cmoncrieffe.dice.DiceRoller;
import com.cmoncrieffe.dice.DiceSize;
import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonBounds;
import com.cmoncrieffe.satdungeongeneration.draw.room.properties.RoomDirection;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Entrance extends RoomDrawing {
    public Entrance(DungeonBounds bounds) {
        super(null, bounds, null, -1);
    }

    @Override
    void createShape() {
        List<Integer> startingXs = new ArrayList<>();
        List<Integer> startingYs = new ArrayList<>();

        for (int multiplier = 5; multiplier < 15; multiplier++) {
            startingXs.add(multiplier * 44);
            startingYs.add(multiplier * 53);
        }

        x = startingXs.get(DiceRoller.roll0Based(DiceSize.D10));
        y = startingYs.get(DiceRoller.roll0Based(DiceSize.D10));
        shape = new Rectangle2D.Double(x, y, 100, 100);

        if (x + shape.getBounds2D().getWidth() > bounds.getImageWidth()) {
            double offset = (x + shape.getBounds2D().getWidth()) - bounds.getImageWidth();
            x = x - offset;
            shape = new Rectangle2D.Double(x, y, 100, 100);
        }
        if (y + shape.getBounds2D().getHeight() > bounds.getImageHeight()) {
            double offset = (y + shape.getBounds2D().getHeight()) - bounds.getImageHeight();
            y = y - offset;
            shape = new Rectangle2D.Double(x, y, 100, 100);
        }

        generateExits();
    }

    @Override
    protected void generateExits() {
        directions.clear();
        int exits = DiceRoller.roll1Based(DiceSize.D4);
        int count = 0;
        int tries = 0;
        while (count < exits && tries++ < 10) {
            RoomDirection childRoomDirection = RoomDirection.random();
            if (directions.containsValue(childRoomDirection))
                continue;
            directions.put(count, childRoomDirection);
            count++;
        }
    }
}
