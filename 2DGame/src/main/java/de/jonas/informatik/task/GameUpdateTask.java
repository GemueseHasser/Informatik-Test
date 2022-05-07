package de.jonas.informatik.task;

import de.jonas.informatik.Game;

import javax.swing.Timer;

import java.awt.event.ActionListener;

public final class GameUpdateTask {

    private static final int PERIOD = 30;


    public void startPeriodicScheduling() {
        final ActionListener task = actionEvent -> Game.getGameInstance().getGui().reduceX();

        final Timer timer = new Timer(PERIOD, task);
        timer.start();
    }

}
