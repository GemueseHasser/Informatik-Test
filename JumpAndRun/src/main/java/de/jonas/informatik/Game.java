package de.jonas.informatik;

import de.jonas.informatik.graphic.Gui;
import de.jonas.informatik.object.GameInstance;
import de.jonas.informatik.task.GameUpdateTask;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Die Haupt- und Main-Klasse dieser Anwendung, worin die gesamte Anwendung instanziiert und initialisiert wird. Da
 * diese Klasse als Main-Klasse festgelegt ist, wird diese auch als allererstes von der JRE aufgerufen und die
 * Main-Methode in dieser Klasse wird ausgeführt.
 */
public final class Game {

    //<editor-fold desc="CONSTANTS">
    /** Das Spiel, welches instanziiert wird, sobald diese Anwendung gestartet wird. */
    public static final Game GAME = new Game();
    /** Das Fenster dieses Spiels, in dem das Spiel angezeigt wird. */
    private static final Gui GUI = new Gui();
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Die {@link GameInstance} dieses Spiels. */
    private static GameInstance gameInstance;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Bild, welches für einen Kaktus genutzt wird. */
    private BufferedImage cactusImage;
    /** Das Hintergrundbild. */
    private BufferedImage backgroundImage;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, welche als allererstes von der JRE aufgerufen wird und worin dann die gesamte
     * Anwendung instanziiert und initialisiert wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        // start game
        startGame();

        // schedule periodic game updating
        new GameUpdateTask().startPeriodicScheduling();
    }
    //</editor-fold>


    /**
     * Startet das Spiel zum ersten Mal oder startet das Spiel neu.
     */
    public static void startGame() {
        // create game instance
        gameInstance = new GameInstance(GUI);
    }

    /**
     * Gibt die {@link GameInstance} dieses Spiels zurück.
     *
     * @return Die {@link GameInstance} dieses Spiels.
     */
    public static GameInstance getGameInstance() {
        return gameInstance;
    }


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Game Spiels}. Es werden alle Prozesse
     * ausgeführt, die nur einmal ausgeführt werden sollen und die vor allen anderen Prozessen ausgeführt werden
     * müssen.
     */
    public Game() {
        // load images
        final URL cactusUrl = getClass().getResource("/cactus.png");
        final URL backgroundUrl = getClass().getResource("/dessert.jpg");

        assert cactusUrl != null;
        assert backgroundUrl != null;

        try {
            this.cactusImage = ImageIO.read(cactusUrl);
            this.backgroundImage = ImageIO.read(backgroundUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>


    /**
     * Gibt das Bild, welches für einen Kaktus genutzt wird zurück.
     *
     * @return Das Bild, welches für einen Kaktus genutzt wird.
     */
    public BufferedImage getCactusImage() {
        return this.cactusImage;
    }

    /**
     * Gibt das Bild, welches als Hintergrund dient zurück.
     *
     * @return Das Bild, welches als Hintergrund dient.
     */
    public BufferedImage getBackgroundImage() {
        return this.backgroundImage;
    }

}
