package de.jonas.informatik.object.entity;

import de.jonas.informatik.Game;
import de.jonas.informatik.graphic.Gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Ein Hindernis wird von einem {@link Entity} abgeleitet.
 */
public final class Obstacle extends Entity {

    //<editor-fold desc="CONSTANTS">
    /** Die Größe des Hindernisses. */
    public static final int SIZE = 130;
    /** Die Toleranz, die es links und rechts beim Sprung für den Spieler gibt. */
    public static final int PLAYER_TOLERANCE_SIDE = 35;
    /** Die Toleranz, die es oben beim Sprung für den Spieler gibt. */
    public static final int PLAYER_TOLERANCE_TOP = 5;
    /** Die anfängliche X-Koordinate des Hindernisses. */
    private static final int START_X = Gui.WIDTH;
    /** Die anfängliche Y-Koordinate des Hindernisses. */
    private static final int START_Y = 356;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    /**
     * Erzeugt mithilfe einer bestimmten X-Addition eine neue Instanz eines {@link Obstacle Hindernis}. Ein Hindernis
     * wird von einem {@link Entity} abgeleitet.
     *
     * @param xAddition Die x-Addition, welche der anfänglichen X-Koordinate direkt hinzugefügt wird.
     */
    public Obstacle(final int xAddition) {
        super(START_X + xAddition, START_Y);
    }
    //</editor-fold>


    /**
     * Bewegt das Hindernis um eine Stelle nach links.
     */
    public void move() {
        super.setX(super.getX() - 1);
    }

    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(Game.GAME.getCactusImage(), super.getX(), super.getY(), SIZE, SIZE / 2, null);
    }
    //</editor-fold>
}
