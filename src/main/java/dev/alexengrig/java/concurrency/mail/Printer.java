package dev.alexengrig.java.concurrency.mail;


public class Printer implements Reader {
    @Override
    public void read(String text) {
        System.out.println(text);
    }
}
