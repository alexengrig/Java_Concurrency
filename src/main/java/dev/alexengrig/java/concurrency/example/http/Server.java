package dev.alexengrig.java.concurrency.example.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) throws IOException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i + 1);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Shutdown");
                executor.shutdown();
            }
        }).start();
        ServerSocket socket = new ServerSocket(8080);
        while (!executor.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                executor.execute(() -> {
                    try {
                        System.out.println("Connection @" + connection.hashCode());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("Task rejected");
            }
        }
    }
}
