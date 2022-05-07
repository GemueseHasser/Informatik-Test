package de.jonas.informatik.object.material;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Eine {@link Brick} stellt einen Ziegelstein dar, bzw. eine Fläche an Ziegelsteinen, die in ihrer Größe variabel ist,
 * jedoch sind die Seitenverhältnisse immer 2 : 1, also die Fläche ist immer doppelt so breit, wie hoch.
 */
public final class Brick {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die X-Koordinate des Steins. */
    private final int posX;
    /** Die Y-Koordinate des Steins. */
    private final int posY;
    /** Die Länge (Breite) des Steins, aus der dann die Höhe berechnet wird. */
    private final int length;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz einer {@link Brick}. Eine {@link Brick} stellt einen
     * Ziegelstein dar, bzw. eine Fläche an Ziegelsteinen, die in ihrer Größe variabel ist, jedoch sind die
     * Seitenverhältnisse immer 2 : 1, also die Fläche ist immer doppelt so breit, wie hoch.
     *
     * @param posX   Die X-Koordinate des Steins.
     * @param posY   Die Y-Koordinate des Steins.
     * @param length Die Länge (Breite) des Steins, aus der dann die Höhe berechnet wird.
     */
    public Brick(
        final int posX,
        final int posY,
        final int length
    ) {
        this.posX = posX;
        this.posY = posY;
        this.length = length;
    }
    //</editor-fold>


    /**
     * Zeichnet diese {@link Brick} mithilfe einer bestimmten {@link Graphics}.
     *
     * @param g Die {@link Graphics}, auf dessen Grundlage diese {@link Brick} gezeichnet wird.
     */
    public void draw(final Graphics g) {
        // draw red background
        g.setColor(Color.RED);
        g.fillRect(posX, posY, length + 1, length / 2 + 1);

        // set color to black
        g.setColor(Color.BLACK);

        final int y = posY + ((length / 4) + (length / 8));

        for (int i = 0; i < 5; i++) {
            // horizontal lines
            g.drawLine(posX, posY + (i * (length / 8)), posX + length, posY + (i * (length / 8)));
            // vertical lines, row 1
            g.drawLine(posX + (i * (length / 4)), posY, posX + (i * (length / 4)), posY + (length / 8));
            // vertical lines, row 3
            g.drawLine(posX + (i * (length / 4)), posY + (length / 4), posX + (i * (length / 4)), y);
        }

        for (int i = 0; i < 4; i++) {
            // vertical lines, row 2
            final int x = posX + (i * (length / 4)) + (length / 8);

            g.drawLine(x, posY + (length / 8), x, posY + (length / 4));
            // vertical lines, row 4
            g.drawLine(x, y, x, posY + (length / 2));
        }
    }

    /**
     * Gibt die X-Koordinate dieses Steins zurück.
     *
     * @return Die X-Koordinate dieses Steins.
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Gibt die Y-Koordinate dieses Steins zurück.
     *
     * @return Die Y-Koordinate dieses Steins.
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Gibt die Länge (Breite) dieses Steins zurück.
     *
     * @return Die Länge (Breite) dieses Steins.
     */
    public int getLength() {
        return this.length;
    }

}
