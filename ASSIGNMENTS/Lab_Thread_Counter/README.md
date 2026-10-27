# Lab: Thread Counter
## Comparison of Static and Non-Static Variables in Multithreading

---

## Student Information

- **Name:** Md. Afif Zaman
- **Student ID:** IT23059
- **Course Name:** Software Management and Project Management


---

# 1. Theory

## 1.1 Introduction

Multithreading is a programming technique that allows multiple threads to execute concurrently within a program. In Java, threads can share class-level variables while maintaining separate instance-level variables.

This experiment investigates the behavior of static and non-static counters when multiple threads perform increment operations. It compares a thread-safe implementation using `AtomicLong` with an unsynchronized implementation using a regular `static long`.

## 1.2 Static Variable

A static variable belongs to the class rather than to an individual object. All instances of the class share the same static variable.

In this experiment, every thread updates the same static counter. When multiple threads increment an ordinary static variable simultaneously without synchronization, race conditions can occur, causing some increments to be lost.

## 1.3 Non-Static Variable

A non-static variable belongs to an individual object. Each object maintains its own copy of the variable.

In this experiment, each thread has its own counter task object and increments its own instance counter. After all threads finish execution, the individual non-static counters are added together to obtain the final total.

## 1.4 Thread Safety and AtomicLong

`AtomicLong` is a Java class that provides atomic operations on a `long` value. Its `incrementAndGet()` method allows multiple threads to update a shared counter safely without losing increments.

This experiment includes two modes:

- **Thread-safe mode:** Uses `AtomicLong` for the shared static counter.
- **Unsynchronized mode:** Uses an ordinary static `long` to demonstrate possible lost updates caused by race conditions.

The non-static counter is maintained separately for each thread.

## 1.5 Mathematical Calculations

Let:

- `N` = number of threads
- `K` = number of increments per thread
- `E` = expected total count
- `S` = final static counter
- `I` = sum of all non-static counters
- `D` = absolute difference
- `P` = percentage difference

### Expected Total Count

The expected total count is:

`E = N * K`

If the implementation is correct, both counters should reach the same expected value:

`S = I = E = N * K`

### Absolute Difference

The absolute difference between the static and non-static counts is:

`D = |S - I|`

### Percentage Difference

The percentage difference relative to the non-static count is:

`P = (|S - I| / I) * 100%`

If `I = 0`, the percentage difference is `0%` when both counts are zero; otherwise, it is undefined.

For the thread-safe experiment, both totals should equal the expected count, resulting in an absolute difference of zero and a percentage difference of `0%`.

For the unsynchronized experiment, the static counter may be lower than the non-static total because concurrent updates can overwrite one another. The absolute and percentage differences must be calculated using the actual program outputs.

## 1.6 Importance of the `join()` Method

The `join()` method makes the main thread wait until a worker thread finishes execution. This ensures that all increment operations are completed before the final counter values are collected and compared.

## 1.7 Race Conditions

A race condition occurs when multiple threads access and modify shared data concurrently without appropriate synchronization. An ordinary increment operation, such as `counter++`, is not atomic.

When multiple threads execute this operation simultaneously, one thread may overwrite another thread's update. As a result, the final static counter can be smaller than the expected total.

## 1.8 Expected Behavior

The thread-safe experiment should produce the expected count for both counters in every test case.

The unsynchronized experiment may produce a smaller static count due to lost updates. However, the exact result depends on thread scheduling, hardware, and runtime conditions.

Increasing the number of threads does not guarantee that the percentage difference will increase monotonically.

---

# 2. Code

## 2.1 Java Source Code

The Java program compares static and non-static counter values under two different conditions:

- Thread-safe counting using `AtomicLong`.
- Unsynchronized counting using an ordinary static `long`.

Each thread performs a specified number of increments. Every thread maintains its own non-static counter, while the static counter is shared by all threads.

After all threads finish, the program calculates the expected count, absolute difference, and percentage difference.

**Java Source Code:** [https://github.com/afif-zaman/SOFTWARE-ENGINEERING_AND_PROJECT-MANAGEMENT/blob/main/ASSIGNMENTS/Lab_Thread_Counter/AfifZaman_Thread.java](YOUR_JAVA_FILE_URL)

## 2.2 Handwritten Java Code

The assignment requires a readable scan or photograph of the handwritten Java source code.

**Handwritten Code:** [https://github.com/afif-zaman/SOFTWARE-ENGINEERING_AND_PROJECT-MANAGEMENT/tree/main/ASSIGNMENTS/Lab_Thread_Counter/handwritten_code](YOUR_HANDWRITTEN_CODE_URL)

## 2.3 Compilation

Compile the Java source file using the following command:

```bash
javac ThreadCounterLab.java
```

## 2.4 Execution

### Thread-Safe Experiment

```bash
java ThreadCounterLab 10 100000 true
```

### Unsynchronized Experiment

```bash
java ThreadCounterLab 10 100000 false
```

The arguments represent:

- First argument: Number of threads.
- Second argument: Number of increments per thread.
- Third argument: Experiment mode (`true` for thread-safe and `false` for unsynchronized).

**Note:** Replace `ThreadCounterLab` with the actual class name if your implementation uses a different name.

---

# 3. Input and Output Images

This section contains the input parameters and execution results for the experiments. The input and output documents are provided as PDF files.

## 3.1 Input PDF

The input PDF documents the test inputs used for the experiments, including the number of threads and increments per thread.

[View Input PDF](./inputs.pdf)

## 3.2 Output PDF

The output PDF contains the actual program execution results obtained from the experiments.

[View Output PDF](./outputs.pdf)

**Note:** Update the PDF links if your actual filenames differ from `input.pdf` and `output.pdf`.

---

# 4. Test Cases

The following test cases investigate the relationship between thread count and counter differences.

| Test Case | Number of Threads | Increments per Thread | Expected Count |
|---|---:|---:|---:|
| TC1 | 1 | 1,000 | 1,000 |
| TC2 | 2 | 10,000 | 20,000 |
| TC3 | 5 | 10,000 | 50,000 |
| TC4 | 10 | 50,000 | 500,000 |
| TC5 | 20 | 50,000 | 1,000,000 |
| TC6 | 50 | 50,000 | 2,500,000 |
| TC7 | 100 | 50,000 | 5,000,000 |

Both thread-safe and unsynchronized modes must be executed for every test case.

The unsynchronized experiment must be repeated at least five times for each thread count. The actual results should be recorded to identify variations between runs.

---

# 5. Result Analysis

The result analysis compares the static counter and the total of all non-static counters for different numbers of threads. It includes the expected count, absolute difference, and percentage difference.

## 5.1 Result Analysis PDF

The following PDF contains the recorded experimental results and the comparison of static and non-static counter values.

[View Result Analysis PDF](./ResultAnalysis.pdf)

---

## 5.2 Thread-Safe Experiment

In the thread-safe experiment, `AtomicLong` ensures that concurrent increments to the shared static counter are not lost.

Expected observations:

- The static counter equals the expected count.
- The non-static total equals the expected count.
- The absolute difference is zero.
- The percentage difference is 0%.

## 5.3 Unsynchronized Experiment

In the unsynchronized experiment, multiple threads update a shared static counter without synchronization.

Possible observations:

- The static counter may be lower than the expected count.
- The non-static total should equal the expected count when all threads complete their increments.
- The absolute difference may be greater than zero.
- The percentage difference may vary between runs.

These outcomes are not guaranteed. Report the actual measured values from your executions.

## 5.4 Repeated Run Analysis

Run the unsynchronized experiment at least five times for each thread count.

Record the static count and calculate the absolute and percentage differences for every run. Compare the results to determine whether the number of lost updates varies between executions.

**Repeated Run Results:** [https://github.com/afif-zaman/SOFTWARE-ENGINEERING_AND_PROJECT-MANAGEMENT/tree/main/ASSIGNMENTS/Lab_Thread_Counter/outputs](YOUR_RESULTS_URL)

---

# 6. Analysis Questions

## 6.1 What Is the Difference Between Static and Non-Static Variables in Java?

A static variable belongs to the class and is shared by all instances of that class. A non-static variable belongs to an individual object, so each object maintains its own copy.

## 6.2 Why Do All Threads Share the Same Static Counter?

The static counter belongs to the class rather than to individual objects. Therefore, all threads accessing that class-level variable operate on the same shared counter.

## 6.3 Why Does Each Thread Have Its Own Non-Static Counter?

Each thread receives a separate counter task object. Since the counter is an instance variable, each object maintains its own independent value.

## 6.4 Why Is `join()` Required Before Calculating the Final Counts?

The `join()` method ensures that the main thread waits for worker threads to finish. Without waiting, the program might calculate the final counts before all increment operations have completed.

## 6.5 Why Can the Unsynchronized Static Count Be Lower Than Expected?

The increment operation `counter++` involves reading, modifying, and writing a value. When multiple threads execute these steps concurrently, updates can overwrite one another, resulting in lost increments.

## 6.6 Does Increasing the Number of Threads Always Increase the Percentage Difference?

No. More threads may increase contention and create more opportunities for lost updates, but the percentage difference does not necessarily increase monotonically. It depends on scheduling, hardware, and runtime conditions.

## 6.7 Why Might Two Runs With the Same Number of Threads Produce Different Results?

Thread scheduling and the timing of concurrent updates can vary between runs. As a result, different executions may experience different numbers of lost updates.

## 6.8 What Changes When `AtomicLong` Is Used Instead of a Regular `long`?

`AtomicLong` provides atomic increment operations, preventing lost updates to the shared counter. An ordinary `long` does not provide this protection when incremented concurrently without synchronization.

## 6.9 How Can All Threads Share One Instance Counter as Well as the Static Counter?

Create one shared counter object and pass a reference to that object to every thread. All threads can then update the same instance variable. Appropriate synchronization or an atomic data type is required to ensure thread-safe updates.

---

# 7. Observation and Conclusion

## 7.1 Observation

The experiment demonstrates the difference between shared class-level variables and object-level instance variables in multithreaded Java programs.

In the thread-safe experiment, the static counter should match the total of the non-static counters because `AtomicLong` protects the shared increment operation.

In the unsynchronized experiment, concurrent updates to the ordinary static counter may cause lost increments. The number of lost updates can vary between repeated executions because thread scheduling and runtime conditions are not identical.

The percentage difference may change as the number of threads increases, but it is not guaranteed to increase in every test case.

## 7.2 Conclusion

This lab demonstrates that static and non-static variables differ in ownership, while thread safety depends on how the variables are accessed and modified.

The static counter is shared by all threads, whereas each thread's non-static counter belongs to its own object. Unsynchronized access to a shared counter can cause race conditions and lost updates. Using `AtomicLong` prevents this problem for the atomic increment operation.

The experiment also shows why repeated testing is necessary when investigating multithreaded behavior. Actual results can vary between runs, so conclusions should be based on recorded program outputs rather than assumptions.

---
