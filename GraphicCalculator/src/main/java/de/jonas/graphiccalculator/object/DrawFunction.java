package de.jonas.graphiccalculator.object;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Mit einem {@link DrawFunction} lässt sich eine bestimmte Funktion, deren Werte in Form von X- und Y-Koordinate in
 * einer {@link Map} abgespeichert übergeben werden, zeichnen.
 */
@NotNull
public final class DrawFunction extends JLabel {

    //<editor-fold desc="CONSTANTS">
    /** Der linke und rechte Abstand, den die x-Achse vom Rand dieses Objekts besitzt. */
    private static final int X_MARGIN = 50;
    /** Der obere und untere Abstand, den die y-Achse vom Rand dieses Objekts besitzt. */
    private static final int Y_MARGIN = 50;
    /** Der Abstand zwischen den einzelnen Beschriftungen des Koordinatensystems. */
    private static final int LABEL_MARGIN = 35;
    /** Die Anzahl an Beschriftungen der x-Achse. */
    private static final int LABEL_AMOUNT_X = 10;
    /** Die Anzahl an Beschriftungen der y-Achse. */
    private static final int LABEL_AMOUNT_Y = 10;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Alle Funktionswerte, aus denen dann eine Funktion gezeichnet wird. */
    @NotNull
    private final NavigableMap<Double, Double> function;
    /** Die Skalierung für die x-Achse. */
    @Range(from = LABEL_AMOUNT_X, to = Integer.MAX_VALUE)
    private final int scaleX;
    /** Die Skalierung für die y-Achse. */
    @Range(from = LABEL_AMOUNT_Y, to = Integer.MAX_VALUE)
    private final int scaleY;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link DrawFunction}. Mit einem {@link DrawFunction}
     * lässt sich eine bestimmte Funktion, deren Werte in Form von X- und Y-Koordinate in einer {@link Map}
     * abgespeichert übergeben werden, zeichnen.
     *
     * @param function Alle Funktionswerte, aus denen dann eine Funktion gezeichnet wird.
     * @param scaleX   Die Skalierung für die x-Achse.
     * @param scaleY   Die Skalierung für die y-Achse.
     */
    public DrawFunction(
        @NotNull final NavigableMap<Double, Double> function,
        @Range(from = 0, to = Integer.MAX_VALUE) final int scaleX,
        @Range(from = 0, to = Integer.MAX_VALUE) final int scaleY
    ) {
        // create temp map
        final NavigableMap<Double, Double> filteredFunction = new TreeMap<>();

        // calculate draw tolerance
        final double xTolerance = (double) scaleX / 10;
        final double yTolerance = (double) scaleY / 10;

        // filter function values
        for (@NotNull final Map.Entry<Double, Double> functionEntry : function.entrySet()) {
            // get current values from entry
            final double x = functionEntry.getKey();
            final double y = functionEntry.getValue();

            // check if values are out of bounds
            if (x > scaleX + xTolerance || x < -scaleX - xTolerance || y > scaleY + yTolerance || y < -scaleY - yTolerance) {
                continue;
            }

            // mark values as filtered
            filteredFunction.put(x, y);
        }

        // initialize variables
        this.function = filteredFunction;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    //</editor-fold>


    /**
     * Berechnet aus einem x-Wert der Funktion den entsprechenden finalen x-Wert, den diese Stelle in dem
     * Koordinatensystem bzw. auf diesem Objekt widerspiegelt.
     *
     * @param x Der x-Wert der Funktion.
     *
     * @return Der finale x-Wert, der dem x-Wert der Funktion entspricht.
     */
    private int getValueX(final double x) {
        return (int) (X_MARGIN + (x * LABEL_MARGIN / ((double) this.scaleX / LABEL_AMOUNT_X)));
    }

    /**
     * Berechnet aus einem y-Wert der Funktion den entsprechenden finalen y-Wert, den diese Stelle in dem
     * Koordinatensystem bzw. auf diesem Objekt widerspiegelt.
     *
     * @param y Der y-Wert der Funktion.
     *
     * @return Der finale y-Wert, der dem y-Wert der Funktion entspricht.
     */
    private int getValueY(final double y) {
        return (int) (super.getHeight() - Y_MARGIN - (y * LABEL_MARGIN / ((double) this.scaleY / LABEL_AMOUNT_Y)));
    }

    //<editor-fold desc="implementation">
    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);

        // draw background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());

        final int yAxisX = super.getWidth() / 2;
        final int xAxisY = super.getHeight() / 2;

        // draw coordinate system
        g.setColor(Color.WHITE);
        g.drawLine(
            X_MARGIN - 20,
            xAxisY,
            super.getWidth() - X_MARGIN + 20,
            xAxisY
        );
        g.drawLine(
            yAxisX,
            super.getHeight() - Y_MARGIN - 5,
            yAxisX,
            Y_MARGIN
        );

        // draw arrows
        g.drawLine(super.getWidth() - X_MARGIN + 20, xAxisY, super.getWidth() - X_MARGIN + 10, xAxisY - 5);
        g.drawLine(super.getWidth() - X_MARGIN + 20, xAxisY, super.getWidth() - X_MARGIN + 10, xAxisY + 5);
        g.drawLine(yAxisX, Y_MARGIN, yAxisX - 5, Y_MARGIN + 10);
        g.drawLine(yAxisX, Y_MARGIN, yAxisX + 5, Y_MARGIN + 10);

        // draw x labels
        for (int i = -LABEL_AMOUNT_X; i <= LABEL_AMOUNT_X; i++) {
            g.drawLine(
                yAxisX + i * LABEL_MARGIN,
                xAxisY - 5,
                yAxisX + i * LABEL_MARGIN,
                xAxisY + 5
            );

            if (i == 0) continue;

            g.drawString(
                String.valueOf(Math.round((((double) this.scaleX / LABEL_AMOUNT_X) * i) * 10D) / 10D),
                yAxisX + i * LABEL_MARGIN - 5,
                xAxisY + 20
            );
        }

        // draw y labels
        for (int i = -LABEL_AMOUNT_Y; i <= LABEL_AMOUNT_Y; i++) {
            g.drawLine(
                yAxisX - 5,
                xAxisY - i * LABEL_MARGIN,
                yAxisX + 5,
                xAxisY - i * LABEL_MARGIN
            );

            if (i == 0) continue;

            g.drawString(
                String.valueOf(Math.round((((double) this.scaleY / LABEL_AMOUNT_Y) * i) * 10D) / 10D),
                yAxisX - 40,
                (xAxisY - i * LABEL_MARGIN) + 5
            );
        }

        // draw function
        g.setColor(Color.RED);
        for (@NotNull final Map.Entry<Double, Double> functionValue : this.function.entrySet()) {
            if (functionValue.getValue().isNaN()) continue;

            // get current values
            final double x = functionValue.getKey();
            final double y = functionValue.getValue();

            // check if next entry is preset
            if (this.function.higherEntry(x) == null) break;

            // get next entry
            final Map.Entry<Double, Double> nextValue = this.function.higherEntry(x);

            // get next values
            final double nextX = nextValue.getKey();
            final double nextY = nextValue.getValue();

            // skip (+ to -) or (- to +)
            if ((y > 0 && nextY < 0) || (y < 0 && nextY > 0)) continue;

            // draw line
            g.drawLine(
                getValueX(x) + (yAxisX - X_MARGIN),
                getValueY(y) - (xAxisY - Y_MARGIN),
                getValueX(nextX) + (yAxisX - X_MARGIN),
                getValueY(nextY) - (xAxisY - Y_MARGIN)
            );
        }
    }
    //</editor-fold>
}
