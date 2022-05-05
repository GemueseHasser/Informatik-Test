package de.jonas.informatik;

import de.jonas.informatik.graphic.Gui;
import de.jonas.informatik.object.GameInstance;
import de.jonas.informatik.task.GameUpdateTask;

public final class Game {

    private static GameInstance gameInstance;


    public static void main(final String[] args) {
        // create gui
        final Gui gui = new Gui();

        // create game instance
        gameInstance = new GameInstance(gui);

        // schedule periodic game updating
        new GameUpdateTask().startPeriodicScheduling();
    }


    public static GameInstance getGameInstance() {
        return gameInstance;
    }

}
