package de.jonas.informatik.temp.object;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein {@link RoundButton} stammt von einem {@link JButton} ab, jedoch ist der {@link RoundButton} erstmal (nur
 * visuell) rund. Es ist nicht mehr möglich einen {@link ActionListener} zu dem Button hinzuzufügen, sondern man
 * kann nur noch einen eigenen und spezifischen {@link ClickListener} des {@link RoundButton} nutzen, welcher
 * wirklich nur dann ausgelöst wird, wenn der Button in der runden Form oder auf dem Rand der runden Form angeklickt
 * wird.
 */
public final class RoundButton extends JButton implements MouseListener {

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
        this.listeners.add(listener);
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
    public void setBounds(
        final int x,
        final int y,
        final int width,
        final int height
    ) {
        // get max size
        final int maxSize = Math.max(width, height);

        // set bounds
        super.setBounds(x, y, maxSize, maxSize);
    }

    @Override
    public void setSize(
        final int width,
        final int height
    ) {
        // get max size
        final int maxSize = Math.max(width, height);

        // set size
        super.setSize(maxSize, maxSize);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();

        if (!isMouseInOval()) {
            return;
        }

        for (final ClickListener listener : this.listeners) {
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
        throw new IllegalArgumentException(
            "sorry, but for these button you cant add an ActionListener (use ClickListener with #addClickListener())."
        );
    }
    //</editor-fold>
}
