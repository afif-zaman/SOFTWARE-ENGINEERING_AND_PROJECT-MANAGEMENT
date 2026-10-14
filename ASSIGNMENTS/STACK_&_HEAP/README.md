Java Lab Practice
Overview

This repository contains two Java lab programs demonstrating stack memory, heap memory, instance variables, local variables, object identity hash codes, and local inner classes.

Lab 1: Demonstrating Stack and Heap Memory
Objective

To demonstrate Java objects, instance variables, local variables, and object identity hash codes.

Concepts Covered
Classes and objects
Instance variables
Local variables
Stack and heap memory
System.identityHashCode()
User input using Scanner
Sample Input
Enter age: 20
Enter marks: 85

Sample Output
Student Object Created
Object Identity Hash Code: 12345678
Instance Variable (age): 20
Local Variable (marks): 85

Lab 2: Stack Frames, Heap Objects, and Inner Classes
Objective

To demonstrate stack frames, heap objects, method parameters, and local inner classes.

Concepts Covered
Constructors and methods
Instance variables and local variables
Heap memory and stack frames
Local inner classes
Object identity hash codes
User input using Scanner
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


Note: Identity hash codes are illustrative and may differ between executions. They are not actual memory addresses.

Memory Concepts
Heap: Stores objects created using new.
Instance variables: Belong to their respective objects.
Local variables: Exist within a method's scope.
Stack frames: Represent method execution in the conventional JVM execution model.
Identity hash code: Identifies an object for hashing purposes; it is not a memory address.
Technologies Used
Java
IntelliJ IDEA
GitHub
How to Run
Open the project in IntelliJ IDEA.
Configure a Java Development Kit (JDK).
Open Student.java or Employee.java.
Click the green Run icon beside main().
Enter the requested values in the Run console.
Conclusion

These programs demonstrate fundamental Java programming concepts, object creation, method execution, memory organization, and local inner classes.

Repository Files
Student.java — Stack and heap memory demonstration.
Employee.java — Stack frames, heap objects, and inner classes.
README.md — Assignment documentation.
