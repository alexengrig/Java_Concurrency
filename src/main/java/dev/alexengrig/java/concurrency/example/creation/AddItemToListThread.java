package dev.alexengrig.java.concurrency.example.creation;

import java.util.List;

public class AddItemToListThread extends Thread {
    private final List<String> list;
    private final String item;

    public AddItemToListThread(List<String> list, String item) {
        this.list = list;
        this.item = item;
    }

    @Override
    public void run() {
        list.add(item);
    }
}
