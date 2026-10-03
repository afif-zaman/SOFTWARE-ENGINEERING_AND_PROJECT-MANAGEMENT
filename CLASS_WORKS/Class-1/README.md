Day 01 — Static vs Non-Static Variables

Course: Java
Topic: Static and Non-Static Variables

What I Learned

Today I learned the difference between static and non-static (instance) variables in Java.

Static variable → belongs to the class and is shared by all objects.

Non-static variable → belongs to an object, so each object has its own copy.

Static Variable

A static variable is declared using the static keyword.

static int count = 0;


The variable is shared by all objects of the class.

For example, if three objects are created:

Object 1 ─┐
Object 2 ─┼──> count = 3
Object 3 ─┘


Code: StaticCount.java

Non-Static Variable

A non-static variable belongs to an individual object.

int count = 0;


Each object has its own separate copy.

For example:

Object 1 → count = 1
Object 2 → count = 1
Object 3 → count = 1


Code: NonStaticCount.java

Comparison
Static	Non-Static
Belongs to the class	Belongs to the object
Shared by all objects	Separate for each object
One shared copy	One copy per object
Uses the static keyword	Does not use static
Key Takeaway
Static     → One shared copy
Non-static → Separate copy for each object

Files
day-01/
├── README.md
├── StaticCount.java
└── NonStaticCount.java
