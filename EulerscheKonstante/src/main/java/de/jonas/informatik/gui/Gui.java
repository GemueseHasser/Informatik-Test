package de.jonas.informatik.gui;

import de.jonas.informatik.EulerscheKonstante;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;

/**
 * Ein {@link Gui} stellt die grafische Oberfläche dieser Anwendung dar, da die berechneten Werte der Eulerschen
 * Konstante, die zuvor mit dem {@link de.jonas.informatik.handler.CalculationHandler} berechnet wurden in diesem
 * Fenster in einem Koordinaten-System grafisch dargestellt werden.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Eulersche Konstante - Graph";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 1000;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 650;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Gui}. Ein {@link Gui} stellt die grafische
     * Oberfläche dieser Anwendung dar, da die berechneten Werte der Eulerschen Konstante, die zuvor mit dem
     * {@link de.jonas.informatik.handler.CalculationHandler} berechnet wurden in diesem Fenster in einem
     * Koordinaten-System grafisch dargestellt werden.
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
    }
    //</editor-fold>


    /**
     * Öffnet dieses {@link Gui}, macht das bereits instanziierte Fenster also sichtbar.
     */
    public void open() {
        super.setVisible(true);
    }

    //<editor-fold desc="Draw">

    /**
     * Mithilfe eines {@link Draw}, werden alle Grafiken und somit das gesamte Koordinaten-System in dieses {@link Gui}
     * gezeichnet.
     */
    private static final class Draw extends JPanel {

        //<editor-fold desc="CONSTANTS">
        /** Der Abstand der x-Achse zur unteren Kante des Fensters. */
        private static final int X_AXIS_MARGIN_BOTTOM = 200;
        /** Der Abstand der y-Achse zur linken Kante des Fensters. */
        private static final int Y_AXIS_MARGIN_LEFT = 60;
        /** Der Abstand in Pixeln zwischen den einzelnen Beschriftungen. */
        private static final int LABEL_MARGIN = 50;
        /** Die standard Schriftart, welche genutzt wird, um auf das Fenster zu schreiben. */
        private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);
        //</editor-fold>


        /**
         * Übersetzt die X-Koordinate in diesem Koordinaten-System für ein bestimmtes X.
         *
         * @param x Das X, welches übersetzt wird, sodass es in diesem Koordinaten-System passend angezeigt wird.
         *
         * @return die übersetzte X-Koordinate in diesem Koordinaten-System für ein bestimmtes X.
         */
        private int getX(final int x) {
            return Y_AXIS_MARGIN_LEFT + (x * LABEL_MARGIN / (EulerscheKonstante.getCalculationHandler().getCalculationSpan() / 17));
        }

        /**
         * Übersetzt die Y-Koordinate in diesem Koordinaten-System für ein bestimmtes Y.
         *
         * @param y Das Y, welches übersetzt wird, sodass es in diesem Koordinaten-System passend angezeigt wird.
         *
         * @return die übersetzte Y-Koordinate in diesem Koordinaten-System für ein bestimmtes Y.
         */
        private int getY(final double y) {
            return (int) (Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM - y * LABEL_MARGIN * 10) + 4 * LABEL_MARGIN;
        }

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

            // draw coordinate system
            g.setColor(Color.BLACK);

            // draw best founded approximate and accuracy
            g.setFont(DEFAULT_FONT);
            g.drawString("Näherungswert: " + EulerscheKonstante.getCalculationHandler().getApproximate(), 10, 20);

            // x-axis
            g.drawLine(
                Y_AXIS_MARGIN_LEFT - 10,
                Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM,
                Gui.WIDTH - 60,
                Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM
            );
            g.drawLine(
                Gui.WIDTH - 70,
                Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM + 10,
                Gui.WIDTH - 60,
                Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM
            );
            g.drawLine(
                Gui.WIDTH - 70,
                Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM - 10,
                Gui.WIDTH - 60,
                Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM
            );

            // y-axis
            g.drawLine(
                Y_AXIS_MARGIN_LEFT,
                Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM + 10,
                Y_AXIS_MARGIN_LEFT,
                X_AXIS_MARGIN_BOTTOM - 100
            );
            g.drawLine(
                Y_AXIS_MARGIN_LEFT + 10,
                X_AXIS_MARGIN_BOTTOM - 90,
                Y_AXIS_MARGIN_LEFT,
                X_AXIS_MARGIN_BOTTOM - 100
            );
            g.drawLine(
                Y_AXIS_MARGIN_LEFT - 10,
                X_AXIS_MARGIN_BOTTOM - 90,
                Y_AXIS_MARGIN_LEFT,
                X_AXIS_MARGIN_BOTTOM - 100
            );

            // draw x labels
            for (int i = 0; i <= 17; i++) {
                g.drawLine(
                    Y_AXIS_MARGIN_LEFT + i * LABEL_MARGIN,
                    Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM - 5,
                    Y_AXIS_MARGIN_LEFT + i * LABEL_MARGIN,
                    Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM + 5
                );

                g.drawString(
                    String.valueOf((EulerscheKonstante.getCalculationHandler().getCalculationSpan() / 17) * i),
                    Y_AXIS_MARGIN_LEFT + i * 50 - 5,
                    Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM + 30
                );
            }

            // draw y labels
            for (double i = 0; i <= 0.6; i += 0.1) {
                g.drawLine(
                    Y_AXIS_MARGIN_LEFT - 5,
                    (int) (Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM - i * 10 * LABEL_MARGIN),
                    Y_AXIS_MARGIN_LEFT + 5,
                    (int) (Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM - i * 10 * LABEL_MARGIN)
                );

                g.drawString(
                    String.valueOf(Math.round((i + 0.4) * 10.0) / 10.0),
                    Y_AXIS_MARGIN_LEFT - 30,
                    (int) (Gui.HEIGHT - X_AXIS_MARGIN_BOTTOM - i * 10 * LABEL_MARGIN) + 5
                );
            }

            // draw values
            g.setColor(Color.RED);

            for (final Map.Entry<Integer, Double> point : EulerscheKonstante.getCalculationHandler().getCalculatedValues().entrySet()) {
                // get current coordinates
                final int x = getX(point.getKey());
                final int y = getY(point.getValue());

                // get next entry in map
                final Map.Entry<Integer, Double> next = EulerscheKonstante.getCalculationHandler().getCalculatedValues().higherEntry(
                    point.getKey()
                );

                // check if next entry is preset
                if (next == null) continue;

                // get next coordinates
                final int nextX = getX(next.getKey());
                final int nextY = getY(next.getValue());

                // draw line
                g.drawLine(x, y, nextX, nextY);
            }
        }
        //</editor-fold>
    }
    //</editor-fold>

}
