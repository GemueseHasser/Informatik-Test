package de.jonas.informatik.temp;

/**
 * Die Aufgabe 'Umgang mit Methoden II'.
 */
public final class MainMethodsII {

    //<editor-fold desc="CONSTANTS">
    /** Die {@link MainMethodsII Instanz} dieser Klasse. */
    private static final MainMethodsII INSTANCE = new MainMethodsII();
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das erste {@link MainObject Objekt} der Klasse. */
    private final MainObject object1;
    /** Das zweite {@link MainObject Objekt} der Klasse. */
    private final MainObject object2;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link MainMethodsII}. Bei der Instanziierung werden
     * die beiden {@link MainObject Objekte} der Klasse initialisiert.
     */
    public MainMethodsII() {
        this.object1 = new MainObject();
        this.object2 = new MainObject(5, -0.1, "xyz");
    }
    //</editor-fold>


    /**
     * Gibt die verschiedenen Objekte in der Konsole aus.
     */
    public void printObjects() {
        System.out.println(properties(object1));
        System.out.println();
        System.out.println(properties(object2));

        object1.setNumber(2);
        object1.setValue(3.1416);
        object1.setName("pi");

        System.out.println();
        System.out.println(properties(object1));
    }

    //<editor-fold desc="utility">

    /**
     * Zeigt die Eigenschaften eines bestimmten {@link MainObject Objekts} an, bzw. baut den String zum Anzeigen der
     * Eigenschaften zusammen.
     *
     * @param object Das Objekt, dessen Eigenschaften angezeigt werden.
     *
     * @return Der gebaute String, welcher dann angezeigt werden kann.
     */
    private static StringBuilder properties(final MainObject object) {
        return new StringBuilder()
            .append(
                "Eigenschaften von "
            ).append(
                object.toString()
            ).append(
                ":\n"
            ).append(
                "Nummer = "
            ).append(
                object.getNumber()
            ).append(
                "\n"
            ).append(
                "Wert = "
            ).append(
                object.getValue()
            ).append(
                "\n"
            ).append(
                "Name = "
            ).append(
                object.getName()
            );
    }
    //</editor-fold>


    //<editor-fold desc="setup and start">

    /**
     * Die Main-Methode der Klasse.
     *
     * @param args Die Argumente, die von der JRE übergeben werden.
     */
    public static void main(final String[] args) {
        INSTANCE.printObjects();

        System.out.println("\n");
    }
    //</editor-fold>


    //<editor-fold desc="MainObject">

    /**
     * Ein {@link MainObject} kann ohne Parameter initialisiert werden und bekommt dann die standard Parameter gesetzt,
     * welche sich aber immer ändern lassen über die Setter der Klasse, oder man kann die Klasse direkt mit individuell
     * übergebenen Parametern initialisieren. Aber auch bei der letzteren Variante lassen sich alle Werte über die
     * Setter der Klasse anpassen.
     */
    private static final class MainObject {

        //<editor-fold desc="CONSTRUCTORS">
        /** Die standard Nummer, die so initialisiert wird, zufalls keine individuellen Parameter übergeben werden. */
        private static final int DEFAULT_NUMBER = 1;
        /** Der standard Wert, der so initialisiert wird, zufalls keine individuellen Parameter übergeben werden. */
        private static final double DEFAULT_VALUE = 2.5;
        /** Der standard Name, der so initialisiert wird, zufalls keine individuellen Parameter übergeben werden. */
        private static final String DEFAULT_NAME = "Max";
        //</editor-fold>


        //<editor-fold desc="LOCAL FIELDS">
        /** Die Nummer dieses Objekts. */
        private int number;
        /** Der Wert dieses Objekts. */
        private double value;
        /** Der Name dieses Objekts. */
        private String name;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link MainObject} mit den Standard Parametern,
         * welche konstant definiert sind.
         */
        public MainObject() {
            this.number = DEFAULT_NUMBER;
            this.value = DEFAULT_VALUE;
            this.name = DEFAULT_NAME;
        }

        /**
         * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link MainObject} mit individuellen Parametern,
         * es wird in diesem Fall auf die Standard Parameter verzichtet.
         *
         * @param number Die individuelle Nummer für dieses Objekt.
         * @param value  Der individuelle Wert für dieses Objekt.
         * @param name   Der individuelle Name für dieses Objekt.
         */
        public MainObject(
            final int number,
            final double value,
            final String name
        ) {
            this.number = number;
            this.value = value;
            this.name = name;
        }
        //</editor-fold>


        //<editor-fold desc="Generated">

        //<editor-fold desc="Getter">
        public int getNumber() {
            return number;
        }

        public double getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
        //</editor-fold>

        //<editor-fold desc="Setter">
        public void setNumber(final int number) {
            this.number = number;
        }

        public void setValue(final double value) {
            this.value = value;
        }

        public void setName(final String name) {
            this.name = name;
        }
        //</editor-fold>

        //</editor-fold>

        //<editor-fold desc="implementation">
        @Override
        public String toString() {
            // don´t print the variable but the object name
            return "MainObject " + this.name;
        }
        //</editor-fold>
    }
    //</editor-fold>

}
