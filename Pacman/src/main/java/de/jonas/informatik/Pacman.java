package de.jonas.informatik;

import de.jonas.informatik.graphic.Gui;

/**
 * Dies ist die Haupt- und Main-Klasse dieser Anwendung bzw. dieses Pacman-Spiels. Das Spiel ähnelt dem herkömmlichen
 * Pacman, weshalb es Pacman heißt. Man muss sich durch logisches Verschieben von Objekten den Weg zum Ziel erarbeiten.
 */
public class Pacman {

    //<editor-fold desc="main">

    /**
     * Die Main-Methode dieser Anwendung, welche vor allen anderen Klassen und Methoden direkt von der JRE beim Starten
     * der Anwendung aufgerufen und ausgeführt wird.
     *
     * @param args Die Argumente, die beim Starten der Anwendung von der JRE mitgegeben werden.
     */
    public static void main(final String[] args) {
        // create gui
        new Gui();
    }
    //</editor-fold>

}
