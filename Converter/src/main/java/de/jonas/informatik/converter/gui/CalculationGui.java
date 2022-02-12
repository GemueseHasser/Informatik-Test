package de.jonas.informatik.converter.gui;

import javax.swing.JFrame;

import java.awt.Graphics;

/**
 * Ein {@link CalculationGui} stellt eine Instanz eines {@link AbstractGui} dar. In diesem Fenster können Zahlen mit
 * beliebigen Potenzen, also Zahlen aus einem beliebigen Zahlensystem miteinander verrechnet werden.
 */
public final class CalculationGui extends AbstractGui {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel des Fensters. */
    private static final String TITLE = "Rechner";
    /** Die Breite des Fensters. */
    private static final int WIDTH = 700;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 500;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link CalculationGui}. Ein {@link CalculationGui} stellt eine Instanz eines
     * {@link AbstractGui} dar. In diesem Fenster können Zahlen mit beliebigen Potenzen, also Zahlen aus einem
     * beliebigen Zahlensystem miteinander verrechnet werden.
     */
    public CalculationGui() {
        super(TITLE, WIDTH, HEIGHT);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {

    }
    //</editor-fold>
}
