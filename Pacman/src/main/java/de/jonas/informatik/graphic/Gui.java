package de.jonas.informatik.graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Ein {@link Gui} stellt ein Fenster dar, worin das {@link de.jonas.informatik.Pacman Spiel} angezeigt wird.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Pacman";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 600;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 500;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}, welches eine Instanz eines {@link JFrame} darstellt. Ein {@link Gui}
     * stellt ein Fenster dar, worin das {@link de.jonas.informatik.Pacman Spiel} angezeigt wird.
     */
    public Gui() {
        super(TITLE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(null);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        super.add(draw);
        super.setVisible(true);
    }
    //</editor-fold>


    //<editor-fold desc="Draw">

    /**
     * Mithilfe eines {@link Draw} werden alle Grafiken auf das Fenster gezeichnet. Die Zeichnungen werden so schnell es
     * geht immer wieder aktualisiert, sodass fortlaufende Bewegungen und Animationen flüssig angezeigt werden können.
     */
    private static final class Draw extends JPanel {

        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2d = (Graphics2D) g;

            // define render quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // draw black background
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Gui.WIDTH, Gui.HEIGHT);

            repaint();
        }
        //</editor-fold>

    }
    //</editor-fold>

}
