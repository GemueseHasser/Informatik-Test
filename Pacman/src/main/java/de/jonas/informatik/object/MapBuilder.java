package de.jonas.informatik.object;

import de.jonas.informatik.object.material.Brick;
import de.jonas.informatik.object.material.BrickSelection;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MapBuilder {

    private final BufferedImage map;


    public MapBuilder(
        final Map<Integer, Integer> coordinates,
        final int brickLength
    ) {
        final List<Brick> bricks = new ArrayList<>();

        for (final Map.Entry<Integer, Integer> entry : coordinates.entrySet()) {
            final int x = entry.getKey();
            final int y = entry.getValue();

            bricks.add(new Brick(x, y, brickLength));
        }

        final BrickSelection mapBrickSelection = new BrickSelection(bricks);
        map = mapBrickSelection.createImage();
    }


    public BufferedImage getBuiltMap() {
        return this.map;
    }

}
