package de.jonas.informatik.object;

import de.jonas.informatik.object.material.Brick;
import de.jonas.informatik.object.material.BrickSelection;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mithilfe eines {@link MapBuilder} wird eine Map zusammengesetzt, die als Spielfeld in diesem Spiel dient. Sie wird in
 * Form eines {@link BufferedImage Bildes} als Hintergrund eingefügt.
 */
public final class MapBuilder {

    //<editor-fold desc="CONSTANTS">
    /** Die Länge aller Ziegel, die in die Map gesetzt werden. */
    private static final int BRICK_LENGTH = 50;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die Koordinaten aller Ziegel, die auf die Map gesetzt werden sollen. */
    private final Map<Integer, Integer> coordinates = new HashMap<>();
    /** Die Karte, die schlussendlich entsteht. */
    private final BufferedImage map;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link MapBuilder}. Mithilfe eines {@link MapBuilder}
     * wird eine Map zusammengesetzt, die als Spielfeld in diesem Spiel dient. Sie wird in Form eines
     * {@link BufferedImage Bildes} als Hintergrund eingefügt.
     */
    public MapBuilder() {
        // build map
        build();

        // create brick list
        final List<Brick> bricks = new ArrayList<>();

        // fill brick list
        for (final Map.Entry<Integer, Integer> entry : coordinates.entrySet()) {
            final int x = entry.getKey();
            final int y = entry.getValue();

            bricks.add(new Brick(x, y, BRICK_LENGTH));
        }

        // create one object from all bricks
        final BrickSelection mapBrickSelection = new BrickSelection(bricks);

        // create final map
        map = mapBrickSelection.createImage();
    }
    //</editor-fold>


    /**
     * Legt alle einzelnen Koordinaten fest, an denen Ziegel auf die Map gesetzt werden sollen.
     */
    private void build() {
        // left upper corner
        this.coordinates.put(0, 0);
        // lower right corner
        this.coordinates.put(540, 442);
    }

    /**
     * Gibt die fertig zusammengesetzte und als Bild konvertierte Map zurück.
     *
     * @return Die fertig zusammengesetzte und als Bild konvertierte Map.
     */
    public BufferedImage getBuiltMap() {
        return this.map;
    }

}
