package de.jonas.informatik;

import java.util.Arrays;

/**
 * Mit einem {@link Sort} lässt sich ein Array aus Zahlen auf verschiedene Arten bearbeiten und sortieren.
 */
public final class Sort {

    //<editor-fold desc="CONSTANTS">
    /** Das Array aus Zahlen, welche sortiert werden sollen. */
    private static final int[] NUMBERS = {9, 5, 6, 7, 3, 1};
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Array aus Zahlen, mit dem die Instanz dieses {@link Sort} arbeitet. */
    private final int[] numbers;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Klasse. Diese wird als aller erstes von der JRE aufgerufen und mithilfe dieser Methode
     * wird zu Test-Zwecken eine Abfolge an Zahlen sortiert.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        final Sort sort = new Sort(NUMBERS);
        sort.show();
        sort.selection_sort();
        System.out.print("\n\n");
    }
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Sort}. Mit einem {@link Sort} lässt sich ein
     * Array aus Zahlen auf verschiedene Arten bearbeiten und sortieren.
     *
     * @param numbers Das Array, bestehend aus Zahlen, welches mithilfe dieses {@link Sort} editiert bzw. sortiert
     *                werden soll.
     */
    public Sort(final int[] numbers) {
        this.numbers = numbers;
    }
    //</editor-fold>

    /**
     * Zeigt den aktuellen Stand der {@code numbers} an, bzw. gibt die aktuellen {@code numbers} aus.
     */
    public void show() {
        System.out.println();
        Arrays.stream(this.numbers).forEach(i -> System.out.print(i + " "));
    }

    /**
     * Sortiert die {@code numbers} nach dem Prinzip 'Selection Sort'. Bei diesem Prinzip wird die Abfolge an Zahlen
     * beginnend bei der kleinsten schritt für schritt sortiert. Also zuerst wird die kleinste Zahl herausgesucht und an
     * die erste Stelle der {@code numbers} gesetzt. Dann wird dasselbe mit der zweit-größten Zahl gemacht, usw.
     */
    public void selection_sort() {
        for (int i = 0; i < this.numbers.length; i++) {
            // pos_min: position of the smallest number
            int pos_min = i;
            for (int j = i + 1; j < this.numbers.length; j++) {
                if (this.numbers[j] < this.numbers[i]) {
                    pos_min = j;
                }
            }

            exchange(i, pos_min);
            show();
        }
    }

    // sort the numbers, beginning with the smallest, using insertion sort
    public void insertion_sort() {
        for (int i = 1; i < this.numbers.length; i++) {
            for (int j = i; j > 0; j--) {
                if (this.numbers[j] < this.numbers[j - 1]) {
                    exchange(j - 1, j);
                    show();
                }
            }
        }
    }

    // sort the numbers, beginning with the smallest, using bubble_sort
    public void bubble_sort() {
        for (int i = 0; i < this.numbers.length; i++) {
            for (int j = i + 1; j < this.numbers.length; j++) {
                if (this.numbers[i] > this.numbers[j]) {
                    exchange(i, j);
                    show();
                }
            }

            System.out.print("\n");
        }
    }

    /**
     * Tauscht zwei verschiedene Zahlen innerhalb der {@code numbers} aus. Also die erste übergebene Zahl (die {@code
     * base}) wird ersetzt durch die zweite übergebene Zahl (die {@code newNumber}) und die erste übergebene Zahl wird
     * dann an die Stelle der zweiten übergebenen Zahl verschoben.
     *
     * @param base      Die Basis, welche durch die {@code newNumber} ausgetauscht wird.
     * @param newNumber Die neue Zahl, durch die die {@code base} ersetzt wird.
     */
    private void exchange(final int base, final int newNumber) {
        final int c = this.numbers[base];
        this.numbers[base] = this.numbers[newNumber];
        this.numbers[newNumber] = c;
    }
}
