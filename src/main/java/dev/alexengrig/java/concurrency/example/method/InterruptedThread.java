package dev.alexengrig.java.concurrency.example.method;

public class InterruptedThread extends Thread {
    public boolean hasInterrupt() {
        return isInterrupted();
    }
}
