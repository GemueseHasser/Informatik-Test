package de.jonas.informatik.converter.gui;

import de.jonas.informatik.converter.ConverterField;
import de.jonas.informatik.converter.ConverterFunction;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Ein {@link AbstractGui} stellt ein Fenster dar, welches alle nötigen Voreinstellungen bekommt, sodass man sich bei
 * der Implementierung auf die wesentlichen Funktionen des Fensters konzentrieren kann.
 */
public abstract class AbstractGui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Die Schriftart, die standardmäßig genutzt wird. */
    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);
    //</editor-fold>


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
     * Gibt mithilfe eines Textes und einer Y-Koordinate einen korrekt formatierten Schriftzug zurück.
     *
     * @param text Der Text, welchen der Schriftzug beinhalten soll.
     * @param x    Die X-Koordinate, an der sich der Schriftzug befinden soll.
     * @param y    Die Y-Koordinate, an der sich der Schriftzug befinden soll.
     *
     * @return Einen korrekt formatierten Schriftzug.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public JLabel getFormattedLabel(final String text, final int x, final int y) {
        final JLabel label = new JLabel(text);

        final int labelWidth = super.getFontMetrics(DEFAULT_FONT).stringWidth(text);

        label.setBounds(x, y, labelWidth, DEFAULT_FONT.getSize() + 10);
        label.setFont(DEFAULT_FONT);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setForeground(Color.BLACK);

        return label;
    }

    /**
     * Diese Methode wird aufgerufen, sobald das {@link Draw} dieses Fensters instanziiert wurde. Mit dieser Methode
     * kann man dann ohne Umstände direkt alle Zeichnungen implementieren, die auf diesem Fenster vorgenommen werden
     * sollen.
     *
     * @param g Die {@link Graphics}, mit denen auf dieses Fenster gezeichnet werden soll.
     */
    public abstract void draw(Graphics g);

    //<editor-fold desc="utility">

    /**
     * Gibt mithilfe einer Y-Koordinate ein {@link ConverterField} zurück, welches bereits korrekt formatiert ist.
     *
     * @param x        Die X-Koordinate, an der sich das Textfeld befinden soll.
     * @param y        Die Y-Koordinate, an der sich das Textfeld befinden soll.
     * @param width    Die Breite des Textfeldes.
     * @param height   Die Höhe des Textfeldes.
     * @param function Die Funktion, mit welcher das Feld formatiert werden soll.
     *
     * @return Ein {@link ConverterField}, welches bereits korrekt formatiert ist.
     */
    public static ConverterField getFormattedConverterField(
        final int x,
        final int y,
        final int width,
        final int height,
        final ConverterFunction function
    ) {
        final ConverterField field = new ConverterField(function);
        field.setBounds(x, y, width, height);
        field.setFont(DEFAULT_FONT);
        field.setOpaque(true);
        field.setBackground(Color.LIGHT_GRAY);
        field.setForeground(Color.BLACK);

        return field;
    }
    //</editor-fold>


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
