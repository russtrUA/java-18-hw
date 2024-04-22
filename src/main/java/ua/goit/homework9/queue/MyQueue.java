package ua.goit.homework9.queue;

import java.util.StringJoiner;

public class MyQueue<T> {
    private Node head;
    private Node trail;
    private int length;
    public void add(T value) {
        Node item = new Node(value);
        length++;
        if (head == null) {
            head = item;
            trail = item;
            return;
        }
        trail.next = item;
        item.prev = trail;
        trail = item;
    }
    public int size() {
        return length;
    }
    public void clear() {
        length = 0;
        head = null;
        trail = null;
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
    public T peek() {
        if (length == 0)
            return null;
        return head.value;
    }
    public T poll() {
        if (length == 0)
            return null;
        Node nodeToRemove = head;
        if (head.next != null) {
            head.next.prev = null;
            head = head.next;
            nodeToRemove.next = null;
        } else {
            head = null;
            trail = null;
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

