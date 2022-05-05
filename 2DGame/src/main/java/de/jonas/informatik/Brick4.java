package de.jonas.informatik;

import java.awt.*;
import javax.swing.*;

public class Brick4 extends JFrame{

    private static final int POS_X = 100;
    private static final int POS_Y = 100;
    private static final int LENGTH = 400;

    private void draw(final Graphics g){
        // red background
        g.setColor(Color.RED);
        g.fillRect(POS_X, POS_Y, LENGTH, LENGTH / 2);

        // black lines
        g.setColor(Color.BLACK);

        for(int i = 0; i < 5; i++){
            // horizontal lines
            g.drawLine(POS_X, POS_Y + (i * (LENGTH / 8)), POS_X + LENGTH, POS_Y + (i * (LENGTH / 8)));
            // vertical lines, row 1
            g.drawLine(POS_X + (i * (LENGTH / 4)), POS_Y, POS_X + (i * (LENGTH / 4)), POS_Y + (LENGTH / 8));
            // vertical lines, row 3
            g.drawLine(POS_X + (i * (LENGTH / 4)), POS_Y + (LENGTH / 4), POS_X + (i * (LENGTH / 4)), POS_Y + ((LENGTH / 4) + (LENGTH / 8)));
        }

        for(int i = 0; i < 4; i++){
            // vertical lines, row 2
            g.drawLine(POS_X + (i * (LENGTH / 4)) + (LENGTH / 8), POS_Y + (LENGTH / 8), POS_X + (i * (LENGTH / 4)) + (LENGTH / 8), POS_Y + (LENGTH / 4));
            // vertical lines, row 4
            g.drawLine(POS_X + (i * (LENGTH / 4)) + (LENGTH / 8), POS_Y + ((LENGTH / 4) + (LENGTH / 8)), POS_X + (i * (LENGTH / 4)) + (LENGTH / 8), POS_Y + (LENGTH / 2));
        }
    }

    @Override
    public void paint(Graphics g){
        setBackground(Color.white);
        draw(g);
    }

    // main method
    public static void main(String[] args){
        Brick4 br = new Brick4();
        br.setSize(600,600);
        br.setResizable(false);
        br.setTitle("Brick3");
        br.setVisible(true);
        br.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
