package de.jonas.informatik.converter.gui;

import de.jonas.informatik.converter.Converter;
import de.jonas.informatik.converter.ConverterField;
import de.jonas.informatik.converter.ConverterFunction;
import de.jonas.informatik.converter.ConverterKeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

/**
 * Ein {@link CustomConverterGui} stellt eine Instanz eines {@link AbstractGui} dar. In diesem Fenster können Zahlen mit
 * beliebigen Potenzen, also Zahlen aus beliebigen Zahlensystemen, in Zahlen anderer Zahlensysteme umgewandelt werden.
 * Somit kann man seine Konvertierung der Zahlen relativ frei auswählen und kann über die standards der Zahlensysteme,
 * die im {@link Gui} aufgeführt sind, hinaus konvertieren.
 */
public final class CustomConverterGui extends AbstractGui {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Individuelle Konvertierung";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 500;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 250;

    //<editor-fold desc="box">
    /** Die X-Koordinate der linken Box, mit der man das jeweilige Zahlensystem des linken Textfeldes wählen kann. */
    private static final int LEFT_BOX_X = 50;
    /** Die X-Koordinate der rechten Box, mit der man das jeweilige Zahlensystem des rechten Textfeldes wählen kann. */
    private static final int RIGHT_BOX_X = 300;
    /** Die Y-Koordinate aller Boxen, mit denen man das jeweilige Zahlensystem wählen kann. */
    private static final int BOX_Y = 50;
    /** Die Breite aller Boxen, mit denen man das jeweilige Zahlensystem wählen kann. */
    private static final int BOX_WIDTH = 75;
    /** Die Höhe aller Boxen, mit denen man das jeweilige Zahlensystem wählen kann. */
    private static final int BOX_HEIGHT = 35;
    /** Die maximale Auswahl aller Boxen, mit denen man das jeweilige Zahlensystem wählen kann. */
    private static final int BOX_SELECT_SIZE = 50;
    /** Das von Beginn ausgewählte Zahlensystem aller Boxen, mit denen man das jeweilige Zahlensystem wählen kann. */
    private static final int BOX_SELECTED_ITEM = 10;
    //</editor-fold>

    //<editor-fold desc="field">
    /** Die X-Koordinate des linken Feldes. */
    private static final int LEFT_FIELD_X = 20;
    /** Die X-Koordinate des rechten Feldes. */
    private static final int RIGHT_FIELD_X = 250;
    /** Die Y-Koordinate aller Textfelder. */
    private static final int FIELD_Y = 100;
    /** Die Breite aller Textfelder. */
    private static final int FIELD_WIDTH = 200;
    /** Die Höhe aller Textfelder. */
    private static final int FIELD_HEIGHT = 30;
    //</editor-fold>

    //<editor-fold desc="custom number-system">
    /** Der Text eines Buttons, mit dem man ein neues Zahlensystem laden kann. */
    private static final String CUSTOM_NUMBER_SYSTEM_TEXT = "» Individuelles System «";
    /** Die Y-Koordinate aller Buttons, mit denen man ein neues Zahlensystem laden kann. */
    private static final int CUSTOM_NUMBER_SYSTEM_Y = 150;
    /** Die Breite aller Buttons, mit denen man ein neues Zahlensystem laden kann. */
    private static final int CUSTOM_NUMBER_SYSTEM_WIDTH = 200;
    /** Die Höhe aller Buttons, mit denen man ein neues Zahlensystem laden kann. */
    private static final int CUSTOM_NUMBER_SYSTEM_HEIGHT = 30;
    /** Die Schriftgröße der Schrift aller Buttons, mit denen man ein neues Zahlensystem laden kann. */
    private static final float CUSTOM_NUMBER_FONT_SIZE = 10F;
    //</editor-fold>

    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die linke Auswahl-Box, mit der man das jeweilige Zahlensystem für das linke Feld auswählen kann. */
    private final JComboBox<Integer> leftBox = new JComboBox<>();
    /** Das linke Textfeld. */
    private final ConverterField leftField;
    /** Das rechte Textfeld. */
    private final ConverterField rightField;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link CustomConverterGui}. Ein {@link CustomConverterGui} stellt eine Instanz
     * eines {@link AbstractGui} dar. In diesem Fenster können Zahlen mit beliebigen Potenzen, also Zahlen aus
     * beliebigen Zahlensystemen, in Zahlen anderer Zahlensysteme umgewandelt werden. Somit kann man seine Konvertierung
     * der Zahlen relativ frei auswählen und kann über die standards der Zahlensysteme, die im {@link Gui} aufgeführt
     * sind, hinaus konvertieren.
     */
    public CustomConverterGui() {
        super(TITLE, WIDTH, HEIGHT);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create converter-fields
        this.leftField = getFormattedConverterField(
            LEFT_FIELD_X,
            FIELD_Y,
            FIELD_WIDTH,
            FIELD_HEIGHT,
            Converter.DECIMAL_FUNCTION
        );
        this.rightField = getFormattedConverterField(
            RIGHT_FIELD_X,
            FIELD_Y,
            FIELD_WIDTH,
            FIELD_HEIGHT,
            Converter.DECIMAL_FUNCTION
        );

        // add key listeners to created fields
        addKeyListener(this.leftField);
        addKeyListener(this.rightField);

        // add fields to gui
        super.add(this.leftField);
        super.add(this.rightField);

        // create combo-boxes and add them to the gui
        final JComboBox<Integer> rightBox = new JComboBox<>();

        super.add(getFormattedBox(this.leftBox, LEFT_BOX_X));
        super.add(getFormattedBox(rightBox, RIGHT_BOX_X));

        // set start value to '10'
        this.leftBox.setSelectedItem(BOX_SELECTED_ITEM);
        rightBox.setSelectedItem(BOX_SELECTED_ITEM);

        final JButton leftButton = getFormattedButton(20);
        leftButton.addActionListener(actionEvent -> {
            // load custom number-system
            loadCustomNumberSystem(this.leftField, this.leftBox);

            // format field instant
            if (!this.leftField.getText().isEmpty()) {
                this.leftField.setText(this.leftField.getConverterFunction().convert(Integer.parseInt(
                    this.rightField.getText(),
                    this.rightField.getConverterFunction().getSystemIdentifier()
                )));
            }

            // show success message
            JOptionPane.showMessageDialog(
                null,
                "Das Zahlensystem wurde erfolgreich geladen!",
                "Erfolg",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        final JButton rightButton = getFormattedButton(250);
        rightButton.addActionListener(actionEvent -> {
            // load custom number-system
            loadCustomNumberSystem(this.rightField, rightBox);

            // format field instant
            if (!this.rightField.getText().isEmpty()) {
                this.rightField.setText(this.rightField.getConverterFunction().convert(Integer.parseInt(
                    this.leftField.getText(),
                    this.leftField.getConverterFunction().getSystemIdentifier()
                )));
            }

            // show success message
            JOptionPane.showMessageDialog(
                null,
                "Das Zahlensystem wurde erfolgreich geladen!",
                "Erfolg",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        super.add(leftButton);
        super.add(rightButton);
    }
    //</editor-fold>


    /**
     * Gibt eine bereits fertig formatierte {@link JComboBox} zurück, mit der man dann ein beliebiges Zahlensystem
     * auswählen kann. Es werden alle nötigen Eigenschaften hinzugefügt. Zudem findet auch direkt die Registrierung des
     * {@link ConverterKeyListener} statt, damit die entsprechenden Textfelder direkt auf die getroffene Auswahl in
     * dieser Box vorkonfiguriert werden.
     *
     * @param box Die Instanz einer {@link JComboBox}, die vollständig formatiert zurückgegeben werden soll.
     * @param x   Die X-Koordinate dieser Box, an der sich diese befinden soll.
     *
     * @return Eine bereits fertig formatierte {@link JComboBox}, mit der man dann ein beliebiges Zahlensystem auswählen
     *     kann.
     */
    private JComboBox<Integer> getFormattedBox(final JComboBox<Integer> box, final int x) {
        // create combo-box-model
        final DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();

        // fill model
        for (int i = 1; i < BOX_SELECT_SIZE; i++) {
            model.addElement(i);
        }

        // set properties
        box.setBounds(x, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
        box.setOpaque(true);
        box.setBackground(Color.LIGHT_GRAY);
        box.setForeground(Color.BLACK);
        box.setModel(model);
        box.addActionListener(actionEvent -> {
            // check if selected item is null
            if (box.getSelectedItem() == null) return;

            // get selected item
            final int selectedItem = (int) box.getSelectedItem();

            // set corresponding converter-function
            if (box.equals(this.leftBox)) {
                this.leftField.setConverterFunction(
                    new ConverterFunction(selectedItem, null)
                );

                // format field instant
                if (!this.leftField.getText().isEmpty()) {
                    this.leftField.setText(this.leftField.getConverterFunction().convert(Integer.parseInt(
                        this.rightField.getText(),
                        this.rightField.getConverterFunction().getSystemIdentifier()
                    )));
                }
                return;
            }

            this.rightField.setConverterFunction(
                new ConverterFunction(selectedItem, null)
            );

            // format field instant
            if (!this.rightField.getText().isEmpty()) {
                this.rightField.setText(this.rightField.getConverterFunction().convert(Integer.parseInt(
                    this.leftField.getText(),
                    this.leftField.getConverterFunction().getSystemIdentifier()
                )));
            }
        });

        return box;
    }

    /**
     * Erzeugt einen fertig konfigurierten Button.
     *
     * @param x Die X-Koordinate, die dieser Button haben soll.
     *
     * @return Einen fertig konfigurierten Button.
     */
    private JButton getFormattedButton(final int x) {
        final JButton button = new JButton(CUSTOM_NUMBER_SYSTEM_TEXT);
        button.setBounds(x, CUSTOM_NUMBER_SYSTEM_Y, CUSTOM_NUMBER_SYSTEM_WIDTH, CUSTOM_NUMBER_SYSTEM_HEIGHT);
        button.setFont(DEFAULT_FONT.deriveFont(CUSTOM_NUMBER_FONT_SIZE));
        button.setFocusable(false);
        button.setOpaque(true);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);

        return button;
    }

    /**
     * Fügt einen {@link ConverterKeyListener} zu einem bestimmten {@link ConverterField} hinzu, welcher sich auf die
     * aktuellen Textfelder dieses Fensters bezieht.
     *
     * @param field Das {@link ConverterField}, zu dem ein {@link ConverterKeyListener} hinzugefügt werden soll.
     */
    private void addKeyListener(final ConverterField field) {
        final ConverterField[] overallFields = {
            this.leftField,
            this.rightField,
        };

        field.addKeyListener(new ConverterKeyListener(
            field,
            overallFields
        ));
    }

    /**
     * Lädt ein bestimmtes Zahlensystem, welches zur Konvertierung genutzt wird.
     *
     * @param field Das Textfeld, welches dieses Zahlensystem injiziert bekommt, sodass eine Konvertierung mit diesen
     *              Zahlen stattfinden kann.
     * @param box   Die Box, welche die Potenz des Zahlensystems enthält.
     */
    private void loadCustomNumberSystem(final ConverterField field, final JComboBox<Integer> box) {
        final int selectedItem = (box.getSelectedItem() == null) ? 0 : (int) box.getSelectedItem();

        final Map<Integer, String> numberSystem = showNumberSystemInputDialog(selectedItem);

        if (numberSystem == null) {
            JOptionPane.showMessageDialog(
                null,
                "Beim Laden ist ein Fehler aufgetreten!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        field.setConverterFunction(new ConverterFunction(
            field.getConverterFunction().getSystemIdentifier(),
            numberSystem
        ));
    }

    /**
     * Öffnet dem Nutzer ein Fenster, wo er sein individuelles Zahlensystem eingeben kann. Dieses wird erst als ein
     * gültiges System erkannt, wenn jedes Feld ausgefüllt wurde.
     *
     * @param entryAmount Die Anzahl an Einträgen, die dieses Fenster haben soll.
     *
     * @return Das Zahlensystem, welches der Nutzer ausgewählt hat bzw. einfach {@code null}, wenn die Eingabe ungültig
     *     war.
     */
    private Map<Integer, String> showNumberSystemInputDialog(final int entryAmount) {
        final Map<Integer, String> numberSystem = new HashMap<>();

        final Object[] messageEntries = new Object[entryAmount * 2];

        for (int i = 0; i < entryAmount * 2; i++) {
            if (i % 2 == 0) {
                messageEntries[i] = String.valueOf(i / 2);
                continue;
            }

            messageEntries[i] = new JTextField();
        }

        final int option = JOptionPane.showConfirmDialog(
            null,
            messageEntries,
            "Individuelles System",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (option != JOptionPane.OK_OPTION) return null;

        for (int i = 0; i < entryAmount * 2; i++) {
            if (!(messageEntries[i] instanceof JTextField)) continue;

            final JTextField field = (JTextField) messageEntries[i];

            if (field.getText().isEmpty()) return null;

            numberSystem.put(i / 2, field.getText());
        }

        return numberSystem;
    }

    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    //</editor-fold>
}
