package de.jonas.informatik.math;

public final class Fraction {

    private Number numerator;
    private Number denominator;


    public Fraction(
        final Number numerator,
        final Number denominator
    ) {
        this.numerator = numerator;
        this.denominator = denominator;
    }


    public void doOperation(final Number.Operation operation, final Fraction fraction) {
        final Fraction extendedFraction;

        // bring fraction to the same denominator
        if (fraction.getDenominator().getNumber() != this.denominator.getNumber()) {
            extendedFraction = new Fraction(
                fraction.numerator.getOperatedNumber(Number.Operation.MULTIPLY, this.denominator),
                fraction.denominator.getOperatedNumber(Number.Operation.MULTIPLY, this.denominator)
            );

            this.numerator = this.numerator.getOperatedNumber(
                Number.Operation.MULTIPLY,
                fraction.getDenominator()
            );
            this.denominator = this.denominator.getOperatedNumber(
                Number.Operation.MULTIPLY,
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
                    Number.Operation.MULTIPLY,
                    extendedFraction.getDenominator()
                );
                this.denominator = this.denominator.getOperatedNumber(
                    Number.Operation.MULTIPLY,
                    extendedFraction.getNumerator()
                );
                break;

            default:
                break;
        }

        // shorten fraction
        shorten();
    }

    public void shorten() {
        final int kgv = (int) (this.numerator.getNumber() * this.denominator.getNumber())
            / getGGT(this.numerator.getNumber(), this.denominator.getNumber());

        this.numerator = this.numerator.getOperatedNumber(Number.Operation.DIVIDE, new Number(kgv));
        this.denominator = this.denominator.getOperatedNumber(Number.Operation.DIVIDE, new Number(kgv));
    }

    private int getGGT(final double one, final double two) {
        if (one == two || two == 0) return (int) one;

        return getGGT(two, one % two);
    }

    public boolean isNegative() {
        return (this.numerator.isNegative() || this.denominator.isNegative())
            && !(this.numerator.isNegative() && this.denominator.isNegative());
    }

    public boolean isPositive() {
        return (this.numerator.isPositive() && this.denominator.isPositive())
            || (this.numerator.isNegative() && this.denominator.isNegative());
    }

    public Number getNumerator() {
        return this.numerator;
    }

    public Number getDenominator() {
        return this.denominator;
    }

    public double getDecimal() {
        return Math.round((this.numerator.getNumber() / this.denominator.getNumber()) * 10000D) / 10000D;
    }

    public String getFraction() {
        return this.numerator.getNumber() + " / " + this.denominator.getNumber();
    }

}
