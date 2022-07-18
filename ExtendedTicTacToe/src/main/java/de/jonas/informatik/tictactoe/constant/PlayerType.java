package de.jonas.informatik.tictactoe.constant;

/**
 * Ein {@link PlayerType} wird genutzt um ein bestimmtes
 * {@link de.jonas.informatik.tictactoe.object.game.TicTacToeField} zuzuordnen.
 */
public enum PlayerType {

    //<editor-fold desc="VALUES">
    /** Der Nutzer, der dieses Spiel aktiv spielt. */
    USER(
        "X"
    ),
    /** Der Computer, der mithilfe von künstlicher Intelligenz versucht gegen den Spieler zu gewinnen. */
    COMPUTER(
        "O"
    ),
    /** Ein leeres Feld, also weder der Nutzer noch der Computer haben dieses Feld bereits belegt. */
    EMPTY(
        ""
    );
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Symbol, welches dieser {@link PlayerType} nutzt. */
    private final String symbol;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt einen neuen und vollständig unabhängigen {@link PlayerType Typ} mithilfe eines Symbols, welcher genutzt
     * wird, um ein bestimmtes {@link de.jonas.informatik.tictactoe.object.game.TicTacToeField} zuzuordnen.
     *
     * @param symbol Das Symbol, welches dieser {@link PlayerType} nutzt.
     */
    PlayerType(
        final String symbol
    ) {
        this.symbol = symbol;
    }
    //</editor-fold>


    /**
     * Gibt das Symbol zurück, welches dieser {@link PlayerType} nutzt.
     *
     * @return Das Symbol, welches dieser {@link PlayerType} nutzt.
     */
    public String getSymbol() {
        return this.symbol;
    }

}
