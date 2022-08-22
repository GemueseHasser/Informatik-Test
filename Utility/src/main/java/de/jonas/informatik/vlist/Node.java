package de.jonas.informatik.vlist;

public class Node {

    private Node next;
    private Player content;


    public Node() {
        this.next = null;
        this.content = null;
    }


    public Node(final Player content) {
        this.next = null;
        this.content = content;
    }


    public void print() {
        content.print();
    }


    public Node getNext() {
        return next;
    }

    public Player getContent() {
        return content;
    }

    public void setNext(final Node next) {
        this.next = next;
    }

    public void setContent(final Player content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
