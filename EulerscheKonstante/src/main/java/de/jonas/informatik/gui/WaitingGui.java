package de.jonas.informatik.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

/**
 * Ein {@link WaitingGui} stellt eine grafische Oberfläche dar, die die Wartezeit visualisiert.
 */
public final class WaitingGui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses {@link WaitingGui}. */
    private static final String TITLE = "Berechnung...";
    /** Die Breite dieses {@link WaitingGui}. */
    private static final int WIDTH = 200;
    /** Die Höhe dieses {@link WaitingGui}. */
    private static final int HEIGHT = 200;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der {@link Timer}, womit die Bewegung des Kreises und die Zeit berechnet wird. */
    private final Timer waitingTimer;
    /** Der Moment, in dem der {@link Timer} gestartet wird. */
    private final Instant waitingTimerBeginMoment;
    /** Die Animation, die die Wartezeit visualisiert. */
    private final Image loadingAnimation;
    /** Die aktuelle Zeit, die die Berechnung bereits dauert (in Form eines formatierten Textes). */
    private String currentTime = "0:00";
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link WaitingGui}. Ein {@link WaitingGui} stellt
     * eine grafische Oberfläche dar, die die Wartezeit visualisiert.
     */
    public WaitingGui() {
        super(TITLE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setUndecorated(true);
        super.setLayout(null);
        super.setResizable(false);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        super.add(draw);

        // set timer begin moment
        this.waitingTimerBeginMoment = Instant.now();

        // get loading animation
        final URL loadingAnimationUrl = getClass().getResource("/loadingAnimation.gif");

        // load loading animation
        assert loadingAnimationUrl != null;
        this.loadingAnimation = new ImageIcon(loadingAnimationUrl).getImage();

        final ActionListener actionListener = actionEvent -> {
            // calculate current duration between timer begin and now
            final Duration currentDuration = Duration.between(this.waitingTimerBeginMoment, Instant.now());

            // calculate minutes and seconds based on the duration
            final long currentMinutes = currentDuration.toMinutes();
            final long currentSeconds = currentDuration.getSeconds() % 60;

            // format minutes and seconds and put them in a text
            this.currentTime = currentMinutes + ":" + ((currentSeconds < 10) ? "0" + currentSeconds : currentSeconds);
        };

        // start waiting timer
        this.waitingTimer = new Timer(500, actionListener);
        this.waitingTimer.start();
    }
    //</editor-fold>


    /**
     * Öffnet dieses {@link Gui}, macht das bereits instanziierte Fenster also sichtbar.
     */
    public void open() {
        super.setVisible(true);
    }

    //<editor-fold desc="implementation">
    @Override
    public void dispose() {
        // close gui
        super.dispose();

        // stop timer
        this.waitingTimer.stop();
    }
    //</editor-fold>


    //<editor-fold desc="Draw">

    /**
     * Mithilfe eines {@link Draw}, werden alle Grafiken und somit das gesamte Koordinaten-System in dieses
     * {@link WaitingGui} gezeichnet.
     */
    private final class Draw extends JPanel {

        //<editor-fold desc="CONSTANTS">
        /** Die Größe der Lade-Animation. */
        private static final int LOADING_ANIMATION_SIZE = 120;
        //</editor-fold>


        //<editor-fold desc="LOCAL FIELDS">
        /** Die Schriftart, die für das Anzeigen der Zeit genutzt wird. */
        private final Font timeFont = new Font("Arial", Font.BOLD, 30);
        //</editor-fold>


        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            // define render quality
            final Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // calculate the middle of the frame
            final int middleX = super.getWidth() / 2;
            final int middleY = super.getHeight() / 2;

            // draw loading animation
            g.drawImage(
                loadingAnimation,
                middleX - LOADING_ANIMATION_SIZE,
                middleY - LOADING_ANIMATION_SIZE + 20,
                LOADING_ANIMATION_SIZE * 2,
                LOADING_ANIMATION_SIZE,
                this
            );

            // draw current time
            g.setColor(Color.BLACK);
            g.setFont(timeFont);

            g.drawString(currentTime, middleX - 29, middleY + LOADING_ANIMATION_SIZE / 2);
        }
        //</editor-fold>
    }
    //</editor-fold>

}
