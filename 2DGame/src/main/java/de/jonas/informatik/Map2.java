package de.jonas.informatik;

import java.awt.*;
import javax.swing.*;

public class Map2 extends JFrame{

    private static final int POS_X = 100;
    private static final int POS_Y = 100;
    private static final int LENGTH = 60;
    
    public void draw(final Graphics g){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                final Brick5 brick = new Brick5(POS_X + (j * LENGTH), POS_Y + (i * (LENGTH / 2)), LENGTH);
                brick.draw(g);
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        setBackground(Color.white);
        draw(g);
    }
    
    // main method
    public static void main(String[] args){
        Map2 m = new Map2();
        m.setSize(600,600);
        m.setResizable(false);
        m.setTitle("Map1");
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
