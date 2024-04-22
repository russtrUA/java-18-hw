package ua.goit.homework9.hashmap;

import java.util.Objects;
import java.util.StringJoiner;

public class MyHashMap<K, V> {
    private static final int INITIAL_ARRAY_LENGTH = 16;
    private Node<K, V>[] table;
    private float loadFactor = 0.75f;
    private int size;

    public MyHashMap() {
        table = new Node[INITIAL_ARRAY_LENGTH];
    }
    public MyHashMap(int capacity) {
        table = new Node[capacity];
    }
    public MyHashMap(int capacity, float loadFactor) {
        table = new Node[capacity];
        this.loadFactor = loadFactor;
    }

    private int getBucketIndex(Node<K, V> node) {
        if (node.key == null)
            return 0;
        return Math.abs(node.hash) % table.length;
    }

    public V put(K key, V value) {
        if (size + 1 >= table.length * loadFactor) {
            size = 0;
            resizeArray();
        }
        Node<K, V> newNode = new Node<>(key, value);
        int index = getBucketIndex(newNode);
        if (table[index] == null) {
            table[index] = newNode;
            size++;
            return null;
        }
        Node<K, V> temp = table[index];
        Node<K, V> prev;
        do {
            if (temp.hash == newNode.hash && temp.equals(newNode)) {
                V prevValue = temp.value;
                temp.value = value;
                temp.key = key;
                return prevValue;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != null);
        prev.next = newNode;
        size++;
        return null;
    }

    public V get(K key) {
        Node<K, V> tempNode = new Node<>(key, null);
        int index = getBucketIndex(tempNode);
        if (table[index] == null)
            return null;
        Node<K, V> temp = table[index];
        while (temp != null) {
            if (tempNode.hash == temp.hash && temp.equals(tempNode))
                return temp.value;
            temp = temp.next;
        }
        return null;
    }

    public V remove(K key) {
        Node<K, V> tempNode = new Node<>(key, null);
        int index = getBucketIndex(tempNode);
        if (table[index] == null)
            return null;
        Node<K, V> temp = table[index];
        Node<K, V> prev = temp;
        while (temp != null) {

            if (tempNode.hash == temp.hash && temp.equals(tempNode)) {
                if (temp == table[index]) {
                    if (temp.next == null) {
                        table[index] = null;
                    } else {
                        table[index] = temp.next;
                    }
                }
                prev.next = temp.next;
                temp.next = null;
                size--;
                return temp.value;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }

    private void resizeArray() {
        Node<K, V>[] oldTable = table;
        table = new Node[table.length * 2];
        for (Node<K, V> item : oldTable) {
            if (item == null)
                continue;
            for (Node<K, V> temp = item; temp != null; temp = temp.next) {
                put(temp.key, temp.value);
            }
        }
        System.out.println("HashMap resized! new size of table is: " + table.length);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Node<K, V> temp = table[i]; temp != null; temp = temp.next) {
                    joiner.add((temp.key == null ? "null" : temp.key.toString()) + "=" + (temp.value == null ? "null" : temp.value.toString()));
                }
            }
        }
        return joiner.toString();
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    private class Node<K, V> {
        int hash;
        K key;
        V value;
        Node next;

        Node(K key, V value) {
            this.hash = hashCode();
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<K, V> node = (Node<K, V>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
