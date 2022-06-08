package de.jonas.informatik.object;

/**
 * Eine {@link Coordinate Koordinate} besteht aus einer X- und einer Y-Koordinate.
 */
public final class Coordinate {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die X-Koordinate dieses Koordinaten-Punktes. */
    private final int x;
    /** Die Y-Koordinate dieses Koordinaten-Punktes. */
    private final int y;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe einer X- und einer Y-Koordinate eine neue und vollständig unabhängige Instanz einer
     * {@link Coordinate Koordinate}.
     *
     * @param x Die X-Koordinate dieses Koordinaten-Punktes.
     * @param y Die Y-Koordinate dieses Koordinaten-Punktes.
     */
    public Coordinate(
        final int x,
        final int y
    ) {
        this.x = x;
        this.y = y;
    }
    //</editor-fold>


    /**
     * Gibt die X-Koordinate dieses Koordinaten-Punktes zurück.
     *
     * @return Die X-Koordinate dieses Koordinaten-Punktes.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gibt die Y-Koordinate dieses Koordinaten-Punktes zurück.
     *
     * @return Die Y-Koordinate dieses Koordinaten-Punktes.
     */
    public int getY() {
        return this.y;
    }

}
