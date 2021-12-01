package de.jonas.informatik;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;

public final class House extends JFrame {

    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        // draw house shape
        g.drawRect(150, 290, 300, 300);
        g.drawLine(150, 290, 300, 140);
        g.drawLine(450, 290, 300, 140);

        // draw square windows
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(210, 350, 60, 60);
        g.fillRect(330, 350, 60, 60);
        g.fillRect(330, 470, 60, 60);

        // draw door
        g.fillRect(210, 470, 60, 120);

        // draw round window
        g.fillOval(270, 185, 60, 60);

        // draw chimney
        g.setColor(Color.BLACK);

        // calculate the middle of the line (subtract point A from point B)
        //
        // first coordinate: 450-300=150; 150/2=75; 300+75=375; (this is the 'x' value from the middle of the line)
        // second coordinate: 290-140=150; 150/2=75; 140+75=215; (this is the 'y' value from the middle of the line)
        //
        // get function of the line: f(x)=x-160
        // calculate the value for x=375 (test the function): f(375)=375-160=215 (the function is correct)
        //
        // width of the chimney: 50; height of the chimney: 150;
        //
        // start of the left line: f(375-25)=350-160=190 (left line starts at point (350|190))
        // start of the right line: f(375+25)=400-160=240 (right line starts at point (400|240))
        //
        // the end of the lines are their 'x' value from the beginning and the 'y' value '140'.


        // draw left line
        g.drawLine(350, 190, 350, 140);

        // draw right line
        g.drawLine(400, 240, 400, 140);

        // draw line on top
        g.drawLine(350, 140, 400, 140);
    }

    public static void main(final String[] args) {
        final House house = new House();
        house.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        house.setBounds(0, 0, 600, 600);
        house.setLocationRelativeTo(null);
        house.setResizable(false);
        house.setTitle("Haus");
        house.setVisible(true);
    }

}
