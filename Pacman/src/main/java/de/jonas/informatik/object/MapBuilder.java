package de.jonas.informatik.object;

import de.jonas.informatik.object.material.Brick;
import de.jonas.informatik.object.material.BrickSelection;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
    private final List<Coordinate> coordinates = new ArrayList<>();
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
        for (final Coordinate coordinate : this.coordinates) {
            bricks.add(new Brick(coordinate.getX(), coordinate.getY(), BRICK_LENGTH));
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
        // fill map with bricks
        for (int x = 0; x < 575; x += BRICK_LENGTH) {
            for (int y = 0; y < 475; y += BRICK_LENGTH / 2) {
                this.coordinates.add(new Coordinate(x, y));
            }
        }
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
