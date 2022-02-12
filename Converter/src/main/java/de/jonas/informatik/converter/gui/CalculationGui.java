package de.jonas.informatik.converter.gui;

import de.jonas.informatik.converter.Converter;
import de.jonas.informatik.converter.handler.CalculationHandler;
import de.jonas.informatik.converter.handler.ConverterFunction;
import de.jonas.informatik.converter.object.ConverterField;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Ein {@link CalculationGui} stellt eine Instanz eines {@link AbstractGui} dar. In diesem Fenster können Zahlen mit
 * beliebigen Potenzen, also Zahlen aus einem beliebigen Zahlensystem miteinander verrechnet werden.
 */
public final class CalculationGui extends AbstractGui {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel des Fensters. */
    private static final String TITLE = "Rechner";
    /** Die Breite des Fensters. */
    private static final int WIDTH = 700;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 500;
    /** Der Text der Überschrift. */
    private static final String HEADING_TEXT = "Rechner für ganze Zahlen (+ | - | * | /)";

    //<editor-fold desc="system-box">
    /** Die X-Koordinate der Box, mit der man das Zahlensystem wählen kann. */
    private static final int SYSTEM_BOX_X = 20;
    /** Die Y-Koordinate der Box, mit der man das Zahlensystem wählen kann. */
    private static final int SYSTEM_BOX_Y = 100;
    /** Die Breite der Box, mit der man das Zahlensystem wählen kann. */
    private static final int SYSTEM_BOX_WIDTH = 100;
    /** Die Höhe der Box, mit der man das Zahlensystem wählen kann. */
    private static final int SYSTEM_BOX_HEIGHT = 30;
    //</editor-fold>

    //<editor-fold desc="term-field">
    /** Die X-Koordinate des Feldes, worin man den Term eingeben kann, der berechnet werden soll. */
    private static final int TERM_FIELD_X = 200;
    /** Die Y-Koordinate des Feldes, worin man den Term eingeben kann, der berechnet werden soll. */
    private static final int TERM_FIELD_Y = 100;
    /** Die Breite des Feldes, worin man den Term eingeben kann, der berechnet werden soll. */
    private static final int TERM_FIELD_WIDTH = 400;
    /** Die Höhe des Feldes, worin man den Term eingeben kann, der berechnet werden soll. */
    private static final int TERM_FIELD_HEIGHT = 50;
    //</editor-fold>

    //<editor-fold desc="result-field">
    /** Die X-Koordinate des Feldes, worin das Ergebnis angezeigt wird, nachdem der Term berechnet wurde. */
    private static final int RESULT_FIELD_X = 200;
    /** Die Y-Koordinate des Feldes, worin das Ergebnis angezeigt wird, nachdem der Term berechnet wurde. */
    private static final int RESULT_FIELD_Y = 200;
    /** Die Breite des Feldes, worin das Ergebnis angezeigt wird, nachdem der Term berechnet wurde. */
    private static final int RESULT_FIELD_WIDTH = 400;
    /** Die Höhe des Feldes, worin das Ergebnis angezeigt wird, nachdem der Term berechnet wurde. */
    private static final int RESULT_FIELD_HEIGHT = 50;
    //</editor-fold>

    //<editor-fold desc="calculate-button">
    /** Der Text des Buttons, womit man den eingegebenen Term berechnen lassen kann. */
    private static final String CALCULATE_TEXT = "Berechnen";
    /** Die X-Koordinate des Buttons, womit man den eingegebenen Term berechnen lassen kann. */
    private static final int CALCULATE_X = 90;
    /** Die Y-Koordinate des Buttons, womit man den eingegebenen Term berechnen lassen kann. */
    private static final int CALCULATE_Y = 350;
    /** Die Breite des Buttons, womit man den eingegebenen Term berechnen lassen kann. */
    private static final int CALCULATE_WIDTH = 500;
    /** Die Höhe des Buttons, womit man den eingegebenen Term berechnen lassen kann. */
    private static final int CALCULATE_HEIGHT = 35;
    //</editor-fold>

    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der {@link CalculationHandler}, womit dieser Rechner funktioniert. */
    private final CalculationHandler calculator = new CalculationHandler(Converter.DECIMAL_FUNCTION);
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link CalculationGui}. Ein {@link CalculationGui} stellt eine Instanz eines
     * {@link AbstractGui} dar. In diesem Fenster können Zahlen mit beliebigen Potenzen, also Zahlen aus einem
     * beliebigen Zahlensystem miteinander verrechnet werden.
     */
    public CalculationGui() {
        super(TITLE, WIDTH, HEIGHT);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // heading
        final JLabel heading = getFormattedLabel(HEADING_TEXT, 0, 20);
        heading.setBounds(heading.getX(), heading.getY(), WIDTH, heading.getHeight());
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBackground(Color.DARK_GRAY);
        heading.setForeground(Color.WHITE);

        // number system box
        final JComboBox<Integer> systemBox = getFormattedBox(
            SYSTEM_BOX_X,
            SYSTEM_BOX_Y,
            SYSTEM_BOX_WIDTH,
            SYSTEM_BOX_HEIGHT
        );
        systemBox.addActionListener(actionEvent -> {
            final Integer system = (Integer) systemBox.getSelectedItem();

            if (system == null) return;

            this.calculator.setConverterFunction(
                new ConverterFunction(
                    system,
                    null
                )
            );
        });

        // converter field without converter-function to show the result
        final ConverterField resultField = getFormattedConverterField(
            RESULT_FIELD_X,
            RESULT_FIELD_Y,
            RESULT_FIELD_WIDTH,
            RESULT_FIELD_HEIGHT,
            null
        );
        resultField.setEnabled(false);
        resultField.setDisabledTextColor(Color.BLACK);

        // converter field without converter-function to type the term
        final ConverterField termField = getFormattedConverterField(
            TERM_FIELD_X,
            TERM_FIELD_Y,
            TERM_FIELD_WIDTH,
            TERM_FIELD_HEIGHT,
            null
        );
        termField.addActionListener(actionEvent -> resultField.setText(
            CalculationGui.this.calculator.calculate(termField.getText())
        ));

        // calculate button
        final JButton calculate = getFormattedButton(
            CALCULATE_TEXT,
            CALCULATE_X,
            CALCULATE_Y,
            CALCULATE_WIDTH,
            CALCULATE_HEIGHT
        );
        calculate.setBackground(Color.LIGHT_GRAY);
        calculate.setForeground(Color.BLACK);
        calculate.addActionListener(actionEvent -> termField.postActionEvent());

        super.add(systemBox);
        super.add(heading);
        super.add(resultField);
        super.add(termField);
        super.add(calculate);
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    //</editor-fold>
}
