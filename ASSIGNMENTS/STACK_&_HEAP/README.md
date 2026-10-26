Java Lab Practice: Stack and Heap Memory

A collection of Java lab practice problems designed to demonstrate stack memory, heap memory, instance variables, local variables, object identity, method execution frames, and local inner classes.

📚 Table of Contents
Overview
Problem 1: Demonstrating Stack and Heap Memory
Problem 2: Stack Frames, Heap Objects, and Inner Classes
Key Java Memory Concepts
Requirements
How to Compile and Run
Learning Objectives
Overview

This project contains two Java programming exercises, ranging from beginner to intermediate level. Each exercise focuses on understanding how Java manages objects, variables, and method execution.

The programs use System.identityHashCode() to display object identity hash codes. These values help identify objects during program execution but are not actual memory addresses.

Problem 1: Demonstrating Stack and Heap Memory

Level: Beginner
Marks: 1 point (problem statement) / 3 points (mark distribution)

Problem Statement

Write a Java program that creates a Student class with an instance variable age and a method display(). Inside the method, declare a local variable marks.

In the main() method, create a Student object and display the instance variable, local variable, and object identity using System.identityHashCode().

Sample Input
text
Age: 20
Marks: 85

Sample Output
text
Student Object Created
Object Identity Hash Code: 12345678
Instance Variable (age): 20
Local Variable (marks): 85


Note: The identity hash code may differ each time the program runs. It does not represent the actual memory address of the object.

Mark Distribution (3 Points)
Criteria	Marks
Correctly define the Student class and instance variable	1
Declare and display the local variable inside display()	1
Create the object and display its identity hash code	1
Total	3
Problem 2: Stack Frames, Heap Objects, and Inner Classes

Level: Intermediate
Marks: 3 points

Problem Statement

Write a Java program that defines an Employee class with an instance variable salary and an instance method display().

Inside display():

Declare a local variable bonus.
Create a local inner class named Report.
Define a method show() inside the Report class.

Your program should:

Create an Employee object and display its identity hash code.
Display the instance variable salary and local variable bonus.
Create a Report object and display its identity hash code.
Call the show() method to display a message containing the employee's salary.
Sample Input
text
Enter salary: 30000
Enter bonus: 5000

Sample Output
text
Employee Object Created
Employee Identity Hash Code: 12345678

Inside display() Method:
Salary (Instance Variable): 30000
Bonus (Local Variable): 5000

Report Object Created
Report Identity Hash Code: 87654321
Report: Employee salary is 30000


Note: The identity hash codes shown above are illustrative. Actual values may differ between executions and are not actual memory addresses.

Memory Concepts
Component	Description
Employee object	Stored in heap memory
Report object	Stored in heap memory
salary	Instance field belonging to the Employee object
bonus	Local variable declared inside display()
display()	Executes using a method stack frame
show()	Executes using its own method stack frame when invoked
Mark Distribution (3 Points)
Criteria	Marks
Correctly implement the Employee class, instance variable, and display() method	1
Implement the local Report class and its show() method	1
Display object identity hash codes and correctly identify the instance field, local variable, and method execution frame	1
Total	3
Key Java Memory Concepts
1. Stack Memory

Stack memory supports method execution. Each method invocation has a stack frame that holds information such as local variables and intermediate computation state.

2. Heap Memory

Heap memory is where Java objects and arrays are allocated. In these exercises, the Student, Employee, and Report instances are heap-allocated objects.

3. Instance Variables

Instance variables belong to individual objects. Each Student object has its own age field, and each Employee object has its own salary field.

4. Local Variables

Local variables are declared inside methods or other blocks. In these exercises, marks is declared inside display() for the Student class, while bonus is declared inside display() for the Employee class.

Local variables are associated with the method invocation in which they are declared. Their
