package de.jonas.informatik.tictactoe.object;

/**
 * Ein {@link Pair Paar} besteht aus zwei Objekten, welche zu Beginn gesetzt werden können, jedoch im Nachhinein nicht
 * mehr verändert werden können. Dieses Paar bietet somit einfach nur die Möglichkeit zwei Objekte zusammen
 * abzuspeichern.
 */
public final class Pair<First, Second> {

    //<editor-fold desc="LOCAL FIELDS">
    /** Das erste Objekt dieses Paars. */
    private final First first;
    /** Das zweite Objekt dieses Paars. */
    private final Second second;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt ein neues und vollständig unabhängiges {@link Pair Paar}. Ein {@link Pair Paar} besteht aus zwei
     * Objekten, welche zu Beginn gesetzt werden können, jedoch im Nachhinein nicht mehr verändert werden können. Dieses
     * Paar bietet somit einfach nur die Möglichkeit zwei Objekte zusammen abzuspeichern.
     *
     * @param first  Das erste Objekt dieses Paars.
     * @param second Das zweite Objekt dieses Paars.
     */
    public Pair(
        final First first,
        final Second second
    ) {
        this.first = first;
        this.second = second;
    }
    //</editor-fold>


    /**
     * Gibt das erste Objekt dieses Paars zurück.
     *
     * @return Das erste Objekt dieses Paars.
     */
    public First getFirst() {
        return this.first;
    }

    /**
     * Gibt das zweite Objekt dieses Paars zurück.
     *
     * @return Das zweite Objekt dieses Paars.
     */
    public Second getSecond() {
        return this.second;
    }

}
