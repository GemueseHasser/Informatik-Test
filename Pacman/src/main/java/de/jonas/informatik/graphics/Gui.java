package de.jonas.informatik.graphics;

import javax.swing.JFrame;

/**
 * Ein {@link Gui} stellt ein Fenster dar, worin das {@link de.jonas.informatik.Pacman Spiel} angezeigt wird.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Pacman";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 600;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 500;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}, welches eine Instanz eines {@link JFrame} darstellt. Ein {@link Gui}
     * stellt ein Fenster dar, worin das {@link de.jonas.informatik.Pacman Spiel} angezeigt wird.
     */
    public Gui() {
        super(TITLE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(null);

        super.setVisible(true);
    }
    //</editor-fold>

}
