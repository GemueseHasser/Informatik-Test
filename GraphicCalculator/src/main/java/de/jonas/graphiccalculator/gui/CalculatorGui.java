package de.jonas.graphiccalculator.gui;

import de.jonas.graphiccalculator.handler.FunctionHandler;
import de.jonas.graphiccalculator.object.CalculatorField;
import de.jonas.graphiccalculator.object.Gui;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ein {@link CalculatorGui} ist eine Instanz eines {@link Gui} und stellt eine Oberfläche zur Berechnung einfacher
 * Terme ohne Variable zur Verfügung.
 */
@NotNull
public final class CalculatorGui extends Gui implements ActionListener {

    //<editor-fold desc="CONSTANTS">
    /** Die Breite des Fensters. */
    public static final int WIDTH = 500;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 720;
    /** Der Titel des Fensters. */
    private static final String TITLE = "Rechner";
    /** Die Höhe des Textfeldes, welches den aktuellen Term darstellt. */
    private static final int TERM_FIELD_HEIGHT = 100;
    /** Die Schriftart, die standardmäßig in diesem Fenster des Taschenrechners genutzt wird. */
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 28);
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Feld des Taschenrechners, in welchem der eingegebene Term visuell für den Nutzer festgehalten wird. */
    @NotNull
    private final JTextField termField;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link CalculatorGui}. Ein {@link CalculatorGui} ist eine Instanz eines
     * {@link Gui} und stellt eine Oberfläche zur Berechnung einfacher Terme ohne Variable zur Verfügung.
     */
    public CalculatorGui(@NotNull final CalculatorField @NotNull [] calculatorFields) {
        super(TITLE, WIDTH, HEIGHT);

        this.termField = new JTextField();
        this.termField.setBounds(0, 0, WIDTH, TERM_FIELD_HEIGHT);
        this.termField.setEnabled(false);
        this.termField.setDisabledTextColor(Color.BLACK);
        this.termField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 6));
        this.termField.setFont(DEFAULT_FONT);

        super.add(this.termField);

        for (int i = 0; i < calculatorFields.length; i++) {
            if (calculatorFields[i].getName().isEmpty()) continue;

            super.add(getButton(calculatorFields[i], i));
        }

        super.setVisible(true);
    }
    //</editor-fold>


    /**
     * Gibt einen Button zurück, welcher aufgrund seines 'counts' bereits platziert ist und alle Eigenschaften eines
     * Buttons für einen Taschenrechner besitzt.
     *
     * @param field Das entsprechende {@link CalculatorField} auf dessen Grundlage dieser Button erzeugt werden soll.
     * @param count Der aktuelle 'count' des Buttons, wodurch dieser Button an der richtigen Stelle platziert werden
     *              kann.
     *
     * @return Ein Button, welcher aufgrund seines 'counts' bereits platziert ist und alle Eigenschaften eines Buttons
     *     für einen Taschenrechner besitzt.
     */
    @NotNull
    private JButton getButton(
        @NotNull final CalculatorField field,
        @Range(from = 0, to = Integer.MAX_VALUE) final int count
    ) {
        final int x = (count % CalculatorField.FIELDS_PER_ROW) * CalculatorField.DEFAULT_WIDTH;
        final int y = (count / CalculatorField.FIELDS_PER_ROW) * CalculatorField.DEFAULT_HEIGHT + TERM_FIELD_HEIGHT;

        final JButton button = new JButton(field.getName());
        button.setBounds(x, y, CalculatorField.DEFAULT_WIDTH, field.getHeight());
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setBackground(Color.LIGHT_GRAY);
        button.setFont(DEFAULT_FONT);
        button.addActionListener(this);

        return button;
    }

    //<editor-fold desc="implementation">
    @Override
    public void actionPerformed(@NotNull final ActionEvent e) {
        if (!(e.getSource() instanceof JButton)) return;

        final JButton source = (JButton) e.getSource();

        switch (source.getText()) {
            case "C":
                this.termField.setText("");
                break;

            case "x^y":
                this.termField.setText(this.termField.getText() + "^");
                break;

            case "⬅":
                if (this.termField.getText().isEmpty()) return;

                this.termField.setText(this.termField.getText().substring(0, this.termField.getText().length() - 1));
                break;

            case "=":
                this.termField.setText(
                    String.valueOf(FunctionHandler.eval(
                        this.termField.getText()
                            .replaceAll("√", "sqrt")
                            .replaceAll("÷", "/")
                            .replaceAll("×", "*")
                            .replaceAll(",", ".")
                    ))
                );
                break;

            default:
                this.termField.setText(this.termField.getText() + source.getText());
                break;
        }
    }
    //</editor-fold>
}
