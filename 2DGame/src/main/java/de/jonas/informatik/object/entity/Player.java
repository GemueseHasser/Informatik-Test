package de.jonas.informatik.object.entity;

import de.jonas.informatik.task.GameUpdateTask;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Ein Spieler wird von einem {@link Entity} abgeleitet und kann springen.
 */
public final class Player extends Entity {

    //<editor-fold desc="CONSTANTS">
    /** Die anfängliche X-Koordinate des Spielers. */
    private static final int BEGIN_X = 100;
    /** Die anfängliche Y-Koordinate des Spielers. */
    private static final int BEGIN_Y = 385;
    /** Die Größe des Spielers. */
    private static final int SIZE = 35;
    /** Die Sprunghöhe des Spielers. */
    private static final int JUMP_HEIGHT = 90;
    /** Die Sprunggeschwindigkeit des Spielers. */
    private static final int JUMP_SPEED = 4;
    /** Die Zeit in Millisekunden, die sich der Spieler in maximaler Sprunghöhe befinden soll. */
    private static final int FLY_TIME = 370;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der Zustand, ob der Spieler gerade springt. */
    private boolean jumping;
    /** Der Zustand, ob der Spieler gerade fliegt. */
    private boolean flying;
    /** Der Zustand, ob der Spieler gerade fällt (dieser Zustand wird nach jedem Sprung wahr). */
    private boolean falling;
    /** Die aktuelle Zeit, die der Spieler sich auf maximaler Sprunghöhe befindet. */
    private int currentFlyTime;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines Spielers, welcher von einem {@link Entity} abgeleitet wird und springen kann.
     */
    public Player() {
        super(BEGIN_X, BEGIN_Y);
    }
    //</editor-fold>


    /**
     * Lässt den Spieler um eine Koordinate springen, also muss diese Methode immer wieder konstant ausgeführt werden,
     * bis der Zustand des Springens ({@code isJumping}) nicht mehr wahr ist. Dann muss so lange {@code isFalling}
     * {@code true} ist die Methode {@code fall} ausgeführt werden, damit der Spieler wieder herunterfällt.
     */
    public void jump() {
        super.setY(super.getY() - JUMP_SPEED);

        if (BEGIN_Y - super.getY() <= JUMP_HEIGHT) return;

        this.flying = true;
        this.jumping = false;
    }

    /**
     * Lässt den Spieler in aktueller Höhe fliegen, bis die Flugzeit erreicht ist und sobald das der Fall ist, fällt der
     * Spieler herunter.
     */
    public void fly() {
        this.currentFlyTime += GameUpdateTask.DELAY;

        if (this.currentFlyTime <= FLY_TIME) return;

        this.currentFlyTime = 0;

        this.falling = true;
        this.flying = false;
    }

    /**
     * Lässt den Spieler von beliebiger Höhe herunterfallen, bis er wieder seine Ursprüngliche anfängliche Höhe erreicht
     * hat.
     */
    public void fall() {
        if (super.getY() >= BEGIN_Y) {
            super.setY(BEGIN_Y);
            this.falling = false;

            return;
        }

        super.setY(super.getY() + JUMP_SPEED);
    }

    /**
     * Setzt den Zustand des Springens neu.
     *
     * @param jumping Der Zustand, ob der Spieler springen soll oder nicht.
     */
    public void setJumping(final boolean jumping) {
        if (super.getY() != BEGIN_Y) return;

        this.jumping = jumping;
    }

    /**
     * Gibt den Zustand wieder, ob der Spieler gerade springt oder nicht.
     *
     * @return Wenn der Spieler gerade springt {@code true}, ansonsten {@code false}.
     */
    public boolean isJumping() {
        return this.jumping;
    }

    /**
     * Gibt den Zustand wieder, ob der Spieler gerade fällt oder nicht.
     *
     * @return Wenn der Spieler gerade fällt {@code true}, ansonsten {@code false}.
     */
    public boolean isFalling() {
        return this.falling;
    }

    /**
     * Gibt den Zustand wieder, ob der Spieler gerade fliegt oder nicht.
     *
     * @return Wenn der Spieler gerade fliegt {@code true}, ansonsten {@code false}.
     */
    public boolean isFlying() {
        return this.flying;
    }

    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(super.getX(), super.getY(), SIZE, SIZE);
    }
    //</editor-fold>
}
