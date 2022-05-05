package de.jonas.informatik;

import java.awt.*;
import javax.swing.*;

public class Brick1 extends JFrame{
    
    private static final int POS_X = 100;
    private static final int POS_Y = 100;
    
    private void draw(final Graphics g){
        // red background
        g.setColor(Color.RED);
        g.fillRect(POS_X, POS_Y, 40, 20);
       
        // black lines
        g.setColor(Color.BLACK);

        // horizontal lines
        g.drawLine(POS_X, POS_Y, POS_X + 40, POS_Y);
        g.drawLine(POS_X, POS_Y + 5, POS_X + 40, POS_Y + 5);
        g.drawLine(POS_X, POS_Y + 10, POS_X + 40, POS_Y + 10);
        g.drawLine(POS_X, POS_Y + 15, POS_X + 40, POS_Y + 15);
        g.drawLine(POS_X, POS_Y + 20, POS_X + 40, POS_Y + 20);

        // vertical lines, row 1
        g.drawLine(POS_X, POS_Y, POS_X, POS_Y + 5);
        g.drawLine(POS_X + 10, POS_Y, POS_X + 10, POS_Y + 5);
        g.drawLine(POS_X + 20, POS_Y, POS_X + 20, POS_Y + 5);
        g.drawLine(POS_X + 30, POS_Y, POS_X + 30, POS_Y + 5);
        g.drawLine(POS_X + 40, POS_Y, POS_X + 40, POS_Y + 5);

        // vertical lines, row 2
        g.drawLine(POS_X + 5, POS_Y + 5, POS_X + 5, POS_Y + 10);
        g.drawLine(POS_X + 15, POS_Y + 5, POS_X + 15, POS_Y + 10);
        g.drawLine(POS_X + 25, POS_Y + 5, POS_X + 25, POS_Y + 10);
        g.drawLine(POS_X + 35, POS_Y + 5, POS_X + 35, POS_Y + 10);


        // vertical lines, row 3
        g.drawLine(POS_X, POS_Y + 10, POS_X, POS_Y + 15);
        g.drawLine(POS_X + 10, POS_Y + 10, POS_X + 10, POS_Y + 15);
        g.drawLine(POS_X + 20, POS_Y + 10, POS_X + 20, POS_Y + 15);
        g.drawLine(POS_X + 30, POS_Y + 10, POS_X + 30, POS_Y + 15);
        g.drawLine(POS_X + 40, POS_Y + 10, POS_X + 40, POS_Y + 15);

        // vertical lines, row 4
        g.drawLine(POS_X + 5, POS_Y + 15, POS_X + 5, POS_Y + 20);
        g.drawLine(POS_X + 15, POS_Y + 15, POS_X + 15, POS_Y + 20);
        g.drawLine(POS_X + 25, POS_Y + 15, POS_X + 25, POS_Y + 20);
        g.drawLine(POS_X + 35, POS_Y + 15, POS_X + 35, POS_Y + 20);
    }
    
    @Override
    public void paint(final Graphics g){
        setBackground(Color.white);
        draw(g);
    }
    
    // main method
    public static void main(String[] args){
        Brick1 br = new Brick1();
        br.setSize(600,600);
        br.setResizable(false);
        br.setTitle("Brick1");
        br.setVisible(true);
        br.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
