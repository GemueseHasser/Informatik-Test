package de.jonas.informatik;

import javax.swing.JFrame;

import java.awt.Graphics;

/**
 * Ein {@link Circlet} besteht aus einer beliebigen Anzahl an Kreisen, die alle kreisförmig angeordnet sind. Der Radius
 * des (imaginären) großen Kreises ist beliebig konfigurierbar (wird bei der Instanziierung festgelegt) und die Anzahl
 * an Kreisen, die auf dem (imaginären) großen Kreis liegen ist ebenfalls beliebig konfigurierbar (wird auch bei der
 * Instanziierung festgelegt).
 */
public final class Circlet extends JFrame {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der insgesamte Radius des {@link Circlet}. */
    private final int radius;
    /** Die Anzahl an Kreisen, aus denen das {@link Circlet} bestehen soll. */
    private final int circleAmount;
    /** Die Größe eines jeden Kreises, aus denen das {@link Circlet} besteht. */
    private final int circleSize;
    /** Die Fenster-Größe, auf dem das {@link Circlet} gezeichnet werden soll (Das Fenster ist Quadratisch). */
    private final int frameSize;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode, die vor allen anderen Methoden von der JRE aufgerufen wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        final Circlet circlet = new Circlet(200, 20);
        circlet.setTitle("Circlet");
        circlet.setBounds(0, 0, circlet.getFrameSize(), circlet.getFrameSize());
        circlet.setLocationRelativeTo(null);
        circlet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        circlet.setResizable(false);
        circlet.setVisible(true);
    }
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe eines Radius und einer Anzahl an Kreisen eine neue und vollständig unabhängige Instanz eines
     * {@link Circlet}. Ein {@link Circlet} besteht aus einer beliebigen Anzahl an Kreisen, die alle kreisförmig
     * angeordnet sind. Der Radius des (imaginären) großen Kreises ist beliebig konfigurierbar (wird bei der
     * Instanziierung festgelegt) und die Anzahl an Kreisen, die auf dem (imaginären) großen Kreis liegen ist ebenfalls
     * beliebig konfigurierbar (wird auch bei der Instanziierung festgelegt). Die Größe eines jeden kleinen Kreises wird
     * mithilfe des Kosinussatzes berechnet, woraus sich dann die Größe des Fensters ergibt.
     *
     * @param radius       Der Radius, den dieses {@link Circlet} insgesamt haben soll.
     * @param circleAmount Die Anzahl an Kreisen, aus denen dieses {@link Circlet} insgesamt bestehen soll.
     */
    public Circlet(
        final int radius,
        final int circleAmount
    ) {
        this.radius = radius;
        this.circleAmount = circleAmount;

        // calculate the size of every circle with the 'cosine law'
        this.circleSize = (int) Math.sqrt(
            (radius * radius) + (radius * radius) - ((2 * radius * radius) * Math.cos(2 * Math.PI / circleAmount))
        );

        // calculate the frame size
        this.frameSize = (int) (2.5 * (this.radius + (this.circleSize / 2)));
    }
    //</editor-fold>


    /**
     * Stellt den Getter für die Fenster-Größe dar. Die Fenster-Größe ist für jedes Instanziierte {@link Circlet}
     * individuell, da diese mit den übergebenen Parametern auf jedes {@link Circlet} angepasst wird bzw. extra
     * kalkuliert wird.
     *
     * @return Die Fenster-Größe.
     */
    public int getFrameSize() {
        return this.frameSize;
    }

    //<editor-fold desc="implementation">
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        // calculate the middle of the frame
        final int middleX = super.getWidth() / 2;
        final int middleY = (super.getHeight() / 2) + 11;

        // draw all circles
        for (int i = 0; i < this.circleAmount; i++) {
            // calculate the x- and y-coordinate of the current circle
            final int x = (int) (this.radius * Math.cos((2 * Math.PI / this.circleAmount) * i)) + middleX - (this.circleSize / 2);
            final int y = (int) (-(this.radius * Math.sin((2 * Math.PI / this.circleAmount) * i))) + middleY - (this.circleSize / 2);

            // draw current circle
            g.drawOval(
                x,
                y,
                this.circleSize,
                this.circleSize
            );
        }
    }
    //</editor-fold>
}
