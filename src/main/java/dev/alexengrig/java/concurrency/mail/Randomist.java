package dev.alexengrig.java.concurrency.mail;

import java.util.Random;

public class Randomist implements Writer {
    private final Random random = new Random();

    @Override
    public String write() {
        StringBuilder builder = new StringBuilder(getClass().toString() + ":");
        int length = random.nextInt(20) + 2;
        for (int i = 0; i < length; i++) {
            builder.append((char) (random.nextInt('z' - 'A' + 1) + 'A'));
        }
        return builder.toString();
    }
}
