\# Thread Counter Lab



Name:Abu Bakor
Student ID: IT24014

Section: 2023-2024

\## Objective



To compare a shared static counter with separate non-static counters

using safe and unsafe multithreading.



\## Environment



Windows

Java JDK 21.0.12.1



\## How the Program Works



Each thread uses a separate object with its own instance counter.

All threads share the static counter.



Safe mode uses AtomicLong.

Unsafe mode uses a regular static long.



The program uses join() to wait for all threads to finish.

It then adds all instance counters to calculate the non-static total.



\## Test Inputs



| Threads | Increments per thread | Expected count |

|---|---|---|

| 1 | 1000 | 1000 |

| 2 | 10000 | 20000 |

| 5 | 10000 | 50000 |

| 10 | 50000 | 500000 |

| 20 | 50000 | 1000000 |

| 50 | 50000 | 2500000 |

| 100 | 50000 | 5000000 |



\## Safe Experiment



Each test was executed in safe mode.

The actual outputs are stored in outputs/safe\_\*.txt.



With AtomicLong, both totals should match the expected count.

The expected percentage difference is 0%.



\## Unsafe Experiment: Actual Results



Each test was executed five times.

The non-static total matched the expected count in every run.



| Threads | Run | Static count | Non-static total | Absolute difference | Difference (%) |

|---|---|---|---|---|---|

| 1 | 1 | 1000 | 1000 | 0 | 0.0000 |

| 1 | 2 | 1000 | 1000 | 0 | 0.0000 |

| 1 | 3 | 1000 | 1000 | 0 | 0.0000 |

| 1 | 4 | 1000 | 1000 | 0 | 0.0000 |

| 1 | 5 | 1000 | 1000 | 0 | 0.0000 |

| 2 | 1 | 11097 | 20000 | 8903 | 44.5150 |

| 2 | 2 | 14283 | 20000 | 5717 | 28.5850 |

| 2 | 3 | 13104 | 20000 | 6896 | 34.4800 |

| 2 | 4 | 11945 | 20000 | 8055 | 40.2750 |

| 2 | 5 | 12827 | 20000 | 7173 | 35.8650 |

| 5 | 1 | 15302 | 50000 | 34698 | 69.3960 |

| 5 | 2 | 16588 | 50000 | 33412 | 66.8240 |

| 5 | 3 | 14846 | 50000 | 35154 | 70.3080 |

| 5 | 4 | 14705 | 50000 | 35295 | 70.5900 |

| 5 | 5 | 14055 | 50000 | 35945 | 71.8900 |

| 10 | 1 | 96731 | 500000 | 403269 | 80.6538 |

| 10 | 2 | 101484 | 500000 | 398516 | 79.7032 |

| 10 | 3 | 121934 | 500000 | 378066 | 75.6132 |

| 10 | 4 | 110304 | 500000 | 389696 | 77.9392 |

| 10 | 5 | 113684 | 500000 | 386316 | 77.2632 |

| 20 | 1 | 221265 | 1000000 | 778735 | 77.8735 |

| 20 | 2 | 169699 | 1000000 | 830301 | 83.0301 |

| 20 | 3 | 239058 | 1000000 | 760942 | 76.0942 |

| 20 | 4 | 215217 | 1000000 | 784783 | 78.4783 |

| 20 | 5 | 254219 | 1000000 | 745781 | 74.5781 |

| 50 | 1 | 694276 | 2500000 | 1805724 | 72.2290 |

| 50 | 2 | 1327669 | 2500000 | 1172331 | 46.8932 |

| 50 | 3 | 983846 | 2500000 | 1516154 | 60.6462 |

| 50 | 4 | 957889 | 2500000 | 1542111 | 61.6844 |

| 50 | 5 | 2016858 | 2500000 | 483142 | 19.3257 |

| 100 | 1 | 3302516 | 5000000 | 1697484 | 33.9497 |

| 100 | 2 | 3603228 | 5000000 | 1396772 | 27.9354 |

| 100 | 3 | 3561153 | 5000000 | 1438847 | 28.7769 |

| 100 | 4 | 4185632 | 5000000 | 814368 | 16.2874 |

| 100 | 5 | 3588237 | 5000000 | 1411763 | 28.2353 |



\## Average Unsafe Percentage Difference



Averages were calculated using the original counts.



| Threads | Average difference (%) |

|---|---|

| 1 | 0.0000 |

| 2 | 36.7440 |

| 5 | 69.8016 |

| 10 | 78.2345 |

| 20 | 78.0108 |

| 50 | 52.1557 |

| 100 | 27.0369 |



\## Calculations



Expected count = Threads × Increments per thread



Absolute difference = |Static count - Non-static total|



Difference (%) = Absolute difference / Non-static total × 100



Example: 100 threads, Run 1



Absolute difference = 5000000 - 3302516 = 1697484



Difference (%) = 1697484 / 5000000 × 100 = 33.94968%



Rounded result = 33.9497%



When both totals are zero, the percentage difference is zero.

If only the non-static total is zero, the percentage is undefined.



\## Analysis Answers



1\. What is the difference between static and non-static variables?



A static variable belongs to the class and is shared by its objects.

A non-static variable belongs to an individual object.



2\. Why do all threads share the static counter?



All worker objects access the same class-level counter.



3\. Why does each thread have its own non-static counter?



Each thread uses a separate object.



4\. Why is join() required?



It waits for each thread to finish and makes its completed

updates visible before the final total is calculated.



5\. Why can the unsafe static count be lower?



The ++ operation is not atomic. Threads can overwrite each

other's updates, causing lost increments.



6\. Does increasing threads always increase the percentage difference?



No. The average difference was 78.2345% for 10 threads,

but only 27.0369% for 100 threads.



7\. Why do repeated runs produce different results?



Thread scheduling and execution overlap vary between runs.



8\. What changes when AtomicLong is used?



Each increment becomes atomic, preventing lost increments.



9\. How can threads share one instance counter?



Give all threads the same counter object and protect updates

using synchronization or atomic operations.



\## Observation



With one thread, unsafe counting produced no lost increments.



With multiple threads, unsafe static counts were lower than

the expected counts. Repeated runs produced different results.



The average percentage difference increased from 1 to 10

threads, then decreased for the larger thread counts tested.



Therefore, increasing thread count does not always increase

the percentage difference. Scheduling, hardware and runtime

conditions can affect the results.



The increments per thread also differed between some test cases,

so thread count was not the only changing input.



\## Conclusion



Static and non-static describe ownership, not thread safety.



Separate instance counters were correct because each had one writer.

Unsafe shared counting lost increments.

AtomicLong provides thread-safe shared counting.



\## Files



\- Rashed\_Thread.java: Java source code.

\- outputs/: actual output text files and screenshots.

\- handwritten/: photographs of handwritten code.

\- report.pdf: final lab report.

