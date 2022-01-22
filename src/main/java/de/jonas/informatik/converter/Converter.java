package de.jonas.informatik.converter;

/**
 * Mit einem {@link Converter} lassen sich Zahlen aus bestimmten Zahlensystemen in Zahlen aus anderen Zahlensystemen
 * übersetzen bzw. konvertieren.
 */
public final class Converter {

    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, die vor allen anderen Methoden als allererstes von der JRE aufgerufen wird.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        final Gui gui = new Gui();
        gui.open();
    }
    //</editor-fold>

}
