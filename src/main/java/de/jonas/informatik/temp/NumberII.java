package de.jonas.informatik.temp;

import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Erzeugt ein Fenster mit dem Titel 'Squares', worauf 5 Rechtecke gezeichnet werden, welche von links an immer weiter
 * nach rechts mit einem Abstand von 5 Pixel nach rechts und 5 Pixel nach oben verlaufen.
 */
public final class NumberII extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Die Größe (Breite und Höhe) des Fensters. */
    private static final int FRAME_SIZE = 600;
    /** Die X-Koordinate, bei der das erste Quadrat anfängt. */
    private static final int POS_X = 35;
    /** Die Y-Koordinate, bei der das erste Quadrat anfängt. */
    private static final int POS_Y = 100;
    /** Die Größe eines jeden Quadrats. */
    private static final int SQUARE_SIZE = 100;
    /** Der Abstand von einem Quadrat zum nächsten (sowohl nach rechts, als auch nach oben). */
    private static final int MARGIN = 5;
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < 5; i++) {
            final int x = POS_X + i * (SQUARE_SIZE + MARGIN);
            final int y = POS_Y - i * MARGIN;

            g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
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
        final NumberII sq = new NumberII();
        sq.setBounds(0, 0, FRAME_SIZE, FRAME_SIZE);
        sq.setLocationRelativeTo(null);
        sq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sq.setTitle("Squares");
        sq.setResizable(false);
        sq.setVisible(true);
    }
    //</editor-fold>

}
