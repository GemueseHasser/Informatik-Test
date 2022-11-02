package de.jonas.informatik.linkedlist;

/**
 * Ein {@link Player} stellt einen Spieler dar, welcher vom {@link Transfermarkt2} verarbeitet wird. Ein
 * {@link Player Spieler} hat einen Namen, einen Wert und ein Team. Diese Eigenschaften werden im Konstruktor übergeben,
 * können jedoch im Nachhinein mittels Getter und Setter geändert und abgerufen werden.
 */
public final class Player {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der Name des Spielers. */
    private String name;
    /** Der Wert des Spielers. */
    private double value;
    /** Das Team des Spielers. */
    private String team;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Player Spielers}. Ein {@link Player} stellt
     * einen Spieler dar, welcher vom {@link Transfermarkt2} verarbeitet wird. Ein {@link Player Spieler} hat einen
     * Namen, einen Wert und ein Team. Diese Eigenschaften werden im Konstruktor übergeben, können jedoch im Nachhinein
     * mittels Getter und Setter geändert und abgerufen werden.
     *
     * @param name  Der Name des Spielers.
     * @param value Der Wert des Spielers.
     * @param team  Das Team des Spielers.
     */
    public Player(
        final String name,
        final double value,
        final String team
    ) {
        this.name = name;
        this.value = value;
        this.team = team;
    }
    //</editor-fold>


    /**
     * Gibt die Eigenschaften dieses Spielers in der Konsole aus.
     */
    public void print() {
        System.out.println(this);
    }

    //<editor-fold desc="getter">

    /**
     * Gibt den Namen dieses Spielers zurück.
     *
     * @return Der Name dieses Spielers.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt den Wert dieses Spielers zurück.
     *
     * @return Der Wert dieses Spielers.
     */
    public double getValue() {
        return value;
    }

    /**
     * Gibt das Team dieses Spielers zurück.
     *
     * @return Das Team dieses Spielers.
     */
    public String getTeam() {
        return team;
    }
    //</editor-fold>

    //<editor-fold desc="setter">

    /**
     * Setzt den Namen dieses Spielers neu.
     *
     * @param name Der neue Name des Spielers.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setzt den Wert dieses Spielers neu.
     *
     * @param value Der neue Wert dieses Spielers.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Setzt das Team dieses Spielers neu.
     *
     * @param team Das neue Team dieses Spielers.
     */
    public void setTeam(String team) {
        this.team = team;
    }
    //</editor-fold>

    //<editor-fold desc="implementation">
    @Override
    public String toString() {
        return "Name: " + name + "\nWert: " + value + "\nTeam: " + team;
    }
    //</editor-fold>

}
