package de.jonas.informatik.math;

/**
 * Eine {@link Operation} hat keine Parameter. Sie dient dazu eine schnelle Rechen-Operation durchführen zu können,
 * welche klar definiert ist.
 */
public enum Operation {

    //<editor-fold desc="VALUES">
    /** Die {@link Operation Rechen-Operation}, mit der addiert wird. */
    ADD,
    /** Die {@link Operation Rechen-Operation}, mit der subtrahiert wird. */
    SUBTRACT,
    /** Die {@link Operation Rechen-Operation}, mit der multipliziert wird. */
    MULTIPLY,
    /** Die {@link Operation Rechen-Operation}, mit der dividiert wird. */
    DIVIDE;
    //</editor-fold>


    /**
     * Führt die momentane {@link Operation Rechen-Operation} mit zwei angegebenen Werten aus. Die zwei angegebenen
     * Werte sind einmal der Grundwert und der Wert, mit dessen Hilfe die Operation ausgeführt wird und der
     * Grundwert verändert wird.
     *
     * @param basic Der Grundwert, welcher verändert werden soll.
     * @param value Der Wert, mit dessen Hilfe die Operation ausgeführt wird.
     *
     * @return Der veränderte Grundwert.
     */
    public double getOperatedNumber(final double basic, final double value) {
        switch (this) {
            case ADD:
                return basic + value;

            case SUBTRACT:
                return basic - value;

            case MULTIPLY:
                return basic * value;

            case DIVIDE:
                return basic / value;

            default:
                return 0;
        }
    }
}
