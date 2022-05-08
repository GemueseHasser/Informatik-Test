package de.jonas.informatik.object;

import de.jonas.informatik.graphic.Gui;
import de.jonas.informatik.object.entity.Obstacle;
import de.jonas.informatik.object.entity.Player;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    /** Die aktuelle Anzahl an Punkten in diesem Spiel. */
    private int points;
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
        this.points = 0;

        for (int i = 0; i < BEGIN_OBSTACLE_AMOUNT; i++) {
            obstacles.add(new Obstacle(i * 170));
        }
    }
    //</editor-fold>


    /**
     * Erhöht die aktuelle Anzahl an Punkten um eine bestimmte Anzahl an Punkten.
     *
     * @param points Die Anzahl an Punkten, um die die aktuelle Anzahl an Punkten erhöht werden soll.
     */
    public void incrementPoints(final int points) {
        this.points += points;
    }

    /**
     * Fügt der aktuellen Liste mit Hindernissen ein neues Hindernis hinzu.
     */
    public void addObstacle() {
        this.obstacles.add(new Obstacle(0));
    }

    /**
     * Gibt die aktuelle Anzahl an Punkten in diesem Spiel zurück.
     *
     * @return Die aktuelle Anzahl an Punkten in diesem Spiel.
     */
    public int getPoints() {
        return this.points;
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
