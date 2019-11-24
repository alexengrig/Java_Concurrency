package dev.alexengrig.java.concurrency.my;

public class MyBiLock implements Lock {
    private volatile boolean[] flags = new boolean[2];
    @SuppressWarnings("FieldCanBeLocal")
    private volatile int currentId; // Anti-deadlock

    @Override
    public void lock() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        int anotherId = 1 - id;
        flags[id] = true;
        currentId = id;
        //noinspection StatementWithEmptyBody
        while (flags[anotherId] && currentId == id) {
            //wait
        }
    }

    @Override
    public void unlock() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        flags[id] = false;
    }
}
