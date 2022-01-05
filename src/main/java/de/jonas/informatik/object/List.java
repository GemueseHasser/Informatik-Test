package de.jonas.informatik.object;

import java.util.Arrays;

public class List<ObjectType> {

    private Object[] objects;
    private int size;


    public List() {
        this.objects = new Object[1];
    }


    public void add(final ObjectType object) {
        if (this.size >= this.objects.length) {
            incrementCapacity();
        }

        this.objects[size] = object;

        this.size++;
    }

    @SuppressWarnings("unchecked")
    public ObjectType get(final int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException(
                "current position number (" + position + ") is out of bounds!"
            );
        }

        return (ObjectType) objects[position];
    }

    @SuppressWarnings("unchecked")
    public ObjectType[] getAll() {
        return (ObjectType[]) objects;
    }

    private void incrementCapacity() {
        this.objects = Arrays.copyOf(this.objects, this.objects.length + 1);
    }

}
