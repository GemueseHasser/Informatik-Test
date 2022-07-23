package de.jonas.informatik.tictactoe.constant;

import de.jonas.informatik.tictactoe.object.GameManager;
import de.jonas.informatik.tictactoe.object.TicTacToeField;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Ein {@link PlayerType} wird genutzt um ein bestimmtes {@link TicTacToeField} zuzuordnen.
 */
public enum PlayerType {

    //<editor-fold desc="VALUES">
    /** Der Nutzer, der dieses Spiel aktiv spielt. */
    USER(
        "x.png"
    ),
    /** Der Computer, der mithilfe von künstlicher Intelligenz versucht gegen den Spieler zu gewinnen. */
    COMPUTER(
        "o.png"
    ),
    /** Ein leeres Feld, also weder der Nutzer noch der Computer haben dieses Feld bereits belegt. */
    EMPTY(
        ""
    );
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Symbol, welches dieser {@link PlayerType} nutzt. */
    private final BufferedImage symbol;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt einen neuen und vollständig unabhängigen {@link PlayerType Typ} mithilfe eines Symbols, welcher genutzt
     * wird, um ein bestimmtes {@link TicTacToeField} zuzuordnen.
     *
     * @param path Der Pfad zu dem Bild, welches als Symbol genutzt werden soll für diesen {@link PlayerType Typ}.
     */
    PlayerType(
        final String path
    ) {
        final BufferedImage image = new BufferedImage(
            GameManager.FIELD_SIZE,
            GameManager.FIELD_SIZE,
            BufferedImage.TYPE_INT_RGB
        );

        final Graphics g = image.createGraphics();
        final URL url = getClass().getResource("/" + path);

        try {
            assert url != null;
            final Image logo = ImageIO.read(url);

            g.drawImage(logo, 0, 0, GameManager.FIELD_SIZE, GameManager.FIELD_SIZE, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.symbol = image;
    }
    //</editor-fold>


    /**
     * Gibt das Symbol zurück, welches dieser {@link PlayerType} nutzt.
     *
     * @return Das Symbol, welches dieser {@link PlayerType} nutzt.
     */
    public BufferedImage getSymbol() {
        return this.symbol;
    }

}
