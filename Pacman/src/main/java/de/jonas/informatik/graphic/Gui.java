package de.jonas.informatik.graphic;

import de.jonas.informatik.object.MapBuilder;
import de.jonas.informatik.object.material.Brick;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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

        final Map<Integer, Integer> backgroundBricks = new HashMap<>();
        backgroundBricks.put(0, 0);
        backgroundBricks.put(WIDTH - 60, HEIGHT - 60);

        // create draw-object to draw graphics
        final Draw draw = new Draw(backgroundBricks, 50);
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

        //<editor-fold desc="LOCAL FIELDS">
        /** Die Karte, die als Hintergrund genutzt wird und welches das Spielfeld darstellt. */
        private final BufferedImage map;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue Instanz eines {@link Draw}, welches eine Instanz eines {@link JPanel} darstellt. Mithilfe
         * eines {@link Draw} werden alle Grafiken auf das Fenster gezeichnet. Die Zeichnungen werden so schnell es geht
         * immer wieder aktualisiert, sodass fortlaufende Bewegungen und Animationen flüssig angezeigt werden können.
         *
         * @param coordinates Die Koordinaten, an denen überall {@link Brick Ziegel} in die Karte aufgenommen werden
         *                    sollen.
         * @param brickLength Die Größe aller einzelnen {@link Brick Ziegel}, die auf der Karte angezeigt werden.
         */
        public Draw(
            final Map<Integer, Integer> coordinates,
            final int brickLength
        ) {
            final MapBuilder mapBuilder = new MapBuilder(coordinates, brickLength);
            this.map = mapBuilder.getBuiltMap();
        }
        //</editor-fold>


        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2d = (Graphics2D) g;

            // define render quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // draw background
            g.drawImage(this.map, 0, 0, this);

            repaint();
        }
        //</editor-fold>

    }
    //</editor-fold>

}
