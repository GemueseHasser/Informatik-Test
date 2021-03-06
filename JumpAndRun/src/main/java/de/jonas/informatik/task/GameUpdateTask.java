package de.jonas.informatik.task;

import de.jonas.informatik.Game;
import de.jonas.informatik.constants.JumpState;
import de.jonas.informatik.object.entity.Obstacle;
import de.jonas.informatik.object.entity.Player;

import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

/**
 * Der {@link GameUpdateTask} stellt eine sich konstant wiederholende Prozedur dar, womit alle Inhalte des Spiels auf
 * dem aktuellen Stand gehalten und alle Komponenten konstant aktualisiert werden.
 */
public final class GameUpdateTask {

    //<editor-fold desc="CONSTANTS">
    /** Der Abstand in Millisekunden, in dem dieser {@link GameUpdateTask} konstant ausgeführt wird. */
    public static final int DELAY = 5;
    /** Die Zeit in Millisekunden, ab wann sich der Boden bewegen soll. */
    private static final int GROUND_MOVE_BEGIN = 1000;
    //</editor-fold>


    /**
     * Startet diesen {@link GameUpdateTask}.
     */
    public void startPeriodicScheduling() {
        final ActionListener task = actionEvent -> {
            // get player
            final Player player = Game.getGameInstance().getPlayer();

            // check if player is currently jumping
            if (player.getJumpState() == JumpState.JUMPING) {
                player.jump();
            }

            // check if player is flying
            if (player.getJumpState() == JumpState.FLYING) {
                player.fly();
            }

            // check if player is currently falling
            if (player.getJumpState() == JumpState.FALLING) {
                player.fall();
            }

            // check if ground should move already
            if (Duration.between(
                Game.getGameInstance().getBeginMoment(),
                Instant.now()
            ).toMillis() <= GROUND_MOVE_BEGIN) {

                player.setX(player.getX() + 1);

                return;
            }

            // move ground
            Game.getGameInstance().getGui().moveGround();

            // move obstacles
            for (final Obstacle obstacle : Game.getGameInstance().getObstacles()) {
                if (obstacle.getX() < -(Obstacle.SIZE / 2)) {
                    Game.getGameInstance().getObstacles().remove(obstacle);
                    Game.getGameInstance().addObstacle();
                    return;
                }

                obstacle.move();
            }

            // check if player is colliding with obstacles
            Game.getGameInstance().checkPlayerCollide();
        };

        final Timer timer = new Timer(DELAY, task);
        timer.start();
    }

}
