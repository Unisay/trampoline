# Stackless Recursion with Trampolines

Demonstration how to avoid running out of stack in a non-tail recursive function by using Trampolines.

* Tail call elimination in scala is limited to a self recursive tail call
* Instead of doing a call and consuming a stack frame, we're returning a data structure that captures the call to be done later (in the trampoline loop)
