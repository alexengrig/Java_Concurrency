package dev.alexengrig.java.concurrency.my;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyBiLockTest {
    @Test
    public void check() throws InterruptedException {
        Lock lock = new MyBiLock();
        int size = 1000000;
        final int[] count = {0};
        Runnable doCount = () -> {
            for (int i = 0; i < size; i++) {
                lock.lock();
                count[0]++;
                lock.unlock();
            }
        };
        Thread thread0 = new Thread(doCount, "0");
        Thread thread1 = new Thread(doCount, "1");
        thread0.start();
        thread1.start();
        thread0.join();
        thread1.join();
        assertEquals(size * 2, count[0]);
    }
}