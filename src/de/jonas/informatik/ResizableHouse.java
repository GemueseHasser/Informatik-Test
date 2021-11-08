package de.jonas.informatik;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Es wird auf ein leeres {@link JFrame Fenster} ein Haus gezeichnet, welches sich bei Veränderung der Größe des
 * Fensters, automatisch neu skaliert und sich somit der Größe des Fensters anpasst. Um Ruckler zu vermeiden, wird die
 * Größe von Anfang an, abhängig von der Fenster-Größe, berechnet, da das {@link ComponentListener Event} erstmalig bei
 * Erzeugen des Fensters ausgelöst wird. Das Haus wird immer so skaliert, sodass es komplett auf dem fenster sichtbar
 * ist. Es wird also immer der größtmögliche Wert berechnet, den das Haus haben darf.
 */
public final class ResizableHouse extends JFrame implements ComponentListener {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Größe, die bei jeder Veränderung der Größe des Fensters automatisch neu berechnet wird. */
    private int size;
    //</editor-fold>

    //<editor-fold desc="implementation">
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        // calculate variables
        final int x = super.getWidth() / 2;
        final int y = super.getHeight() - 15;

        final int houseStartX = x - size / 2;
        final int houseEndX = x + size / 2;

        final int houseStartY = y - size;

        final int rooftopY = (int) (y - size * 1.5);

        // draw house shape
        g.drawRect(houseStartX, houseStartY, size, size);
        g.drawLine(houseStartX, houseStartY, x, rooftopY);
        g.drawLine(houseEndX, houseStartY, x, rooftopY);

        // draw square windows
        g.setColor(Color.LIGHT_GRAY);

        final int windowSize = size / 5;

        g.fillRect(houseStartX + windowSize, houseStartY + windowSize, windowSize, windowSize);
        g.fillRect(houseStartX + windowSize * 3, houseStartY + windowSize, windowSize, windowSize);
        g.fillRect(houseStartX + windowSize * 3, houseStartY + windowSize * 3, windowSize, windowSize);

        // draw door
        g.fillRect(houseStartX + windowSize, houseStartY + windowSize * 3, windowSize, windowSize * 2);

        // draw round window
        g.fillOval(x - windowSize / 2, rooftopY + size / 4 - windowSize / 2, windowSize, windowSize);

        // draw chimney
        g.setColor(Color.BLACK);

        final int chimneyWidth = size / 6;

        final int middleX = x + ((houseEndX - x) / 2);
        final int middleY = rooftopY + ((houseStartY - rooftopY) / 2);

        g.drawLine(middleX - chimneyWidth / 2, middleY - chimneyWidth / 2, middleX - chimneyWidth / 2, rooftopY);
        g.drawLine(middleX + chimneyWidth / 2, middleY + chimneyWidth / 2, middleX + chimneyWidth / 2, rooftopY);
        g.drawLine(middleX + chimneyWidth / 2, rooftopY, middleX - chimneyWidth / 2, rooftopY);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // rescale house (this method is also called when frame was created)
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

    /**
     * Skaliert das Haus neu auf dem Fenster oder skaliert es erstmalig, da diese Methode direkt nach dem erzeugen des
     * {@link JFrame Fensters} aufgerufen wird (das Event wird getriggert). Die Skalierung des Hauses wird mithilfe der
     * Breite und der Höhe des Fensters berechnet.
     */
    private void scale() {
        final int maxFrameScale = Math.min(super.getWidth(), super.getHeight());
        this.size = maxFrameScale - 50;
        this.size -= this.size / 3;
    }


    //<editor-fold desc="initialising">

    /**
     * Die Haupt- und Main-Methode der Anwendung, welche als aller erstes vor allen anderen Methoden der Anwendung von
     * der JRE aufgerufen und ausgeführt wird. Hiermit wird also das gesamte Programm initialisiert und gestartet.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        final ResizableHouse house = new ResizableHouse();
        house.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        house.setBounds(0, 0, 600, 600);
        house.setLocationRelativeTo(null);
        house.setResizable(true);
        house.setTitle("Automatisch skalierbares Haus");
        house.addComponentListener(house);
        house.setVisible(true);
    }
    //</editor-fold>
}
