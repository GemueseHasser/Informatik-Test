package de.jonas.functiondraw.object;

import org.jetbrains.annotations.NotNull;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

/**
 * Mit einem {@link DrawFunction} lässt sich eine bestimmte Funktion, deren Werte in Form von X- und Y-Koordinate in
 * einer {@link Map} abgespeichert übergeben werden, zeichnen.
 */
@NotNull
public final class DrawFunction extends JLabel {

    //<editor-fold desc="implementation">
    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());
    }
    //</editor-fold>
}
