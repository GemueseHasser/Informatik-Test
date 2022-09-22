package de.jonas.informatik.linkedlist;

/**
 * Eine {@link LinkedList} stellt eine doppelt verkettete Liste dar, in der jeder Eintrag mit dem davor und dem danach
 * verkettet ist, man also von einem Eintrag aus auf den davor und den danach zugreifen kann.
 *
 * @param <ObjectType> Der Typ aller Objekte, die in dieser Liste abgespeichert werden.
 */
public final class LinkedList<ObjectType> {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der Anfangspunkt der Liste, hinter dem weitere Einträge eingefügt werden. */
    private final ListNode head = new ListNode();
    /** Der Endpunkt der Liste, vor dem weitere Einträge eingefügt werden. */
    private final ListNode tail = new ListNode();
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz einer {@link LinkedList}. Eine {@link LinkedList} stellt
     * eine doppelt verkettete Liste dar, in der jeder Eintrag mit dem davor und dem danach verkettet ist, man also von
     * einem Eintrag aus auf den davor und den danach zugreifen kann.
     */
    public LinkedList() {
        this.head.setNext(this.tail);
        this.tail.setPrevious(this.head);
    }
    //</editor-fold>


    /**
     * Fügt ein {@link ObjectType Objekt} am Ende der Liste an.
     *
     * @param object Das {@link ObjectType Objekt}, welches an das Ende der Liste angefügt werden soll.
     */
    public void append(final ObjectType object) {
        final ListNode node = new ListNode(object);

        this.tail.getPrevious().setNext(node);
        node.setNext(this.tail);
        this.tail.setPrevious(node);
    }

    /**
     * Gibt die Liste aus mittels {@code toString}.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Gibt den ersten Eintrag der Liste zurück.
     *
     * @return Der erste Eintrag der Liste.
     */
    public ObjectType getFirst() {
        return this.head.getNext().getContent();
    }

    /**
     * Gibt den letzten Eintrag der Liste zurück.
     *
     * @return Der letzte Eintrag der Liste.
     */
    public ObjectType getLast() {
        return this.tail.getPrevious().getContent();
    }

    //<editor-fold desc="implementation">
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        ListNode node = this.head.getNext();

        while (node != this.tail) {
            builder.append(node.getContent().toString()).append("\n\n");
            node = node.getNext();
        }

        return builder.toString();
    }
    //</editor-fold>


    //<editor-fold desc="ListNode">

    /**
     * Ein {@link ListNode} stellt einen Knotenpunkt dar, welcher auf einen anderen Knotenpunkt vorher und einen nachher
     * zugreifen kann. Eine {@link LinkedList} besteht aus beliebig vielen Knotenpunkten. Ein Knotenpunkt wird immer
     * dann erzeugt, wenn man der Liste einen neuen Eintrag anfügt.
     */
    private final class ListNode {

        //<editor-fold desc="LOCAL FIELDS">
        /** Der auf diesen Knotenpunkt folgenden Knotenpunkt. */
        private ListNode next;
        /** Der vorherige Knotenpunkt dieses Knotenpunkts. */
        private ListNode previous;
        /** Der Inhalt dieses Knotenpunkts. */
        private ObjectType content;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt einen neuen und vollständig unabhängigen {@link ListNode} ohne Inhalt. Beim Erzeugen einer Instanz
         * eines Knotenpunktes wird dieser noch nicht mit anderen Knotenpunkten verkettet.
         */
        public ListNode() {
            this.next = null;
            this.previous = null;
            this.content = null;
        }


        /**
         * Erzeugt einen neuen und vollständig unabhängigen {@link ListNode} mit einem bestimmten Inhalt. Beim Erzeugen
         * einer Instanz eines Knotenpunktes wird dieser noch nicht mit anderen Knotenpunkten verkettet.
         *
         * @param content Der Inhalt dieses Knotenpunktes.
         */
        public ListNode(final ObjectType content) {
            this.next = null;
            this.previous = null;
            this.content = content;
        }
        //</editor-fold>


        /**
         * Gibt den Inhalt dieses Knotenpunktes aus.
         */
        public void print() {
            System.out.println(this.content.toString());
        }

        /**
         * Gibt den auf diesen folgenden Knotenpunkt zurück.
         *
         * @return Der auf diesen folgenden Knotenpunkt.
         */
        public ListNode getNext() {
            return this.next;
        }

        /**
         * Gibt den vorherigen dieses Knotenpunkts zurück.
         *
         * @return Der vorherige dieses Knotenpunkts.
         */
        public ListNode getPrevious() {
            return this.previous;
        }

        /**
         * Gibt den Inhalt dieses Knotenpunkts zurück.
         *
         * @return Der Inhalt dieses Knotenpunks.
         */
        public ObjectType getContent() {
            return content;
        }

        /**
         * Setzt den auf diesen folgenden Knotenpunkt.
         *
         * @param next Der Knotenpunkt, der auf diesen folgen soll.
         */
        public void setNext(final ListNode next) {
            this.next = next;
        }

        /**
         * Setzt den Knotenpunkt vor diesem.
         *
         * @param previous Der Knotenpunkt, der vor diesem sein soll.
         */
        public void setPrevious(final ListNode previous) {
            this.previous = previous;
        }

        /**
         * Setzt den Inhalt dieses Knotenpunkts.
         *
         * @param content Der Inhalt dieses Knotenpunkts.
         */
        public void setContent(final ObjectType content) {
            this.content = content;
        }

        //<editor-fold desc="implementation">
        @Override
        public String toString() {
            return content.toString();
        }
        //</editor-fold>

    }
    //</editor-fold>

}
