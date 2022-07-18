package de.jonas.informatik;

import de.jonas.informatik.tictactoe.gui.Gui;
import de.jonas.informatik.tictactoe.object.game.GameManager;

/**
 * Die Haupt- und Main-Klasse dieses erweiterten Tic-Tac-Toe Spiels, welches ein Spielfeld von 20 x 20 Kästchen besitzt.
 * In dieser Klasse wird die gesamte Anwendung instanziiert und initialisiert, da diese Klasse die Main-Methode besitzt,
 * die als allererstes direkt von der JRE aufgerufen wird.
 */
public class ExtendedTicTacToe {

    //<editor-fold desc="STATIC FIELDS">
    /** Der {@link GameManager} dieses Spiels. */
    private static GameManager gameManager;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, die direkt von der JRE aufgerufen wird.
     *
     * @param args Die Argumente, die von der JRE beim Ausführen des Programms übergeben werden.
     */
    public static void main(final String[] args) {
        // create game-manager instance
        gameManager = new GameManager();

        // create and open gui
        final Gui gui = new Gui();
        gui.open();
    }
    //</editor-fold>


    /**
     * Gibt den {@link GameManager} dieses Spiels zurück.
     *
     * @return Der {@link GameManager} dieses Spiels.
     */
    public static GameManager getGameManager() {
        return gameManager;
    }

}
