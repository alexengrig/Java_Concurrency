# Runnable vs Thread

## Runnable

A class implements the `Runnable` interface:

- can inherit a class other than the `Thread`;
- needs to be passed to the `Thread` constructor.

## Thread

A class extends the `Thread` class:

- contains thread control methods.

### Current thread

Current thread object can be obtained anywhere - `Thread.currentThread()`.
