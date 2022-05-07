package de.jonas.informatik.object.material;

import java.awt.Color;
import java.awt.Graphics;

public final class Brick {

    private final int posX;
    private final int posY;
    private final int length;


    public Brick(
        final int posX,
        final int posY,
        final int length
    ) {
        this.posX = posX;
        this.posY = posY;
        this.length = length;
    }


    public void draw(final Graphics g){
        // draw red background
        g.setColor(Color.RED);
        g.fillRect(posX, posY, length + 1, length / 2 + 1);

        // set color to black
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

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getLength() {
        return this.length;
    }

}
