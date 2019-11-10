# Thread methods

## Sleep

`Thread.sleep()` method can be used to pause the execution of current thread
for specified time in milliseconds ('real timeout' >= timeout argument).
The argument value for milliseconds can’t be negative, else it throws IllegalArgumentException.

## Interrupt

`new Thread().interrupt()`, if (_thread_ is sleep) throw InterruptedException; else _thread_.isInterrupted is true.
