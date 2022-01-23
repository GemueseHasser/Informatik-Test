package de.jonas.informatik.converter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Mithilfe des {@link ConverterKeyListener} werden alle neuen Zahleneingaben verarbeitet, die getätigt werden.
 */
public final class ConverterKeyListener implements KeyListener {

    //<editor-fold desc="LOCAL FIELDS">
    /** Das {@link ConverterField}, mit dem unmittelbar interagiert wird. */
    private final ConverterField current;
    /** Alle {@link ConverterField ConverterFields}, die nun formatiert werden sollen. */
    private final ConverterField[] fields;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz eines {@link ConverterKeyListener}, womit alle neuen Zahleneingaben verarbeitet werden,
     * die getätigt werden.
     *
     * @param current       Das {@link ConverterField}, mit dem unmittelbar interagiert wird.
     * @param overallFields Alle {@link ConverterField ConverterFields}, die nun formatiert werden sollen.
     */
    public ConverterKeyListener(
        final ConverterField current,
        final ConverterField[] overallFields
    ) {
        this.current = current;
        this.fields = overallFields;
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void keyTyped(final KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(final KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        for (final ConverterField field : this.fields) {
            if (this.current.getText().isEmpty()) {
                field.setText("");
                continue;
            }

            try {
                field.setText(field.getConverterFunction().convert(Integer.parseInt(
                    this.current.getText(),
                    this.current.getConverterFunction().getSystemIdentifier()
                )));
            } catch (final NumberFormatException ignored) {
                return;
            }
        }
    }
    //</editor-fold>
}
