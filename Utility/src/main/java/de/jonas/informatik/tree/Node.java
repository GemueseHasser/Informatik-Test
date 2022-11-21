package de.jonas.informatik.tree;

/**
 * Eine {@link Node} stellt einen beliebigen Knotenpunkt in einer Baumstruktur dar. In einer Node wird der aktuelle
 * Wert, der vorherige Knoten und der linke und rechte abzweigende Knoten abgespeichert.
 */
public final class Node {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der vorherige Knotenpunkt der Baumstruktur. */
    private Node pre;
    /** Der linke nachfolgende Knotenpunkt der Baumstruktur. */
    private Node left;
    /** Der rechte nachfolgende Knotenpunkt der Baumstruktur. */
    private Node right;
    /** Der aktuelle Wert dieses Knotenpunktes. */
    private String value;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Node Knotenpunktes} einer Baumstruktur. Eine
     * {@link Node} stellt einen beliebigen Knotenpunkt in einer Baumstruktur dar. In einer Node wird der aktuelle Wert,
     * der vorherige Knoten und der linke und rechte abzweigende Knoten abgespeichert.
     *
     * @param pre   Der vorherige Knotenpunkt der Baumstruktur.
     * @param left  Der linke nachfolgende Knotenpunkt der Baumstruktur.
     * @param right Der rechte nachfolgende Knotenpunkt der Baumstruktur.
     * @param value Der aktuelle Wert dieses Knotenpunktes.
     */
    public Node(
        final Node pre,
        final Node left,
        final Node right,
        final String value
    ) {
        this.pre = pre;
        this.left = left;
        this.right = right;
        this.value = value;
    }
    //</editor-fold>


    /**
     * Gibt den Wert für einen bestimmten Morsecode von diesem Knotenpunkt aus an. Dabei werden die Verzweigungen der
     * Baumstruktur genutzt.
     *
     * @param code Der Morsecode, welcher mithilfe der Baumstruktur von diesem Knotenpunkt aus gefiltert werden soll.
     *
     * @return der Wert für einen bestimmten Morsecode von diesem Knotenpunkt aus an. Dabei werden die Verzweigungen der
     *     Baumstruktur genutzt.
     */
    public String get(final String code) {
        // check if code is null
        if (code == null || code.length() == 0) return this.value;

        // check if code starts with '.'
        if (code.charAt(0) == '.' && this.left != null) return this.left.get(code.substring(1));

        // check if code starts with '_'
        if (code.charAt(0) == '_' && this.right != null) return this.right.get(code.substring(1));

        // return error code
        return "#";
    }

    /**
     * Gibt diesen Knotenpunkt mit allen nachfolgenden Verzweigungen aus.
     */
    public void print() {
        System.out.println(this);
    }

    //<editor-fold desc="getter">

    /**
     * Gibt den Vorgänger dieses Knotenpunktes zurück.
     *
     * @return Der Vorgänger dieses Knotenpunktes.
     */
    public Node getPre() {
        return this.pre;
    }

    /**
     * Gibt den linken Nachfolger dieses Knotenpunktes zurück.
     *
     * @return der linke Nachfolger dieses Knotenpunktes.
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * Gibt den rechten Nachfolger dieses Knotenpunktes zurück.
     *
     * @return der rechte Nachfolger dieses Knotenpunktes.
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * Gibt den aktuellen Wert dieses Knotenpunktes zurück.
     *
     * @return der aktuelle Wert dieses Knotenpunktes.
     */
    public String getValue() {
        return this.value;
    }
    //</editor-fold>

    //<editor-fold desc="setter">

    /**
     * Setzt den Vorgänger dieses Knotenpunktes.
     *
     * @param pre Der Vorgänger dieses Knotenpunktes.
     */
    public void setPre(final Node pre) {
        this.pre = pre;
    }

    /**
     * Setzt den linken Nachfolger dieses Knotenpunktes.
     *
     * @param left Der linke Nachfolger dieses Knotenpunktes.
     */
    public void setLeft(final Node left) {
        this.left = left;
    }

    /**
     * Setzt den rechten Nachfolger dieses Knotenpunktes.
     *
     * @param right Der rechten Nachfolger dieses Knotenpunktes.
     */
    public void setRight(final Node right) {
        this.right = right;
    }

    /**
     * Setzt den aktuellen Wert dieses Knotenpunktes.
     *
     * @param value Der neue Wert, den dieser Knotenpunkt besitzen soll.
     */
    public void setValue(final String value) {
        this.value = value;
    }
    //</editor-fold>

    //<editor-fold desc="implementation">
    @Override
    public String toString() {
        return this.value + "\nLinks: " + this.left + " | Rechts: " + this.right;
    }
    //</editor-fold>
}
