package de.jonas.informatik;

public final class Sort {

    private static final int[] NUMBERS = {9, 5, 6, 7, 3, 1};


    private final int[] numbers;

    // main method
    public static void main(String[] args) {
        Sort sort = new Sort(NUMBERS);
        sort.show();
        sort.selection_sort();
        System.out.print("\n\n");
    }

    public Sort(final int[] numbers) {
        this.numbers = numbers;
    }

    // print all numbers in a row
    public void show() {
        System.out.print("\n  ");

        for (final int number : this.numbers) {
            System.out.print(number + "  ");
        }
    }

    // sort the numbers, beginning with the smallest, using selection sort
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

    // exchange the numbers at positions a and b in the array
    private void exchange(final int a, final int b) {
        final int c = this.numbers[a];
        this.numbers[a] = this.numbers[b];
        this.numbers[b] = c;
    }
}
