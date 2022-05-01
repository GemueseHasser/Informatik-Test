package de.jonas.informatik;

import javax.swing.*;
import java.awt.*;

/**
 * Beim Erstellen einer Instanz des {@link BlackSquares}, wird ein Fenster erzeugt, auf dem 25 (5 x 5) Kästchen angezeigt
 * werden. Beim Anklicken dieser Kästchen verändert sich die Farbe immer wieder. Alle Kästchen sind zu Beginn schwarz,
 * wenn man sie dann anklickt, verändert sich die Farbe zu gelb und wenn man sie dann nochmals anklickt, wird das Kästchen
 * wieder schwarz. Das ist ein Prozess, den man endlos ausführen kann.
 */
public final class BlackSquares extends JFrame {

    /** Der Titel des Fensters. */
    private static final String TITLE = "Black Squares";
    /** Die Breite des Fensters. */
    private static final int WIDTH = 400;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 420;
    /** Die X- und die Y-Koordinate, ab der die Kästchen beginnen. */
    private static final int SQUARES_BEGIN_XY = 25;
    /** Die Größe (Breite und Höhe) der einzelnen Kästchen. */
    private static final int SQUARE_SIZE = 50;
    /** Der Abstand zwischen jedem Kästchen. */
    private static final int SQUARE_MARGIN = 20;


    /**
     * Die Main-Methode dieser Klasse, die als allererstes aufgerufen wird und von der aus die gesamte Klasse (Anwendung)
     * instanziiert wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        new BlackSquares();
    }


    /**
     * Erzeugt eine neue Instanz eines {@link BlackSquares} Fensters. Beim Erstellen einer Instanz des {@link BlackSquares},
     * wird ein Fenster erzeugt, auf dem 25 (5 x 5) Kästchen angezeigt werden. Beim Anklicken dieser Kästchen verändert
     * sich die Farbe immer wieder. Alle Kästchen sind zu Beginn schwarz, wenn man sie dann anklickt, verändert sich
     * die Farbe zu gelb und wenn man sie dann nochmals anklickt, wird das Kästchen wieder schwarz. Das ist ein Prozess,
     * den man endlos ausführen kann.
     */
    public BlackSquares() {
        super(TITLE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(null);

        int x = SQUARES_BEGIN_XY;
        int y = SQUARES_BEGIN_XY;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final JButton button = new JButton();
                button.setBounds(x, y, SQUARE_SIZE, SQUARE_SIZE);
                button.setBorderPainted(false);
                button.setFocusable(false);
                button.setBackground(Color.BLACK);

                button.addActionListener(e -> {
                    if (button.getBackground() == Color.BLACK) {
                        button.setBackground(Color.YELLOW);
                    } else {
                        button.setBackground(Color.BLACK);
                    }
                });

                super.add(button);

                x += (SQUARE_SIZE + SQUARE_MARGIN);
            }

            x = SQUARES_BEGIN_XY;
            y += (SQUARE_SIZE + SQUARE_MARGIN);
        }

        super.setVisible(true);
    }

}
