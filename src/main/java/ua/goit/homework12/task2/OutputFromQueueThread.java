package ua.goit.homework12.task2;

import java.util.Queue;

public class OutputFromQueueThread extends Thread{
    private volatile boolean isAlive = true;
    private final Queue<String> numbers;

    public OutputFromQueueThread(Queue<String> numbers) {
        this.numbers = numbers;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private String number() {
        return numbers.poll();
    }
    @Override
    public void run() {
        while (isAlive || !numbers.isEmpty()) {
            if (!numbers.isEmpty()) {
                System.out.print(number() + " ");
                continue;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
