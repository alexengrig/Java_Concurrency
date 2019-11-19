# Java Concurrency

The education project.

## Books

### [Java Concurrency in Practice](https://www.amazon.com/gp/product/0321349601)

#### Quotes

The same mutable state:

> If multiple threads access the same mutable state variable without appropriate synchronization, 
> your program is broken. There are three ways to fix it:
> - Don't share the state variable across threads;
> - Make the state variable immutable; or
> - Use synchronization whenever accessing the state variable

Best friends:

> When designing thread-safe classes, good object-oriented techniques - encapsulation, immutability, 
> and clear specification of invariants - are your best friends.

Thread-safe class:

> A class is thread-safe if it behaves correctly when accessed from multiple threads, regardless of 
> the scheduling or interleaving of the execution of those threads by the runtime environment, 
> and with no additional synchronization or other coordination on the part of the calling code.

> Thread-safe classes encapsulate any needed synchronization so that clients need not provide their own.

> Stateless objects are always thread-safe.

Atomicity:

> Operations A and B are atomic with respect to each other if, from the perspective of a thread executing A,
> when another thread executes B, either all of B has executed or none of it has.
> An atomic operation is one that is atomic with respect to all operations, including itself,
> that operate on the same state.

> Where practical, use existing thread-safe objects, like AtomicLong, to manage your class's state.
> It is simpler to reason about the possible states and state transitions for existing thread-safe objects
> than it is for arbitrary state variables, and this makes it easier to maintain and verify thread safety.

## Status

In progress.
