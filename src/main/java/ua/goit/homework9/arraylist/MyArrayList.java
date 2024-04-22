package ua.goit.homework9.arraylist;

import java.util.StringJoiner;

public class MyArrayList<T> {
    private static final int INIT_SIZE = 8;
    private Object[] array;
    private int index;

    public MyArrayList() {
        this.array = new Object[INIT_SIZE];
    }
    public void add(T value) {
        if (index == array.length) {
            Object[] newArray = new Object[index * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[index++] = value;
    }
    public int size() {
        return index;
    }

    public T get(int i) {
        if(i >= index) {
            throw new IndexOutOfBoundsException("Size is " + index);
        }
        return (T) array[i];
    }
    public void clear() {
        array = new Object[INIT_SIZE];
        index = 0;
    }

    public T remove(int i) {
        T itemToRemove = get(i);

        if (i == index - 1) {
            array[i] = null;
        } else {
            System.arraycopy(array, i + 1, array, i, index - i - 1);
            array[index - 1] = null;
        }
        index--;
        return itemToRemove;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < index; i++) {
            joiner.add(array[i].toString());
        }
        return "[" + joiner.toString() + "]";
    }

}
