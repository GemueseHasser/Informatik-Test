package de.jonas.functiondraw.object;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Mit einem {@link DrawFunction} lässt sich eine bestimmte Funktion, deren Werte in Form von X- und Y-Koordinate in
 * einer {@link Map} abgespeichert übergeben werden, zeichnen.
 */
@NotNull
@RequiredArgsConstructor
public final class DrawFunction extends JLabel {

    private static final int X_MARGIN = 50;
    private static final int Y_MARGIN = 80;
    private static final int LABEL_MARGIN = 40;
    private static final int LABEL_AMOUNT_X = 10;
    private static final int LABEL_AMOUNT_Y = 10;


    //<editor-fold desc="LOCAL FIELDS">
    /** Alle Funktionswerte, aus denen dann eine Funktion gezeichnet wird. */
    @NotNull
    private final NavigableMap<Integer, Integer> function;
    /** Die Skalierung für die x-Achse. */
    @Range(from = LABEL_AMOUNT_X, to = Integer.MAX_VALUE)
    private final int scaleX;
    /** Die Skalierung für die y-Achse. */
    @Range(from = LABEL_AMOUNT_Y, to = Integer.MAX_VALUE)
    private final int scaleY;
    //</editor-fold>


    private int getValueX(final int x) {
        return X_MARGIN + (x * LABEL_MARGIN / (this.scaleX / LABEL_AMOUNT_X));
    }

    private int getValueY(final int y) {
        return super.getHeight() - Y_MARGIN - (y * LABEL_MARGIN / (this.scaleY / LABEL_AMOUNT_Y));
    }

    //<editor-fold desc="implementation">
    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);

        // draw background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());

        // draw coordinate system
        g.setColor(Color.WHITE);
        g.drawLine(
            X_MARGIN - 20,
            super.getHeight() - Y_MARGIN,
            super.getWidth() - X_MARGIN,
            super.getHeight() - Y_MARGIN
        );
        g.drawLine(
            X_MARGIN,
            super.getHeight() - Y_MARGIN + 20,
            X_MARGIN,
            Y_MARGIN
        );

        // draw x labels
        for (int i = 0; i <= LABEL_AMOUNT_X; i++) {
            g.drawLine(
                X_MARGIN + i * LABEL_MARGIN,
                super.getHeight() - Y_MARGIN - 5,
                X_MARGIN + i * LABEL_MARGIN,
                super.getHeight() - Y_MARGIN + 5
            );

            g.drawString(
                String.valueOf((this.scaleX / LABEL_AMOUNT_X) * i),
                X_MARGIN + i * LABEL_MARGIN - 5,
                super.getHeight() - Y_MARGIN + 30
            );
        }

        // draw y labels
        for (int i = 0; i <= LABEL_AMOUNT_Y; i++) {
            g.drawLine(
                X_MARGIN - 5,
                super.getHeight() - Y_MARGIN - i * LABEL_MARGIN,
                X_MARGIN + 5,
                super.getHeight() - Y_MARGIN - i * LABEL_MARGIN
            );

            g.drawString(
                String.valueOf(i * (this.scaleY / LABEL_AMOUNT_Y)),
                X_MARGIN - 30,
                (super.getHeight() - Y_MARGIN - i * LABEL_MARGIN) + 5
            );
        }

        // draw function
        g.setColor(Color.RED);
        for (@NotNull final Map.Entry<Integer, Integer> functionValue : this.function.entrySet()) {
            // get current values
            final int x = functionValue.getKey();
            final int y = functionValue.getValue();

            // check if next entry is preset
            if (this.function.higherEntry(x) == null) break;

            // get next entry
            final Map.Entry<Integer, Integer> nextValue = this.function.higherEntry(x);

            // get next values
            final int nextX = nextValue.getKey();
            final int nextY = nextValue.getValue();

            // draw line
            g.drawLine(getValueX(x), getValueY(y), getValueX(nextX), getValueY(nextY));
        }
    }
    //</editor-fold>
}
