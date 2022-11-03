package de.jonas.informatik.linkedlist;
// Klaus Wiele September 2022

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Ein {@link Transfermarkt2} stellt eine Instanz eines {@link JFrame} dar, ist also eine grafische Oberfläche, die dazu
 * genutzt werden kann, um eine {@link LinkedList} mit {@link Player Spielern} zu füllen, zu sortieren, zu verwalten und
 * auszugeben.
 */
public class Transfermarkt2 extends JFrame {

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
        final JTextField jtfName = new JTextField("name");
        final JTextField jtfValue = new JTextField("value");
        final JTextField jtfTeam = new JTextField("team");

        // set up the output field
        // The JTextArea needs a surrounding JScrollPane for scrolling.
        final JTextArea jtaOutput = new JTextArea("");
        jtaOutput.setLineWrap(true);
        jtaOutput.setWrapStyleWord(true);

        final JScrollPane jspScroll = new JScrollPane(jtaOutput);

        // set up the buttons, each with an action listener
        final JButton jbPrint = new JButton("print");
        jbPrint.addActionListener(e -> jtaOutput.setText(myList.toString()));

        final JButton jbClear = new JButton("clear");
        jbClear.addActionListener(e -> {
            jtfName.setText("name");
            jtfValue.setText("value");
            jtfTeam.setText("team");
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
                jtfName.setText("name");
                jtfValue.setText("value");
                jtfTeam.setText("team");
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
        jbSortAlph.addActionListener(e -> myList.sort(new CompAlpha()));

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
        this.add(jspScroll);
        this.add(jpButtons);

        // open the input file for reading and writing
        try {
            database = new RandomAccessFile("Spielerdaten.dat", "rw");
        } catch (final FileNotFoundException fnf) {
            System.out.println("\n File not found.\n\n");
            jtaOutput.setText("I cannot find the input file.");
        }

        // read data from input file
        boolean eof = false;
        // Are we at the end of the file?
        while (!eof) {
            String name = "";
            double value = -1.;
            String team = "";
            try {
                name = database.readLine();
                String s = database.readLine();
                if (s != null) {
                    value = Double.parseDouble(s.substring(6));
                }
                team = database.readLine();
                database.readLine();
            } catch (IOException io) {
                System.out.println("\n\n Error reading the data file.\n\n");
                jtaOutput.setText("Error reading the data file.");
            }
            if (name == null || value == -1. || team == null) {
                // We have reached the end of the file.
                eof = true;
            } else {
                final Player p = new Player(name, value, team);
                myList.append(p);
            }
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
        t.setResizable(false);
        t.setTitle("Transfermarkt");
        t.setVisible(true);
    }
    //</editor-fold>

}