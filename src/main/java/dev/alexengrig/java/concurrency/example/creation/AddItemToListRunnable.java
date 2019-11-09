package dev.alexengrig.java.concurrency.example.creation;

import java.util.List;

public class AddItemToListRunnable implements Runnable {
    private final List<String> list;
    private final String item;

    public AddItemToListRunnable(List<String> list, String item) {
        this.list = list;
        this.item = item;
    }

    @Override
    public void run() {
        list.add(item);
    }
}
