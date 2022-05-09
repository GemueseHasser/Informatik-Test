package de.jonas.informatik.object;

import de.jonas.informatik.Game;
import de.jonas.informatik.graphic.Gui;
import de.jonas.informatik.object.entity.Obstacle;
import de.jonas.informatik.object.entity.Player;

import javax.swing.JOptionPane;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Eine {@link GameInstance} stellt eine Instanz eines Spiels dar, d.h. mit einer {@link GameInstance} wird der gesamte
 * Ablauf eines Spiels geregelt und zentrale Abläufe werden festgelegt. Beim Erzeugen einer Instanz wird das Spiel
 * gestartet.
 */
public final class GameInstance {

    //<editor-fold desc="CONSTANTS">
    /** Die Anzahl an Hindernissen zu Beginn. */
    public static final int BEGIN_OBSTACLE_AMOUNT = 3;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Fenster, in dem dieses Spiel angezeigt wird. */
    private final Gui gui;
    /** Der Moment, an dem diese {@link GameInstance} erzeugt bzw. gestartet wird. */
    private final Instant beginMoment;
    /** Der Spieler dieses Spiels. */
    private final Player player = new Player();
    /** Alle Hindernisse, die sich aktuell im Spiel befinden. */
    private final List<Obstacle> obstacles = new ArrayList<>();
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz einer {@link GameInstance}. Eine {@link GameInstance}
     * stellt eine Instanz eines Spiels dar, d.h. mit einer {@link GameInstance} wird der gesamte Ablauf eines Spiels
     * geregelt und zentrale Abläufe werden festgelegt. Beim Erzeugen einer Instanz wird das Spiel gestartet.
     *
     * @param gui Das Fenster, in dem dieses Spiel angezeigt wird.
     */
    public GameInstance(final Gui gui) {
        this.gui = gui;
        this.beginMoment = Instant.now();

        // fill obstacle list
        for (int i = 0; i < BEGIN_OBSTACLE_AMOUNT; i++) {
            final int randomNumber = ThreadLocalRandom.current().nextInt(170, 200);

            obstacles.add(new Obstacle(i * randomNumber));
        }
    }
    //</editor-fold>


    /**
     * Prüft, ob der Spieler aktuell mit einem Hindernis kollidiert.
     */
    public void checkPlayerCollide() {
        // check all obstacles
        for (final Obstacle obstacle : this.obstacles) {
            // check if player jumped already above this obstacle
            if (this.player.getX() > obstacle.getX() + Obstacle.SIZE - Obstacle.IMAGE_TOLERANCE_SIDE * 1.5) continue;

            // check if player is colliding with current obstacle
            if (this.player.getX() + Player.SIZE < obstacle.getX() + Obstacle.IMAGE_TOLERANCE_SIDE
                    || this.player.getY() + Player.SIZE < obstacle.getY() + Obstacle.IMAGE_TOLERANCE_TOP) continue;

            // handle collide
            handleCollide();
            return;
        }
    }

    /**
     * Verarbeitet die Kollision und öffnet dem Spieler ein Pop-up-Fenster, worin er auswählen kann, ob er erneut
     * spielen möchte oder das Spiel beenden möchte.
     */
    private void handleCollide() {
        // create popup window
        final int result = JOptionPane.showConfirmDialog(
            null,
            "Möchtest du erneut spielen?",
            "Erneut spielen?",
            JOptionPane.YES_NO_OPTION
        );

        // check if user clicked 'yes'
        if (result == 0) {
            Game.startGame();
            return;
        }

        // exit game
        System.exit(0);
    }

    /**
     * Fügt der aktuellen Liste mit Hindernissen ein neues Hindernis hinzu.
     */
    public void addObstacle() {
        final int xAddition = ThreadLocalRandom.current().nextInt(0, 45);

        this.obstacles.add(new Obstacle(xAddition));
    }

    /**
     * Gibt die aktuelle Zeit in Form eines Strings zurück, die beschreibt, wie lange diese Instanz schon existiert bzw.
     * wie lange das Spiel schon läuft.
     *
     * @return die aktuelle Zeit in Form eines Strings, die beschreibt, wie lange diese Instanz schon existiert bzw. wie
     *     lange das Spiel schon läuft.
     */
    public String getCurrentTime() {
        final Duration duration = Duration.between(this.beginMoment, Instant.now());

        final int minutes = (int) (duration.getSeconds() / 60);
        final int seconds = (int) (duration.getSeconds() % 60);

        return minutes + ":" + ((seconds < 10) ? "0" + seconds : seconds);
    }

    /**
     * Gibt das Fenster zurück, in dem dieses Spiel angezeigt wird.
     *
     * @return Das Fenster, in dem dieses Spiel angezeigt wird.
     */
    public Gui getGui() {
        return this.gui;
    }

    /**
     * Gibt den Moment zurück, in dem das Spiel beginnt.
     *
     * @return Der Moment, in dem das Spiel beginnt.
     */
    public Instant getBeginMoment() {
        return this.beginMoment;
    }

    /**
     * Gibt den Spieler dieses Spiels zurück.
     *
     * @return Der Spieler dieses Spiels.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gibt alle Hindernisse zurück, die sich aktuell im Spiel befinden.
     *
     * @return Alle Hindernisse, die sich aktuell im Spiel befinden.
     */
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

}
