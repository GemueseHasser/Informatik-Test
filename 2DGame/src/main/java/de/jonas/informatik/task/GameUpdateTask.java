package de.jonas.informatik.task;

import de.jonas.informatik.Game;

import javax.swing.Timer;

import java.awt.event.ActionListener;

/**
 * Der {@link GameUpdateTask} stellt eine sich konstant wiederholende Prozedur dar, womit alle Inhalte des Spiels auf
 * dem aktuellen Stand gehalten und alle Komponenten konstant aktualisiert werden.
 */
public final class GameUpdateTask {

    //<editor-fold desc="CONSTANTS">
    /** Der Abstand in Millisekunden, in dem dieser {@link GameUpdateTask} konstant ausgeführt wird. */
    private static final int DELAY = 30;
    //</editor-fold>


    /**
     * Startet diesen {@link GameUpdateTask}.
     */
    public void startPeriodicScheduling() {
        final ActionListener task = actionEvent -> Game.getGameInstance().getGui().moveGround();

        final Timer timer = new Timer(DELAY, task);
        timer.start();
    }

}
