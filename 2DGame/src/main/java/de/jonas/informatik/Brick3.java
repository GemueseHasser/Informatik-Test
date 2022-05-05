package de.jonas.informatik;

import java.awt.*;
import javax.swing.*;

public class Brick3 extends JFrame{

    private static final int POS_X = 100;
    private static final int POS_Y = 100;
    
    private void draw(final Graphics g){
        // red background
        g.setColor(Color.RED);
        g.fillRect(POS_X, POS_Y, 40, 20);
        
        // black lines
        g.setColor(Color.BLACK);

        for(int i = 0; i < 5; i++){
            // horizontal lines
            g.drawLine(POS_X, POS_Y + (i * 5), POS_X + 40, POS_Y + (i * 5));
            // vertical lines, row 1
            g.drawLine(POS_X + (i * 10), POS_Y, POS_X + (i * 10), POS_Y + 5);
            // vertical lines, row 3
            g.drawLine(POS_X + (i * 10), POS_Y + 10, POS_X + (i * 10), POS_Y + 15);
        }
        
        for(int i = 0; i < 4; i++){
            // vertical lines, row 2
            g.drawLine(POS_X + (i * 10) + 5, POS_Y + 5, POS_X + (i * 10) + 5, POS_Y + 10);
            // vertical lines, row 4
            g.drawLine(POS_X + (i * 10) + 5, POS_Y + 15, POS_X + (i * 10) + 5, POS_Y + 20);
        }
    }
    
    @Override
    public void paint(Graphics g){
        setBackground(Color.white);
        draw(g);
    }
    
    // main method
    public static void main(String[] args){
        Brick3 br = new Brick3();
        br.setSize(600,600);
        br.setResizable(false);
        br.setTitle("Brick3");
        br.setVisible(true);
        br.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
