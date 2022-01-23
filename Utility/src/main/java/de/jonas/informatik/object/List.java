package de.jonas.informatik.object;

import java.util.Arrays;

/**
 * Eine {@link List} stellt eine Liste dar, in der beliebig viele Objekte eines gleichen Objekt-Typen abgespeichert
 * werden können.
 *
 * @param <ObjectType> Der Typ, aus dem alle Objekte bestehen, die sich in der Liste befinden.
 */
public class List<ObjectType> {

    //<editor-fold desc="LOCAL FIELDS">
    /** Alle Objekte, die sich in der Liste befinden (Alle bestehen aus demselben {@link ObjectType}). */
    private Object[] objects;
    /** Die aktuelle Größe der Liste. */
    private int size;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz einer Liste, in der beliebig viele Objekte eines gleichen
     * Objekt-Typen abgespeichert werden können.
     */
    public List() {
        this.objects = new Object[1];
    }
    //</editor-fold>


    /**
     * Fügt ein bestimmtes Objekt des entsprechenden {@link ObjectType} zu der Liste hinzu.
     *
     * @param object Das Objekt, welches hinzugefügt werden soll.
     */
    public void add(final ObjectType object) {
        if (this.size >= this.objects.length) {
            incrementCapacity();
        }

        this.objects[size] = object;

        this.size++;
    }

    /**
     * Gibt ein bestimmtes Objekt des entsprechenden {@link ObjectType} zurück, welches sich an einer bestimmten Stelle
     * in der Liste befindet.
     *
     * @param position Die Position des Objekts, welches abgefragt wird.
     *
     * @return Ein bestimmtes Objekt des entsprechenden {@link ObjectType} zurück, welches sich an einer bestimmten
     *     Stelle in der Liste befindet.
     */
    @SuppressWarnings("unchecked")
    public ObjectType get(final int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException(
                "current position number (" + position + ") is out of bounds!"
            );
        }

        return (ObjectType) objects[position];
    }

    /**
     * Gibt alle Objekte des entsprechenden {@link ObjectType} zurück, die sich in der Liste befinden (in Form eines
     * Arrays).
     *
     * @return Alle Objekte des entsprechenden {@link ObjectType} zurück, die sich in der Liste befinden (in Form eines
     *     Arrays).
     */
    @SuppressWarnings("unchecked")
    public ObjectType[] getAll() {
        return (ObjectType[]) objects;
    }

    /**
     * Gibt die aktuelle Größe dieser Liste zurück bzw. gibt zurück, wie viele Objekte sich momentan in dieser Liste
     * befinden.
     *
     * @return die aktuelle Größe dieser Liste zurück bzw. gibt zurück, wie viele Objekte sich momentan in dieser Liste
     *     befinden.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Erhöht die momentane Kapazität der Liste um 1.
     */
    private void incrementCapacity() {
        this.objects = Arrays.copyOf(this.objects, this.objects.length + 1);
    }

    /**
     * Erzeugt eine Liste auf der Basis einiger Werte.
     *
     * @param objects Die Objekte, welche in die Liste zu Beginn herein sollen.
     *
     * @return Eine Liste auf der Basis einiger Werte.
     */
    public List<ObjectType> of(final ObjectType[] objects) {
        final List<ObjectType> list = new List<>();

        for (final ObjectType object : objects) {
            list.add(object);
        }

        return list;
    }

}