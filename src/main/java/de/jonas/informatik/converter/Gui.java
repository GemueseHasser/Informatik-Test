package de.jonas.informatik.converter;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

/**
 * Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar. In diesem Gui können Zahlen eingegeben werden, die dann
 * konvertiert werden sollen, in andere Formate, wie zum Beispiel in Binärzahlen, Oktalzahlen, Hexadezimalzahlen, etc.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Title des Fensters. */
    private static final String TITLE = "Zahlen Konverter";
    /** Die Breite des Fensters. */
    private static final int WIDTH = 500;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 390;
    /** Die Schriftart, die standardmäßig genutzt wird. */
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);
    /** Die Breite eines Feldes, in welches man eine Zahl eingeben kann. */
    private static final int FIELD_WIDTH = 150;
    /** Die Höhe eines Feldes, in welches man eine Zahl eingeben kann. */
    private static final int FIELD_HEIGHT = 40;
    /** Die X-Koordinate eines jeden Feldes, in welches man eine Zahl eingeben kann. */
    private static final int FIELD_X = 170;
    /** Die X-Koordinate eines jeden Schriftzugs. */
    private static final int LABEL_X = 10;
    /** Alle Felder, die erzeugt werden sollen, um ein bestimmtes Zahlensystem widerzuspiegeln. */
    private static final ConverterField[] CONVERTER_FIELDS = {
        getFormattedConverterField(50, Converter.DECIMAL_FUNCTION),
        getFormattedConverterField(120, Converter.BINARY_FUNCTION),
        getFormattedConverterField(190, Converter.OCTAL_FUNCTION),
        getFormattedConverterField(260, Converter.HEX_FUNCTION),
    };
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}. Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar. In
     * diesem Gui können Zahlen eingegeben werden, die dann konvertiert werden sollen, in andere Formate, wie zum
     * Beispiel in Binärzahlen, Oktalzahlen, Hexadezimalzahlen, etc.
     */
    public Gui() {
        // create gui
        super(TITLE);

        // set properties
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(null);

        // add key listeners
        for (final ConverterField field : CONVERTER_FIELDS) {
            field.addKeyListener(new ConverterKeyListener(field, CONVERTER_FIELDS));
            super.add(field);
        }

        // add labels
        super.add(getFormattedLabel("Dezimal:", 50 + DEFAULT_FONT.getSize() / 2));
        super.add(getFormattedLabel("Binär:", 120 + DEFAULT_FONT.getSize() / 2));
        super.add(getFormattedLabel("Oktal:", 190 + DEFAULT_FONT.getSize() / 2));
        super.add(getFormattedLabel("Hexadezimal:", 260 + DEFAULT_FONT.getSize() / 2));
    }
    //</editor-fold>


    /**
     * Öffnet dieses Fenster bzw. macht die bereits erzeugte Instanz dieses Fensters auf dem Bildschirm sichtbar, sodass
     * es den Anschein macht, als dass es geöffnet wird.
     */
    public void open() {
        super.setVisible(true);
    }

    /**
     * Gibt mithilfe eines Textes und einer Y-Koordinate einen korrekt formatierten Schriftzug zurück.
     *
     * @param text Der Text, welchen der Schriftzug beinhalten soll.
     * @param y    Die Y-Koordinate, an der sich der Schriftzug befinden soll.
     *
     * @return Einen korrekt formatierten Schriftzug.
     */
    private JLabel getFormattedLabel(final String text, final int y) {
        final JLabel label = new JLabel(text);

        final int width = super.getFontMetrics(DEFAULT_FONT).stringWidth(text);

        label.setBounds(LABEL_X, y, width, DEFAULT_FONT.getSize() + 5);
        label.setFont(DEFAULT_FONT);

        return label;
    }

    //<editor-fold desc="utility">

    /**
     * Gibt mithilfe einer Y-Koordinate ein {@link ConverterField} zurück, welches bereits korrekt formatiert ist.
     *
     * @param y        Die Y-Koordinate, an der sich das Textfeld befinden soll.
     * @param function Die Funktion, mit welcher das Feld formatiert werden soll.
     *
     * @return Ein {@link ConverterField}, welches bereits korrekt formatiert ist.
     */
    private static ConverterField getFormattedConverterField(final int y, final ConverterFunction function) {
        final ConverterField field = new ConverterField(function);
        field.setBounds(FIELD_X, y, FIELD_WIDTH, FIELD_HEIGHT);
        field.setFont(DEFAULT_FONT);
        field.setOpaque(true);
        field.setBackground(Color.LIGHT_GRAY);
        field.setForeground(Color.BLACK);

        return field;
    }
    //</editor-fold>
}
