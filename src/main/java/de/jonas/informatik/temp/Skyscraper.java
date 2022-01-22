package de.jonas.informatik.temp;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Es wird auf ein leeres {@link JFrame Fenster} ein Haus mit beliebig vielen Etagen gezeichnet. Das Haus hat im
 * Erdgeschoss eine Tür und ein Fenster und dann in beliebig vielen (variable Anzahl an Etagen, welche im Konstruktor
 * übergeben wird) Etagen, in gleichen Abständen, Fenster.
 */
public final class Skyscraper extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Die Breite des Haus, worauf die restlichen Werte und Proportionen des Haus aufbauen. */
    private static final int HOUSE_WIDTH = 200;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die Anzahl an Etagen, die das Haus haben soll. */
    private final int floors;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz des {@link Skyscraper}. Hiermit wird dann auf ein leeres {@link JFrame Fenster} ein
     * Haus mit beliebig vielen Etagen gezeichnet. Das Haus hat im Erdgeschoss eine Tür und ein Fenster und dann in
     * beliebig vielen (variable Anzahl an Etagen, welche im Konstruktor übergeben wird) Etagen, in gleichen Abständen,
     * Fenster.
     *
     * @param floors Die Anzahl an Etagen, die das Haus haben soll.
     */
    public Skyscraper(final int floors) {
        this.floors = floors;
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        final int floorHeight = HOUSE_WIDTH / 2;
        final int windowSize = HOUSE_WIDTH / 5;

        final int bodyX = super.getWidth() / 2 - HOUSE_WIDTH / 2;
        final int bodyY = super.getHeight() - 10 - this.floors * floorHeight;

        final int rooftopY = bodyY - HOUSE_WIDTH / 2;

        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // draw house shape
        g.drawRect(
            bodyX,
            bodyY,
            HOUSE_WIDTH,
            this.floors * floorHeight
        );

        g.drawLine(
            bodyX,
            bodyY,
            bodyX + HOUSE_WIDTH / 2,
            bodyY - HOUSE_WIDTH / 2
        );

        g.drawLine(
            bodyX + HOUSE_WIDTH,
            bodyY,
            bodyX + HOUSE_WIDTH / 2,
            bodyY - HOUSE_WIDTH / 2
        );

        g.setColor(Color.LIGHT_GRAY);

        // draw ground floor
        g.fillRect(
            bodyX + windowSize,
            bodyY + this.floors * floorHeight - windowSize * 2,
            windowSize,
            windowSize * 2
        );

        g.fillRect(
            bodyX + windowSize * 3,
            bodyY + this.floors * floorHeight - windowSize * 2,
            windowSize,
            windowSize
        );

        // draw all floors
        for (int i = 0; i < this.floors; i++) {
            final int y = bodyY + (i == 0 ? windowSize : windowSize * (3 + (i - 1) * 2));
            g.fillRect(
                bodyX + windowSize,
                y,
                windowSize,
                windowSize
            );

            g.fillRect(
                bodyX + windowSize * 3,
                y,
                windowSize,
                windowSize
            );
        }

        // draw round window
        g.fillOval(
            bodyX + HOUSE_WIDTH / 2 - windowSize / 2,
            rooftopY + HOUSE_WIDTH / 4 - windowSize / 2,
            windowSize,
            windowSize
        );

        // draw chimney
        g.setColor(Color.BLACK);

        final int chimneyWidth = HOUSE_WIDTH / 6;

        final int middleX = bodyX + 3 * (HOUSE_WIDTH / 4);
        final int middleY = rooftopY + ((bodyY - rooftopY) / 2);

        g.drawLine(middleX - chimneyWidth / 2, middleY - chimneyWidth / 2, middleX - chimneyWidth / 2, rooftopY);
        g.drawLine(middleX + chimneyWidth / 2, middleY + chimneyWidth / 2, middleX + chimneyWidth / 2, rooftopY);
        g.drawLine(middleX + chimneyWidth / 2, rooftopY, middleX - chimneyWidth / 2, rooftopY);
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
        final Skyscraper skyscraper = new Skyscraper(3);
        skyscraper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        skyscraper.setBounds(0, 0, 600, 600);
        skyscraper.setLocationRelativeTo(null);
        skyscraper.setResizable(true);
        skyscraper.setTitle("Hochhaus");
        skyscraper.setVisible(true);
    }
    //</editor-fold>
}
