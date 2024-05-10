package ua.goit.homework9.linkedlist;

import java.util.StringJoiner;

public class MyLinkedList<T> {
    private Node head;
    private Node tail;
    private int length;
    public void add(T value) {
        Node item = new Node(value);
        length++;
        if (head == null) {
            head = item;
            tail = item;
            return;
        }
        tail.next = item;
        item.prev = tail;
        tail = item;
//        Node temp = head;
//        while (temp.next != null) {
//            temp = temp.next;
//        }
//        temp.next = item;
//        item.prev = temp;
    }
    public int size() {
        return length;
    }
    public void clear() {
        length = 0;
        head = null;
        tail = null;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[","]");
        Node temp = head;
        while (temp != null) {
            joiner.add(temp.value.toString());
            temp = temp.next;
        }
        return joiner.toString();
    }
    private Node getByIndex(int index) {
        if (index >= length)
            throw new IndexOutOfBoundsException("Size is: " + length);
        if (index > length / 2) {
            index = length - index - 1;
            Node current = tail;
            for (int i = 0; i < index; i++) {
                current = current.prev;
            }
            return current;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
    }
    public T get(int index) {
        return getByIndex(index).value;
    }

    public T remove(int index) {
        Node nodeToRemove = getByIndex(index);
        if(index == 0 && head == tail) {
            head = null;
            tail = null;
            length--;
            return nodeToRemove.value;
        }
        if (index == 0) {
            head.next.prev = null;
            head = head.next;
            nodeToRemove.next = null;
            length--;
            return nodeToRemove.value;
        }
        if (index == length - 1) {
            tail.prev.next = null;
            tail = tail.prev;
            nodeToRemove.prev = null;
            length--;
            return nodeToRemove.value;
        }
        nodeToRemove.prev.next = nodeToRemove.next;
        nodeToRemove.next.prev = nodeToRemove.prev;
        nodeToRemove.prev = null;
        nodeToRemove.next = null;
        length--;
        return nodeToRemove.value;
    }
    private class Node {
        T value;
        Node next;
        Node prev;
        Node(T value) {
            this.value = value;
        }
    }
}
