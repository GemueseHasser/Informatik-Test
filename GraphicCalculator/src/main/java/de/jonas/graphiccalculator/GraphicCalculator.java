package de.jonas.graphiccalculator;

import de.jonas.graphiccalculator.gui.FunctionGui;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Ein {@link GraphicCalculator} ist eine grafische Oberfläche, auf der verschiedene mathematische Funktionen
 * gezeichnet werden können. Um diese Funktionen zu zeichnen werden verschiedene X- und Y-Werte der Funktionen
 * ausgerechnet und abgespeichert, die dann zum Zeichnen genutzt werden. Diese Punkte werden dann mit Linien verbunden,
 * wobei die Punkte so nah aneinander liegen, dass man diese Linien kaum sieht. Sie dienen nur dazu, die Konturen des
 * Graphen zu verbessern. </p>
 *
 * <p>Dies ist die Haupt- und Main-Klasse dieser Anwendung, die als erstes von der JRE aufgerufen wird und in der
 * sich die Main-Methode dieser Anwendung befindet. Diese Klasse stellt die höchste Instanz dieser Anwendung dar und
 * dient als Schnittstelle für alle Unterinstanzen dieses Programms. </p>
 */
public class GraphicCalculator {

    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser {@link GraphicCalculator Anwendung}, die als erstes von der JRE aufgerufen wird und von der
     * aus die gesamte Anwendung initialisiert bzw. instanziiert wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(@NotNull final String @NotNull [] args) {
        final FunctionGui gui = new FunctionGui();
        gui.setVisible(true);
    }
    //</editor-fold>

}
