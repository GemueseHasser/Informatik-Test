package de.jonas.informatik.math;

public final class Main {

    public static void main(final String[] args) {
        final Fraction fraction = new Fraction(
            new Number(10),
            new Number(15)
        );

        final Fraction fraction1 = new Fraction(
            new Number(5),
            new Number(10)
        );

        final Number number = new Number(9);

        fraction.doOperation(Operation.DIVIDE, fraction1);
    }

}
