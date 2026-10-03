CLASS 01 — TOPIC : Static vs Non-Static Variables

Course name: SOFTWARE ENGINEERING AND PROJECT MANAGEMENT 


Overview

Today I learned the difference between static and non-static (instance) variables in Java.

The main difference is where the variable belongs:

A static variable belongs to the class and is shared by all objects.

A non-static variable belongs to an individual object, so every object has its own copy.

Static Variable

A static variable is declared using the static keyword.

static int count = 0;


There is only one shared copy of this variable for the entire class.

For example, if three objects are created and the constructor increases count each time:

Object 1 ─┐
Object 2 ─┼──> shared count = 3
Object 3 ─┘

Code

See StaticCount.java.

The program creates three objects and uses a static count to keep track of the total number of objects created.

Output
Number of objects: 3

Non-Static Variable

A non-static variable is an instance variable.

int count = 0;


Each object gets its own separate copy of the variable.

Object 1 ──> count = 1

Object 2 ──> count = 1

Object 3 ──> count = 1

Code

See NonStaticCount.java.

Each object has its own count, so creating three objects results in three separate variables.

Output
s1 count: 1
s2 count: 1
s3 count: 1

Comparison
	Static	Non-Static
Belongs to	Class	Object
Shared between objects	Yes	No
Copies	One shared copy	One copy per object
Keyword	static	No static keyword
Example	static int count	int count
Useful for	Class-level/shared data	Object-specific data
Simple Way to Remember
static     → one copy → shared by all objects

non-static → separate copy → each object

What I Learned

The important concept from today's class is that static changes the ownership of a variable.

If the data should be shared across all objects of a class, a static variable can be used.

If every object needs its own separate value, the variable should be non-static.

Files
Class-1/
├── README.md
├── StaticCount.java
└── NonStaticCount.java

Programs

StaticCount.java — Demonstrates a shared static count.

NonStaticCount.java — Demonstrates an object-specific non-static count.
