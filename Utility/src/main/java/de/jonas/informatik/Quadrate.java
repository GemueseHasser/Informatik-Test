package de.jonas.informatik;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p>Beim Erstellen einer Instanz des {@link Quadrate}, wird ein Fenster erzeugt, auf dem 25 (5 x 5) Kästchen
 * angezeigt werden. Beim Anklicken dieser Kästchen verändert sich die Farbe immer wieder. Alle Kästchen sind zu Beginn
 * schwarz, wenn man sie dann anklickt, verändert sich die Farbe zu gelb und wenn man sie dann nochmals anklickt, wird
 * das Kästchen wieder schwarz. Das ist ein Prozess, den man endlos ausführen kann.</p>
 *
 * <p>Die Größe der Quadrate wird bei Veränderung der Größe des Fensters immer wieder konstant angepasst. Somit
 * liegen die Quadrate immer proportional zu der Größe des Fensters an den passenden X- und Y-Koordinaten im
 * Fenster.</p>
 */
public final class Quadrate extends JFrame implements ComponentListener {

    //<editor-fold desc="CONSTANTS">
    /** Die Anzahl an Quadraten per Reihe und per Spalte. */
    private static final int N = 5;
    /** Die Größe des Fensters zu Beginn. */
    private static final int BEGIN_SIZE = 500;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Alle {@link SquareData Daten} der einzelnen Quadrate. */
    private final SquareData[] squareData = new SquareData[N * N];
    /** Die aktuelle Größe jedes Quadrats. */
    private int size;
    /** Der Abstand zwischen den einzelnen Kästchen. */
    private int margin;
    //</editor-fold>


    /**
     * Die Main-Methode dieser Klasse, die als allererstes aufgerufen wird und von der aus die gesamte Klasse
     * (Anwendung) instanziiert wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(String[] args) {
        final Quadrate q = new Quadrate();
        q.setBounds(0, 0, BEGIN_SIZE, BEGIN_SIZE);
        q.setLocationRelativeTo(null);
        q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        q.setResizable(true);
        q.setTitle("Black Squares");
        q.addComponentListener(new Quadrate());
        q.setVisible(true);
    }


    /**
     * <p>Erzeugt eine neue Instanz eines {@link Quadrate}. Beim Erstellen einer Instanz des {@link Quadrate}, wird
     * ein Fenster erzeugt, auf dem 25 (5 x 5) Kästchen angezeigt werden. Beim Anklicken dieser Kästchen verändert sich
     * die Farbe immer wieder. Alle Kästchen sind zu Beginn schwarz, wenn man sie dann anklickt, verändert sich die
     * Farbe zu gelb und wenn man sie dann nochmals anklickt, wird das Kästchen wieder schwarz. Das ist ein Prozess, den
     * man endlos ausführen kann.</p>
     *
     * <p>Die Größe der Quadrate wird bei Veränderung der Größe des Fensters immer wieder konstant angepasst. Somit
     * liegen die Quadrate immer proportional zu der Größe des Fensters an den passenden X- und Y-Koordinaten im
     * Fenster.</p>
     */
    public Quadrate() {
        calculateSize(BEGIN_SIZE, BEGIN_SIZE);
        updateSquareData(BEGIN_SIZE, BEGIN_SIZE);

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

    /**
     * Gibt das angeklickte Quadrat zurück, wenn ein Quadrat angeklickt wurde. Wenn nicht, wird einfach {@code null}
     * zurückgegeben.
     *
     * @param me Das {@link MouseEvent}, aus dem die X- und Y-Koordinaten genutzt werden, womit die Position der Maus
     *           bestimmt wird.
     *
     * @return Wenn ein Quadrat angeklickt wurde bzw. sich die Maus in einem Quadrat befindet und diese Methode
     *     aufgerufen wurde, wird die {@link SquareData} des passenden Quadrats zurückgegeben, ansonsten {@code null}.
     */
    private SquareData getClickedSquare(MouseEvent me) {
        for (final SquareData data : squareData) {
            // check the x coordinate
            if (data.getX() > me.getX() || (data.getX() + size) < me.getX()) continue;

            // check the y coordinate
            if (data.getY() > me.getY() || (data.getY() + size) < me.getY()) continue;

            // return the correct data
            return data;
        }

        // return null - no square was clicked
        return null;
    }

    /**
     * Berechnet die Größe aller Quadrate neu.
     *
     * @param width  Die Breite des Fensters, welches zur Berechnung der Größe genutzt wird.
     * @param height Die Höhe des Fensters, welches zur Berechnung der Größe genutzt wird.
     */
    private void calculateSize(final int width, final int height) {
        final int minSize = Math.min(width, height);
        this.size = (minSize / N) - (minSize / (N * 2));
        this.margin = this.size / (N - 1);
    }

    /**
     * Aktualisiert alle {@link SquareData}. Wenn eine {@link SquareData} fehlt wird eine neue Instanz erzeugt und
     * ansonsten wird die aktuelle Instanz aktualisiert.
     *
     * @param width  Die Breite des Fensters, welches zur Berechnung der Koordinaten genutzt wird.
     * @param height Die Höhe des Fensters, welches zur Berechnung der Koordinaten genutzt wird.
     */
    private void updateSquareData(final int width, final int height) {
        int x = ((width - (this.size * N)) / 2) - (width / 20);
        int y = ((height - (this.size * N)) / 2) - (height / 70);
        int current = 0;

        // fill square data array
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (squareData[current] == null) {
                    squareData[current] = new SquareData(x, y);
                } else {
                    squareData[current].updateCoordinates(x, y);
                }

                x += (size + margin);
                current++;
            }

            y += (size + margin);
            x = ((width - (this.size * N)) / 2) - (width / 20);
        }
    }

    //<editor-fold desc="implementation">
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        calculateSize(super.getWidth(), super.getHeight());
        updateSquareData(super.getWidth(), super.getHeight());

        for (final SquareData data : squareData) {
            g.setColor(data.getColor());
            g.fillRect(data.getX(), data.getY(), size, size);
        }

    }

    @Override
    public void componentResized(final ComponentEvent componentEvent) {
        repaint();
    }

    @Override
    public void componentMoved(final ComponentEvent componentEvent) {

    }

    @Override
    public void componentShown(final ComponentEvent componentEvent) {

    }

    @Override
    public void componentHidden(final ComponentEvent componentEvent) {

    }
    //</editor-fold>


    //<editor-fold desc="SquareData">

    /**
     * Eine {@link SquareData} stellt eine Data-Klasse dar, in der die Daten für jedes einzelne Quadrat abgespeichert
     * werden, welche aus X- und Y-Koordinate und einer Farbe bestehen.
     */
    private static final class SquareData {

        /** Die X-Koordinate des Quadrats. */
        private int x;
        /** Die Y-Koordinate des Quadrats. */
        private int y;
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
         * Aktualisiert die X- und Y-Koordinaten dieses Quadrats.
         *
         * @param x Die neue X-Koordinate dieses Quadrats.
         * @param y Die neue Y-Koordinate dieses Quadrats.
         */
        public void updateCoordinates(final int x, final int y) {
            this.x = x;
            this.y = y;
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