package dev.alexengrig.java.concurrency.mail;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mail {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 3; i++) {
            Randomist randomist = new Randomist();
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    String text = randomist.write();
                    queue.add(text);
                }
            }).start();
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (queue.peek() != null) {
                String text = queue.poll();
                new Printer().read(text);
            }
        });
        executorService.shutdown();
    }
}
