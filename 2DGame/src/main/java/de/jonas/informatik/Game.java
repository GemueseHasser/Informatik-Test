package de.jonas.informatik;

import de.jonas.informatik.graphic.Gui;
import de.jonas.informatik.object.GameInstance;
import de.jonas.informatik.task.GameUpdateTask;

/**
 * Die Haupt- und Main-Klasse dieser Anwendung, worin die gesamte Anwendung instanziiert und initialisiert wird. Da
 * diese Klasse als Main-Klasse festgelegt ist, wird diese auch als allererstes von der JRE aufgerufen und die
 * Main-Methode in dieser Klasse wird ausgeführt.
 */
public final class Game {

    //<editor-fold desc="STATIC FIELDS">
    /** Die {@link GameInstance} dieses Spiels. */
    private static GameInstance gameInstance;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, welche als allererstes von der JRE aufgerufen wird und worin dann die gesamte
     * Anwendung instanziiert und initialisiert wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        // create gui
        final Gui gui = new Gui();

        // create game instance
        gameInstance = new GameInstance(gui);

        // schedule periodic game updating
        new GameUpdateTask().startPeriodicScheduling();
    }
    //</editor-fold>


    /**
     * Gibt die {@link GameInstance} dieses Spiels zurück.
     *
     * @return Die {@link GameInstance} dieses Spiels.
     */
    public static GameInstance getGameInstance() {
        return gameInstance;
    }

}