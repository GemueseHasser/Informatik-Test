package de.jonas.informatik;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class Quadrate extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Die Anzahl an Quadraten per Reihe und per Spalte. */
    private static final int N = 5;
    /** Die Größe jedes Quadrats. */
    private static final int SIZE = 50;
    /** Der jeweilige Abstand links und rechts zum Rand des Fensters. */
    private static final int BORDER = 100;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Alle {@link SquareData Daten} der einzelnen Quadrate. */
    private final SquareData[] squareData = new SquareData[N * N];
    //</editor-fold>


    // main method
    public static void main(String[] args) {
        final Quadrate q = new Quadrate();
        q.setBounds(0, 0, 600, 600);
        q.setLocationRelativeTo(null);
        q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        q.setResizable(false);
        q.setTitle("Black Squares");
        q.setVisible(true);
    }


    // Constructor    
    public Quadrate() {

        int x = BORDER;
        int y = BORDER;
        int current = 0;

        // fill square data array
        for (int i = 0; i < N; i++) {
            // space between the squares
            int sep = 15;
            for (int j = 0; j < N; j++) {
                squareData[current] = new SquareData(x, y);

                x += (SIZE + sep);
                current++;
            }

            y += (SIZE + sep);
            x = BORDER;
        }

        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                final SquareData clicked = getClickedSquare(me);

                if (clicked != null) {

                    if (clicked.getColor() == Color.BLACK) {
                        clicked.setColor(Color.YELLOW);
                    } else {
                        clicked.setColor(Color.BLACK);
                    }

                    repaint();
                }
            }
        });
    }

    private SquareData getClickedSquare(MouseEvent me) {
        for (final SquareData data : squareData) {
            // check the x coordinate
            if (data.getX() > me.getX() || (data.getX() + SIZE) < me.getX()) continue;

            // check the y coordinate
            if (data.getY() > me.getY() || (data.getY() + SIZE) < me.getY()) continue;

            // return the correct data
            return data;
        }

        // return null - no square was clicked
        return null;
    }

    @Override
    public void paint(Graphics g) {
        for (final SquareData data : squareData) {
            g.setColor(data.getColor());
            g.fillRect(data.getX(), data.getY(), SIZE, SIZE);
        }
    }


    //<editor-fold desc="SquareData">

    /**
     * Eine {@link SquareData} stellt eine Data-Klasse dar, in der die Daten für jedes einzelne Quadrat abgespeichert
     * werden, welche aus X- und Y-Koordinate und einer Farbe bestehen.
     */
    private static final class SquareData {

        /** Die X-Koordinate des Quadrats. */
        private final int x;
        /** Die Y-Koordinate des Quadrats. */
        private final int y;
        /** Die aktuelle Farbe des Quadrats. */
        private Color color = Color.BLACK;


        /**
         * Erzeugt mithilfe einer X- und einer Y-Koordinate eine neue und vollständig unabhängige Instanz einer {@link
         * SquareData}, welche eine Data-Klasse darstellt, in der die Daten für jedes einzelne Quadrat abgespeichert
         * werden, welche aus X- und Y-Koordinate und einer Farbe bestehen.
         *
         * @param x Die X-Koordinate des Quadrats.
         * @param y Die Y-Koordinate des Quadrats.
         */
        public SquareData(
            final int x,
            final int y
        ) {
            this.x = x;
            this.y = y;
        }


        /**
         * Setzt die Farbe des Quadrats neu.
         *
         * @param color Die neue Farbe des Quadrats.
         */
        public void setColor(final Color color) {
            this.color = color;
        }

        /**
         * Gibt die X-Koordinate des Quadrats zurück.
         *
         * @return Die X-Koordinate des Quadrats.
         */
        public int getX() {
            return this.x;
        }

        /**
         * Gibt die Y-Koordinate des Quadrats zurück.
         *
         * @return Die Y-Koordinate des Quadrats.
         */
        public int getY() {
            return this.y;
        }

        /**
         * Gibt die aktuelle Farbe des Quadrats zurück.
         *
         * @return Die aktuelle Farbe des Quadrats.
         */
        public Color getColor() {
            return this.color;
        }
    }
    //</editor-fold>
}