package ua.goit.homework12.task2;

import java.util.function.Consumer;

public class ProcessorThread extends Thread {
    private volatile boolean isAlive = true;
    private volatile boolean isProcessed = true;
    private volatile int numberToProcess;
    private final Consumer<Integer> consumer;

    public ProcessorThread(Consumer<Integer> consumer) {
        this.consumer = consumer;
    }

    public void setNumberToProcess(int numberToProcess) {
        this.numberToProcess = numberToProcess;
        this.isProcessed = false;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void run() {
        while (isAlive) {
            if (!isProcessed) {
                consumer.accept(numberToProcess);
                isProcessed = true;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
