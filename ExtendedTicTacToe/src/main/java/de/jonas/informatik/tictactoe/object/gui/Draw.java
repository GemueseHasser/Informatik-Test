package de.jonas.informatik.tictactoe.object.gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Mithilfe eines {@link Draw} wird auf das {@link de.jonas.informatik.tictactoe.gui.Gui} das gesamte Spielfeld
 * gezeichnet, welches sich auch immer wieder aktualisiert.
 */
public final class Draw extends JPanel {

    //<editor-fold desc="implementation">
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        // define render quality
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());
    }
    //</editor-fold>
}
