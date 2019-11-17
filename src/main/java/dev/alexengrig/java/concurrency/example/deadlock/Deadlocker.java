package dev.alexengrig.java.concurrency.example.deadlock;

public class Deadlocker {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public static void main(String[] args) {
        Deadlocker deadlocker = new Deadlocker();
        Thread thread1 = new Thread(deadlocker::doOne);
        Thread thread2 = new Thread(deadlocker::doTwo);
        thread1.start();
        thread2.start();
    }

    void doOne() {
        synchronized (lock1) {
            System.out.println("Lock1 locked.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wait lock2.");
            synchronized (lock2) {
                System.out.println("Lock2 locked.");
            }
            System.out.println("Lock2 unlocked.");
        }
        System.out.println("Lock1 unlocked.");
    }

    void doTwo() {
        synchronized (lock2) {
            System.out.println("Lock2 locked.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wait lock1.");
            synchronized (lock1) {
                System.out.println("Lock1 locked.");
            }
            System.out.println("Lock1 unlocked.");
        }
        System.out.println("Lock2 unlocked.");
    }
}
