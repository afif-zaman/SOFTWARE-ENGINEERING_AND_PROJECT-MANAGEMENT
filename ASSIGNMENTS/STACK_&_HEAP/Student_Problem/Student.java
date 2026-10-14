import java.util.Scanner;

public class Student {
    int age; // Instance variable

    void display(int marks) {
        // marks is a local variable (method parameter)
        System.out.println("Instance Variable (age): " + age);
        System.out.println("Local Variable (marks): " + marks);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter marks: ");
        int marks = sc.nextInt();

        Student s = new Student();
        s.age = age;

        System.out.println("\nStudent Object Created");
        System.out.println("Object Identity Hash Code: "
                + System.identityHashCode(s));

        s.display(marks);

        sc.close();
    }
}
