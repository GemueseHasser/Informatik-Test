package de.jonas.informatik.tree;

/**
 * Ein {@link Tree} stellt eine Baumstruktur dar, in welcher Morsecode gespeichert wird durch den {@link Converter}. Es
 * gibt die Möglichkeit Morsecode in Text zu übersetzen, welcher dann aus der Baumstruktur, bestehend aus
 * {@link Node Knotenpunkten}, gefiltert wird.
 */
public final class Tree {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der erste Knotenpunkt, von dem aus die Baumstruktur entfaltet wird. */
    private final Node root = new Node(null, null, null, "");
    //</editor-fold>


    /**
     * Konvertiert einen Morsecode in lesbaren Text und gibt diesen zurück.
     *
     * @param code Der Morsecode, welcher in lesbaren Text übersetzt werden soll.
     *
     * @return Der in lesbaren Text konvertierte Morsecode.
     */
    public String convert(final String code) {
        String tempCode = code.replaceAll(" ", "|");
        final StringBuilder plainBuilder = new StringBuilder();

        while (tempCode.contains("|")) {
            for (int i = 0; i < tempCode.length(); i++) {
                if (tempCode.charAt(i) != '|') continue;

                plainBuilder.append(this.root.get(tempCode.substring(0, i)));
                tempCode = tempCode.substring(i + 1);
                break;
            }
        }

        return plainBuilder.toString();
    }

    /**
     * Gibt die gesamte Baumstruktur aus.
     */
    public void print() {
        this.root.print();
    }

    /**
     * Gibt den Knotenpunkt zurück, von dem aus die Baumstruktur entfaltet wird, also den ersten Knotenpunkt.
     *
     * @return Der erste Knotenpunkt, von dem aus die Baumstruktur entfaltet wird.
     */
    public Node getRoot() {
        return this.root;
    }

}
