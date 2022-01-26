package de.jonas.informatik.converter.gui;

import de.jonas.informatik.converter.Converter;
import de.jonas.informatik.converter.ConverterField;
import de.jonas.informatik.converter.ConverterFunction;
import de.jonas.informatik.converter.ConverterKeyListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

/**
 * Ein {@link Gui} stellt eine Instanz eines {@link AbstractGui} dar. In diesem Gui können Zahlen eingegeben werden, die
 * dann konvertiert werden sollen, in andere Formate, wie zum Beispiel in Binärzahlen, Oktalzahlen, Hexadezimalzahlen,
 * etc.
 */
public final class Gui extends AbstractGui {

    //<editor-fold desc="CONSTANTS">
    /** Der Title des Fensters. */
    private static final String TITLE = "Zahlen Konverter - by Jonas";
    /** Die Breite des Fensters. */
    private static final int WIDTH = 500;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 450;

    //<editor-fold desc="field">
    /** Die Breite eines Feldes, in welches man eine Zahl eingeben kann. */
    private static final int FIELD_WIDTH = 250;
    /** Die Höhe eines Feldes, in welches man eine Zahl eingeben kann. */
    private static final int FIELD_HEIGHT = 40;
    /** Die X-Koordinate eines jeden Feldes, in welches man eine Zahl eingeben kann. */
    private static final int FIELD_X = 170;
    /** Alle Felder, die erzeugt werden sollen, um ein bestimmtes Zahlensystem widerzuspiegeln. */
    private static final ConverterField[] CONVERTER_FIELDS = {
        getConverterField(50, Converter.DECIMAL_FUNCTION),
        getConverterField(120, Converter.BINARY_FUNCTION),
        getConverterField(190, Converter.OCTAL_FUNCTION),
        getConverterField(260, Converter.HEX_FUNCTION),
    };
    //</editor-fold>

    //<editor-fold desc="label">
    /** Die X-Koordinate eines jeden Schriftzugs. */
    private static final int LABEL_X = 10;
    /** Die Y-Koordinate des Schriftzuges für das Dezimalsystem. */
    private static final int DECIMAL_Y = 50;
    /** Die Y-Koordinate des Schriftzuges für das Binärsystem. */
    private static final int BINARY_Y = 120;
    /** Die Y-Koordinate des Schriftzuges für das Oktalsystem. */
    private static final int OCTAL_Y = 190;
    /** Die Y-Koordinate des Schriftzuges für das Hexadezimalsystem. */
    private static final int HEX_Y = 260;
    //</editor-fold>

    //<editor-fold desc="custom-convert">
    /** Der Text des Buttons für die individuelle Konvertierung. */
    private static final String CUSTOM_CONVERT_BUTTON = "Individuelle Konvertierung";
    /** Die X-Koordinate des Buttons um eine individuelle Konvertierung vorzunehmen. */
    private static final int CUSTOM_CONVERT_X = 42;
    /** Die Y-Koordinate des Buttons um eine individuelle Konvertierung vorzunehmen. */
    private static final int CUSTOM_CONVERT_Y = 350;
    /** Die Breite des Buttons um eine individuelle Konvertierung vorzunehmen. */
    private static final int CUSTOM_CONVERT_WIDTH = 400;
    /** Die Höhe des Buttons um eine individuelle Konvertierung vorzunehmen. */
    private static final int CUSTOM_CONVERT_HEIGHT = 40;
    //</editor-fold>

    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}. Ein {@link Gui} stellt eine Instanz eines {@link AbstractGui} dar.
     * In diesem Gui können Zahlen eingegeben werden, die dann konvertiert werden sollen, in andere Formate, wie zum
     * Beispiel in Binärzahlen, Oktalzahlen, Hexadezimalzahlen, etc.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public Gui() {
        // create gui
        super(TITLE, WIDTH, HEIGHT);

        // add key listeners
        for (final ConverterField field : CONVERTER_FIELDS) {
            field.addKeyListener(new ConverterKeyListener(field, CONVERTER_FIELDS));
            super.add(field);
        }

        // add labels
        super.add(super.getFormattedLabel("Dezimal:", LABEL_X, DECIMAL_Y + DEFAULT_FONT.getSize() / 2 - 5));
        super.add(super.getFormattedLabel("Binär:", LABEL_X, BINARY_Y + DEFAULT_FONT.getSize() / 2 - 5));
        super.add(super.getFormattedLabel("Oktal:", LABEL_X, OCTAL_Y + DEFAULT_FONT.getSize() / 2 - 5));
        super.add(super.getFormattedLabel("Hexadezimal:", LABEL_X, HEX_Y + DEFAULT_FONT.getSize() / 2 - 5));

        // custom convert
        final JButton customConvert = new JButton(CUSTOM_CONVERT_BUTTON);
        customConvert.setBounds(CUSTOM_CONVERT_X, CUSTOM_CONVERT_Y, CUSTOM_CONVERT_WIDTH, CUSTOM_CONVERT_HEIGHT);
        customConvert.setFont(DEFAULT_FONT);
        customConvert.setFocusable(false);
        customConvert.setOpaque(true);
        customConvert.setBackground(Color.DARK_GRAY);
        customConvert.setForeground(Color.WHITE);
        customConvert.addActionListener(actionEvent -> new CustomConverterGui().open());

        super.add(customConvert);
    }
    //</editor-fold>


    //<editor-fold desc="utility">

    /**
     * Gibt ein {@link ConverterField} auf der Basis von {@code getFormattedConverterField} zurück.
     *
     * @param y        Die Y-Koordinate des {@link ConverterField}.
     * @param function Die Funktion, mit welcher das Feld formatiert werden soll.
     *
     * @return Ein {@link ConverterField} auf der Basis von {@code getFormattedConverterField}.
     */
    private static ConverterField getConverterField(final int y, final ConverterFunction function) {
        return getFormattedConverterField(FIELD_X, y, FIELD_WIDTH, FIELD_HEIGHT, function);
    }
    //</editor-fold>

    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {
        final Image basicBackground;

        try {
            // get background image url
            final URL backgroundUrl = this.getClass().getResource("/background.jpg");

            // read image from url
            assert backgroundUrl != null;
            basicBackground = ImageIO.read(backgroundUrl);
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }

        // draw background image
        g.drawImage(basicBackground, 0, 0, WIDTH, HEIGHT, null);
    }
    //</editor-fold>
}
