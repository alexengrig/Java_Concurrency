package dev.alexengrig.java.concurrency.example.creation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddItemToListRunnableTest {
    @Test
    public void check() {
        List<String> list = new ArrayList<>();
        String item = "an item";
        Runnable addItemToList = new AddItemToListRunnable(list, item);
        Thread addItemToListThread = new Thread(addItemToList);
        assertTrue(list.isEmpty());
        addItemToListThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(list.isEmpty());
    }
}