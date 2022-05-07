package de.jonas.informatik.graphic;

import de.jonas.informatik.Game;
import de.jonas.informatik.object.material.Brick;
import de.jonas.informatik.object.material.BrickSelection;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public final class Gui extends JFrame {

    private static final String TITLE = "2D-Spiel";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);


    private final BufferedImage groundImage;
    private int currentX = 0;


    public Gui() {
        super(TITLE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setLayout(null);
        super.setResizable(false);

        final Brick[] ground = new Brick[8];

        for (int i = 0; i < 8; i++) {
            ground[i] = new Brick(i * 100, 0, 100);
        }

        final BrickSelection groundSelection = new BrickSelection(Arrays.asList(ground));
        this.groundImage = groundSelection.createImage();

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        super.add(draw);
        super.setVisible(true);
    }

    public void reduceX() {
        this.currentX -= 1;

        if (this.currentX <= -1 * this.groundImage.getWidth()) {
            this.currentX = 0;
        }
    }


    private final class Draw extends JLabel {

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // set black color and default font
            g.setColor(Color.BLACK);
            g.setFont(DEFAULT_FONT);

            // write data
            g.drawString("Punkte: " + Game.getGameInstance().getPoints(), WIDTH - 300, 30);
            g.drawString("Zeit: " + Game.getGameInstance().getCurrentTime(), 20, 30);

            // draw ground
            g.drawImage(groundImage, currentX, 420, this);
            g.drawImage(groundImage, currentX + groundImage.getWidth(), 420, this);

            repaint();
        }

    }
}
