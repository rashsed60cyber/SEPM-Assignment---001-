Thread Counting Using Static and Non-Static Variables
Objective

To compare thread counting using a shared static counter and individual non-static counters in Java.
Technologies

    Java
    Multithreading
    AtomicLong

Program Execution

Compile the program:

javac Raiyyan_Thread.java

Run in unsafe mode:

java Raiyyan_Thread 5 10000 false

Run in thread-safe mode:

java Raiyyan_Thread 5 10000 true

Arguments: numberOfThreads incrementsPerThread true/false

    true: Uses a thread-safe static counter with AtomicLong.
    false: Uses a regular static counter, which may lose increments due to race conditions.

Test Cases
Threads 	Increments per Thread 	Expected Count
1 	1,000 	1,000
2 	10,000 	20,000
5 	10,000 	50,000
10 	50,000 	500,000
20 	50,000 	1,000,000
50 	50,000 	2,500,000
100 	50,000 	5,000,000
Results

Actual outputs and the result recording sheet are included in the results/ directory.
Observation

A static variable is shared among all threads. A non-static counter belongs to an individual thread's counter object. An ordinary shared counter may lose updates when multiple threads access it concurrently. AtomicLong provides thread-safe increment operations.
Conclusion

This experiment demonstrates the difference between shared static counters, individual non-static counters, and thread-safe counting in Java.
