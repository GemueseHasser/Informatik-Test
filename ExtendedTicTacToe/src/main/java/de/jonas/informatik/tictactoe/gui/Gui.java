package de.jonas.informatik.tictactoe.gui;

import de.jonas.informatik.tictactoe.object.gui.Draw;

import javax.swing.JFrame;

/**
 * Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar und erzeugt somit ein Fenster, worin dann das gesamte
 * {@link de.jonas.informatik.ExtendedTicTacToe Spiel} dargestellt wird.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Tic-Tac-Toe (20 x 20)";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 800;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 600;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}. Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar und
     * erzeugt somit ein Fenster, worin dann das gesamte {@link de.jonas.informatik.ExtendedTicTacToe Spiel} dargestellt
     * wird.
     */
    public Gui() {
        super(TITLE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(null);
        super.setResizable(false);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        super.add(draw);
    }
    //</editor-fold>


    /**
     * Öffnet dieses {@link Gui}.
     */
    public void open() {
        super.setVisible(true);
    }

}
