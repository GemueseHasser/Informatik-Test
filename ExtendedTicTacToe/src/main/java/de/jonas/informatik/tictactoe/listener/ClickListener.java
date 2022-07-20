package de.jonas.informatik.tictactoe.listener;

import de.jonas.informatik.ExtendedTicTacToe;
import de.jonas.informatik.tictactoe.constant.PlayerType;
import de.jonas.informatik.tictactoe.object.TicTacToeField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Mithilfe eines {@link ClickListener} wird die Aktion geregelt, die ausgeführt werden soll, sobald der Nutzer einen
 * Button eines {@link TicTacToeField} anklickt.
 */
public final class ClickListener implements ActionListener {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Zeile, in der sich das {@link TicTacToeField} befindet. */
    private final int row;
    /** Die Spalte, in der sich das {@link TicTacToeField} befindet. */
    private final int column;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link ClickListener}. Mithilfe eines {@link ClickListener} wird die Aktion
     * geregelt, die ausgeführt werden soll, sobald der Nutzer einen Button eines {@link TicTacToeField} anklickt.
     *
     * @param row    Die Zeile, in der sich das {@link TicTacToeField} befindet.
     * @param column Die Spalte, in der sich das {@link TicTacToeField} befindet.
     */
    public ClickListener(
        final int row,
        final int column
    ) {
        this.row = row;
        this.column = column;
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        // check if game is running
        if (ExtendedTicTacToe.getGameManager().isGameNotRunning()) return;

        // check if players turn
        if (!ExtendedTicTacToe.getGameManager().isUsersTurn()) return;

        final TicTacToeField field = ExtendedTicTacToe.getGameManager().getFields()[this.row][this.column];

        // check if player is able to place
        if (field.getPlayerType() != PlayerType.EMPTY) return;

        // set players turn to false
        ExtendedTicTacToe.getGameManager().setUsersTurn(false);

        // place
        field.setPlayerType(PlayerType.USER);
        field.getButton().setText(PlayerType.USER.getSymbol());

        // check the game
        ExtendedTicTacToe.getGameManager().checkGame();

        // have the computer placed
        ExtendedTicTacToe.getGameManager().getComputer().place();
    }
    //</editor-fold>
}
