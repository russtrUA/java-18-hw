package ua.goit.homework12.task2;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

public class Main {
    public static void fizz(Integer number, Queue<String> result) {
        if (number % 3 == 0 && number % 5 != 0) {
            result.add("fizz");
        } else if (number % 3 != 0 && number % 5 != 0) {
            result.add(number.toString());
        }
    }

    public static void buzz(Integer number, Queue<String> result) {
        if (number % 5 == 0 && number % 3 != 0) {
            result.add("buzz");
        }
    }

    public static void fizzbuzz(Integer number, Queue<String> result) {
        if (number % 15 == 0) {
            result.add("fizzbuzz");
        }
    }

    public static void main(String[] args) {
        Queue<String> resultQueue = new ConcurrentLinkedQueue<>();
        OutputFromQueueThread outputFromQueueThread = new OutputFromQueueThread(resultQueue);
        outputFromQueueThread.start();
        List<ProcessorThread> threads = List.of(new ProcessorThread(number -> fizz(number, resultQueue)),
                                                new ProcessorThread(number -> buzz(number, resultQueue)),
                                                new ProcessorThread(number -> fizzbuzz(number, resultQueue))
                                        );
        threads.forEach(Thread::start);
        IntStream.range(1, 101)
                .forEach(i -> {
                    threads.forEach(thread -> thread.setNumberToProcess(i));
                    while (true) {
                        boolean allMatch = threads.stream()
                                .allMatch(ProcessorThread::isProcessed);
                        if (allMatch)
                            break;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        threads.forEach(thread -> thread.setAlive(false));
        outputFromQueueThread.setAlive(false);
    }
}
