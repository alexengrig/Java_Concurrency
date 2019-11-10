package dev.alexengrig.java.concurrency.example.method;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SleepyThreadTest {
    @Test
    public void check() {
        SleepyThread sleepyThread = new SleepyThread();
        sleepyThread.start();
        sleepyThread.interrupt();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(sleepyThread.hasInterruptedException());
    }
}