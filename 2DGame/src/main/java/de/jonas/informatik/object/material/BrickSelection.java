package de.jonas.informatik.object.material;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public final class BrickSelection {

    private final List<Brick> bricks;


    public BrickSelection(final List<Brick> bricks) {
        this.bricks = bricks;
    }

    public BufferedImage createImage() {
        // calculate image size
        final int width = getMaxX() - getMinX();
        final int height = getMaxY() - getMinY();

        // create new buffered image
        final BufferedImage image = new BufferedImage(
            width,
            height,
            BufferedImage.TYPE_INT_RGB
        );

        // create graphics
        final Graphics2D g = image.createGraphics();

        // draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // draw brick selection
        for (final Brick brick : this.bricks) {
            brick.draw(g);
        }

        // return created image
        return image;
    }

    private int getMinX() {
        int currentMin = Integer.MAX_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosX() < currentMin) {
                currentMin = brick.getPosX();
            }
        }

        return currentMin;
    }

    private int getMaxX() {
        int currentMax = Integer.MIN_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosX() + brick.getLength() > currentMax) {
                currentMax = brick.getPosX() + brick.getLength();
            }
        }

        return currentMax;
    }

    private int getMinY() {
        int currentMin = Integer.MAX_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosY() < currentMin) {
                currentMin = brick.getPosY();
            }
        }

        return currentMin;
    }

    private int getMaxY() {
        int currentMax = Integer.MIN_VALUE;

        for (final Brick brick : this.bricks) {
            if (brick.getPosY() + (brick.getLength() / 2) > currentMax) {
                currentMax = brick.getPosY() + (brick.getLength() / 2);
            }
        }

        return currentMax;
    }

}
