package de.jonas.informatik.vlist;

public final class LinkedList {

    private Node head;
    private int size;


    public Player getFirst() {
        return head.getContent();
    }

    public Player getLast() {
        return getTail().getContent();
    }

    public void append(final Player player) {
        if (getTail() == null) {
            head = new Node(player);
        } else {
            getTail().setNext(new Node(player));
        }

        size++;
    }

    public void remove(final Player player) {
        Node node = head;

        if (node.getContent().equals(player)) {
            this.head = node.getNext();
        }

        while (node.getNext() != null) {
            if (node.getNext().getContent().equals(player)) {
                node.setNext(node.getNext().getNext());
            }

            node = node.getNext();
        }

        size--;
    }

    public void insertAfter(final Player player, final Player processor) {
        Node node = head;

        if (node.getContent().equals(processor)) {
            final Node next = node.getNext();
            node.setNext(new Node(player));
            node.getNext().setNext(next);
        }

        for (int i = 0; i < size; i++) {
            if (node.getNext().getContent().equals(processor)) {
                final Node next = node.getNext().getNext();
                node.getNext().setNext(new Node(player));
                node.getNext().getNext().setNext(next);
            }

            node = node.getNext();
        }

        size++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private Node getTail() {
        Node node = head;

        for (int i = 0; i < size - 1; i++) {
            node = node.getNext();
        }

        return node;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        String values = head.toString();

        Node node = head;

        while (node.getNext() != null) {
            node = node.getNext();

            values += "\n\n" + node.toString();
        }

        return values;
    }
}
