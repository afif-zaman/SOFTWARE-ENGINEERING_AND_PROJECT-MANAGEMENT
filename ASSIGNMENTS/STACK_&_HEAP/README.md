Java Lab Practice
Overview

This repository contains two Java lab practice programs demonstrating object-oriented programming and Java memory concepts, including stack memory, heap memory, instance variables, local variables, object identity hash codes, method frames, and local inner classes.

Lab 1: Demonstrating Stack and Heap Memory in Java
Objective

To create a Student class and demonstrate instance variables, local variables, object creation, and object identity hash codes.

Concepts Covered
Classes and objects
Instance variables
Local variables
Object creation using the new keyword
System.identityHashCode()
Stack and heap memory concepts
Keyboard input using Scanner
Program Description

The program defines a Student class with an instance variable age and a display() method. The method displays the student's age and marks. The main() method accepts age and marks as user input, creates a Student object, and displays its identity hash code.

Sample Input
Enter age: 20
Enter marks: 85

Sample Output
Student Object Created
Object Identity Hash Code: 12345678
Instance Variable (age): 20
Local Variable (marks): 85


Note: The identity hash code shown is illustrative and may differ between executions.

Lab 2: Demonstrating Stack Frames, Heap Objects, and Inner Classes
Objective

To demonstrate instance variables, local variables, heap objects, method execution frames, and local inner classes in Java.

Concepts Covered
Classes and objects
Instance variables and method parameters
Stack frames and heap memory
Local inner classes
Object identity hash codes
Methods and constructors
Keyboard input using Scanner
Program Description

The program defines an Employee class with an instance variable salary and a display() method that receives bonus as a parameter. Inside display(), a local inner class named Report is declared with a show() method.

The program accepts salary and bonus from the user, creates an Employee object and a Report object, displays their identity hash codes, and prints a report containing the employee's salary.

Sample Input
Enter salary: 30000
Enter bonus: 5000

Sample Output
Employee Object Created
Employee Identity Hash Code: 12345678

Inside display() Method:
Salary (Instance Variable): 30000
Bonus (Local Variable): 5000

Report Object Created
Report Identity Hash Code: 87654321
Report: Employee salary is 30000


Note: The identity hash codes shown are illustrative and may differ between executions.

Memory Concepts
Component	Description
Employee and Student objects	Objects allocated on the heap
Report object	Local inner class instance allocated on the heap
age and salary	Instance variables stored as part of their respective objects
marks and bonus	Local variables or method parameters associated with method execution
Method calls	Execute using method frames in the conventional JVM execution model
System.identityHashCode()	Returns an object's identity hash code, not its actual memory address

Local variables and references are commonly associated with stack frames in the JVM execution model, although JVM implementations may optimize how they are stored.

Technologies Used
Java
IntelliJ IDEA
Java Scanner class
GitHub for version control and code submission
How to Run the Programs
Open the project in IntelliJ IDEA.
Ensure a Java Development Kit (JDK) is configured.
Open Student.java or Employee.java.
Click the green Run icon beside the main() method.
Enter the requested values in the Run console.
Observe the program output.
Conclusion

These programs demonstrate fundamental Java programming concepts and explain the relationship between objects, instance variables, local variables, method execution, and heap memory. They also illustrate how identity hash codes can be used to examine object identity without representing actual memory addresses.

Repository Contents

Student.java — Stack and heap memory demonstration.
Employee.java — Stack frames, heap objects, and local inner class demonstration.
README.md — Assignment description, sample inputs and outputs, and instructions.
