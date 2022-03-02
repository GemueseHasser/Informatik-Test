package de.jonas.informatik.encryptor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar. Dieses {@link Gui} wird dem Nutzer sofort geöffnet, es
 * ist also das Fenster, worin sich der grundlegende {@link de.jonas.informatik.Encryptor} befindet.
 */
public final class Gui extends JFrame {

    //<editor-fold desc="CONSTANTS">
    /** Der Titel dieses Fensters. */
    private static final String TITLE = "Encryptor";
    /** Die Breite dieses Fensters. */
    private static final int WIDTH = 600;
    /** Die Höhe dieses Fensters. */
    private static final int HEIGHT = 500;
    /** Die Standard-Schriftart, welche in diesem Fenster genutzt wird. */
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);

    //<editor-fold desc="field">
    /** Die X-Koordinate aller Textfelder. */
    private static final int FIELD_X = 50;
    /** Die Breite aller Textfelder. */
    private static final int FIELD_WIDTH = 500;
    /** Die Höhe aller Textfelder. */
    private static final int FIELD_HEIGHT = 50;
    //</editor-fold>

    //<editor-fold desc="key-box">
    /** Die X-Koordinate der Box, womit man den aktuellen Schlüssel wählen kann. */
    private static final int KEY_BOX_X = 50;
    /** Die Y-Koordinate der Box, womit man den aktuellen Schlüssel wählen kann. */
    private static final int KEY_BOX_Y = 350;
    /** Die Breite der Box, womit man den aktuellen Schlüssel wählen kann. */
    private static final int KEY_BOX_WIDTH = 75;
    /** Die Höhe der Box, womit man den aktuellen Schlüssel wählen kann. */
    private static final int KEY_BOX_HEIGHT = 40;
    //</editor-fold>

    //<editor-fold desc="encryption-button">
    /** Der Text, welchen der Button trägt, mit dem man den Text verschlüsseln kann. */
    private static final String ENCRYPTION_BUTTON_TEXT = "Verschlüsseln";
    /** Die X-Koordinate des Buttons, womit man den Text verschlüsseln kann. */
    private static final int ENCRYPTION_BUTTON_X = 200;
    /** Die Y-Koordinate des Buttons, womit man den Text verschlüsseln kann. */
    private static final int ENCRYPTION_BUTTON_Y = 350;
    /** Die Breite des Buttons, womit man den Text verschlüsseln kann. */
    private static final int ENCRYPTION_BUTTON_WIDTH = 300;
    /** Die Höhe des Buttons, womit man den Text verschlüsseln kann. */
    private static final int ENCRYPTION_BUTTON_HEIGHT = 40;
    //</editor-fold>

    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der aktuell ausgewählte Schlüssel, mit dem der Text verschlüsselt werden soll. */
    private int currentKey = 1;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link Gui}. Ein {@link Gui} stellt eine Instanz eines {@link JFrame} dar. Dieses
     * {@link Gui} wird dem Nutzer sofort geöffnet, es ist also das Fenster, worin sich der grundlegende {@link
     * de.jonas.informatik.Encryptor} befindet.
     */
    public Gui() {
        // create instance
        super(TITLE);

        // set properties
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0, 0, WIDTH, HEIGHT);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setLayout(null);

        // create draw-object
        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        // create text fields
        final JTextField defaultField = getFormattedTextField(60, true);
        final JTextField encryptionField = getFormattedTextField(200, false);

        // create key box
        final JComboBox<Integer> keyBox = new JComboBox<>();
        keyBox.setBounds(KEY_BOX_X, KEY_BOX_Y, KEY_BOX_WIDTH, KEY_BOX_HEIGHT);
        setBasicProperties(keyBox);

        // create combo-box-model
        final DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();

        // fill model
        for (int i = 1; i < 26; i++) {
            model.addElement(i);
        }

        keyBox.setModel(model);
        keyBox.addActionListener(actionEvent -> {
            if (keyBox.getSelectedItem() == null) {
                currentKey = 26;
                return;
            }

            currentKey = (int) keyBox.getSelectedItem();
        });

        // create encryption-button
        final JButton encryptionButton = new JButton(ENCRYPTION_BUTTON_TEXT);
        encryptionButton.setBounds(
            ENCRYPTION_BUTTON_X,
            ENCRYPTION_BUTTON_Y,
            ENCRYPTION_BUTTON_WIDTH,
            ENCRYPTION_BUTTON_HEIGHT
        );
        encryptionButton.setFont(DEFAULT_FONT);
        setBasicProperties(encryptionButton);
        encryptionButton.addActionListener(actionEvent -> {
            final String defaultText = defaultField.getText();
            final String encryptedText = EncryptionHandler.encrypt(defaultText, this.currentKey);

            encryptionField.setText(encryptedText);
        });

        // add components
        super.add(defaultField);
        super.add(encryptionField);
        super.add(keyBox);
        super.add(encryptionButton);
        super.add(draw);

        // open gui
        super.setVisible(true);
    }
    //</editor-fold>


    /**
     * Erzeugt ein vollständig formatiertes {@link JTextField} und gibt dieses zurück.
     *
     * @param y       Die Y-Koordinate dieses Textfeldes.
     * @param enabled Der Zustand, ob man in das Feld schreiben darf.
     *
     * @return Ein neues und vollständig formatiertes {@link JTextField}.
     */
    private JTextField getFormattedTextField(
        final int y,
        final boolean enabled
    ) {
        final JTextField field = new JTextField();
        field.setBounds(FIELD_X, y, FIELD_WIDTH, FIELD_HEIGHT);
        field.setFont(DEFAULT_FONT.deriveFont(16F));
        setBasicProperties(field);
        field.setFocusable(true);
        field.setEnabled(enabled);
        field.setDisabledTextColor(Color.BLACK);

        return field;
    }

    /**
     * Legt die grundlegenden Eigenschaften eines {@link JComponent} fest.
     *
     * @param component Der {@link JComponent}, dessen grundlegenden Eigenschaften festgelegt werden.
     */
    private void setBasicProperties(final JComponent component) {
        component.setOpaque(true);
        component.setBackground(Color.LIGHT_GRAY);
        component.setForeground(Color.BLACK);
        component.setFocusable(false);
    }


    //<editor-fold desc="Draw">

    /**
     * Das Zeichen-Objekt, womit alle nötigen Grafiken auf dieses {@link Gui} gezeichnet werden.
     */
    private static final class Draw extends JLabel {

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, super.getWidth(), super.getHeight());

            g.setFont(DEFAULT_FONT);
            g.setColor(Color.WHITE);

            g.drawString("Texteingabe:", 50, 40);
            g.drawString("Verschlüsselte Ausgabe:", 50, 180);
        }
    }
    //</editor-fold>

}
