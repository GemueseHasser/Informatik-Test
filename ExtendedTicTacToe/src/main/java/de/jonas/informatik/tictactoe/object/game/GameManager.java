package de.jonas.informatik.tictactoe.object.game;

import de.jonas.informatik.tictactoe.constant.PlayerType;
import de.jonas.informatik.tictactoe.listener.ClickListener;

import javax.swing.JButton;

/**
 * Der {@link GameManager} ist für den gesamten Ablauf des Spiels und dessen Komponenten zuständig. Er ist also eine der
 * Main-Klasse untergestellte Main-Klasse, die beliebig oft instanziiert werden kann.
 */
public final class GameManager {

    //<editor-fold desc="CONSTANTS">
    /** Die Größe der einzelnen {@link TicTacToeField Felder}. */
    private static final int FIELD_SIZE = 50;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link TicTacToeField Felder}, aus denen das Spielfeld besteht. */
    private final TicTacToeField[][] fields = new TicTacToeField[20][20];
    /** Der {@link Computer} dieses Spiels, welcher automatisch platzieren kann. */
    private final Computer computer = new Computer();
    /** Der Zustand, ob der Spieler momentan platzieren darf. */
    private boolean playersTurn = true;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link GameManager}. Der {@link GameManager} ist für
     * den gesamten Ablauf des Spiels und dessen Komponenten zuständig. Er ist also eine der Main-Klasse untergestellte
     * Main-Klasse, die beliebig oft instanziiert werden kann.
     */
    public GameManager() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new TicTacToeField(
                    getFormattedButton(i, j),
                    PlayerType.EMPTY
                );
            }
        }
    }
    //</editor-fold>


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
     * @param playersTurn Der Zustand, ob der Spieler platzieren darf.
     */
    public void setPlayersTurn(final boolean playersTurn) {
        this.playersTurn = playersTurn;
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
    public boolean isPlayersTurn() {
        return this.playersTurn;
    }

}
