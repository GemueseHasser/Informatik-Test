package de.jonas.informatik.linkedlist;

import java.util.Comparator;

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
    /** Die Größe dieser Liste. */
    private int size;
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

        // append element
        node.setPrevious(this.tail.getPrevious());
        this.tail.getPrevious().setNext(node);
        node.setNext(this.tail);
        this.tail.setPrevious(node);

        // increment size
        size++;
    }

    /**
     * Fügt ein bestimmtes Objekt am Anfang der Liste an.
     *
     * @param object Das Objekt, welches am Anfang der Liste angefügt wird.
     */
    public void appendFirst(final ObjectType object) {
        final ListNode node = new ListNode(object);

        final ListNode first = this.head.getNext();

        this.head.setNext(node);
        node.setPrevious(this.head);
        node.setNext(first);
        first.setPrevious(node);

        size++;
    }

    /**
     * Prüft, ob diese Liste ein bestimmtes Element enthält.
     *
     * @param object Das Objekt, auf das diese Liste untersucht werden soll.
     *
     * @return Wenn diese Liste dieses Objekt beinhaltet {@code true}, ansonsten {@code false}.
     */
    public boolean contains(final ObjectType object) {
        ListNode node = this.head.getNext();

        while (node != this.tail) {
            if (node.getContent().equals(object)) return true;

            node = node.getNext();
        }

        return false;
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

    /**
     * Filtert ein Objekt an einer bestimmten Position aus der Liste heraus.
     *
     * @param position Die Position des Elements in der Liste, welches zurückgegeben werden soll.
     *
     * @return Ein Element dieser Liste an einer bestimmten Position.
     */
    public ObjectType get(final int position) {
        if (position > this.size) {
            throw new IllegalArgumentException(
                "requested position " + position + " is larger than the list (" + this.size + ")."
            );
        }

        ListNode node = this.head.getNext();

        for (int i = 1; i < position; i++) {
            node = node.getNext();
        }

        return node.getContent();
    }

    /**
     * Gibt die Position eines Objekts zurück, also an welcher Stelle sich dieses Objekt in der Liste befindet.
     *
     * @param object Das Objekt, dessen Position in der Liste zurückgegeben werden soll.
     *
     * @return Wenn sich das Objekt in der Liste befindet die Position eines Objekts, also an welcher Stelle sich dieses
     *     Objekt in der Liste befindet, ansonsten wird einfach -1 zurückgegeben.
     */
    public int positionOf(final ObjectType object) {
        ListNode node = this.head.getNext();

        for (int i = 1; i < this.size; i++) {
            if (node.getContent().equals(object)) return i;

            node = node.getNext();
        }

        return -1;
    }

    /**
     * Fügt ein bestimmtes Objekt an einer bestimmten Position in der Liste ein. Diese Funktion funktioniert genauso wie
     * {@code append}, nur dass das neue Objekt nicht am Ende angefügt wird, sondern an einer bestimmten Position in der
     * Liste.
     *
     * @param position  Die Position, hinter der das neue Objekt eingefügt werden soll.
     * @param newObject Das neue Objekt, welches hinter der bestimmten Position eingefügt werden soll.
     */
    public void insertAfter(final int position, final ObjectType newObject) {
        insertAfter(get(position), newObject);
    }

    /**
     * Fügt ein bestimmtes Objekt hinter einem Objekt ein. Diese Funktion funktioniert genauso wie {@code append}, nur
     * dass das neue Objekt nicht am Ende angefügt wird, sondern an einer bestimmten Position in der Liste.
     *
     * @param object    Das Objekt, hinter dem das neue Objekt eingefügt werden soll.
     * @param newObject Das neue Objekt, welches hinter einem bestimmten Objekt eingefügt werden soll.
     */
    public void insertAfter(final ObjectType object, final ObjectType newObject) {
        ListNode node = this.head.getNext();

        while (node != this.tail) {
            if (node.getContent().equals(object)) {
                // get next node
                final ListNode next = node.getNext();

                // create new node from new object
                final ListNode newNode = new ListNode(newObject);

                node.setNext(newNode);
                newNode.setPrevious(node);
                newNode.setNext(next);
                next.setPrevious(newNode);

                size++;
                return;
            }

            node = node.getNext();
        }
    }

    /**
     * Entfernt ein bestimmtes Objekt an einer bestimmten Stelle in der Liste, wenn dieses Objekt vorhanden ist.
     * Andernfalls passiert gar nichts.
     *
     * @param position Die Position / Stelle, an der das Objekt aus der Liste entfernt werden soll, wenn es vorhanden
     *                 ist.
     */
    public void remove(final int position) {
        remove(get(position));
    }

    /**
     * Entfernt ein bestimmtes Objekt aus der Liste, wenn dieses Objekt vorhanden ist. Andernfalls passiert gar nichts.
     *
     * @param object Das Objekt, welches aus der Liste entfernt werden soll, wenn es vorhanden ist.
     */
    public void remove(final ObjectType object) {
        ListNode node = this.head.getNext();

        while (node != this.tail) {
            if (node.getContent().equals(object)) {
                // get next and previous node
                final ListNode next = node.getNext();
                final ListNode previous = node.getPrevious();

                // remove node
                previous.setNext(next);
                next.setPrevious(previous);

                // reduce size
                size--;

                remove(object);
                return;
            }

            node = node.getNext();
        }
    }

    /**
     * Leert die Liste, entfernt also alle Einträge.
     */
    public void clear() {
        this.head.setNext(this.tail);
        this.tail.setPrevious(this.head);
    }

    /**
     * Gibt die Liste aus mittels {@code toString}.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Gibt die Größe dieser Liste zurück.
     *
     * @return Die Größe dieser Liste.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sortiert diese Liste mithilfe eines {@link Comparator}, der auf dem {@link ObjectType} dieser Liste basiert.
     *
     * @param comparator Der {@link Comparator}, mit dem diese Liste sortiert werden soll.
     */
    public void sort(final Comparator<ObjectType> comparator) {
        ListNode m = this.head.getNext();
        ListNode n = m.getNext();

        while (m != this.tail.getPrevious()) {
            while (n != this.tail) {
                final ObjectType p = m.getContent();
                final ObjectType q = n.getContent();

                if (comparator.compare(p, q) > 0) {
                    exchange(m, n);
                }

                n = n.getNext();
            }

            m = m.getNext();
            n = m.getNext();
        }
    }

    /**
     * Tauscht den Inhalt von zwei {@link ListNode} aus.
     *
     * @param first  Die erste {@link ListNode}, die vertauscht werden soll.
     * @param second Die zweite {@link ListNode}, die vertauscht werden soll.
     */
    private void exchange(final ListNode first, final ListNode second) {
        final ObjectType firstContent = first.getContent();
        final ObjectType secondContent = second.getContent();

        first.setContent(secondContent);
        second.setContent(firstContent);
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
