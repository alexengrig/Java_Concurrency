package dev.alexengrig.java.concurrency.example.method;

public class SleepyThread extends Thread {
    private boolean hasInterruptedException;

    public boolean hasInterruptedException() {
        return hasInterruptedException;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            hasInterruptedException = true;
        }
    }
}
