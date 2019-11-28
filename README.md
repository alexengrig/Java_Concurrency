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

> To preserve state consistency, update related state variables in a single atomic operation.

Intrinsic Locks:

> ```java
> synchronized (lock) {
>     // Access or modify shared state guarded by lock
> }
> ```

Guarding State with Locks:

> For each mutable state variable that may be accessed by more than one thread,
> all accesses to that variable must be performed with the same lock held.
> In this case, we say that the variable is guarded by that lock.

> Every shared, mutable variable should be guarded by exactly one lock.
> Make it clear to maintainers which lock that is.

> For every invariant that involves more than one variable,
> all the variables involved in that invariant must be guarded by the same lock.

Simplicity and performance:

> There is frequently a tension between simplicity and performance.
> When implementing a synchronization policy, resist the temptation
> to prematurely sacrifice simplicity (potentially compromising safety) for the sake of performance.

> Avoid holding locks during lengthy computations or operations at risk
> of not completing quickly such as network or console I/O.

Visibility:

> In the absence of synchronization, the compiler, processor,
> and runtime can do some downright weird things to the order in which operations appear to execute.
> Attempts to reason about the order in which memory actions "must" happen in
> insufficiently synchronized multithreaded programs will almost certainly be incorrect.

Locking and Visibility:

> Locking is not just about mutual exclusion; it is also about memory visibility.
> To ensure that all threads see the most up-to-date values of shared mutable variables,
> the reading and writing threads must synchronize on a common lock.
>
> ![Visibility Guarantees for Synchronization](doc/image/visibility_guarantees_for_synchronization.png)

Volatile Variables:

> Use volatile variables only when they simplify implementing and verifying your synchronization policy; avoid using
> volatile variables when verifying correctness would require subtle reasoning about visibility. Good uses of volatile
> variables include ensuring the visibility of their own state, that of the object they refer to, or indicating that an
> important lifecycle event (such as initialization or shutdown) has occurred.

Visibility and atomicity:

> Locking can guarantee both visibility and atomicity; volatile variables can only guarantee visibility.

Safe Construction:

> Do not allow the this reference to escape during construction.

Immutability:

> Immutable objects are always thread-safe.

> An object is immutable if:
> - Its state cannot be modified after construction;
> - All its fields are final; and
> - It is properly constructed (the this reference does not escape during construction).

Final Fields:

> Just as it is a good practice to make all fields private unless they need greater visibility,
> it is a good practice to make all fields final unless they need to be mutable.

Immutable objects:

> Immutable objects can be used safely by any thread without additional synchronization,
> even when synchronization is not used to publish them.

Safe Publication Idioms:

> To publish an object safely, both the reference to the object and the object's state must be made visible to other
> threads at the same time. A properly constructed object can be safely published by:
> - Initializing an object reference from a static initializer;
> - Storing a reference to it into a volatile field or AtomicReference;
> - Storing a reference to it into a final field of a properly constructed object; or
> - Storing a reference to it into a field that is properly guarded by a lock.

> Using a static initializer is often the easiest and safest way to publish objects that can be statically constructed:
> ```java
> public static Holder holder = new Holder(42);
> ```

Effectively Immutable Objects:

> Safely published effectively immutable objects can be used safely by any thread without additional synchronization

## Status

In progress.
