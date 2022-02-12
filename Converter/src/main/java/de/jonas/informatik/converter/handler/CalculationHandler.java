package de.jonas.informatik.converter.handler;

/**
 * Mithilfe eines {@link CalculationHandler} lässt sich ein Term berechnen, welcher sich in einem bestimmten
 * Zahlensystem befindet.
 */
public final class CalculationHandler {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link ConverterFunction}, mit der der Term berechnet wird. */
    private ConverterFunction function;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt einen neuen und vollständig unabhängigen {@link CalculationHandler}. Mithilfe eines {@link
     * CalculationHandler} lässt sich ein Term berechnen, welcher sich in einem bestimmten Zahlensystem befindet.
     *
     * @param function Die {@link ConverterFunction}, mit der der Term berechnet wird.
     */
    public CalculationHandler(final ConverterFunction function) {
        this.function = function;
    }
    //</editor-fold>


    /**
     * Berechnet einen Term in einem bestimmten Zahlensystem.
     *
     * @param term Der Term, welcher berechnet werden soll.
     *
     * @return Das Ergebnis des Terms im jeweiligen Zahlensystem.
     */
    public String calculate(final String term) {
        final StringBuilder builder = new StringBuilder();

        int currentBegin = 0;

        for (int i = 0; i < term.length(); i++) {
            switch (term.charAt(i)) {
                case '+':
                case '-':
                case '*':
                case '/':
                    builder.append(Integer.parseInt(
                        term.substring(currentBegin, i),
                        this.function.getSystemIdentifier()
                    ));
                    builder.append(term.charAt(i));

                    currentBegin = i + 1;
                    break;

                default:
                    break;
            }
        }

        builder.append(Integer.parseInt(term.substring(currentBegin), this.function.getSystemIdentifier()));

        final int decimalResult = (int) eval(builder.toString());

        return this.function.convert(decimalResult);
    }

    /**
     * Berechnet einen Term, welcher sich im normalen Dezimalsystem befindet.
     *
     * @param term Der Term, der berechnet werden soll (im Dezimalsystem).
     *
     * @return Das Ergebnis des Terms.
     */
    private double eval(final String term) {
        return new Object() {
            private int pos = -1;
            private int ch;

            private void nextChar() {
                ch = (++pos < term.length()) ? term.charAt(pos) : -1;
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

                if (pos < term.length()) {
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

                final int startPos = this.pos;

                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(term.substring(startPos, this.pos));
                } else {
                    return 0;
                }

                return x;
            }
        }.parse();
    }

    /**
     * Setzt die {@link ConverterFunction} dieses {@link CalculationHandler} neu, mit der dieser Rechner rechnen soll.
     *
     * @param function Die neue {@link ConverterFunction}, mit der dieser Rechner rechnen soll.
     */
    public void setConverterFunction(final ConverterFunction function) {
        this.function = function;
    }

}
