Java Lab Assignment: Stack and Heap Memory
Student Information
Name: Md. Afif Zaman
ID: IT23059
Course: Software Engineering and Project Management
Assignment Topic: Stack and Heap Memory in Java
Problem 1: Demonstrating Stack and Heap Memory
Problem Statement

Write a Java program that creates a Student class with an instance variable age and a method display(). Inside the method, declare a local variable marks.

Create a Student object in the main() method and display the instance variable, local variable, and object identity using System.identityHashCode().

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


Note: The identity hash code may differ each time the program runs. It is not the actual memory address of the object.

Java Source Code

Code URL: https://github.com/afif-zaman/SOFTWARE-ENGINEERING_AND_PROJECT-MANAGEMENT/blob/main/ASSIGNMENTS/STACK_%26_HEAP/Student_Problem/Student.java

Theory
1. Stack Memory

Stack memory is used to manage method execution. Each method invocation creates a stack frame that holds information such as local variables and intermediate computation state.

In this program, the display() method executes using a method stack frame. The local variable marks is associated with that method invocation.

2. Heap Memory

Heap memory is used for allocating Java objects and arrays. In this program, the Student object is allocated in heap memory.

3. Instance Variable

An instance variable belongs to an object of a class. In this example, age is an instance variable of the Student class. Each Student object has its own instance field.

4. Local Variable

A local variable is declared inside a method or block. In this program, marks is declared inside the display() method and is associated with that method invocation.

5. Object Identity Hash Code

The System.identityHashCode() method returns the identity hash code of an object, even if the object's class overrides hashCode(). The returned value is not a memory address and is not guaranteed to be unique.

Problem 2: Stack Frames, Heap Objects, and Inner Classes
Problem Statement

Write a Java program that defines an Employee class with an instance variable salary and an instance method display().

Inside the display() method, declare a local variable bonus and create a local inner class named Report with a method show().

The program should:

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


Note: The identity hash codes shown above are illustrative. Actual values may differ between executions and do not represent actual memory addresses.

Java Source Code

Code URL: https://github.com/afif-zaman/SOFTWARE-ENGINEERING_AND_PROJECT-MANAGEMENT/blob/main/ASSIGNMENTS/STACK_%26_HEAP/Employee_Problem/Employee.java

Theory
1. Stack Frames

A stack frame is created when a method is invoked. It holds the information needed for that method's execution, including local variables and intermediate computation state.

In this program, both display() and show() execute using their respective method stack frames.

2. Heap Objects

Java objects are allocated in heap memory. In this program, both the Employee object and the Report object are allocated in heap memory.

3. Instance Variable

The salary variable is an instance field of the Employee class. It belongs to an Employee object and stores that employee's salary.

4. Local Variable

The bonus variable is declared inside the display() method. It is a local variable associated with the method invocation.

5. Local Inner Class

A local inner class is a class declared inside a method or block. In this program, Report is declared inside display() and contains the show() method.

The Report object can access the enclosing Employee object's instance field salary. The show() method displays the employee's salary.

6. Object Identity Hash Code

The System.identityHashCode() method returns an object's identity hash code. This value can help distinguish objects during program execution, but it is not a memory address and is not guaranteed to be unique.

Conclusion

These two Java programs demonstrate fundamental concepts of Java memory management and object-oriented programming.

The first problem explains the relationship between a class, its instance variable, a local variable, and a heap-allocated object.

The second problem extends these concepts by introducing method stack frames and a local inner class. It demonstrates how multiple objects can be created and how methods execute within their own stack frames.

Understanding these concepts helps build a stronger foundation in Java programming, object-oriented design, and memory management.

Submitted by: Md. Afif Zaman
Student ID: IT23059
