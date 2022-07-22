package de.jonas.informatik.tictactoe.gui;

import de.jonas.informatik.ExtendedTicTacToe;
import de.jonas.informatik.tictactoe.object.GameManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar und erzeugt somit ein Fenster, worin dann das gesamte
 * {@link de.jonas.informatik.ExtendedTicTacToe Spiel} dargestellt wird.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Tic-Tac-Toe (20 x 20)";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 1075;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 1100;
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

        // get game manager
        final GameManager gameManager = ExtendedTicTacToe.getGameManager();

        // add all fields
        for (int i = 0; i < gameManager.getFields().length; i++) {
            for (int j = 0; j < gameManager.getFields()[i].length; j++) {
                super.add(gameManager.getFields()[i][j].getButton());
            }
        }

        // create new draw instance
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


    //<editor-fold desc="Draw">

    /**
     * Mithilfe eines {@link Draw} werden alle nötigen Zeichnungen auf diesem {@link Gui} vorgenommen.
     */
    private static final class Draw extends JPanel {

        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            // define render quality
            final Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // draw background
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, super.getWidth(), super.getHeight());
        }
        //</editor-fold>

    }
    //</editor-fold>

}
