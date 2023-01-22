package de.jonas.graphiccalculator.handler;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Mithilfe eines {@link FunctionHandler} lassen sich sowohl alle Funktionswerte in einem bestimmten Bereich, als auch
 * ein bestimmter Funktionswert wiedergeben. Außerdem stellt dieser Handler die Utility-Methode {@code eval} zur
 * Verfügung, welche einen Term ausrechnet.
 */
@NotNull
@RequiredArgsConstructor
public final class FunctionHandler {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Funktion, für die dieser {@link FunctionHandler} erzeugt wird. */
    @NotNull
    private final String function;
    /** Die Skalierung der x-Achse. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private final int xScaling;
    //</editor-fold>


    /**
     * Gibt alle Funktionswerte in Abständen von 0.1 im Bereich der x-Achsen-Skalierung wieder. In der
     * {@link NavigableMap} sind alle x-Werte den entsprechenden y-Werten zugeordnet.
     *
     * @return Eine {@link NavigableMap}, welche alle Funktionswerte im Bereich der x-Achsen-Skalierung in Abständen von
     *     0.1 beinhaltet.
     */
    @NotNull
    public NavigableMap<Double, Double> getFunctionValues() {
        final NavigableMap<Double, Double> values = new TreeMap<>();

        // calculate function values
        for (double i = -this.xScaling; i < this.xScaling; i = Math.round((i + 0.1) * 100D) / 100D) {
            values.put(i, getFunctionValue(i));
        }

        return values;
    }

    /**
     * Gibt den Funktionswert für einen bestimmten x-Wert zurück.
     *
     * @param x Der x-Wert, dessen Funktionswert wiedergegeben werden soll.
     *
     * @return Der Funktionswert für einen bestimmten x-Wert.
     */
    public double getFunctionValue(final double x) {
        final String function = this.function.replaceAll("x", "(" + x + ")");

        return eval(function);
    }

    //<editor-fold desc="utility">

    /**
     * Berechnet einen Term, welcher in Form eines Strings übergeben wird und gibt diesen ausgerechnet wieder zurück.
     *
     * @param term Der String der mathematisch berechnet wird.
     *
     * @return Das Ergebnis der Rechnung.
     */
    public static double eval(@NotNull final String term) {
        final String finalTerm = term.replaceAll("e", String.valueOf(Math.E)).replaceAll("π", String.valueOf(Math.PI));

        return new Object() {
            private int pos = -1;
            private int ch;

            private void nextChar() {
                ch = (++pos < finalTerm.length()) ? finalTerm.charAt(pos) : -1;
            }

            private boolean eat(final int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            private double parse() {
                nextChar();
                double x = parseExpression();

                if (pos < finalTerm.length()) {
                    return 0;
                }

                return x;
            }

            private double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) {
                        x += parseTerm();
                    } else if (eat('-')) {
                        x -= parseTerm();
                    } else {
                        return x;
                    }
                }
            }

            private double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) {
                        x *= parseFactor();
                    } else if (eat('/')) {
                        x /= parseFactor();
                    } else {
                        return x;
                    }
                }
            }

            private double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(finalTerm.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = finalTerm.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;

                        case "sin":
                            x = Math.sin(x);
                            break;

                        case "cos":
                            x = Math.cos(x);
                            break;

                        case "tan":
                            x = Math.tan(x);
                            break;

                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    return 0;
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }
    //</editor-fold>

}
