package de.jonas.informatik.graphic;

import de.jonas.informatik.Game;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public final class Gui extends JFrame {

    private static final String TITLE = "2D-Spiel";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);


    public Gui() {
        super(TITLE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setLayout(null);
        super.setResizable(false);

        super.setVisible(true);
    }


    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        // set black color and default font
        g.setColor(Color.BLACK);
        g.setFont(DEFAULT_FONT);

        // write data
        g.drawString("Punkte: " + Game.getGameInstance().getPoints(), WIDTH - 200, 60);
        g.drawString("Zeit: " + Game.getGameInstance().getCurrentTime(), 20, 60);
    }
}
