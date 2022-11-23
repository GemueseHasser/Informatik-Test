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
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

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
    /** Der Dateiname, unter dem die Morse-Codes zu finden sind. */
    private static final String INTERNATIONAL_MORSE_CODE_PROPERTIES = "InternationalMorseCode.properties";
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link Tree Baumstruktur}, die zum Übersetzen genutzt wird. */
    private final Tree myTree = new Tree();
    /** Die {@link Properties}, die die Morse-Codes beinhalten. */
    private final Properties morseCodes = new Properties();
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

        // load morse codes properties
        try {
            this.morseCodes.load(new FileReader(INTERNATIONAL_MORSE_CODE_PROPERTIES));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        // load morse code tree
        loadMorseCodeTree();

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

        // set up the buttons
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

        // add the components to the frame
        super.add(jspMorse);
        super.add(jspPlain);
        super.add(decryptButton);
        super.add(encryptButton);
        super.add(clearButton);
        super.add(draw);
    }
    //</editor-fold>


    /**
     * Lädt den {@link Tree Baum}, in welchem die Morse-Codes abgespeichert werden sollen.
     */
    private void loadMorseCodeTree() {
        for (final Map.Entry<Object, Object> morseCodeEntry : this.morseCodes.entrySet()) {
            // get root node
            Node node = myTree.getRoot();

            // get key and value from entry
            final String letter = (String) morseCodeEntry.getKey();
            final String morseCode = (String) morseCodeEntry.getValue();

            // split string into characters and sort them into the tree
            for (int i = 0; i < morseCode.length(); i++) {
                if (morseCode.charAt(i) == '.') {
                    if (node.getLeft() == null) node.setLeft(new Node(node, null, null, ""));

                    node = node.getLeft();
                }

                if (morseCode.charAt(i) == '_') {
                    if (node.getRight() == null) node.setRight(new Node(node, null, null, ""));

                    node = node.getRight();
                }
            }

            // set the value at this position into the tree
            node.setValue(letter);
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