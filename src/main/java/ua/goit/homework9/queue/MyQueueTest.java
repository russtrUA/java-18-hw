package ua.goit.homework9.queue;

public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<>();
        q.add(12);
        q.add(4);
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.size());
        System.out.println(q);
        q.add(4);
        q.add(7);
        q.add(3);
        System.out.println(q);
        q.poll();
        q.poll();
        System.out.println(q);
        System.out.println(q.size());
        q.clear();
        System.out.println(q.size());
        System.out.println(q);
    }
}
