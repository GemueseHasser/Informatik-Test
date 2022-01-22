package de.jonas.informatik.temp;

public final class Schleifen {

    private static final Schleifen INSTANCE = new Schleifen();


    public static void main(final String[] args) {
        // number 1
        INSTANCE.numberOne();

        // number 2
        INSTANCE.numberTwo();

        // number 3
        INSTANCE.numberThree();

        // number 4
        INSTANCE.numberFour();
    }

    private void numberOne() {
        System.out.println("Nummer 1: \n \n1)\n");

        for (int i = 8; i < 15; i += 2) {
            System.out.println(i);
        }

        System.out.println("\n2)\n");

        for (int i = 100; i > 0; i -= 10) {
            System.out.println(i);
        }

        System.out.println("\n3)\n");

        for (int i = 78; i > 0; i -= 7) {
            System.out.println(i);
        }

        System.out.println("\n4)\n");

        for (int i = 4; i < 1000; i = 3 * i + 1) {
            System.out.println(i);
        }

        System.out.println("\n5)\n");

        int z = 1;
        for (int i = 1; i < 10; i++) {
            z *= i;
            System.out.println(z);
        }
    }

    private void numberTwo() {
        System.out.println("Nummer 2: \n \na)\n");

        for (int i = 2; i < 21; i += 2) {
            System.out.println(i);
        }

        System.out.println("\nb)\n");

        for (int i = 30; i > -1; i-= 3) {
            System.out.println(i);
        }

        System.out.println("\nc)\n");

        int z = 2;
        for (int i = 0; i < 6; i++) {
            z *= -1;
            System.out.println(z);
        }
    }

    private void numberThree() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private void numberFour() {
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.print(i * j);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

}
