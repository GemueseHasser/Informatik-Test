package de.jonas.informatik;

import java.awt.*;

public class Brick5 {

    private final int posX;
    private final int posY;
    private final int length;


    public Brick5(final int posX, final int posY, final int length) {
        this.posX = posX;
        this.posY = posY;
        this.length = length;
    }


    public void draw(final Graphics g){
        // red background
        g.setColor(Color.RED);
        g.fillRect(posX, posY, length + 1, length / 2 + 1);

        // black lines
        g.setColor(Color.BLACK);

        final int y = posY + ((length / 4) + (length / 8));

        for(int i = 0; i < 5; i++){
            // horizontal lines
            g.drawLine(posX, posY + (i * (length / 8)), posX + length, posY + (i * (length / 8)));
            // vertical lines, row 1
            g.drawLine(posX + (i * (length / 4)), posY, posX + (i * (length / 4)), posY + (length / 8));
            // vertical lines, row 3
            g.drawLine(posX + (i * (length / 4)), posY + (length / 4), posX + (i * (length / 4)), y);
        }

        for(int i = 0; i < 4; i++){
            // vertical lines, row 2
            final int x = posX + (i * (length / 4)) + (length / 8);

            g.drawLine(x, posY + (length / 8), x, posY + (length / 4));
            // vertical lines, row 4
            g.drawLine(x, y, x, posY + (length / 2));
        }
    }

}
