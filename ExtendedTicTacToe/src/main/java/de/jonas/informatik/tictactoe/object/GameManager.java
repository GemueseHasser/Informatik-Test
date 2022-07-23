package de.jonas.informatik.tictactoe.object;

import de.jonas.informatik.ExtendedTicTacToe;
import de.jonas.informatik.tictactoe.constant.PlayerType;
import de.jonas.informatik.tictactoe.listener.ClickListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;

/**
 * Der {@link GameManager} ist für den gesamten Ablauf des Spiels und dessen Komponenten zuständig. Er ist also eine der
 * Main-Klasse untergestellte Main-Klasse, die beliebig oft instanziiert werden kann.
 */
public final class GameManager {

    //<editor-fold desc="CONSTANTS">
    /** Die Größe, die das Spielfeld besitzen soll, also aus wie vielen Feldern es bestehen soll. */
    public static final int GAME_FIELD_SIZE = 20;
    /** Die Größe der einzelnen {@link TicTacToeField Felder}. */
    private static final int FIELD_SIZE = 50;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link TicTacToeField Felder}, aus denen das Spielfeld besteht. */
    private final TicTacToeField[][] fields = new TicTacToeField[GAME_FIELD_SIZE][GAME_FIELD_SIZE];
    /** Der {@link Computer} dieses Spiels, welcher automatisch platzieren kann. */
    private final Computer computer = new Computer();
    /** Der Zustand, ob der Spieler momentan platzieren darf. */
    private boolean UsersTurn = true;
    /** Der Zustand, ob das Spiel momentan läuft. */
    private boolean gameRunning = true;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link GameManager}. Der {@link GameManager} ist für
     * den gesamten Ablauf des Spiels und dessen Komponenten zuständig. Er ist also eine der Main-Klasse untergestellte
     * Main-Klasse, die beliebig oft instanziiert werden kann.
     */
    public GameManager() {
        for (int i = 0; i < GAME_FIELD_SIZE; i++) {
            for (int j = 0; j < GAME_FIELD_SIZE; j++) {
                fields[i][j] = new TicTacToeField(
                    getFormattedButton(i, j),
                    i,
                    j,
                    PlayerType.EMPTY
                );
            }
        }
    }
    //</editor-fold>


    /**
     * Prüft, ob das Spiel entweder von dem Spieler oder dem Computer gewonnen wurde und leitet alle nötigen Aktionen
     * ein. Das Spiel sollte nach jedem Zug (sowohl vom Computer, als auch vom Nutzer) überprüft werden, um Fehler zu
     * vermeiden.
     */
    public void checkGame() {
        if (hasWon(PlayerType.COMPUTER)) {
            this.gameRunning = false;

            final int playAgain = JOptionPane.showConfirmDialog(
                null,
                "Der Computer hat das Spiel leider gewonnen.\n\nMöchtest du erneut spielen?",
                "Verloren!",
                JOptionPane.YES_NO_OPTION
            );

            if (playAgain == 1) {
                System.exit(0);
            }

            ExtendedTicTacToe.restartGame();
        }

        if (hasWon(PlayerType.USER)) {
            this.gameRunning = false;

            final int playAgain = JOptionPane.showConfirmDialog(
                null,
                "Du hast dieses Spiel gewonnen.\n\nMöchtest du erneut spielen?",
                "Gewonnen!",
                JOptionPane.YES_NO_OPTION
            );

            if (playAgain == 1) {
                System.exit(0);
            }

            ExtendedTicTacToe.restartGame();
        }
    }

    /**
     * Prüft, ob ein bestimmter {@link PlayerType} dieses Spiel gewonnen hat.
     *
     * @param playerType Der {@link PlayerType}, dessen Sieg überprüft werden soll.
     *
     * @return Wenn ein bestimmter {@link PlayerType} dieses Spiel gewonnen hat {@code true}, ansonsten {@code false}.
     */
    private boolean hasWon(final PlayerType playerType) {
        int currentBegin = 0;

        // check horizontal
        for (final TicTacToeField[] field : fields) {
            for (int j = 0; j < GameManager.GAME_FIELD_SIZE; j++) {
                if (field[j].getPlayerType() != playerType) {
                    currentBegin = j;
                }

                // check win
                if (j - currentBegin > 4) {
                    for (int i = currentBegin + 1; i < j + 1; i++) {
                        field[i].getButton().setForeground(Color.RED);
                    }
                    return true;
                }
            }
        }

        currentBegin = 0;

        // check vertical and diagonal
        for (int i = 0; i < GAME_FIELD_SIZE; i++) {
            for (int j = 0; j < GAME_FIELD_SIZE; j++) {
                // vertical
                if (fields[j][i].getPlayerType() != playerType) {
                    currentBegin = j;
                }

                // check win
                if (j - currentBegin > 4) {
                    for (int k = currentBegin; k < j + 1; k++) {
                        fields[k][i].getButton().setForeground(Color.RED);
                    }
                    return true;
                }

                // diagonal
                if (fields[i][j].getPlayerType() != playerType) continue;

                int diagonalRight = 1;
                int diagonalLeft = 1;

                for (int k = 1; k < 5; k++) {
                    if (i + k < GAME_FIELD_SIZE && j + k < GAME_FIELD_SIZE) {
                        if (fields[i + k][j + k].getPlayerType() == playerType) {
                            diagonalRight++;
                        }
                    }

                    if (i + k < GAME_FIELD_SIZE && j - k >= 0) {
                        if (fields[i + k][j - k].getPlayerType() == playerType) {
                            diagonalLeft++;
                        }
                    }

                    // check win
                    if (diagonalRight > 4) {
                        for (int h = 0; h < 5; h++) {
                            fields[i + h][j + h].getButton().setForeground(Color.RED);
                        }
                        return true;
                    }

                    if (diagonalLeft > 4) {
                        for (int h = 0; h < 5; h++) {
                            fields[i + h][j - h].getButton().setForeground(Color.RED);
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Gibt einen formatierten {@link JButton} zurück, welcher dann als Komponente genutzt wird, um ein bestimmtes
     * {@link TicTacToeField Feld} zu instanziieren und es in dessen Zeile und Spalte zu platzieren.
     *
     * @param row    Die Zeile, in der sich das {@link TicTacToeField} befinden soll, für das dieser Button erzeugt
     *               wird.
     * @param column Die Spalte, in der sich das {@link TicTacToeField} befinden soll, für das dieser Button erzeugt
     *               wird.
     *
     * @return einen formatierten {@link JButton}, welcher dann als Komponente genutzt wird, um ein bestimmtes
     *     {@link TicTacToeField Feld} zu instanziieren und es in dessen Zeile und Spalte zu platzieren.
     */
    private JButton getFormattedButton(final int row, final int column) {
        final JButton button = new JButton();
        button.setBounds(30 + column * FIELD_SIZE, 30 + row * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.addActionListener(new ClickListener(row, column));

        return button;
    }

    /**
     * Setzt den Zustand neu, ob der Spieler platzieren darf.
     *
     * @param usersTurn Der Zustand, ob der Spieler platzieren darf.
     */
    public void setUsersTurn(final boolean usersTurn) {
        this.UsersTurn = usersTurn;
    }

    /**
     * Gibt alle Felder zurück, aus denen das Spielfeld besteht.
     *
     * @return Alle Felder, aus denen das Spielfeld besteht.
     */
    public TicTacToeField[][] getFields() {
        return this.fields;
    }

    /**
     * Gibt den Computer zurück, welcher automatisch platzieren kann.
     *
     * @return Der Computer, welcher automatisch platzieren kann.
     */
    public Computer getComputer() {
        return this.computer;
    }

    /**
     * Gibt zurück, ob der Spieler momentan platzieren darf.
     *
     * @return Wenn der Spieler platzieren darf {@code true}, ansonsten {@code false}.
     */
    public boolean isUsersTurn() {
        return this.UsersTurn;
    }

    /**
     * Gibt zurück, ob das Spiel momentan nicht läuft.
     *
     * @return Wenn das Spiel momentan nicht läuft {@code true}, ansonsten {@code false}.
     */
    public boolean isGameNotRunning() {
        return !this.gameRunning;
    }

}
