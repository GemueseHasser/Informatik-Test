package de.jonas.graphiccalculator.gui;

import de.jonas.graphiccalculator.handler.FunctionHandler;
import de.jonas.graphiccalculator.object.DrawFunction;
import de.jonas.graphiccalculator.object.Gui;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;

/**
 * Ein {@link FunctionGui} stellt eine Instanz eines {@link Gui} dar, welches eine grafische Oberfläche darstellt, auf
 * der eine bestimmte Funktion gezeichnet werden kann, mithilfe eines
 * {@link de.jonas.graphiccalculator.object.DrawFunction}.
 */
@NotNull
public final class FunctionGui extends Gui {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    @NotNull
    private static final String TITLE = "Function-Draw";
    /** Die Breite dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int WIDTH = 800;
    /** Die Höhe dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int HEIGHT = 850;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Textfeld, in welchem die Skalierung der x-Achse angegeben wird. */
    @NotNull
    private final JTextField xScalingField = new JTextField("10", 10);
    /** Das Textfeld, in welchem die Skalierung der y-Achse angegeben wird. */
    @NotNull
    private final JTextField yScalingField = new JTextField("10", 10);
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link FunctionGui}. Ein {@link FunctionGui} stellt eine Instanz eines
     * {@link Gui} dar, welches eine grafische Oberfläche darstellt, auf der eine bestimmte Funktion gezeichnet werden
     * kann, mithilfe eines {@link de.jonas.graphiccalculator.object.DrawFunction}.
     */
    public FunctionGui() {
        // create frame instance
        super(TITLE, WIDTH, HEIGHT);

        // create function message panel
        final JPanel[] messagePanel = new JPanel[3];

        messagePanel[0] = new JPanel();
        messagePanel[1] = new JPanel();
        messagePanel[2] = new JPanel();

        // add function field
        final JTextField functionField = new JTextField(10);
        messagePanel[0].add(new JLabel("f(x) = "));
        messagePanel[0].add(functionField);

        // add x-scaling field
        messagePanel[1].add(new JLabel("x-Skalierung: "));
        messagePanel[1].add(this.xScalingField);

        // add y-scaling field
        messagePanel[2].add(new JLabel("y-Skalierung: "));
        messagePanel[2].add(this.yScalingField);

        // create dialog
        final int functionDrawOption = JOptionPane.showConfirmDialog(
            null,
            messagePanel,
            "Funktion zeichnen",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (functionDrawOption != JOptionPane.OK_OPTION) return;

        // create draw object
        final DrawFunction drawFunction = new DrawFunction(
            new FunctionHandler(functionField.getText(), getXScaling()),
            getXScaling(),
            getYScaling()
        );
        drawFunction.setBounds(0, 0, WIDTH, HEIGHT);
        drawFunction.setVisible(true);

        final JButton rootsButton = getOptionButton("Nullstellen berechnen", 0, e -> {
            final JButton source = (JButton) e.getSource();

            if (source.getText().equalsIgnoreCase("Nullstellen berechnen")) {
                drawFunction.setEnableRoots(true);
                source.setText("Nullstellen ausblenden");
            } else {
                drawFunction.setEnableRoots(false);
                source.setText("Nullstellen berechnen");
            }

            drawFunction.repaint();
        });
        final JButton extremesButton = getOptionButton("Extremstellen berechnen", 1, e -> {
            final JButton source = (JButton) e.getSource();

            if (source.getText().equalsIgnoreCase("Extremstellen berechnen")) {
                drawFunction.setEnableExtremes(true);
                source.setText("Extremstellen ausblenden");
            } else {
                drawFunction.setEnableExtremes(false);
                source.setText("Extremstellen berechnen");
            }

            drawFunction.repaint();
        });
        final JButton markPointButton = getOptionButton("Punkt einzeichnen", 2, e -> {
            final String input = JOptionPane.showInputDialog(
                null,
                "X-Wert:",
                "Punkt einzeichnen",
                JOptionPane.PLAIN_MESSAGE
            );

            if (input == null) return;

            try {
                final double x = Double.parseDouble(input.replaceAll(",", "."));

                drawFunction.addMarkedPoint(x);
                drawFunction.repaint();
            } catch (@NotNull final NumberFormatException ignored) {
            }
        });
        final JButton derivationButton = getOptionButton("Ableitung zeichnen", 3, e -> {
            final JButton source = (JButton) e.getSource();

            if (source.getText().equalsIgnoreCase("Ableitung zeichnen")) {
                drawFunction.setEnableDerivation(true);
                source.setText("Ableitung ausblenden");
            } else {
                drawFunction.setEnableDerivation(false);
                source.setText("Ableitung zeichnen");
            }

            drawFunction.repaint();
        });

        // add components
        super.add(rootsButton);
        super.add(extremesButton);
        super.add(markPointButton);
        super.add(derivationButton);
        super.add(drawFunction);
        super.setVisible(true);
    }
    //</editor-fold>


    /**
     * Gibt die Skalierung der x-Achse unter Berücksichtigung einer falschen Eingebe des Nutzers zurück.
     *
     * @return Die Skalierung der x-Achse unter Berücksichtigung einer falschen Eingebe des Nutzers.
     */
    public int getXScaling() {
        try {
            return Integer.parseInt(this.xScalingField.getText());
        } catch (@NotNull final NumberFormatException ignored) {
            return 10;
        }
    }

    /**
     * Gibt die Skalierung der y-Achse unter Berücksichtigung einer falschen Eingebe des Nutzers zurück.
     *
     * @return Die Skalierung der y-Achse unter Berücksichtigung einer falschen Eingebe des Nutzers.
     */
    public int getYScaling() {
        try {
            return Integer.parseInt(this.yScalingField.getText());
        } catch (@NotNull final NumberFormatException ignored) {
            return 10;
        }
    }

    /**
     * Gibt einen positionierten Button mit entsprechendem Text und entsprechendem {@link ActionListener} zurück.
     *
     * @param text           Der Text des Buttons.
     * @param count          Die Nummer des Buttons beginnend bei 0.
     * @param actionListener Der {@link ActionListener}, welcher ausgeführt werden soll, wenn der Button angeklickt
     *                       wird.
     *
     * @return Ein positionierter Button mit entsprechendem Text und entsprechendem {@link ActionListener}.
     */
    @NotNull
    private JButton getOptionButton(
        @NotNull final String text,
        @Range(from = 0, to = Integer.MAX_VALUE) final int count,
        @NotNull final ActionListener actionListener
    ) {
        final JButton button = new JButton(text);
        button.setFocusable(false);
        button.setBounds(WIDTH - 220, 20 + (count * 40), 190, 30);
        button.addActionListener(actionListener);

        return button;
    }

}
