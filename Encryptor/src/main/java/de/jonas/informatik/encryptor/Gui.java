package de.jonas.informatik.encryptor;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar. Dieses {@link Gui} wird dem Nutzer sofort geöffnet, es
 * ist also das Fenster, worin sich der grundlegende {@link de.jonas.informatik.Encryptor} befindet.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Encryptor";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 600;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 500;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}. Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar. Dieses
     * {@link Gui} wird dem Nutzer sofort geöffnet, es ist also das Fenster, worin sich der grundlegende {@link
     * de.jonas.informatik.Encryptor} befindet.
     */
    public Gui() {
        // create instance
        super(TITLE);

        // set properties
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setLayout(null);

        // create draw-object
        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        // add components
        super.add(draw);

        // open gui
        super.setVisible(true);
    }
    //</editor-fold>


    //<editor-fold desc="Draw">

    /**
     * Das Zeichen-Objekt, womit alle nötigen Grafiken auf dieses {@link Gui} gezeichnet werden.
     */
    private static final class Draw extends JLabel {

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, super.getWidth(), super.getHeight());
        }
    }
    //</editor-fold>

}
