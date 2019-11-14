package dev.alexengrig.java.concurrency.example.race;

public class SequencePrinter {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new SerialSequencePrinter(i);
            thread.start();
        }
    }
}

class RandomSequencePrinter extends Thread {
    protected final int id;

    RandomSequencePrinter(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Print id: " + id);
    }
}

class SerialSequencePrinter extends RandomSequencePrinter {
    private final static Object lock = new Object();
    private static int current_id = 0;

    SerialSequencePrinter(int id) {
        super(id);
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                while (id > current_id) {
                    lock.wait();
                }
                super.run();
                current_id++;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
