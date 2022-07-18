package de.jonas.informatik.tictactoe.constant;

/**
 * Ein {@link PlayerType} wird genutzt um ein bestimmtes
 * {@link de.jonas.informatik.tictactoe.object.game.TicTacToeField} zuzuordnen.
 */
public enum PlayerType {

    //<editor-fold desc="VALUES">
    /** Der Nutzer, der dieses Spiel aktiv spielt. */
    USER(),
    /** Der Computer, der mithilfe von künstlicher Intelligenz versucht gegen den Spieler zu gewinnen. */
    COMPUTER(),
    /** Ein leeres Feld, also weder der Nutzer noch der Computer haben dieses Feld bereits belegt. */
    EMPTY();
    //</editor-fold>

}
