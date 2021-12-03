package de.jonas.informatik.math;

import java.util.LinkedList;

import static de.jonas.informatik.math.Operation.DIVIDE;
import static de.jonas.informatik.math.Operation.MULTIPLY;

/**
 * Ein {@link Fraction Bruch} besteht aus zwei {@link Number Zahlen}, einmal dem Zähler und einmal dem Nenner. Mit
 * diesem Bruch lassen sich einige Überprüfungen und Operationen ausführen, um Eigenschaften des Bruches herauszufinden
 * und um besser bzw. einfacher mit dem bruch umgehen zu können.
 */
public final class Fraction {

    //<editor-fold desc="CONSTANTS">
    /** Die Anzahl an Dezimalstellen, auf die der Bruch als Dezimalzahl gerundet wird. */
    private static final int DECIMAL_PLACES = 6;
    /** Die Anzahl an Stellen, die ein Integer haben darf. */
    private static final int MAX_INTEGER_PLACES = String.valueOf(Integer.MAX_VALUE).length() - 1;
    /** Die Zeichenfolge, womit der Beginn einer Periode in einer Dezimalzahl gekennzeichnet wird. */
    private static final String PERIOD_IDENTIFIER = "p";
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der Zähler des Bruchs. */
    private final Number numerator;
    /** Der Nenner des Bruchs. */
    private final Number denominator;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt einen neuen und vollständig unabhängigen {@link Fraction Bruch}. Ein {@link Fraction Bruch} besteht aus
     * zwei {@link Number Zahlen}, einmal dem Zähler und einmal dem Nenner. Mit diesem Bruch lassen sich einige
     * Überprüfungen und Operationen ausführen, um Eigenschaften des Bruches herauszufinden und um besser bzw. einfacher
     * mit dem bruch umgehen zu können.
     *
     * @param numerator   Die {@link Number}, mit der der Zähler initialisiert wird.
     * @param denominator Die {@link Number}, mit der der Nenner initialisiert wird.
     */
    public Fraction(
        final Number numerator,
        final Number denominator
    ) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    //</editor-fold>


    /**
     * Führt eine bestimmte {@link Operation Rechen-Operation} aus, mithilfe von vorgegebenen {@link Operation
     * Operationen} und einem {@link Fraction Bruch}, welcher für jene Operation genutzt wird. Wenn die {@link
     * Operation} zum berechnen des Ergebnisses einen gemeinsamen Nenner benötigt, wird der Nenner der beiden {@link
     * Fraction Brüche} auf einen gemeinsamen Nenner gebracht. Dieser Nenner ist dann vorerst zu groß, wird aber
     * schlussendlich gekürzt, was aber deutlich performanter ist, als direkt den kleinsten Nenner ausfindig zu machen.
     *
     * @param operation Die {@link Operation Rechen-Operation}, die ausgeführt werden soll.
     * @param fraction  Der {@link Fraction Bruch}, welcher für diese Operation genutzt wird.
     */
    public void doOperation(final Operation operation, final Fraction fraction) {
        final Fraction extendedFraction;

        // bring fraction to the same denominator
        if ((fraction.getDenominator().getNumber() != this.denominator.getNumber())
            && (operation != MULTIPLY && operation != DIVIDE)
        ) {
            extendedFraction = new Fraction(
                fraction.numerator.getOperatedNumber(MULTIPLY, this.denominator),
                fraction.denominator.getOperatedNumber(MULTIPLY, this.denominator)
            );

            this.numerator.doOperation(
                MULTIPLY,
                fraction.getDenominator()
            );
            this.denominator.doOperation(
                MULTIPLY,
                fraction.getDenominator()
            );
        } else {
            extendedFraction = fraction;
        }

        // do operation
        switch (operation) {
            case ADD:
            case SUBTRACT:
                this.numerator.doOperation(
                    operation,
                    extendedFraction.getNumerator()
                );
                break;

            case MULTIPLY:
                this.numerator.doOperation(
                    operation,
                    extendedFraction.getNumerator()
                );
                this.denominator.doOperation(
                    operation,
                    extendedFraction.getDenominator()
                );
                break;

            case DIVIDE:
                this.numerator.doOperation(
                    MULTIPLY,
                    extendedFraction.getDenominator()
                );
                this.denominator.doOperation(
                    MULTIPLY,
                    extendedFraction.getNumerator()
                );
                break;

            default:
                break;
        }

        // shorten fraction
        shorten();
    }

    /**
     * Kürzt diesen {@link Fraction Bruch} auf den kleinsten Wert, welcher ausfindig gemacht werden kann. Um diesen
     * kleinsten Wert ausfindig zu machen, wird der größte gemeinsame Teiler genutzt, welcher mithilfe der Methode
     * {@code getGGT} kalkuliert wird.
     */
    public void shorten() {
        final int ggt = getGGT(this.numerator.getNumber(), this.denominator.getNumber());

        final Number ggtNumber = new Number(ggt);

        this.numerator.doOperation(DIVIDE, ggtNumber);
        this.denominator.doOperation(DIVIDE, ggtNumber);
    }

    /**
     * Berechnet aus zwei Werten den größten gemeinsamen Teiler. Dieser wird mithilfe des Euklidischen Algorithmus
     * berechnet (es ist verkürzt aufgeschrieben, also ohne Schleife, die mithilfe von 3 Variablen immer wieder die
     * Werte neu setzt, sondern die Methode wird so lange ausgeführt (und die Werte werden immer "um eins verschoben"),
     * bis der gesuchte Wert erreicht ist).
     *
     * @param one Der erste Wert, der genutzt wird, um den größten gemeinsamen Teiler zu berechnen.
     * @param two Der zweite Wert, der genutzt wird, um größten gemeinsamen Teiler zu berechnen.
     *
     * @return Der größte gemeinsame Teiler der beiden Werte.
     */
    private int getGGT(final double one, final double two) {
        // check if the first value equals the second value
        if (one == two || two == 0) return (int) one;

        // recalculate values
        return getGGT(two, one % two);
    }

    /**
     * Prüft, ob der aktuelle {@link Fraction Bruch} negativ ist, mithilfe des Xor-Operator, also wenn genau ein Wert
     * des Zählers und Nenner negativ ist.
     *
     * @return Wenn der aktuelle Bruch negativ ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isNegative() {
        return this.numerator.isNegative() ^ this.denominator.isNegative();
    }

    /**
     * Prüft, ob der aktuelle {@link Fraction Bruch} positiv ist.
     *
     * @return Wenn der aktuelle Bruch positiv ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isPositive() {
        return (this.numerator.isPositive() && this.denominator.isPositive())
            || (this.numerator.isNegative() && this.denominator.isNegative());
    }

    /**
     * Gibt den aktuellen Zähler des {@link Fraction Bruchs} zurück.
     *
     * @return Der aktuelle Zähler des Bruchs.
     */
    public Number getNumerator() {
        return this.numerator;
    }

    /**
     * Gibt den aktuellen Nenner des {@link Fraction Bruchs} zurück.
     *
     * @return Der aktuelle Nenner des Bruchs.
     */
    public Number getDenominator() {
        return this.denominator;
    }

    /**
     * Gibt den aktuellen {@link Fraction Bruch} als Dezimalzahl (einem {@link Double}) zurück. Die Dezimalzahl, die
     * zurück gegeben wird, wird auf 4 Nachkommastellen gerundet.
     *
     * @return Der aktuelle Bruch in Form einer Dezimalzahl (einem {@link Double}).
     */
    public double getDecimal() {
        final double decimal = this.numerator.getNumber() / this.denominator.getNumber();
        final double roundValue = Math.pow(10, DECIMAL_PLACES);

        return Math.round(decimal * roundValue) / roundValue;
    }

    /**
     * Gibt den aktuellen {@link Fraction Bruch} als Dezimalzahl, aber in Form eines Strings, zurück, da zufalls die
     * Dezimalzahl eine periodische Zahl ist, die Periode der Zahl zu Beginn der Periode mit einem 'p' gekennzeichnet
     * wird. Wenn aber keine Periode vorhanden ist, wird einfach die normale Dezimalzahl zurück gegeben (mithilfe von
     * {@code getDecimal}).
     *
     * @return Der aktuelle Bruch in Form einer Dezimalzahl, dessen Periode gekennzeichnet ist (zufalls eine Periode
     *     vorhanden ist).
     */
    public String getPeriodicDecimal() {
        // calculate decimal from this fraction
        final double decimal = this.numerator.getNumber() / this.denominator.getNumber();

        // create lists to split the decimal places and values of division to identify the period
        final LinkedList<Integer> splitNumber = new LinkedList<>();
        final LinkedList<Double> divisionValues = new LinkedList<>();

        // split the basic decimal by the dot
        final String[] splittedDecimal = Double.toString(decimal).split("\\.");

        // check if the length of decimal places are less than the maximum of integer places
        if (splittedDecimal[1].length() > MAX_INTEGER_PLACES) {
            splittedDecimal[1] = splittedDecimal[1].substring(0, MAX_INTEGER_PLACES);
        }

        // get all decimal places of the split number
        int numberDecimal = Integer.parseInt("1" + splittedDecimal[1]);

        // append decimal places to split-number-list while number decimal is preset
        while (numberDecimal > 0) {
            splitNumber.push(numberDecimal % 10);
            numberDecimal /= 10;
        }

        // remove unnecessary number (the number '1' prevents removing '0' at beginning)
        splitNumber.removeFirst();

        // the latest rest in the calculation (auxiliary variable)
        double latestRest = this.numerator.getNumber();

        // calculate the values of division to identify the period later
        for (int i = 0; i < splitNumber.size(); i++) {
            final double rest = (latestRest % this.denominator.getNumber()) * 10;

            latestRest = rest;

            divisionValues.add(rest);
        }

        // compare every numbers to find the period (if a period is preset)
        for (int i = 0; i < divisionValues.size(); i++) {
            for (int j = 1; j < divisionValues.size() - i; j++) {
                // check if a period is preset (check if current numbers are equal)
                if (!divisionValues.get(i).equals(divisionValues.get(j))) continue;

                // create a string builder to build the period
                final StringBuilder periodBuilder = new StringBuilder();

                // build the period with the period builder (string builder)
                for (int k = 0; k < j; k++) {
                    if (k == i) {
                        periodBuilder.append(PERIOD_IDENTIFIER);
                    }

                    periodBuilder.append(splitNumber.get(k));
                }

                // get the whole number before the dot
                final int wholeNumber = Integer.parseInt(splittedDecimal[0]);

                // return the whole number with decimal places (included period)
                return wholeNumber + "," + periodBuilder;
            }
        }

        // no period is preset - return basic decimal
        return String.valueOf(getDecimal());
    }

    //<editor-fold desc="implementation">
    @Override
    public String toString() {
        return this.numerator.getNumber() + " / " + this.denominator.getNumber();
    }
    //</editor-fold>
}
