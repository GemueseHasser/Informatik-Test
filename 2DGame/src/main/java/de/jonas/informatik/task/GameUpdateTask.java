package de.jonas.informatik.task;

import de.jonas.informatik.Game;

import java.util.Timer;
import java.util.TimerTask;

public final class GameUpdateTask {

    private static final int PERIOD = 1000;


    public void startPeriodicScheduling() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(
            new TimerTask() {
                @Override
                public void run() {
                    Game.getGameInstance().update();
                }
            }, 0, PERIOD
        );
    }

}
