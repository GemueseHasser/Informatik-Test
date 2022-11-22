package de.jonas.informatik.tree;
// Klaus Wiele November 2022

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Ein {@link Converter} stellt eine Instanz eines {@link JFrame} dar, ist eine also eine grafische Benutzeroberfläche.
 * Mithilfe eines {@link Converter} kann ein Nutzer einen Morse-Code in Normalschrift übersetzen und umgekehrt.
 */
public class Converter extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel des Fensters. */
    private static final String TITLE = "Morse Converter v1.0 by KW";
    /** Die Breite des Fensters. */
    private static final int WIDTH = 700;
    /** Die Höhe des Fensters. */
    private static final int HEIGHT = 500;
    /** Die X-Koordinate der Textfelder. */
    private static final int JTA_X = 50;
    /** Die Y-Koordinate der Textfelder. */
    private static final int JTA_Y = 130;
    /** Die Breite der Textfelder. */
    private static final int JTA_WIDTH = (WIDTH / 2) - (JTA_X * 2) + 17;
    /** Die Höhe der Textfelder. */
    private static final int JTA_HEIGHT = 100;
    /** Die Y-Koordinate des obersten Buttons. */
    private static final int BUTTON_Y = 300;
    /** Die Breite der Buttons. */
    private static final int BUTTON_WIDTH = 100;
    /** Die Höhe der Buttons. */
    private static final int BUTTON_HEIGHT = 30;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link Tree Baumstruktur}, die zum Übersetzen genutzt wird. */
    private final Tree myTree = new Tree();
    /** Die Datei, in der die Morse-Codes gespeichert sind. */
    private RandomAccessFile database;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Converter}. Ein {@link Converter} stellt eine Instanz eines {@link JFrame}
     * dar, ist eine also eine grafische Benutzeroberfläche. Mithilfe eines {@link Converter} kann ein Nutzer einen
     * Morse-Code in Normalschrift übersetzen und umgekehrt.
     */
    public Converter() {
        // set frame properties
        super(TITLE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(null);

        // load database
        loadDatabase();

        // create basic draw object for frame graphics
        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        // set up the text areas
        // The JTextArea needs a surrounding JScrollPane for scrolling.
        final JTextArea jtaMorse = new JTextArea("");
        jtaMorse.setLineWrap(true);
        jtaMorse.setWrapStyleWord(true);

        final JScrollPane jspMorse = new JScrollPane(jtaMorse);
        jspMorse.setBounds(JTA_X, JTA_Y, JTA_WIDTH, JTA_HEIGHT);

        final JTextArea jtaPlain = new JTextArea("");
        jtaPlain.setLineWrap(true);
        jtaPlain.setWrapStyleWord(true);

        final JScrollPane jspPlain = new JScrollPane(jtaPlain);
        jspPlain.setBounds((JTA_X * 2) + JTA_WIDTH, JTA_Y, JTA_WIDTH, JTA_HEIGHT);

        // set up the buttons, each with an action listener
        final JButton decryptButton = new JButton("decrypt");
        decryptButton.setBounds((WIDTH / 2) - (BUTTON_WIDTH / 2), BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        decryptButton.addActionListener(e -> {
            String text = jtaMorse.getText();
            jtaPlain.setText(myTree.convert(text));
            jtaMorse.setText("");
        });

        final JButton encryptButton = new JButton("encrypt");
        encryptButton.setBounds(
            (WIDTH / 2) - (BUTTON_WIDTH / 2),
            BUTTON_Y + BUTTON_HEIGHT + 20,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        final JButton clearButton = new JButton("clear");
        clearButton.setBounds(
            (WIDTH / 2) - (BUTTON_WIDTH / 2),
            BUTTON_Y + (2 * (BUTTON_HEIGHT + 20)),
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );
        clearButton.addActionListener(e -> {
            jtaPlain.setText("");
            jtaMorse.setText("");
        });

        // add the buttons and the text fields to the JFrame
        super.add(jspMorse);
        super.add(jspPlain);
        super.add(decryptButton);
        super.add(encryptButton);
        super.add(clearButton);
        super.add(draw);
    }
    //</editor-fold>


    /**
     * Lädt die Datei, in welcher die einzelnen Morse-Codes abgespeichert sind.
     */
    private void loadDatabase() {
        // open the input file for reading and writing
        try {
            database = new RandomAccessFile("International_Morse_Code.dat", "rw");
        } catch (FileNotFoundException fnf) {
            System.out.println("\n File not found.\n\n");
        }

        // read data from input file
        String entry = "";
        while (entry != null) {
            try {
                entry = database.readLine();
            } catch (IOException io) {
                System.out.println("\n\n Error reading the data file.\n\n");
            }
            if (entry != null) {
                Node n = myTree.getRoot();
                // The Node n points currently to the tree's root. It will shift to
                // the Node which shall contain the newly read String.

                // In every step the first letter in entry is saved in s
                // and entry is stripped off the first letter.

                // If s is a "." then the tree is continued to the left, otherwise
                // to the right. If that node does not yet exist, it is generated
                // as a new Node. The node n always points to the actual node in the
                // tree.

                // Once all .'s and _'s in the entry have been used, the actual node
                // n gets the remainder of entry as its new value.

                String s = entry.substring(0, 1);
                entry = entry.substring(1);
                while (!s.equals(" ")) {
                    if (s.equals(".")) {  // continue to the left
                        if (n.getLeft() == null) {
                            n.setLeft(new Node(n, null, null, ""));
                        }
                        n = n.getLeft();
                    }
                    if (s.equals("_")) {  // continue to the right
                        if (n.getRight() == null) {
                            n.setRight(new Node(n, null, null, ""));
                        }
                        n = n.getRight();
                    }
                    s = entry.substring(0, 1);
                    entry = entry.substring(1);
                }  // while( !s.equals(" ") )
                n.setValue(entry);
            }
        }
    }


    //<editor-fold desc="Draw">

    /**
     * Mithilfe eines {@link Draw} werden alle Grafiken auf das Fenster des {@link Converter} gezeichnet.
     */
    private static final class Draw extends JLabel {

        //<editor-fold desc="CONSTANTS">
        /** Die Standard-Schriftart dieses Fensters. */
        private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);
        /** Die Schriftart für die Überschrift in diesem Fenster. */
        private static final Font HEADER_FONT = DEFAULT_FONT.deriveFont(30F);
        /** Die Überschrift dieses Fensters. */
        private static final String HEADING = "Morse-Konverter";
        /** Die Größe des Abschnittes, in dem die Überschrift steht. */
        private static final int HEADER_SIZE = 70;
        /** Die Y-Koordinate der Button-Oberfläche. */
        private static final int BUTTON_RECT_Y = 280;
        /** Die Breite der Button-Oberfläche. */
        private static final int BUTTON_RECT_WIDTH = 150;
        /** Die Höhe der Button-Oberfläche. */
        private static final int BUTTON_RECT_HEIGHT = 170;
        //</editor-fold>


        //<editor-fold desc="implementation">
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            // define render quality
            final Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // draw basic background
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, super.getWidth(), super.getHeight());

            // draw converter background
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, HEADER_SIZE, super.getWidth(), super.getHeight() - HEADER_SIZE);

            // draw button rect
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(
                (super.getWidth() / 2) - (BUTTON_RECT_WIDTH / 2),
                BUTTON_RECT_Y,
                BUTTON_RECT_WIDTH,
                BUTTON_RECT_HEIGHT
            );

            // write heading
            g.setColor(Color.BLACK);
            g.setFont(HEADER_FONT);
            g.drawString(
                HEADING,
                (super.getWidth() / 2) - (g.getFontMetrics(HEADER_FONT).stringWidth(HEADING) / 2),
                (HEADER_SIZE / 2) + (HEADER_FONT.getSize() / 3)
            );

            // write field names
            g.setColor(Color.WHITE);
            g.setFont(DEFAULT_FONT);

            g.drawString(
                "Morsezeichen:",
                JTA_X,
                JTA_Y - DEFAULT_FONT.getSize()
            );

            g.drawString(
                "Normalschrift:",
                (JTA_X * 2) + JTA_WIDTH,
                JTA_Y - DEFAULT_FONT.getSize()
            );
        }
        //</editor-fold>
    }
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieses {@link Converter}, welche direkt von der JRE beim Start der Anwendung aufgerufen wird.
     *
     * @param args Die Argumente, die beim Starten dieser Anwendung von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        Converter app = new Converter();
        app.setVisible(true);
    }
    //</editor-fold>

}