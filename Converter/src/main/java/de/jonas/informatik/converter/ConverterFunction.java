package de.jonas.informatik.converter;

import java.util.LinkedList;
import java.util.Map;

/**
 * Eine {@link ConverterFunction} kann genutzt werden, um Zahlen eines Zahlensystems in ein anderes Zahlensystem zu
 * übersetzen.
 */
public final class ConverterFunction {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Systematik, mit der dieses Zahlensystem aufgebaut ist (Anzahl an Zahlen). */
    private final int system;
    /** Alle Zahlen, die dieses Zahlensystem beinhaltet (null, wenn normale Dezimalzahlen genutzt werden). */
    private final Map<Integer, String> newNumberSystem;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige {@link ConverterFunction}. Eine {@link ConverterFunction} kann
     * genutzt werden, um Zahlen eines Zahlensystems in ein anderes Zahlensystem zu übersetzen.
     *
     * @param system          Die Systematik, mit der dieses Zahlensystem aufgebaut ist (Anzahl an Zahlen).
     * @param newNumberSystem Alle Zahlen, die dieses Zahlensystem beinhaltet (null, wenn normale Dezimalzahlen genutzt
     *                        werden).
     */
    public ConverterFunction(
        final int system,
        final Map<Integer, String> newNumberSystem
    ) {
        this.system = system;
        this.newNumberSystem = newNumberSystem;
    }
    //</editor-fold>


    /**
     * Konvertiert eine bestimmte Dezimalzahl gemäß dieses Zahlensystems und gibt diese dann als String zurück.
     *
     * @param number Die Dezimalzahl, die in dieses Zahlensystem übersetzt werden soll.
     *
     * @return Die konvertierte Dezimalzahl in diesem Zahlensystem.
     */
    public String convert(final int number) {
        final LinkedList<String> convertedNumbers = new LinkedList<>();
        int tempResult = number;

        if (newNumberSystem == null || newNumberSystem.isEmpty()) {
            while (tempResult > 0) {
                convertedNumbers.addFirst(String.valueOf(tempResult % this.system));
                tempResult = tempResult / this.system;
            }
        } else {
            while (tempResult > 0) {
                convertedNumbers.addFirst(newNumberSystem.get(tempResult % this.system));
                tempResult = tempResult / this.system;
            }
        }

        final StringBuilder builder = new StringBuilder();

        for (final String convertedNumber : convertedNumbers) {
            builder.append(convertedNumber);
        }

        return builder.toString();
    }

    /**
     * Gibt die Systematik, mit der dieses Zahlensystem aufgebaut ist (Anzahl an Zahlen) zurück.
     *
     * @return Die Systematik, mit der dieses Zahlensystem aufgebaut ist (Anzahl an Zahlen).
     */
    public int getSystemIdentifier() {
        return this.system;
    }

}
