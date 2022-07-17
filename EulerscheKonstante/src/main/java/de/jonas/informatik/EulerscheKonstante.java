package de.jonas.informatik;

import de.jonas.informatik.gui.Gui;
import de.jonas.informatik.gui.WaitingGui;
import de.jonas.informatik.handler.CalculationHandler;

import javax.swing.JOptionPane;

/**
 * Die Haupt- und Main-Klasse der {@link EulerscheKonstante}, worin die gesamte Software instanziiert bzw. initialisiert
 * wird.
 */
public class EulerscheKonstante {

    //<editor-fold desc="STATIC FIELDS">
    /** Der {@link CalculationHandler}, mit dem alle Werte der Eulerschen Konstante berechnet werden. */
    private static CalculationHandler calculationHandler;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, die als allererstes von der JRE aufgerufen wird.
     *
     * @param args Die Argumente, die von der JRE beim Start des Programms übergeben werden.
     */
    public static void main(final String[] args) {
        // ask user for scaling
        int tempScale;

        try {
            tempScale = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Wie groß soll die Skalierung sein?\n\nDie Skalierung wird automatisch optimiert.",
                "Skalierung",
                JOptionPane.WARNING_MESSAGE
            ));
        } catch (final NumberFormatException ignored) {
            tempScale = 170 / 2;
        }

        // calculate scale
        final int scale = (tempScale < 170 / 2) ? 170 / 2 : tempScale - (tempScale % (170 / 2));

        // open waiting gui
        final WaitingGui waitingGui = new WaitingGui();
        waitingGui.open();

        // initialize calculation-handler
        calculationHandler = new CalculationHandler(scale);

        // close waiting gui
        waitingGui.dispose();

        // open gui
        new Gui().open();
    }
    //</editor-fold>


    /**
     * Gibt den {@link CalculationHandler}, womit alle Werte der Eulerschen Konstante berechnet werden, zurück.
     *
     * @return Der {@link CalculationHandler}, womit alle Werte der Eulerschen Konstante berechnet werden.
     */
    public static CalculationHandler getCalculationHandler() {
        return calculationHandler;
    }

}
