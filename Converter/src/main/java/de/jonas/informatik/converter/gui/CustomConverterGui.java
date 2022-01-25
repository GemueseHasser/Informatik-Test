package de.jonas.informatik.converter.gui;

import de.jonas.informatik.converter.Converter;
import de.jonas.informatik.converter.ConverterField;
import de.jonas.informatik.converter.ConverterFunction;
import de.jonas.informatik.converter.ConverterKeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;

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
    /** Die Y-Koordinate aller Textfelder. */
    private static final int FIELD_Y = 100;
    /** Die Breite aller Textfelder. */
    private static final int FIELD_WIDTH = 200;
    /** Die Höhe aller Textfelder. */
    private static final int FIELD_HEIGHT = 30;
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
            20,
            FIELD_Y,
            FIELD_WIDTH,
            FIELD_HEIGHT,
            Converter.DECIMAL_FUNCTION
        );
        this.rightField = getFormattedConverterField(
            250,
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

        super.add(getFormattedBox(this.leftBox, 50));
        super.add(getFormattedBox(rightBox, 300));

        // set start value to '10'
        this.leftBox.setSelectedItem(BOX_SELECTED_ITEM);
        rightBox.setSelectedItem(BOX_SELECTED_ITEM);
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
     * Fügt einen {@link ConverterKeyListener} zu einem bestimmten {@link ConverterField} hinzu, welcher sich auf die
     * aktuellen Textfelder dieses Fensters bezieht.
     *
     * @param field Das {@link ConverterField}, zu dem ein {@link ConverterKeyListener} hinzugefügt werden soll.
     */
    private void addKeyListener(ConverterField field) {
        final ConverterField[] overallFields = {
            this.leftField,
            this.rightField,
        };

        field.addKeyListener(new ConverterKeyListener(
            field,
            overallFields
        ));
    }

    //<editor-fold desc="implementation">
    @Override
    public void draw(final Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    //</editor-fold>
}
