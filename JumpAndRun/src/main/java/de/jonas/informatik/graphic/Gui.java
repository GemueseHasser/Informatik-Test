package de.jonas.informatik.graphic;

import de.jonas.informatik.Game;
import de.jonas.informatik.listener.KeyListener;
import de.jonas.informatik.object.entity.Obstacle;
import de.jonas.informatik.object.material.Brick;
import de.jonas.informatik.object.material.BrickSelection;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein {@link Gui} stellt ein Fenster, welches auf einer Instanz eines {@link JFrame} basiert, dar, worin das gesamte
 * 2D-Spiel angezeigt wird bzw. worin das hauptsächliche Spiel stattfindet.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Die Breite des Fensters. */
    public static final int WIDTH = 700;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 500;
    /** Der Titel des Fensters. */
    private static final String TITLE = "2D-Spiel";
    /** Die standard Schriftart, die in diesem Fenster genutzt wird. */
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Bild, welches durch eine {@link BrickSelection} erzeugt wird und woraus der sich bewegende Boden besteht. */
    private final BufferedImage groundImage;
    /** Die aktuelle X-Koordinate des Bodens, womit die Bewegung erzeugt wird. */
    private int currentGroundX = 0;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}. Ein {@link Gui} stellt ein Fenster, welches auf einer Instanz eines
     * {@link JFrame} basiert, dar, worin das gesamte 2D-Spiel angezeigt wird bzw. worin das hauptsächliche Spiel
     * stattfindet.
     */
    public Gui() {
        super(TITLE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setLayout(null);
        super.setResizable(false);
        super.addKeyListener(new KeyListener());

        final List<Brick> ground = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            ground.add(new Brick(i * 100, 0, 100));
        }

        final BrickSelection groundSelection = new BrickSelection(ground);
        this.groundImage = groundSelection.createImage();

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        super.add(draw);
        super.setVisible(true);
    }
    //</editor-fold>


    /**
     * Bewegt den Boden um eine Koordinate weiter nach links bzw. reduziert die X-Koordinate des Bodens um 1, sodass
     * sich der Boden um eine Stelle weiter nach links schiebt.
     */
    public void moveGround() {
        this.currentGroundX -= 1;

        if (this.currentGroundX <= -1 * this.groundImage.getWidth()) {
            this.currentGroundX = 0;
        }
    }


    //<editor-fold desc="Draw">

    /**
     * Mithilfe eines {@link Draw} werden alle Grafiken auf das Fenster gezeichnet. Die Zeichnungen werden so schnell es
     * geht immer wieder aktualisiert, sodass fortlaufende Bewegungen und Animationen, wie die des Bodens, flüssig
     * angezeigt werden können.
     */
    private final class Draw extends JPanel {

        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // set black color and default font
            g.setColor(Color.BLACK);
            g.setFont(DEFAULT_FONT);

            // write data
            g.drawString("Zeit: " + Game.getGameInstance().getCurrentTime(), 20, 30);

            // draw ground
            g.drawImage(groundImage, currentGroundX, 420, this);
            g.drawImage(groundImage, currentGroundX + groundImage.getWidth(), 420, this);

            // draw player
            Game.getGameInstance().getPlayer().draw(g);

            // draw obstacles
            for (final Obstacle obstacle : Game.getGameInstance().getObstacles()) {
                obstacle.draw(g);
            }

            repaint();
        }
        //</editor-fold>

    }
    //</editor-fold>
}
