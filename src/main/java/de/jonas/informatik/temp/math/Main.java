package de.jonas.informatik.temp.math;

public final class Main {

    public static void main(final String[] args) {
        System.out.println("Starting testing Number: \n");
        testNumber();

        System.out.println("Starting testing Fraction: \n");
        testFraction();
    }


    private static void testNumber() {
        // create test number
        final Number number = new Number(100);

        // test default math operations
        final Number calcNumber = new Number(10);
        for (final Operation operation : Operation.values()) {
            number.doOperation(operation, calcNumber);
            System.out.println(operation.name() + " " + calcNumber + " <=> " + number);
        }

        System.out.println();

        // test pow function
        System.out.print(number + "^" + "10 = ");
        number.pow(10);
        System.out.print(number);

        System.out.println("\n");

        // check if number is even or odd
        if (number.isEven()) {
            System.out.println("Die Zahl ist Gerade!");
        } else if (number.isOdd()) {
            System.out.println("Die Zahl ist ungerade!");
        }

        System.out.println();

        // check if number is positive or negative (or nothing)
        if (number.isPositive()) {
            System.out.println("Die Zahl ist positiv!");
        } else if (number.isNegative()) {
            System.out.println("Die Zahl ist negativ!");
        } else {
            System.out.println("Die Zahl ist weder positiv, noch negativ!");
        }

        System.out.println();

        // check if number is prime
        if (number.isPrime()) {
            System.out.println("Die Zahl ist eine Primzahl!");
        }

        System.out.println("---------------------------");
    }

    private static void testFraction() {
        // create test numbers for test fraction
        final Number numerator = new Number(1);
        final Number denominator = new Number(101);

        // create test fraction
        final Fraction fraction = new Fraction(
            numerator,
            denominator
        );

        // test default math operations
        final Number calcNumerator = new Number(2);
        final Number calcDenominator = new Number(50);

        final Fraction calcFraction = new Fraction(
            calcNumerator,
            calcDenominator
        );
        for (final Operation operation : Operation.values()) {
            fraction.doOperation(operation, calcFraction);
            System.out.println(operation + " " + calcFraction + " <=> " + fraction);
        }

        System.out.println();

        // check if fraction is positive or negative (or nothing)
        if (fraction.isPositive()) {
            System.out.println("Der Bruch ist positiv!");
        } else if (fraction.isNegative()) {
            System.out.println("Der Bruch ist negativ!");
        } else {
            System.out.println("Der Bruch ist weder positiv, noch negativ!");
        }

        System.out.println();

        System.out.println("Der Bruch als gerundete Dezimalzahl: " + fraction.getDecimal());

        System.out.println();

        System.out.println("Der Bruch als periodische Dezimalzahl: " + fraction.getPeriodicDecimal());

        System.out.println("---------------------------");
    }

}
