package de.jonas.informatik.math;

/**
 * Eine {@link Number} ist eine Dezimalzahl, welche auf einem {@link Double} basiert. Mit jeder neuen Instanz einer
 * solchen Zahl, kann gerechnet werden und die Zahl kann auf verschiedene Zustände geprüft werden, mithilfe den Methoden
 * in dieser Klasse, welche für jede Instanz mit erzeugt werden.
 */
public final class Number {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Zahl, basierend auf einem {@link Double}, worauf die gesamte {@link Number} basiert. */
    private double number;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe einer Dezimalzahl eine neue und vollständig unabhängige {@link Number}. Eine {@link Number} ist
     * eine Dezimalzahl, welche auf einem {@link Double} basiert. Mit jeder neuen Instanz einer solchen Zahl, kann
     * gerechnet werden und die Zahl kann auf verschiedene Zustände geprüft werden, mithilfe den Methoden in dieser
     * Klasse, welche für jede Instanz mit erzeugt werden.
     *
     * @param number Die Dezimalzahl, mit der diese {@link Number} instanziiert wird.
     */
    public Number(final double number) {
        this.number = number;
    }
    //</editor-fold>


    /**
     * Führt eine bestimmte {@link Operation Rechen-Operation} aus, mithilfe von vorgegebenen {@link Operation
     * Operationen} und einer {@link Number}, in Form einer Dezimalzahl, welche für jene Operation genutzt wird.
     *
     * @param operation Die {@link Operation Rechen-Operation}, welche ausgeführt werden soll.
     * @param value     Der Zahlenwert, welcher für diese Operation genutzt wird.
     */
    public Number getOperatedNumber(final Operation operation, final Number value) {
        return new Number(operation.getOperatedNumber(this.number, value.getNumber()));
    }

    /**
     * Potenziert die aktuelle {@link Number} um einen bestimmten Zahlenwert.
     *
     * @param power Der Exponent.
     */
    public void raiseToPower(final int power) {
        // save current number
        final double currentNumber = this.number;

        // raise number to current power (with current number)
        for (int i = 1; i < power; i++) {
            this.number *= currentNumber;
        }
    }

    /**
     * Prüft, ob die aktuelle {@link Number} gerade ist.
     *
     * @return Wenn die aktuelle Zahl gerade ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isEven() {
        return this.number % 2 == 0;
    }

    /**
     * Prüft, ob die aktuelle {@link Number} ungerade ist.
     *
     * @return Wenn die aktuelle Zahl ungerade ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isOdd() {
        return !isEven();
    }

    /**
     * Prüft, ob die aktuelle {@link Number} negativ ist.
     *
     * @return Wenn die aktuelle Zahl negativ ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isNegative() {
        return this.number < 0;
    }

    /**
     * Prüft, ob die aktuelle {@link Number} positiv ist.
     *
     * @return Wenn die aktuelle Zahl positiv ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isPositive() {
        return this.number > 0;
    }

    /**
     * Prüft, ob die aktuelle {@link Number} eine Primzahl ist.
     *
     * @return Wenn die aktuelle Zahl eine Primzahl ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isPrime() {
        // return if number is negative
        if (isNegative()) return false;

        // check if number is not prime
        for (int i = 2; i <= (Math.sqrt(this.number) + 1); i++) {
            // only check odd numbers
            if (i % 2 == 0) continue;

            // check if number can divide
            if (this.number % i == 0) {
                return false;
            }
        }

        // if number is not 0 or 1 return true (number is prime)
        return !(this.number == 0 || this.number == 1);
    }

    /**
     * Gibt die aktuelle Zahl dieser {@link Number} in Form eines {@link Double Doubles} zurück.
     *
     * @return Die aktuelle Zahl dieser {@link Number} in Form eines {@link Double Doubles}.
     */
    public double getNumber() {
        return this.number;
    }


    //<editor-fold desc="Operation">

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
    //</editor-fold>

}
