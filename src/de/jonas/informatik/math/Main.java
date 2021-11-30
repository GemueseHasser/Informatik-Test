package de.jonas.informatik.math;

public final class Main {

    public static void main(final String[] args) {
        System.out.println("Starting testing Number:");
        testNumber();
    }


    private static void testNumber() {
        final Number number = new Number(100);

        number.doOperation(Operation.MULTIPLY, new Number(10));

        System.out.println(number);
    }

}
