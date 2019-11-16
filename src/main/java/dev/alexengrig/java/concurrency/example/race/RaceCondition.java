package dev.alexengrig.java.concurrency.example.race;

import dev.alexengrig.java.concurrency.annotation.NotThreadSafe;
import dev.alexengrig.java.concurrency.annotation.ThreadSafe;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface Counter {
    void inc();

    int get();
}

class CountCallback implements Callable<Integer> {
    private final int max;
    private final Counter counter;
    private final AtomicInteger atomicInteger;

    CountCallback(int max, Counter counter, AtomicInteger atomicInteger) {
        this.max = max;
        this.counter = counter;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public Integer call() {
        while (atomicInteger.get() < max) {
            atomicInteger.incrementAndGet();
            counter.inc();
        }
        return counter.get();
    }
}

public class RaceCondition {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        check(new SimpleCounter());
        check(new VolatileCounter());
        check(new SynchronizedMethodCounter());
        check(new SynchronizedStatementsCounter());
        check(new LockCounter());
        check(new AtomicCounter());
    }

    private static void check(Counter counter) throws ExecutionException, InterruptedException {
        System.out.printf("Start check %s%n", counter.getClass().getSimpleName());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int max = 10_000_000;
        AtomicInteger atomicInteger = new AtomicInteger();
        Callable<Integer> callback1 = new CountCallback(max, counter, atomicInteger);
        Callable<Integer> callable2 = new CountCallback(max, counter, atomicInteger);
        long startTime = new Date().getTime();
        Future<Integer> future1 = executorService.submit(callback1);
        Future<Integer> future2 = executorService.submit(callable2);
        Integer value1 = future1.get();
        Integer value2 = future2.get();
        System.out.printf("Future 1: %d / %d\t(missing %d)%n", value1, max, max - value1);
        System.out.printf("Future 2: %d / %d\t(missing %d)%n", value2, max, max - value2);
        System.out.printf("AtomicInteger: %s / %d (missing %d)%n", atomicInteger, max, max - atomicInteger.get());
        long finishTime = new Date().getTime();
        System.out.printf("Time spent: %d%n", finishTime - startTime);
        executorService.shutdown();
        System.out.printf("Finish check %s\n%n", counter.getClass().getSimpleName());
    }
}

@NotThreadSafe
class SimpleCounter implements Counter {
    private int count;

    @Override
    public void inc() {
        count++;
    }

    @Override
    public int get() {
        return count;
    }
}

@NotThreadSafe
class VolatileCounter implements Counter {
    private volatile int count;

    @Override
    public void inc() {
        //noinspection NonAtomicOperationOnVolatileField
        count++;
    }

    @Override
    public int get() {
        return count;
    }
}

@ThreadSafe
class SynchronizedMethodCounter implements Counter {
    private int count;

    @Override
    public synchronized void inc() {
        count++;
    }

    @Override
    public int get() {
        return count;
    }
}

@ThreadSafe
class SynchronizedStatementsCounter implements Counter {
    private final Object lock = new Object();
    private int count;

    @Override
    public void inc() {
        synchronized (lock) {
            count++;
        }
    }

    @Override
    public int get() {
        return count;
    }
}

@ThreadSafe
class LockCounter implements Counter {
    private static final Lock locker = new ReentrantLock();
    private int count = 0;

    @Override
    public void inc() {
        locker.lock();
        count++;
        locker.unlock();
    }

    @Override
    public int get() {
        return count;
    }
}

@ThreadSafe
class AtomicCounter implements Counter {
    private final AtomicInteger count = new AtomicInteger();

    @Override
    public void inc() {
        count.incrementAndGet();
    }

    @Override
    public int get() {
        return count.get();
    }
}
