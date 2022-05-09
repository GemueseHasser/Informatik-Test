package de.jonas.informatik.object.material;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Eine {@link BrickSelection} stellt eine beliebige Anzahl an {@link Brick} dar, welche in einem Objekt zusammengefasst
 * ist, um eine bessere Performance zu garantieren. Man kann mehrere Brick-Objekte mithilfe einer {@link BrickSelection}
 * zu einem Bild verschmelzen lassen, welches man dann einmal speichern kann und nicht immer wieder auf viele einzelne
 * Objekte zurückgreifen muss.
 */
public final class BrickSelection {

    //<editor-fold desc="LOCAL FIELDS">
    /** Alle {@link Brick Bricks}, welche in dieser {@link BrickSelection} zusammengefasst werden sollen. */
    private final List<Brick> bricks;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe einer Liste, welche alle {@link Brick Bricks} beinhaltet, welche in dieser {@link
     * BrickSelection} zusammengefasst werden sollen, eine neue und vollständig unabhängige Instanz einer neuen {@link
     * BrickSelection}. Eine {@link BrickSelection} stellt eine beliebige Anzahl an {@link Brick} dar, welche in einem
     * Objekt zusammengefasst ist, um eine bessere Performance zu garantieren. Man kann mehrere Brick-Objekte mithilfe
     * einer {@link BrickSelection} zu einem Bild verschmelzen lassen, welches man dann einmal speichern kann und nicht
     * immer wieder auf viele einzelne Objekte zurückgreifen muss.
     *
     * @param bricks Alle {@link Brick Bricks}, welche in dieser {@link BrickSelection} zusammengefasst werden sollen.
     */
    public BrickSelection(final List<Brick> bricks) {
        this.bricks = bricks;
    }
    //</editor-fold>


    /**
     * Erzeugt das Bild dieser {@link BrickSelection} und lässt somit alle einzelnen Brick-Objekte zu einem Bild
     * verschmelzen.
     *
     * @return Das fertig generierte Bild aus allen Bricks.
     */
    public BufferedImage createImage() {
        // calculate image size
        final int width = getMaxX() - getMinX();
        final int height = getMaxY() - getMinY();

        // create new buffered image
        final BufferedImage image = new BufferedImage(
            width,
            height,
            BufferedImage.TYPE_INT_RGB
        );

        // create graphics
        final Graphics2D g = image.createGraphics();

        // draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // draw brick selection
        for (final Brick brick : this.bricks) {
            brick.draw(g);
        }

        // return created image
        return image;
    }

    /**
     * Gibt die minimale X-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden,
     * zurück.
     *
     * @return Die minimale X-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden.
     */
    private int getMinX() {
        int currentMin = Integer.MAX_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosX() < currentMin) {
                currentMin = brick.getPosX();
            }
        }

        return currentMin;
    }

    /**
     * Gibt die maximale X-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden,
     * zurück.
     *
     * @return Die maximale X-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden.
     */
    private int getMaxX() {
        int currentMax = Integer.MIN_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosX() + brick.getLength() > currentMax) {
                currentMax = brick.getPosX() + brick.getLength();
            }
        }

        return currentMax;
    }

    /**
     * Gibt die minimale Y-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden,
     * zurück.
     *
     * @return Die minimale Y-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden.
     */
    private int getMinY() {
        int currentMin = Integer.MAX_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosY() < currentMin) {
                currentMin = brick.getPosY();
            }
        }

        return currentMin;
    }

    /**
     * Gibt die maximale Y-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden,
     * zurück.
     *
     * @return Die maximale Y-Koordinate aller Bricks, welche mithilfe dieser {@link BrickSelection} verarbeitet werden.
     */
    private int getMaxY() {
        int currentMax = Integer.MIN_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosY() + (brick.getLength() / 2) > currentMax) {
                currentMax = brick.getPosY() + (brick.getLength() / 2);
            }
        }

        return currentMax;
    }

}
