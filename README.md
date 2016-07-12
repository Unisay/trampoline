# Stackless Recursion with Trampolines

This repository demonstrates a solution to the stack overflow problem using a method called "Trampolining".

## Intro

### The Java Stack

When a new thread is launched, the Java virtual machine creates a new Java stack for the thread. A Java stack stores a thread's state in discrete frames. 

The Java virtual machine only performs two operations directly on Java Stacks: it pushes and pops frames. When a thread invokes a Java method, the virtual machine creates and pushes a new frame onto the thread's Java stack. This new frame then becomes the current frame. As the method executes, it uses the frame to store parameters, local variables, intermediate computations, and other data.

A method can complete in either of two ways. If a method completes by returning, it is said to have normal completion. If it completes by throwing an exception, it is said to have abrupt completion. When a method completes, whether normally or abruptly, the Java virtual machine pops and discards the method's stack frame. The frame for the previous method then becomes the current frame.

Like the method area and heap, the Java stack and stack frames need not be contiguous in memory. Frames could be allocated on a contiguous stack, or they could be allocated on a heap, or some combination of both. The actual data structures used to represent the Java stack and stack frames is a decision of implementation designers. Implementations may allow users or programmers to specify an initial size for Java stacks, as well as a maximum or minimum size. 

### Difference between Java Heap Space and Stack Memory

* Heap memory is used by all the parts of the application whereas stack memory is used only by one thread of execution.
* Whenever an object is created, it’s always stored in the Heap space and stack memory contains the reference to it. Stack memory only contains local primitive variables and reference variables to objects in heap space.
* Objects stored in the heap are globally accessible whereas stack memory can’t be accessed by other threads.
* Memory management in stack is done in LIFO manner whereas it’s more complex in Heap memory because it’s used globally. Heap memory is divided into Young-Generation, Old-Generation etc, more details at Java Garbage Collection.
* Stack memory is short-lived whereas heap memory lives from the start till the end of application execution.
* One can use -Xms and -Xmx JVM option to define the startup size and maximum size of heap memory. One can use -Xss to define the stack memory size.
* When stack memory is full, Java runtime throws java.lang.StackOverFlowError whereas if heap memory is full, it throws java.lang.OutOfMemoryError: Java Heap Space error.
* Stack memory size is very less when compared to Heap memory. Because of simplicity in memory allocation (LIFO), stack memory is very fast when compared to heap memory.

http://www.journaldev.com/4098/java-heap-space-vs-stack-memory

## Demo

The repository is organized in a set of incremental steps where each step is represented by a git branch prefixed by step number:

### 1. The Stack Overflow Problem (git branch 1-problem)

Recursion is an essential part of functional programming. But if each call allocates a stack frame, then too much recursion will overflow the stack. Most functional programming languages solve this problem by eliminating stack frames through a process called tail-call optimisation. Unfortunately for Scala programmers, the JVM doesn't perform this optimisation. Tail call elimination in scala is limited to a self recursive tail call.

Examples:

1. Non-tail recursive Fibonacci function: Fibonacci.scala
2. Tail recursive function: foldLeft
3. Mutual recursion: even|odd

### 2. Solution: rewrite a non-tail recursive function to use accumulator

Example:

1. Tail-recursive fibonacci

### 3. Solution: use trampolining

Instead of doing a call and consuming a stack frame, we're returning a data structure that captures the call to be done later (in the trampoline loop)


# Links
http://blog.richdougherty.com/2009/04/tail-calls-tailrec-and-trampolines.html
