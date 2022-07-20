package de.jonas.informatik.tictactoe.object.game;

import de.jonas.informatik.ExtendedTicTacToe;
import de.jonas.informatik.tictactoe.constant.PlayerType;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Der Computer ersetzt einen zweiten Spieler, sodass der Nutzer dieses Spiels alleine gegen diesen Computer spielen
 * kann. Der Computer setzt mithilfe von künstlicher Intelligenz völlig automatisiert.
 */
public final class Computer {

    /**
     * Lässt den Computer seinen Zug vollständig automatisiert ausführen. Nach Beendigung dieses Zugs ist der Nutzer
     * wieder an der Reihe.
     */
    public void place() {
        // check if game is running
        if (ExtendedTicTacToe.getGameManager().isGameNotRunning()) return;

        // check if computers turn
        if (ExtendedTicTacToe.getGameManager().isUsersTurn()) return;

        // place random
        placeRandom();

        // its players turn :)
        ExtendedTicTacToe.getGameManager().setUsersTurn(true);

        // check the game
        ExtendedTicTacToe.getGameManager().checkGame();
    }

    /**
     * Platziert an einer komplett zufälligen Position auf dem Spielfeld.
     */
    private void placeRandom() {
        int row = ThreadLocalRandom.current().nextInt(0, 20);
        int column = ThreadLocalRandom.current().nextInt(0, 20);

        while (
            ExtendedTicTacToe.getGameManager().getFields()[row][column].getPlayerType()
                != PlayerType.EMPTY
        ) {
            row = ThreadLocalRandom.current().nextInt(0, 20);
            column = ThreadLocalRandom.current().nextInt(0, 20);
        }

        placeAt(row, column);
    }

    /**
     * Platziert an einer exakten Stelle auf dem Spielfeld.
     *
     * @param row    Die Zeile, in der der Computer platzieren soll.
     * @param column Die Spalte, in der der Computer platzieren soll.
     */
    private void placeAt(final int row, final int column) {
        ExtendedTicTacToe.getGameManager().getFields()[row][column].setPlayerType(PlayerType.COMPUTER);
        ExtendedTicTacToe.getGameManager().getFields()[row][column].getButton().setText(PlayerType.COMPUTER.getSymbol());
    }

}
