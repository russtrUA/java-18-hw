package ua.goit.homework9.stack;

import java.util.StringJoiner;

public class MyStack<T> {
    private Node head;
    private Node tail;
    private int length;

    public void push(T value) {
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
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
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

    public T remove(int index) {
        Node nodeToRemove = getByIndex(index);
        if (index == 0 && head == tail) {
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

    public T peek() {
        if (length == 0)
            return null;
        return tail.value;
    }

    public T pop() {
        if (length == 0)
            return null;
        Node nodeToRemove = tail;
        if (tail.prev != null) {
            tail.prev.next = null;
            tail = tail.prev;
            nodeToRemove.prev = null;
        } else {
            head = null;
            tail = null;
        }
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
