package de.jonas.functiondraw.gui;

import de.jonas.functiondraw.object.DrawFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JFrame;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Ein {@link FunctionGui} stellt eine Instanz eines {@link JFrame} dar, welches eine grafische Oberfläche darstellt,
 * auf der eine bestimmte Funktion gezeichnet werden kann, mithilfe eines
 * {@link de.jonas.functiondraw.object.DrawFunction}.
 */
@NotNull
public final class FunctionGui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    @NotNull
    private static final String TITLE = "Function-Draw";
    /** Die Breite dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int WIDTH = 800;
    /** Die Höhe dieses Fensters. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int HEIGHT = 600;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link FunctionGui}. Ein {@link FunctionGui} stellt eine Instanz eines
     * {@link JFrame} dar, welches eine grafische Oberfläche darstellt, auf der eine bestimmte Funktion gezeichnet
     * werden kann, mithilfe eines {@link de.jonas.functiondraw.object.DrawFunction}.
     */
    public FunctionGui() {
        // create frame instance
        super(TITLE);

        // set frame properties
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(null);

        final NavigableMap<Integer, Integer> values = new TreeMap<>();
        values.put(0, 0);
        values.put(1, 1);
        values.put(10, 10);

        // create draw object
        final DrawFunction drawFunction = new DrawFunction(values, 10, 10);
        drawFunction.setBounds(0, 0, WIDTH, HEIGHT);
        drawFunction.setVisible(true);

        // add components
        super.add(drawFunction);
    }
    //</editor-fold>

}
