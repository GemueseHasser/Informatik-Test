package de.jonas.graphiccalculator.gui;

import de.jonas.graphiccalculator.object.CalculatorField;
import de.jonas.graphiccalculator.object.Gui;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.util.Objects;

/**
 * Das {@link MainGui} ist eine Instanz eines {@link Gui} und stellt das Menü des Taschenrechners dar, in welchem man
 * die Aktion auswählen kann, die man mit dem {@link de.jonas.graphiccalculator.GraphicCalculator} durchführen möchte.
 */
@NotNull
public final class MainGui extends Gui {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    @NotNull
    private static final String TITLE = "Grafischer Taschenrechner";
    /** Die Breite dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int WIDTH = 340;
    /** Die Höhe dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int HEIGHT = 500;
    /** Die Größe jeder Auswahlmöglichkeit an Aktionen, die ausgeführt werden sollen. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int CALC_ACTION_SIZE = 150;
    /** Alle Felder, die das {@link CalculatorGui} des Taschenrechners besitzen soll. */
    @NotNull
    private static final CalculatorField[] CALCULATOR_FIELDS = {
        new CalculatorField("sin"),
        new CalculatorField("cos"),
        new CalculatorField("tan"),
        new CalculatorField("√"),
        new CalculatorField("π"),
        new CalculatorField("C"),
        new CalculatorField("x^y"),
        new CalculatorField("("),
        new CalculatorField(")"),
        new CalculatorField("⬅"),
        new CalculatorField("7"),
        new CalculatorField("8"),
        new CalculatorField("9"),
        new CalculatorField("-"),
        new CalculatorField("÷"),
        new CalculatorField("4"),
        new CalculatorField("5"),
        new CalculatorField("6"),
        new CalculatorField("+"),
        new CalculatorField("×"),
        new CalculatorField("1"),
        new CalculatorField("2"),
        new CalculatorField("3"),
        new CalculatorField(","),
        new CalculatorField("=", true),
        new CalculatorField(""),
        new CalculatorField("0"),
        new CalculatorField(""),
        new CalculatorField("e"),
    };
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link MainGui}. Das {@link MainGui} ist eine Instanz eines {@link Gui} und
     * stellt das Menü des Taschenrechners dar, in welchem man die Aktion auswählen kann, die man mit dem
     * {@link de.jonas.graphiccalculator.GraphicCalculator} durchführen möchte.
     */
    public MainGui() {
        super(TITLE, WIDTH, HEIGHT);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton calcFunctionButton = getActionButton("calcFunction.png", "Rechnen");
        calcFunctionButton.setBounds(10, 10, CALC_ACTION_SIZE, CALC_ACTION_SIZE);
        calcFunctionButton.addActionListener(actionEvent -> new CalculatorGui(CALCULATOR_FIELDS));

        final JButton drawFunctionButton = getActionButton("drawFunction.png", "Zeichnen");
        drawFunctionButton.setBounds(20 + CALC_ACTION_SIZE, 10, CALC_ACTION_SIZE, CALC_ACTION_SIZE);
        drawFunctionButton.addActionListener(actionEvent -> new FunctionGui());

        super.add(calcFunctionButton);
        super.add(drawFunctionButton);
    }
    //</editor-fold>


    /**
     * Gibt einen Button zurück, welcher ein Icon und einen Namen besitzt, der unter dem Icon angezeigt wird zurück.
     *
     * @param ressourceName Die Bilddatei, welche als Icon genutzt werden soll.
     * @param displayName   Der Name, welcher unter dem Icon angezeigt werden soll.
     *
     * @return Ein Button, welcher ein Icon und einen Namen besitzt, der unter dem Icon angezeigt wird.
     */
    @NotNull
    private JButton getActionButton(
        @NotNull final String ressourceName,
        @NotNull final String displayName
    ) {
        final JButton button = new JButton(displayName);
        button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + ressourceName))));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        return button;
    }

}
