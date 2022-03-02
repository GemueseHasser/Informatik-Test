package de.jonas.informatik;

import de.jonas.informatik.encryptor.Gui;

/**
 * <p>Die Haupt- und Main-Klasse der Anwendung. Der gesamte {@link Encryptor} wird in dieser Klasse instanziiert und
 * initialisiert.</p>
 *
 * <p>Ein {@link Encryptor} wird zum Verschlüsseln eines Textes genutzt. Man kann also einen Text eingeben und
 * diesen wieder verschlüsselt ausgeben lassen. Umlaute werden auch verschlüsselt, jedoch in normalen Buchstaben
 * ausgegeben, also ein 'ä' wird beispielsweise zu einem 'ae'.</p>
 */
public final class Encryptor {

    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, in der die gesamte Anwendung instanziiert und initialisiert wird und welche
     * als allererstes von der JRE direkt aufgerufen wird.
     *
     * @param args Die Argumente, die beim Starten der Anwendung von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        // create new gui
        new Gui();
    }
    //</editor-fold>

}
