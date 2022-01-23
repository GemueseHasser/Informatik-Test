package de.jonas.informatik.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mit einem {@link Converter} lassen sich Zahlen aus bestimmten Zahlensystemen in Zahlen aus anderen Zahlensystemen
 * übersetzen bzw. konvertieren.
 */
public final class Converter {

    //<editor-fold desc="CONSTANTS">
    /** Alle Zahlen, die im Hexadezimalsystem vorkommen mit Bezug auf das Dezimalsystem. */
    public static final Map<Integer, String> HEX_SYSTEM = new HashMap<>();
    /** Die Funktion, womit sich Dezimalzahlen konvertieren lassen. */
    public static final ConverterFunction DECIMAL_FUNCTION = new ConverterFunction(10, null);
    /** Die Funktion, womit sich Binärzahlen konvertieren lassen. */
    public static final ConverterFunction BINARY_FUNCTION = new ConverterFunction(2, null);
    /** Die Funktion, womit sich Oktalzahlen konvertieren lassen. */
    public static final ConverterFunction OCTAL_FUNCTION = new ConverterFunction(8, null);
    /** Die Funktion, womit sich Hexadezimalzahlen konvertieren lassen. */
    public static final ConverterFunction HEX_FUNCTION = new ConverterFunction(16, HEX_SYSTEM);
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, die vor allen anderen Methoden als allererstes von der JRE aufgerufen wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        // fill hex system map
        HEX_SYSTEM.put(0, "0");
        HEX_SYSTEM.put(1, "1");
        HEX_SYSTEM.put(2, "2");
        HEX_SYSTEM.put(3, "3");
        HEX_SYSTEM.put(4, "4");
        HEX_SYSTEM.put(5, "5");
        HEX_SYSTEM.put(6, "6");
        HEX_SYSTEM.put(7, "7");
        HEX_SYSTEM.put(8, "8");
        HEX_SYSTEM.put(9, "9");
        HEX_SYSTEM.put(10, "A");
        HEX_SYSTEM.put(11, "B");
        HEX_SYSTEM.put(12, "C");
        HEX_SYSTEM.put(13, "D");
        HEX_SYSTEM.put(14, "E");
        HEX_SYSTEM.put(15, "F");

        // open gui
        final Gui gui = new Gui();
        gui.open();
    }
    //</editor-fold>

}
