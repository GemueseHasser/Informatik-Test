package de.jonas.informatik;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Es wird auf ein leeres {@link JFrame Fenster} ein Haus gezeichnet, welches sich bei Veränderung der Größe des
 * Fensters, automatisch neu skaliert und sich somit der Größe des Fensters anpasst. Um Ruckler zu vermeiden, wird die
 * Größe von Anfang an, abhängig von der Fenster-Größe, berechnet, da das {@link ComponentListener Event} erstmalig bei
 * Erzeugen des Fensters ausgelöst wird. Das Haus wird immer so skaliert, sodass es komplett auf dem fenster sichtbar
 * ist. Es wird also immer der größtmögliche Wert berechnet, den das Haus haben darf. Zudem kann man das Licht sowohl
 * ein als auch wieder ausschalten, indem man auf die einzelnen Fenster klickt, hinter denen man das Licht ein bzw.
 * ausschalten möchte.
 */
public final class ColorfulHouse extends JFrame implements ComponentListener {

    //<editor-fold desc="CONSTANTS">
    /** Die Instanz des {@link ColorfulHouse Hauses}. */
    private static final ColorfulHouse HOUSE = new ColorfulHouse();
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Die Größe, die bei jeder Veränderung der Größe des Fensters automatisch neu berechnet wird. */
    private static int size;
    /** Die X-Koordinate, an der das Haus beginnt. */
    private static int houseStartX;
    /** Die Y-Koordinate, an der das Haus beginnt. */
    private static int houseStartY;
    /** Die Größe eines jeden einzelnen Fensters (und demnach auch die Länge des Abstandes zwischen den Fenstern). */
    private static int windowSize;
    /** Die Y-Koordinate der Dachspitze. */
    private static int rooftopY;
    //</editor-fold>

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Buttons, die als rechteckige Fenster angezeigt werden. */
    private final JButton[] buttons = new JButton[3];
    /** Der Button, für das runde Dachfenster. */
    private final RoundButton roundButton = new RoundButton();
    //</editor-fold>

    //<editor-fold desc="initialising">

    /**
     * Die Haupt- und Main-Methode der Anwendung, welche als aller erstes vor allen anderen Methoden der Anwendung von
     * der JRE aufgerufen und ausgeführt wird. Hiermit wird also das gesamte Programm initialisiert und gestartet.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        HOUSE.setTitle("Farbenfrohes Haus");
        HOUSE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HOUSE.setBounds(0, 0, 600, 600);
        HOUSE.setLocationRelativeTo(null);
        HOUSE.setResizable(true);
        HOUSE.addComponentListener(HOUSE);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, HOUSE.getWidth(), HOUSE.getHeight());
        draw.setVisible(true);

        for (int i = 0; i < HOUSE.buttons.length; i++) {
            HOUSE.buttons[i] = new JButton();
        }

        HOUSE.placeButtonWindow(
            HOUSE.buttons[0],
            houseStartX + windowSize,
            houseStartY + windowSize
        );

        HOUSE.placeButtonWindow(
            HOUSE.buttons[1],
            houseStartX + windowSize * 3,
            houseStartY + windowSize
        );

        HOUSE.placeButtonWindow(
            HOUSE.buttons[2],
            houseStartX + windowSize * 3,
            houseStartY + windowSize * 3
        );

        HOUSE.placeButtonWindow(
            HOUSE.roundButton,
            HOUSE.getWidth() / 2 - windowSize / 2,
            rooftopY + size / 4 - windowSize / 2
        );

        HOUSE.add(draw);
        HOUSE.setVisible(true);
    }
    //</editor-fold>

    /**
     * Skaliert das Haus neu auf dem Fenster oder skaliert es erstmalig, da diese Methode direkt nach dem erzeugen des
     * {@link JFrame Fensters} aufgerufen wird (das Event wird getriggert). Die Skalierung des Hauses wird mithilfe der
     * Breite und der Höhe des Fensters berechnet. Zudem werden auch die Buttons, welche als Fenster fungieren neu
     * skaliert (sowie Position der Buttons, als auch die Größe werden neu berechnet).
     */
    private void scale() {
        final int maxFrameScale = Math.min(super.getWidth(), super.getHeight());
        size = maxFrameScale - 50;
        size -= size / 3;

        buttons[0].setBounds(houseStartX + windowSize, houseStartY + windowSize, windowSize, windowSize);
        buttons[1].setBounds(houseStartX + windowSize * 3, houseStartY + windowSize, windowSize, windowSize);
        buttons[2].setBounds(houseStartX + windowSize * 3, houseStartY + windowSize * 3, windowSize, windowSize);

        roundButton.setBounds(
            HOUSE.getWidth() / 2 - windowSize / 2 - 5,
            rooftopY + size / 4 - windowSize / 2,
            windowSize,
            windowSize
        );
    }

    /**
     * Platziert einen {@link JButton Button} als Fenster an eine bestimmte Position (x|y). Wenn der Button ein normaler
     * eckiger Button ist, bekommt er eine schwarze Umrandung und wenn der Button rund ist, bekommt er gar keine.
     *
     * @param button Der {@link JButton Button}, welcher platziert werden soll.
     * @param x      Die X-Koordinate des Buttons.
     * @param y      Die Y-Koordinate des Buttons.
     */
    private void placeButtonWindow(
        final JButton button,
        final int x,
        final int y
    ) {
        button.setFocusable(false);
        button.setBackground(Color.LIGHT_GRAY);
        button.setBounds(x, y, windowSize, windowSize);

        super.add(button);

        if (button instanceof RoundButton) {
            button.setBorder(null);
            ((RoundButton) button).addClickListener(() -> doWindowAction(button));
            return;
        }

        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setOpaque(true);
        button.addActionListener(actionEvent -> doWindowAction(button));
    }

    /**
     * Führt die Aktion für das anklicken eines Fensters (Buttons) aus.
     *
     * @param button Der {@link JButton}, welcher als Fenster angeklickt wurde.
     */
    private void doWindowAction(final JButton button) {
        if (button.getBackground() == Color.LIGHT_GRAY) {
            button.setBackground(Color.YELLOW);
            return;
        }

        button.setBackground(Color.LIGHT_GRAY);
    }

    //<editor-fold desc="implementation">
    @Override
    public void componentResized(ComponentEvent e) {
        // rescale house (this method is also called when frame was created)

        // these method is also called in the draw class (else the buttons are invisible before frame were resized)
        // and also here to fix mini bugs
        scale();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    //</editor-fold>

    //<editor-fold desc="Draw">

    /**
     * Mithilfe des {@link Draw} wird das Grundgerüst des Hauses auf das Fenster gezeichnet.
     */
    private static final class Draw extends JLabel {

        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2d = (Graphics2D) g;

            // define render quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // calculate variables
            final int x = super.getWidth() / 2;
            final int y = super.getHeight() - 10;
            final int houseEndX = x + size / 2;

            houseStartX = x - size / 2;
            houseStartY = y - size;
            windowSize = size / 5;
            rooftopY = (int) (y - size * 1.5);

            // draw house shape
            g.drawRect(houseStartX, houseStartY, size, size);
            g.drawLine(houseStartX, houseStartY, x, rooftopY);
            g.drawLine(houseEndX, houseStartY, x, rooftopY);

            // draw door
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(houseStartX + windowSize, houseStartY + windowSize * 3, windowSize, windowSize * 2);

            // draw door border and chimney
            g.setColor(Color.BLACK);

            g.drawRect(houseStartX + windowSize, houseStartY + windowSize * 3, windowSize, windowSize * 2);

            final int chimneyWidth = size / 6;

            final int middleX = x + ((houseEndX - x) / 2);
            final int middleY = rooftopY + ((houseStartY - rooftopY) / 2);

            g.drawLine(middleX - chimneyWidth / 2, middleY - chimneyWidth / 2, middleX - chimneyWidth / 2, rooftopY);
            g.drawLine(middleX + chimneyWidth / 2, middleY + chimneyWidth / 2, middleX + chimneyWidth / 2, rooftopY);
            g.drawLine(middleX + chimneyWidth / 2, rooftopY, middleX - chimneyWidth / 2, rooftopY);

            // scale house
            HOUSE.scale();
        }
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="RoundButton">

    /**
     * Ein {@link RoundButton} stammt von einem {@link JButton} ab, jedoch ist der {@link RoundButton} erstmal (nur
     * visuell) rund. Es ist nicht mehr möglich einen {@link ActionListener} zu dem Button hinzuzufügen, sondern man
     * kann nur noch einen eigenen und spezifischen {@link ClickListener} des {@link RoundButton} nutzen, welcher
     * wirklich nur dann ausgelöst wird, wenn der Button in der runden Form oder auf dem Rand der runden Form angeklickt
     * wird.
     */
    private static final class RoundButton extends JButton implements MouseListener {

        //<editor-fold desc="LOCAL FIELDS">
        /** Alle hinzugefügten {@link ClickListener} des Buttons. */
        private final List<ClickListener> listeners = new ArrayList<>();
        /** Der X-Wert, an dem der Button angeklickt wurde. */
        private int mouseX;
        /** Der Y-Wert, an dem der Button angeklickt wurde. */
        private int mouseY;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue und abhängige Instanz eines {@link RoundButton} (Abhängig von dem {@link JButton}). Ein
         * {@link RoundButton} ist einfach ein runder {@link JButton}, jedoch ist die runde Eigenschaft ausschließlich
         * visuell.
         */
        public RoundButton() {
            final Dimension dimension = super.getPreferredSize();
            dimension.width = dimension.height = Math.max(dimension.width, dimension.height);
            super.setPreferredSize(dimension);

            super.setContentAreaFilled(false);
            super.addMouseListener(this);
        }
        //</editor-fold>


        /**
         * Fügt einen {@link ClickListener} zu diesem {@link RoundButton} hinzu.
         *
         * @param listener Der {@link ClickListener}, welcher hinzugefügt wird.
         */
        public void addClickListener(final ClickListener listener) {
            listeners.add(listener);
        }

        /**
         * <p>Überprüft, ob der jeweilige {@link RoundButton} quadratisch ist und wenn dem so ist, überprüft er, ob der
         * zuletzt angeklickte Punkt der Maus auf dem Button in dem Kreis ist.</p>
         * <p>So lässt sich mithilfe dieser Methode die ausschließlich visuelle Form des {@link RoundButton}
         * umgehen.</p>
         *
         * @return {@code true}, wenn der Mauszeiger beim Klicken in dem Kreis ist und {@code false}, wenn sich der
         *     Mauszeiger beim Klicken zwar in dem Lebensraum des Buttons befindet, aber außerhalb des Kreises.
         */
        private boolean isMouseInOval() {
            if (super.getSize().width != super.getSize().height) {
                System.out.println("please use the same values for width and height.");
                return false;
            }

            final int radius = (super.getSize().width - 1) / 2;

            final int x = mouseX - radius;
            final int y = mouseY - radius;

            final int distance = x * x + y * y;

            return distance <= radius * radius;
        }

        //<editor-fold desc="click-listener">

        /**
         * Ein {@link ClickListener} wird genutzt, um das Anklicken des {@link RoundButton} zu verarbeiten. Er
         * funktioniert ähnlich, wie der {@link ActionListener}, jedoch wird der {@link ClickListener} nur dann
         * ausgeführt, wenn die visuelle runde Form des {@link RoundButton} angeklickt wird. Der {@link ClickListener}
         * kann jedoch nur dann ausgeführt werden bzw getriggert und hinzugefügt werden, wenn die Breite und die Höhe
         * des {@link RoundButton} gleich sind.
         */
        public interface ClickListener {
            void onCLick();
        }
        //</editor-fold>

        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            g.setColor(super.getBackground());

            g.fillOval(0, 0, super.getSize().width - 1, super.getSize().height - 1);

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(final Graphics g) {
            super.paintBorder(g);

            g.setColor(super.getForeground());
            g.drawOval(0, 0, super.getSize().width - 1, super.getSize().height - 1);
        }

        @Override
        public void mouseClicked(final MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();

            if (!isMouseInOval()) {
                return;
            }

            for (final ClickListener listener : listeners) {
                listener.onCLick();
            }
        }

        @Override
        public void mousePressed(final MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(final MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(final MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(final MouseEvent mouseEvent) {

        }

        @Override
        public void addActionListener(final ActionListener actionListener) {
            // cancel adding default action-listener
            throw new IllegalArgumentException("sorry, but for these button you cant add an action-listener.");
        }
        //</editor-fold>
    }
    //</editor-fold>
}
