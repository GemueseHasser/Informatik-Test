package de.jonas.informatik.tictactoe.object;

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

        final TicTacToeField computerWinField = getFieldWithMin(PlayerType.COMPUTER, 3);
        final TicTacToeField userWinField = getFieldWithMin(PlayerType.USER, 3);

        final TicTacToeField computerNextWinField = getFieldWithMin(PlayerType.COMPUTER, 2);
        final TicTacToeField userNextWinField = getFieldWithMin(PlayerType.USER, 2);

        final TicTacToeField computerSecondNextWinField = getFieldWithMin(PlayerType.COMPUTER, 1);
        final TicTacToeField userSecondNextWinField = getFieldWithMin(PlayerType.USER, 1);

        final TicTacToeField computerNearField = getFieldWithMin(PlayerType.COMPUTER, 0);
        final TicTacToeField userNearField = getFieldWithMin(PlayerType.USER, 0);

        if (computerWinField != null) {
            placeAt(computerWinField);
        } else if (userWinField != null) {
            placeAt(userWinField);
        } else if (userNextWinField != null) {
            placeAt(userNextWinField);
        } else if (userSecondNextWinField != null) {
            placeAt(userSecondNextWinField);
        } else if (userNearField != null) {
            placeAt(userNearField);
        } else if (computerNextWinField != null) {
            placeAt(computerNextWinField);
        } else if (computerSecondNextWinField != null) {
            placeAt(computerSecondNextWinField);
        } else if (computerNearField != null) {
            placeAt(computerNearField);
        } else {
            placeRandom();
        }

        // its players turn :)
        ExtendedTicTacToe.getGameManager().setUsersTurn(true);

        // check the game
        ExtendedTicTacToe.getGameManager().checkGame();
    }

    /**
     * Gibt das erstbeste - falls überhaupt vorhandene - {@link TicTacToeField} zurück, welches direkt an eine Kette aus
     * Feldern, bestehend aus einer Mindestanzahl an Feldern, grenzt.
     *
     * @param playerType Der {@link PlayerType} dessen Felder auf eine Mindestanzahl an direkt beieinander liegende
     *                   Felder überprüft werden soll.
     * @param min        Die Mindestanzahl an Feldern, die erreicht sein muss, damit ein angrenzendes leeres Feld
     *                   zurückgegeben wird.
     *
     * @return Das erstbeste - falls überhaupt vorhandene - {@link TicTacToeField}, welches direkt an eine Kette aus
     *     Feldern, bestehend aus einer Mindestanzahl an Feldern, grenzt.
     */
    private TicTacToeField getFieldWithMin(final PlayerType playerType, final int min) {
        final TicTacToeField horizontal = getFieldWithMinHorizontal(playerType, min);
        final TicTacToeField vertical = getFieldWithMinVertical(playerType, min);
        final TicTacToeField diagonal = getFieldWithMinDiagonal(playerType, min);

        if (horizontal != null) return horizontal;
        if (vertical != null) return vertical;
        return diagonal;
    }

    /**
     * Gibt ein mögliches {@link TicTacToeField} zurück, welches direkt neben einer Kette von einer Mindestanzahl an
     * Feldern eines {@link PlayerType} horizontal liegt. Wenn keine derartige Kette von Feldern vorhanden ist, wird
     * {@code null} zurückgegeben.
     *
     * @param playerType Der {@link PlayerType} dessen horizontalen Ketten auf eine Mindestanzahl an Kettengliedern
     *                   überprüft werden soll.
     * @param min        Die Mindestanzahl an Feldern, aus denen eine bestimmte horizontale Kette bestehen muss, damit
     *                   ein angrenzendes Feld - soweit vorhanden - zurückgegeben wird.
     *
     * @return Ein mögliches {@link TicTacToeField}, welches direkt neben einer Kette von einer Mindestanzahl an Feldern
     *     eines {@link PlayerType} horizontal liegt. Wenn keine derartige Kette von Feldern vorhanden ist, wird
     *     {@code null} zurückgegeben.
     */
    private TicTacToeField getFieldWithMinHorizontal(final PlayerType playerType, final int min) {
        int currentBegin = 0;

        for (final TicTacToeField[] field : ExtendedTicTacToe.getGameManager().getFields()) {
            for (int j = 0; j < field.length; j++) {
                if (field[j].getPlayerType() != playerType) {
                    currentBegin = j;
                }

                if (j - currentBegin > min) {
                    if (j - min - 1 >= 0) {
                        if (field[j - min - 1].getPlayerType() == PlayerType.EMPTY) {
                            return field[j - min - 1];
                        }
                    }

                    if (j + 1 < GameManager.GAME_FIELD_SIZE) {
                        if (field[j + 1].getPlayerType() == PlayerType.EMPTY) {
                            return field[j + 1];
                        }
                    }

                    currentBegin = j;
                }
            }
        }

        return null;
    }

    /**
     * Gibt ein mögliches {@link TicTacToeField} zurück, welches direkt neben einer Kette von einer Mindestanzahl an
     * Feldern eines {@link PlayerType} vertikal liegt. Wenn keine derartige Kette von Feldern vorhanden ist, wird
     * {@code null} zurückgegeben.
     *
     * @param playerType Der {@link PlayerType} dessen vertikale Ketten auf eine Mindestanzahl an Kettengliedern
     *                   überprüft werden soll.
     * @param min        Die Mindestanzahl an Feldern, aus denen eine bestimmte vertikale Kette bestehen muss, damit ein
     *                   angrenzendes Feld - soweit vorhanden - zurückgegeben wird.
     *
     * @return Ein mögliches {@link TicTacToeField}, welches direkt neben einer Kette von einer Mindestanzahl an Feldern
     *     eines {@link PlayerType} vertikal liegt. Wenn keine derartige Kette von Feldern vorhanden ist, wird
     *     {@code null} zurückgegeben.
     */
    private TicTacToeField getFieldWithMinVertical(final PlayerType playerType, final int min) {
        final TicTacToeField[][] fields = ExtendedTicTacToe.getGameManager().getFields();

        int currentBegin = 0;

        for (int i = 0; i < GameManager.GAME_FIELD_SIZE; i++) {
            for (int j = 0; j < GameManager.GAME_FIELD_SIZE; j++) {
                if (fields[j][i].getPlayerType() != playerType) {
                    currentBegin = j;
                }

                if (j - currentBegin > min) {
                    if (j - min - 1 >= 0) {
                        if (fields[j - min - 1][i].getPlayerType() == PlayerType.EMPTY) {
                            return fields[j - min - 1][i];
                        }
                    }

                    if (j + 1 < GameManager.GAME_FIELD_SIZE) {
                        if (fields[j + 1][i].getPlayerType() == PlayerType.EMPTY) {
                            return fields[j + 1][i];
                        }
                    }

                    currentBegin = j;
                }
            }
        }

        return null;
    }

    /**
     * Gibt ein mögliches {@link TicTacToeField} zurück, welches direkt neben einer Kette von einer Mindestanzahl an
     * Feldern eines {@link PlayerType} diagonal liegt. Wenn keine derartige Kette von Feldern vorhanden ist, wird
     * {@code null} zurückgegeben.
     *
     * @param playerType Der {@link PlayerType} dessen diagonalen Ketten auf eine Mindestanzahl an Kettengliedern
     *                   überprüft werden soll.
     * @param min        Die Mindestanzahl an Feldern, aus denen eine bestimmte diagonale Kette bestehen muss, damit ein
     *                   angrenzendes Feld - soweit vorhanden - zurückgegeben wird.
     *
     * @return Ein mögliches {@link TicTacToeField}, welches direkt neben einer Kette von einer Mindestanzahl an Feldern
     *     eines {@link PlayerType} diagonal liegt. Wenn keine derartige Kette von Feldern vorhanden ist, wird
     *     {@code null} zurückgegeben.
     */
    private TicTacToeField getFieldWithMinDiagonal(final PlayerType playerType, final int min) {
        final TicTacToeField[][] fields = ExtendedTicTacToe.getGameManager().getFields();

        for (int i = 0; i < GameManager.GAME_FIELD_SIZE; i++) {
            for (int j = 0; j < GameManager.GAME_FIELD_SIZE; j++) {
                if (fields[i][j].getPlayerType() != playerType) continue;

                int diagonalRight = 1;
                int diagonalLeft = 1;

                for (int k = 1; k < 5; k++) {
                    if (i + k < GameManager.GAME_FIELD_SIZE && j + k < GameManager.GAME_FIELD_SIZE) {
                        if (fields[i + k][j + k].getPlayerType() == playerType) {
                            diagonalRight++;
                        }
                    }

                    if (i + k < GameManager.GAME_FIELD_SIZE && j - k > 0) {
                        if (fields[i + k][j - k].getPlayerType() == playerType) {
                            diagonalLeft++;
                        }
                    }

                    if (diagonalRight > min) {
                        if (i - 1 >= 0 && j - 1 >= 0) {
                            if (fields[i - 1][j - 1].getPlayerType() == PlayerType.EMPTY) {
                                return fields[i - 1][j - 1];
                            }
                        }

                        if ((i + min + 1) < GameManager.GAME_FIELD_SIZE && (j + min + 1) < GameManager.GAME_FIELD_SIZE) {
                            if (fields[i + min + 1][j + min + 1].getPlayerType() == PlayerType.EMPTY) {
                                return fields[i + min + 1][j + min + 1];
                            }
                        }

                        diagonalRight = 1;
                    }

                    if (diagonalLeft > min) {
                        if (i - 1 >= 0 && j + 1 < GameManager.GAME_FIELD_SIZE) {
                            if (fields[i - 1][j + 1].getPlayerType() == PlayerType.EMPTY) {
                                return fields[i - 1][j + 1];
                            }
                        }

                        if (i + min + 1 < GameManager.GAME_FIELD_SIZE && j - min - 1 >= 0) {
                            if (fields[i + min + 1][j - min - 1].getPlayerType() == PlayerType.EMPTY) {
                                return fields[i + min + 1][j - min - 1];
                            }
                        }

                        diagonalLeft = 1;
                    }
                }
            }
        }

        return null;
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

    /**
     * Platziert an einer exakten Stelle auf dem Spielfeld.
     *
     * @param field Das Feld, in dem der Computer setzen soll.
     */
    private void placeAt(final TicTacToeField field) {
        field.setPlayerType(PlayerType.COMPUTER);
        field.getButton().setText(PlayerType.COMPUTER.getSymbol());
    }

}
