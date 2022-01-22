package de.jonas.informatik.temp;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Erzeugt ein Fenster mit dem Titel 'Number 3', worauf ein Bild von 5 x 5 Kreisen gezeichnet wird. Alle 2 Kreise, ist
 * der Kreis schwarz, sodass ein Muster entsteht (schwarz-weiß).
 */
public final class NumberIII extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Die Größe (Höhe und Breite) des Fensters. */
    private static final int FRAME_SIZE = 350;
    /** Die Größe eines jeden einzelnen Kreises. */
    private static final int CIRCLE_SIZE = 50;
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);

        for (int i = 0; i < 5; i++) {
            final int y = 50 + i * CIRCLE_SIZE;
            for (int j = 0; j < 5; j++) {
                final int x = 50 + j * CIRCLE_SIZE;

                switch (i % 2) {
                    case 0:
                        if (j % 2 == 1) {
                            g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
                            continue;
                        }

                        g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
                        break;

                    case 1:
                        if (j % 2 == 0) {
                            g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
                            continue;
                        }

                        g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
                        break;

                    default:
                        break;
                }
            }
        }
    }
    //</editor-fold>


    //<editor-fold desc="initialising">

    /**
     * Die Main-Methode der Anwendung. Diese wird vor allen anderen Methoden als aller erstes von der JRE aufgerufen und
     * wird somit zum initialisieren der Anwendung genutzt.
     *
     * @param args Die Argumente, die der Anwendung übergeben werden.
     */
    public static void main(final String[] args) {
        final NumberIII frame = new NumberIII();
        frame.setBounds(0, 0, FRAME_SIZE, FRAME_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Number 3");
        frame.setResizable(false);
        frame.setVisible(true);
    }
    //</editor-fold>
}
