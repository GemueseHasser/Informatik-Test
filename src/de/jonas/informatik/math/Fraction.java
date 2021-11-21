package de.jonas.informatik.math;

import static de.jonas.informatik.math.Operation.DIVIDE;
import static de.jonas.informatik.math.Operation.MULTIPLY;

/**
 * Ein {@link Fraction Bruch} besteht aus zwei {@link Number Zahlen}, einmal dem Zähler und einmal dem Nenner. Mit
 * diesem Bruch lassen sich einige Überprüfungen und Operationen ausführen, um Eigenschaften des Bruches herauszufinden
 * und um besser bzw. einfacher mit dem bruch umgehen zu können.
 */
public final class Fraction {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der Zähler des Bruchs. */
    private Number numerator;
    /** Der Nenner des Bruchs. */
    private Number denominator;
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

            this.numerator = this.numerator.getOperatedNumber(
                MULTIPLY,
                fraction.getDenominator()
            );
            this.denominator = this.denominator.getOperatedNumber(
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
                this.numerator = this.numerator.getOperatedNumber(
                    operation,
                    extendedFraction.getNumerator()
                );
                break;

            case MULTIPLY:
                this.numerator = this.numerator.getOperatedNumber(
                    operation,
                    extendedFraction.getNumerator()
                );
                this.denominator = this.denominator.getOperatedNumber(
                    operation,
                    extendedFraction.getDenominator()
                );
                break;

            case DIVIDE:
                this.numerator = this.numerator.getOperatedNumber(
                    MULTIPLY,
                    extendedFraction.getDenominator()
                );
                this.denominator = this.denominator.getOperatedNumber(
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

        this.numerator = this.numerator.getOperatedNumber(DIVIDE, ggtNumber);
        this.denominator = this.denominator.getOperatedNumber(DIVIDE, ggtNumber);
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
     * Prüft, ob der aktuelle {@link Fraction Bruch} negativ ist.
     *
     * @return Wenn der aktuelle Bruch negativ ist, {@code true}, ansonsten {@code false}.
     */
    public boolean isNegative() {
        return (this.numerator.isNegative() || this.denominator.isNegative())
            && !(this.numerator.isNegative() && this.denominator.isNegative());
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
        return Math.round((this.numerator.getNumber() / this.denominator.getNumber()) * 10000D) / 10000D;
    }

    //<editor-fold desc="implementation">
    @Override
    public String toString() {
        return this.numerator.getNumber() + " / " + this.denominator.getNumber();
    }
    //</editor-fold>
}
