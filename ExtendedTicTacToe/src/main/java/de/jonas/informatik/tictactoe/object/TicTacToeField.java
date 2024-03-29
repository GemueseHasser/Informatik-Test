package de.jonas.informatik.tictactoe.object;

import de.jonas.informatik.tictactoe.constant.PlayerType;

import javax.swing.JButton;

/**
 * Ein {@link TicTacToeField} beschreibt ein einzelnes Feld, welches entweder der Nutzer oder der Computer belegen
 * können. Das gesamte Spielfeld besteht aus mehreren dieser Felder.
 */
public final class TicTacToeField {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Zeile in der sich dieses Feld befindet. */
    private final int row;
    /** Die Spalte in der sich dieses Feld befindet. */
    private final int column;
    /** Der {@link JButton Button}, der diesem {@link TicTacToeField} zugeordnet wird. */
    private final JButton button;
    /** Der {@link PlayerType}, den dieses Feld momentan besitzt. */
    private PlayerType playerType;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link TicTacToeField}. Ein {@link TicTacToeField}
     * beschreibt ein einzelnes Feld, welches entweder der Nutzer oder der Computer belegen können. Das gesamte
     * Spielfeld besteht aus mehreren dieser Felder.
     *
     * @param button     Der {@link JButton Button}, der diesem {@link TicTacToeField} zugeordnet wird.
     * @param row        Die Zeile in der sich dieses Feld befindet.
     * @param column     Die Spalte in der sich dieses Feld befindet.
     * @param playerType Der {@link PlayerType}, den dieses Feld momentan besitzt.
     */
    public TicTacToeField(
        final JButton button,
        final int row,
        final int column,
        final PlayerType playerType
    ) {
        this.button = button;
        this.row = row;
        this.column = column;
        this.playerType = playerType;
    }
    //</editor-fold>


    /**
     * Legt den {@link PlayerType}, den dieses Feld momentan besitzt neu fest.
     *
     * @param playerType Der neue {@link PlayerType}, den dieses Feld bekommen soll.
     */
    public void setPlayerType(final PlayerType playerType) {
        this.playerType = playerType;
    }

    /**
     * Gibt den Button zurück, der diesem {@link TicTacToeField} zugeordnet ist.
     *
     * @return Der Button, der diesem {@link TicTacToeField} zugeordnet ist.
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * Gibt die Zeile zurück, in der sich dieses {@link TicTacToeField Feld} befindet.
     *
     * @return Die Zeile, in der sich dieses {@link TicTacToeField Feld} befindet.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gibt die Spalte zurück, in der sich dieses {@link TicTacToeField Feld} befindet.
     *
     * @return Die Spalte, in der sich dieses {@link TicTacToeField Feld} befindet.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Gibt den {@link PlayerType} zurück, den dieses Feld momentan besitzt.
     *
     * @return Der {@link PlayerType}, den dieses Feld momentan besitzt.
     */
    public PlayerType getPlayerType() {
        return this.playerType;
    }

}
