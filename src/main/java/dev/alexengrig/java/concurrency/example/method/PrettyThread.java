package dev.alexengrig.java.concurrency.example.method;

public class PrettyThread extends Thread {
    public PrettyThread(Runnable target) {
        super(target);
    }

    @Override
    public String toString() {
        return "PrettyThread{" +
                String.join(", ",
                        getPrettyId(),
                        getPrettyName(),
                        getPrettyPriority(),
                        getPrettyIsInterrupted(),
                        getPrettyIsAlive(),
                        getPrettyIsDaemon()) +
                "}";
    }

    private String getPrettyId() {
        return "id: " + getId();
    }

    private String getPrettyName() {
        return "name: " + getName();
    }

    private String getPrettyPriority() {
        String prettyPriority = "priority: " + getPriority();
        String allPriority = "(" +
                String.join("-",
                        String.valueOf(Thread.MIN_PRIORITY),
                        String.valueOf(Thread.NORM_PRIORITY),
                        String.valueOf(Thread.MAX_PRIORITY)) +
                ")";
        return prettyPriority + " " + allPriority;
    }

    private String getPrettyIsInterrupted() {
        return "is interrupted: " + isInterrupted();
    }

    private String getPrettyIsAlive() {
        return "is alive: " + isAlive();
    }

    private String getPrettyIsDaemon() {
        return "is daemon: " + isDaemon();
    }
}
