package de.jonas.informatik.handler;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Mithilfe eines {@link CalculationHandler} können alle Werte der Eulerschen Konstante in einem bestimmten Intervall
 * berechnet werden.
 */
public final class CalculationHandler {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Spanne von 1 beginnend, zwischen der alle Werte der Eulerschen Konstante berechnet werden. */
    private final int calculationSpan;
    /** Alle Werte dieses {@link CalculationHandler}. */
    private final NavigableMap<Integer, Double> calculatedValues;
    /** Der beste berechnete Näherungswert der Eulerschen Konstante. */
    private final String approximate;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link CalculationHandler}, womit alle Werte der
     * Eulerschen Konstante in einem bestimmten Intervall berechnet werden können.
     *
     * @param calculationSpan Die Spanne von 1 beginnend, zwischen der alle Werte der Eulerschen Konstante berechnet
     *                        werden.
     */
    public CalculationHandler(final int calculationSpan) {
        this.calculationSpan = calculationSpan;
        this.calculatedValues = new TreeMap<>();

        // calculate values
        for (int i = 1; i < this.calculationSpan; i++) {
            this.calculatedValues.put(i, getValue(i));
        }

        // initialize best calculated approximate
        final double approximateValue = this.calculatedValues.lastEntry().getValue();

        // get last and last * 4 value
        final String lastValue = String.valueOf(this.calculatedValues.lastEntry().getValue());
        final String higherValue = String.valueOf(getValue(this.calculationSpan * 4));

        // create string from double
        String tempApproximate = String.valueOf(approximateValue);

        // calculate accuracy from best calculated approximate
        for (int i = 0; i < 16; i++) {
            if (lastValue.charAt(2 + i) != higherValue.charAt(2 + i)) {
                tempApproximate = tempApproximate.substring(0, 2 + i) + "(" + tempApproximate.substring(2 + i) + ")";
                break;
            }
        }

        this.approximate = tempApproximate;
    }
    //</editor-fold>


    /**
     * Gibt den Funktionswert der Eulerschen Konstante für die Stelle n zurück.
     *
     * @param n Die Stelle n, für die der Funktionswert der Eulerschen Konstante berechnet werden soll.
     *
     * @return Der Funktionswert der Eulerschen Konstante für die Stelle n.
     */
    public double getValue(final double n) {
        double current = 0;

        for (double i = 1; i <= n; i++) {
            current += 1 / i;
        }

        return current - Math.log(n);
    }

    /**
     * Gibt alle Werte der Eulerschen Konstante in einer bestimmten Spanne, die bei der Instanziierung eines
     * {@link CalculationHandler} berechnet werden, abgespeichert in einer Map zurück.
     *
     * @return Alle Werte der Eulerschen Konstante in einer bestimmten Spanne, die bei der Instanziierung eines
     *     {@link CalculationHandler} berechnet werden, abgespeichert in einer Map.
     */
    public NavigableMap<Integer, Double> getCalculatedValues() {
        return this.calculatedValues;
    }

    /**
     * Gibt die Spanne von 1 beginnend, zwischen der alle Werte der Eulerschen Konstante berechnet werden zurück.
     *
     * @return Die Spanne von 1 beginnend, zwischen der alle Werte der Eulerschen Konstante berechnet werden.
     */
    public int getCalculationSpan() {
        return this.calculationSpan;
    }

    /**
     * Gibt den besten berechneten Näherungswert der Eulerschen Konstante zurück.
     *
     * @return Der beste berechnete Näherungswert der Eulerschen Konstante.
     */
    public String getApproximate() {
        return this.approximate;
    }

}
