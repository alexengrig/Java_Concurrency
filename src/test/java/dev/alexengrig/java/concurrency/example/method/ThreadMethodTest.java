package dev.alexengrig.java.concurrency.example.method;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThreadMethodTest {
    @Test
    public void checkIdGet() {
        Thread thread = new Thread();
        long id = thread.getId();
        assertTrue(id > -1);
    }

    @Test
    public void checkNameGet() {
        String expected = "Custom thread name";
        Thread thread = new Thread(expected);
        assertEquals(expected, thread.getName());
    }

    @Test
    public void checkPriorityGet() {
        Thread thread = new Thread();
        assertEquals(Thread.NORM_PRIORITY, thread.getPriority());
    }

    @Test
    public void checkCustomPriorityGet() {
        int expected = 3;
        Thread thread = new Thread();
        thread.setPriority(expected);
        assertEquals(expected, thread.getPriority());
    }
}
