package de.jonas.informatik;

import javax.swing.JFrame;

import java.awt.Graphics;

public final class Circles extends JFrame {

    private static final int CIRCLE_AMOUNT = 3;

    private int r = 50;

    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        for (int i = 0; i < CIRCLE_AMOUNT; i++) {
            g.drawOval(300 - r, 300 - r, r * 2, r * 2);
            r += 50;
        }
    }

    public static void main(final String[] args) {
        final Circles circles = new Circles();
        circles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        circles.setBounds(0, 0, 600, 600);
        circles.setLocationRelativeTo(null);
        circles.setResizable(false);
        circles.setTitle("Kreise");
        circles.setVisible(true);
    }

}
