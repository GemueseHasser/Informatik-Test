package de.jonas.informatik.graphic;

import de.jonas.informatik.object.Brick;

import javax.swing.JFrame;

import java.awt.Graphics;

public final class Gui extends JFrame {

    private static final String TITLE = "2D-Spiel";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;


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
    public void paint(final Graphics graphics) {
        super.paint(graphics);

        final Brick brick = new Brick(100, 100, 100);
        brick.draw(graphics);
    }
}
