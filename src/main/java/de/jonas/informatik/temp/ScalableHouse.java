package de.jonas.informatik.temp;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;

public final class ScalableHouse extends JFrame {

    private static final int SIZE = 300;
    private static final int X = 300;
    private static final int Y = 590;

    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        final int houseStartX = X - SIZE / 2;
        final int houseEndX = X + SIZE / 2;

        final int houseStartY = Y - SIZE;

        final int rooftopY = (int) (Y - SIZE * 1.5);

        // draw house shape
        g.drawRect(houseStartX, houseStartY, SIZE, SIZE);
        g.drawLine(houseStartX, houseStartY, X, rooftopY);
        g.drawLine(houseEndX, houseStartY, X, rooftopY);

        // draw square windows
        g.setColor(Color.LIGHT_GRAY);

        final int windowSize = SIZE / 5;

        g.fillRect(houseStartX + windowSize, houseStartY + windowSize, windowSize, windowSize);
        g.fillRect(houseStartX + windowSize * 3, houseStartY + windowSize, windowSize, windowSize);
        g.fillRect(houseStartX + windowSize * 3, houseStartY + windowSize * 3, windowSize, windowSize);

        // draw door
        g.fillRect(houseStartX + windowSize, houseStartY + windowSize * 3, windowSize, windowSize * 2);

        // draw round window
        g.fillOval(X - windowSize / 2, rooftopY + SIZE / 4 - windowSize / 2, windowSize, windowSize);

        // draw chimney
        g.setColor(Color.BLACK);

        final int chimneyWidth = SIZE / 6;

        final int middleX = X + ((houseEndX - X) / 2);
        final int middleY = rooftopY + ((houseStartY - rooftopY) / 2);

        g.drawLine(middleX - chimneyWidth / 2, middleY - chimneyWidth / 2, middleX - chimneyWidth / 2, rooftopY);
        g.drawLine(middleX + chimneyWidth / 2, middleY + chimneyWidth / 2, middleX + chimneyWidth / 2, rooftopY);
        g.drawLine(middleX + chimneyWidth / 2, rooftopY, middleX - chimneyWidth / 2, rooftopY);
    }

    public static void main(final String[] args) {
        final ScalableHouse house = new ScalableHouse();
        house.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        house.setBounds(0, 0, 600, 600);
        house.setLocationRelativeTo(null);
        house.setResizable(false);
        house.setTitle("Skalierbares Haus");
        house.setVisible(true);
    }
}
