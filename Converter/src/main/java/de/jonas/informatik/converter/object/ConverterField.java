package de.jonas.informatik.converter.object;

import de.jonas.informatik.converter.handler.ConverterFunction;

import javax.swing.JTextField;

/**
 * Ein {@link ConverterField} ist ein erweitertes {@link JTextField}, welches zusätzlich eine {@link ConverterFunction}
 * beinhaltet.
 */
public final class ConverterField extends JTextField {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link ConverterFunction} dieses Textfeldes. */
    private ConverterFunction converterFunction;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe einer {@link ConverterFunction} eine neue und vollständig unabhängige Instanz eines {@link
     * ConverterField}. Ein {@link ConverterField} ist ein erweitertes {@link JTextField}, welches zusätzlich eine
     * {@link ConverterFunction} beinhaltet.
     *
     * @param converterFunction Die {@link ConverterFunction}, mit der dieses {@link ConverterField} ausgestattet werden
     *                          soll.
     */
    public ConverterField(final ConverterFunction converterFunction) {
        this.converterFunction = converterFunction;
    }
    //</editor-fold>


    /**
     * Gibt die {@link ConverterFunction} dieses {@link ConverterField} zurück.
     *
     * @return Die {@link ConverterFunction} dieses {@link ConverterField}.
     */
    public ConverterFunction getConverterFunction() {
        return this.converterFunction;
    }

    /**
     * Setzt die {@link ConverterFunction} dieses {@link ConverterField} neu, mit der dieses {@link ConverterField}
     * konvertiert bzw. übersetzt werden soll.
     *
     * @param function Die neue {@link ConverterFunction}, mit der dieses {@link ConverterField} konvertiert bzw.
     *                 übersetzt werden soll.
     */
    public void setConverterFunction(final ConverterFunction function) {
        this.converterFunction = function;
    }

}
