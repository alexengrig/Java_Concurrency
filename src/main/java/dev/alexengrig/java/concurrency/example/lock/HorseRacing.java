package dev.alexengrig.java.concurrency.example.lock;

import java.util.concurrent.CountDownLatch;

public class HorseRacing {
    public static void main(String[] args) {
        Gun gun = new Gun();
        for (int i = 0; i < 7; i++) {
            new Horse(gun, "Horse #" + (i + 1)).start();
        }
        for (int i = 3; i > 0; i--) {
            System.out.println(i);
        }
        gun.shot();
    }
}

class Horse extends Thread {
    private final Gun gun;
    private final String name;

    Horse(Gun gun, String name) {
        this.gun = gun;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            gun.await();
            System.out.println(toString() + " run!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

class Gun {
    private final CountDownLatch latch = new CountDownLatch(1);

    void await() throws InterruptedException {
        latch.await();
    }

    void shot() {
        System.out.println("BANG!");
        latch.countDown();
    }
}