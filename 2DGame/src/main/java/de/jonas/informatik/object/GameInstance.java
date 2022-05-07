package de.jonas.informatik.object;

import de.jonas.informatik.graphic.Gui;

import java.time.Duration;
import java.time.Instant;

public final class GameInstance {

    private final Gui gui;
    private final Instant beginMoment;
    private int points;


    public GameInstance(final Gui gui) {
        this.gui = gui;
        this.beginMoment = Instant.now();
        this.points = 0;
    }

    public int getPoints() {
        return this.points;
    }

    public void incrementPoints(final int points) {
        this.points += points;
    }

    public String getCurrentTime() {
        final Duration duration = Duration.between(this.beginMoment, Instant.now());

        final int minutes = (int) (duration.getSeconds() / 60);
        final int seconds = (int) (duration.getSeconds() % 60);

        return minutes + ":" + ((seconds < 10) ? "0" + seconds : seconds);
    }

    public Gui getGui() {
        return this.gui;
    }

}
