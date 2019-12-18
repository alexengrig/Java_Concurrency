package dev.alexengrig.java.concurrency.example.lock;

public class PrepareAndSend {
    private final Object monitor = new Object();
    private boolean ready;

    public static void main(String[] args) {
        PrepareAndSend prepareAndSend = new PrepareAndSend();
        Thread prepareDataThread = new Thread(() -> {
            try {
                prepareAndSend.prepareData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        prepareDataThread.start();
        Thread sendDataThread = new Thread(() -> {
            try {
                prepareAndSend.sendData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        sendDataThread.start();
    }

    void prepareData() throws InterruptedException {
        Thread.sleep(2000);
        synchronized (monitor) {
            System.out.println("Data prepared!");
            ready = true;
            monitor.notifyAll();
        }
    }

    void sendData() throws InterruptedException {
        synchronized (monitor) {
            System.out.println("Waiting for data...");
            while (!ready) {
                monitor.wait();
            }
            System.out.println("Sending data...");
        }
    }
}
