package de.jonas.informatik.linkedlist;
// Klaus Wiele September 2022

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Ein {@link Transfermarkt2} stellt eine Instanz eines {@link JFrame} dar, ist also eine grafische Oberfläche, die dazu
 * genutzt werden kann, um eine {@link LinkedList} mit {@link Player Spielern} zu füllen, zu sortieren, zu verwalten und
 * auszugeben.
 */
public class Transfermarkt2 extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Standard-Text, der für das Textfeld des Namens hinterlegt wird. */
    private static final String JTF_NAME_TEXT = "name";
    /** Der Standard-Text, der für das Textfeld des Wertes hinterlegt wird. */
    private static final String JTF_VALUE_TEXT = "value";
    /** Der Standard-Text, der für das Textfeld des Teams hinterlegt wird. */
    private static final String JTF_TEAM_TEXT = "team";
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die Datei, in der die bisher angelegte Liste zwischengespeichert werden kann. */
    private RandomAccessFile database;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">#

    /**
     * Erzeugt eine neue Instanz eines {@link Transfermarkt2}. Ein {@link Transfermarkt2} stellt eine Instanz eines
     * {@link JFrame} dar, ist also eine grafische Oberfläche, die dazu genutzt werden kann, um eine {@link LinkedList}
     * mit {@link Player Spielern} zu füllen, zu sortieren, zu verwalten und auszugeben.
     */
    public Transfermarkt2() {
        // create list with players
        final LinkedList<Player> myList = new LinkedList<>();

        // set up the text fields
        final JTextField jtfName = new JTextField(JTF_NAME_TEXT);
        final JTextField jtfValue = new JTextField(JTF_VALUE_TEXT);
        final JTextField jtfTeam = new JTextField(JTF_TEAM_TEXT);

        // set up the text-fields, each with a focus listener
        jtfName.addFocusListener(new TextFieldFocusListener(JTF_NAME_TEXT));
        jtfValue.addFocusListener(new TextFieldFocusListener(JTF_VALUE_TEXT));
        jtfTeam.addFocusListener(new TextFieldFocusListener(JTF_TEAM_TEXT));

        // set up the output field
        // The JTextArea needs a surrounding JScrollPane for scrolling.
        final JTextArea jtaOutput = new JTextArea("");
        jtaOutput.setDisabledTextColor(Color.BLACK);
        jtaOutput.setEnabled(false);
        jtaOutput.setLineWrap(true);
        jtaOutput.setWrapStyleWord(true);

        // set up the buttons, each with an action listener
        final JButton jbPrint = new JButton("print");
        jbPrint.addActionListener(e -> jtaOutput.setText(myList.toString()));

        final JButton jbClear = new JButton("clear");
        jbClear.addActionListener(e -> {
            jtfName.setText(JTF_NAME_TEXT);
            jtfValue.setText(JTF_VALUE_TEXT);
            jtfTeam.setText(JTF_TEAM_TEXT);
            jtaOutput.setText("");
        });

        final JButton jbAppend = new JButton("append");
        jbAppend.addActionListener(e -> {
            String name = jtfName.getText();
            double value = -1.;
            try {
                value = Double.parseDouble(jtfValue.getText());
            } catch (final NumberFormatException nf) {
                jtaOutput.setText("I cannot read the player's value.");
            }
            String team = jtfTeam.getText();
            if (value >= 0) {
                final Player player = new Player(name, value, team);
                myList.append(player);
                jtfName.setText(JTF_NAME_TEXT);
                jtfValue.setText(JTF_VALUE_TEXT);
                jtfTeam.setText(JTF_TEAM_TEXT);
            }
        });

        final JButton jbSave = new JButton("save");
        jbSave.addActionListener(e -> {
            try {
                // delete all content
                database.setLength(0);
                // write the new data
                database.writeBytes(myList.toString());
            } catch (IOException io) {
                jtaOutput.setText("I cannot save the data.");
            }
        });

        final JButton jbSortAlph = new JButton("sort by alphabet");
        jbSortAlph.addActionListener(e -> {
            jtaOutput.setText("sort by alphabet.");
            myList.sort(new CompAlpha());
        });

        // All buttons are grouped in one JPanel.
        final JPanel jpButtons = new JPanel(new FlowLayout());
        jpButtons.add(jbAppend);
        jpButtons.add(jbPrint);
        jpButtons.add(jbClear);
        jpButtons.add(jbSave);
        jpButtons.add(jbSortAlph);

        // add the buttons and the text fields to the JFrame
        this.setLayout(new GridLayout(5, 1));
        this.add(jtfName);
        this.add(jtfValue);
        this.add(jtfTeam);
        this.add(new JScrollPane(jtaOutput));
        this.add(jpButtons);

        // open the input file for reading and writing
        try {
            database = new RandomAccessFile("Spielerdaten.dat", "rw");
        } catch (final FileNotFoundException fnf) {
            System.out.println("\n File not found.\n\n");
            jtaOutput.setText("I cannot find the input file.");
        }

        // read data from input file
        while (true) {
            String nameLine = "";
            String valueLine = "";
            String teamLine = "";

            try {
                nameLine = database.readLine();
                valueLine = database.readLine();
                teamLine = database.readLine();

                database.readLine();
            } catch (final IOException ignored) {
                System.out.println("\n\n Error reading the data file.\n\n");
                jtaOutput.setText("Error reading the data file.");
            }

            if (nameLine == null || valueLine == null || teamLine == null) {
                // We have reached the end of the file.
                break;
            }

            final String name = nameLine.substring(6);
            final double value = Double.parseDouble(valueLine.substring(6));
            final String team = teamLine.substring(6);

            final Player p = new Player(name, value, team);

            myList.append(p);
        }
    }
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode, womit diese grafische Benutzeroberfläche geöffnet wird und der Nutzer mithilfe dieses
     * {@link Transfermarkt2} die Liste mit den Spielern verwalten kann.
     *
     * @param args Die Argumente, die von der JRE beim Start des Programms übergeben werden.
     */
    public static void main(final String[] args) {
        Transfermarkt2 t = new Transfermarkt2();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setBounds(0, 0, 600, 600);
        t.setLocationRelativeTo(null);
        t.setAutoRequestFocus(false);
        t.setResizable(false);
        t.setTitle("Transfermarkt");
        t.setVisible(true);
    }
    //</editor-fold>


    //<editor-fold desc="TextFieldFocusListener">

    /**
     * Mithilfe eines {@link TextFieldFocusListener} lässt sich der Hintergrund-Text eines {@link JTextField} bei
     * einfachem Fokussieren entfernen und sollte der Fokus entfernt werden, wenn das {@link JTextField} leer ist, wird
     * der Hintergrund-Text wieder gesetzt.
     */
    private static final class TextFieldFocusListener implements FocusListener {

        //<editor-fold desc="LOCAL FIELDS">
        /** Der Hintergrund-Text, der immer angezeigt werden soll. */
        private final String backgroundText;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link FocusListener}. Mithilfe eines
         * {@link TextFieldFocusListener} lässt sich der Hintergrund-Text eines {@link JTextField} bei einfachem
         * Fokussieren entfernen und sollte der Fokus entfernt werden, wenn das {@link JTextField} leer ist, wird der
         * Hintergrund-Text wieder gesetzt.
         *
         * @param backgroundText Der Hintergrund-Text, der immer angezeigt werden soll.
         */
        public TextFieldFocusListener(final String backgroundText) {
            this.backgroundText = backgroundText;
        }
        //</editor-fold>


        //<editor-fold desc="implementation">
        @Override
        public void focusGained(final FocusEvent e) {
            if (!(e.getSource() instanceof JTextField)) return;

            final JTextField source = (JTextField) e.getSource();
            if (source.getText().equalsIgnoreCase(this.backgroundText)) source.setText("");
        }

        @Override
        public void focusLost(final FocusEvent e) {
            if (!(e.getSource() instanceof JTextField)) return;

            final JTextField source = (JTextField) e.getSource();
            if (source.getText().trim().equalsIgnoreCase("")) source.setText(this.backgroundText);
        }
        //</editor-fold>
    }
    //</editor-fold>

}