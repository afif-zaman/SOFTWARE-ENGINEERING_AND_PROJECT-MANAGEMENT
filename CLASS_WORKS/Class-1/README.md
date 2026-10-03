CLASS 1 — Static vs Non-Static Variables in Java
Topic

Understanding the difference between static and non-static (instance) variables in Java.

1. Static Variable

A static variable belongs to the class, not to individual objects.

There is only one copy of a static variable, and it is shared by all objects of that class.

Example
class StaticCount {
    static int count = 0;

    StaticCount() {
        count++;
    }

    public static void main(String[] args) {
        StaticCount s1 = new StaticCount();
        StaticCount s2 = new StaticCount();
        StaticCount s3 = new StaticCount();

        System.out.println("Number of objects: " + count);
    }
}

Output
Number of objects: 3

Why?

Every time an object is created, the constructor increases the same count variable.

s1 → count = 1
s2 → count = 2
s3 → count = 3


All objects share the same static count.

2. Non-Static Variable

A non-static variable, also called an instance variable, belongs to an individual object.

Every object gets its own separate copy of the variable.

Example
class NonStaticCount {
    int count = 0;

    NonStaticCount() {
        count++;
    }

    public static void main(String[] args) {
        NonStaticCount s1 = new NonStaticCount();
        NonStaticCount s2 = new NonStaticCount();
        NonStaticCount s3 = new NonStaticCount();

        System.out.println("s1 count: " + s1.count);
        System.out.println("s2 count: " + s2.count);
        System.out.println("s3 count: " + s3.count);
    }
}

Output
s1 count: 1
s2 count: 1
s3 count: 1

Why?

Each object has its own count.

s1 → count = 1
s2 → count = 1
s3 → count = 1


Changing one object's count does not change the others.

3. Comparison
Feature	Static	Non-Static
Belongs to	Class	Object
Number of copies	One shared copy	One copy per object
Shared between objects	Yes	No
Access	ClassName.variable	object.variable
Memory	Shared	Separate for each object
Example	static int count	int count
4. Key Difference
Static
static int count = 0;


All objects share the same variable.

Non-Static
int count = 0;


Every object has its own variable.

5. Quick Example

If three objects are created:

Static
Object 1 ─┐
Object 2 ─┼──> count = 3
Object 3 ─┘

Non-Static
Object 1 ──> count = 1
Object 2 ──> count = 1
Object 3 ──> count = 1

6. What I Learned

A static variable belongs to the class.

A non-static variable belongs to an object.

Static variables are shared among all objects.

Each object has its own copy of an instance variable.

A static count can be used to keep track of the total number of objects created.
