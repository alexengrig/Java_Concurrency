package dev.alexengrig.java.concurrency.example.method;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InterruptedThreadTest {
    @Test
    public void check() {
        InterruptedThread interruptedThread = new InterruptedThread();
        interruptedThread.start();
        interruptedThread.interrupt();
        assertTrue(interruptedThread.hasInterrupt());
    }
}