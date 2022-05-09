package de.jonas.informatik.object.entity;

import de.jonas.informatik.constants.JumpState;
import de.jonas.informatik.task.GameUpdateTask;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Ein Spieler wird von einem {@link Entity} abgeleitet und kann springen.
 */
public final class Player extends Entity {

    //<editor-fold desc="CONSTANTS">
    /** Die Größe des Spielers. */
    public static final int SIZE = 35;
    /** Die anfängliche X-Koordinate des Spielers. */
    private static final int BEGIN_X = 100;
    /** Die anfängliche Y-Koordinate des Spielers. */
    private static final int BEGIN_Y = 385;
    /** Die Sprunghöhe des Spielers. */
    private static final int JUMP_HEIGHT = 90;
    /** Die Sprunggeschwindigkeit des Spielers. */
    private static final int JUMP_SPEED = 4;
    /** Die Zeit in Millisekunden, die sich der Spieler in maximaler Sprunghöhe befinden soll. */
    private static final int FLY_TIME = 500;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der aktuelle Status des Springens des Spielers. */
    private JumpState jumpState = JumpState.DEFAULT;
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

        this.jumpState = JumpState.FLYING;
    }

    /**
     * Lässt den Spieler in aktueller Höhe fliegen, bis die Flugzeit erreicht ist und sobald das der Fall ist, fällt der
     * Spieler herunter.
     */
    public void fly() {
        this.currentFlyTime += GameUpdateTask.DELAY;

        if (this.currentFlyTime <= FLY_TIME) return;

        this.currentFlyTime = 0;
        this.jumpState = JumpState.FALLING;
    }

    /**
     * Lässt den Spieler von beliebiger Höhe herunterfallen, bis er wieder seine Ursprüngliche anfängliche Höhe erreicht
     * hat.
     */
    public void fall() {
        if (super.getY() >= BEGIN_Y) {
            super.setY(BEGIN_Y);
            this.jumpState = JumpState.DEFAULT;

            return;
        }

        super.setY(super.getY() + JUMP_SPEED);
    }

    /**
     * Leitet einen Sprung ein.
     */
    public void initJump() {
        this.jumpState = JumpState.JUMPING;
    }

    /**
     * Gibt den aktuellen Status des Springens des Spielers zurück.
     *
     * @return Der aktuelle Status des Springens.
     */
    public JumpState getJumpState() {
        return this.jumpState;
    }

    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(super.getX(), super.getY(), SIZE, SIZE);
    }
    //</editor-fold>
}
