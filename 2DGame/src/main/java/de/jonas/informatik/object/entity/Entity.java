package de.jonas.informatik.object.entity;

import java.awt.Graphics;

/**
 * Ein {@link Entity} stellt eine Instanz einer Entität dar, welche in jeglicher äußerer Erscheinungsform existieren
 * kann und mit dem Spiel interagieren kann.
 */
public abstract class Entity {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die X-Koordinate dieser Entität. */
    private int x;
    /** Die Y-Koordinate dieser Entität. */
    private int y;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Entity}. Ein {@link Entity} stellt eine
     * Instanz einer Entität dar, welche in jeglicher äußerer Erscheinungsform existieren kann und mit dem Spiel
     * interagieren kann.
     *
     * @param x Die anfängliche X-Koordinate dieser Entität.
     * @param y Die anfängliche Y-Koordinate dieser Entität.
     */
    public Entity(
        final int x,
        final int y
    ) {
        this.x = x;
        this.y = y;
    }
    //</editor-fold>


    /**
     * Zeichnet diese Entität mithilfe einer bestimmten {@link Graphics}.
     *
     * @param g Die {@link Graphics} mit dessen Hilfe diese Entität gezeichnet wird.
     */
    public abstract void draw(final Graphics g);

    /**
     * Gibt die X-Koordinate dieser Entität zurück.
     *
     * @return Die X-Koordinate dieser Entität.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gibt die Y-Koordinate dieser Entität zurück.
     *
     * @return Die Y-Koordinate dieser Entität.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Setzt die X-Koordinate dieser Entität auf einen bestimmten X-Wert.
     *
     * @param x Der X-Wert, auf den die aktuelle X-Koordinate dieser Entität gesetzt werden soll.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Setzt die Y-Koordinate dieser Entität auf einen bestimmten Y-Wert.
     *
     * @param y Der Y-Wert, auf den die aktuelle Y-Koordinate dieser Entität gesetzt werden soll.
     */
    public void setY(final int y) {
        this.y = y;
    }

}
