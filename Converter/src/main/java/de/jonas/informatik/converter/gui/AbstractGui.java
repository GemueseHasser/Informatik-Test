package de.jonas.informatik.converter.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Graphics;

/**
 * Ein {@link AbstractGui} stellt ein Fenster dar, welches alle nötigen Voreinstellungen bekommt, sodass man sich bei
 * der Implementierung auf die wesentlichen Funktionen des Fensters konzentrieren kann.
 */
public abstract class AbstractGui extends JFrame {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Breite dieses Fensters. */
    private final int width;
    /** Die Höhe dieses Fensters. */
    private final int height;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link AbstractGui}. Ein {@link AbstractGui} stellt ein Fenster dar, welches alle
     * nötigen Voreinstellungen bekommt, sodass man sich bei der Implementierung auf die wesentlichen Funktionen des
     * Fensters konzentrieren kann.
     *
     * @param title  Der Titel dieses Fensters.
     * @param width  Die Breite dieses Fensters.
     * @param height Die Höhe dieses Fensters.
     */
    public AbstractGui(
        final String title,
        final int width,
        final int height
    ) {
        // create gui
        super(title);

        this.width = width;
        this.height = height;

        // set properties
        super.setBounds(0, 0, width, height);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(null);
    }
    //</editor-fold>

    /**
     * Öffnet dieses Fenster bzw. macht die bereits erzeugte Instanz dieses Fensters auf dem Bildschirm sichtbar, sodass
     * es den Anschein macht, als dass es geöffnet wird.
     */
    public void open() {
        super.setVisible(true);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, this.width, this.height);
        draw.setVisible(true);

        super.add(draw);
    }

    /**
     * Diese Methode wird aufgerufen, sobald das {@link Draw} dieses Fensters instanziiert wurde. Mit dieser Methode
     * kann man dann ohne Umstände direkt alle Zeichnungen implementieren, die auf diesem Fenster vorgenommen werden
     * sollen.
     *
     * @param g Die {@link Graphics}, mit denen auf dieses Fenster gezeichnet werden soll.
     */
    public abstract void draw(final Graphics g);


    //<editor-fold desc="Draw">

    /**
     * Mithilfe eines {@link Draw} wird auf dieses Fenster gezeichnet.
     */
    private final class Draw extends JLabel {

        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            draw(g);
        }
        //</editor-fold>
    }
    //</editor-fold>

}
