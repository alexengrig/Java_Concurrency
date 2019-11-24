package dev.alexengrig.java.concurrency.example.interrupt;

public class InterruptedThread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new InterruptedThread();
        thread.start();
        thread.interrupt();
        thread.join();
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted!");
                break;
            }
        }
    }
}
