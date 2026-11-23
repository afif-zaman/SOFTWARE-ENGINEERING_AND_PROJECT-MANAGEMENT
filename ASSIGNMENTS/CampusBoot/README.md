

# CampusBoot — Java Reflection & Custom Annotations

## Software Engineering and Project Management

**Assignment:** Build CampusBoot with Custom Annotations  
**Topic:** Java Reflection, Custom Annotations & Mini IoC Framework  
**Language:** Java  
**JDK Used:** Java 25  
**IDE:** IntelliJ IDEA Ultimate 2026.1.1  
**Build System:** IntelliJ IDEA (No External Libraries)

---

## Student Information

| Information | Details |
|---|---|
| **Student Name** | Afif Zaman |
| **Student ID** | IT23059 |
| **Course Name** | Software Engineering and Project Management |

---

## 1. Project Overview

CampusBoot is a lightweight Java framework developed to demonstrate the fundamental concepts behind Spring Boot.

The project uses **Java Reflection and Custom Annotations** to implement basic framework functionality without using Spring Boot or any external libraries.

The application demonstrates how annotations can be detected and processed at runtime to perform automatic object creation, configuration injection, data validation, and method routing.

### Project Objectives

- Understand Java Reflection and runtime annotation processing.
- Create custom Java annotations.
- Implement a simple Inversion of Control (IoC) container.
- Inject configuration values into class fields.
- Validate object fields using custom constraints.
- Map URL paths to Java methods.
- Understand how Spring Boot implements similar concepts.

---

## 2. Technologies Used

| Technology | Purpose |
|---|---|
| Java 25 | Programming language |
| Java Reflection API | Runtime class, field, and method inspection |
| Java Annotations | Metadata-based framework configuration |
| Java Collections Framework | Bean storage and validation results |
| IntelliJ IDEA Ultimate | Development environment |
| Git & GitHub | Version control and project submission |

**Note:** The project requires Java 11 or later and does not use Spring Boot, Maven dependencies, or external libraries.

---

## 3. Project Features

### 3.1 Custom Annotations

The framework implements four custom annotations.

| Annotation | Target | Purpose |
|---|---|---|
| `@Component` | Class | Identifies classes managed by the container |
| `@Value` | Field | Injects configuration values |
| `@Range` | Field | Validates integer field values |
| `@Route` | Method | Maps request paths to Java methods |

All annotations use runtime retention so they can be accessed through Java Reflection.

### 3.2 Inversion of Control Container

The `Container` class automatically:

- Scans supplied classes.
- Identifies classes annotated with `@Component`.
- Creates instances using no-argument constructors.
- Registers objects in a `Map<String, Object>`.
- Skips classes without `@Component`.
- Retrieves registered objects using their bean names.

### 3.3 Configuration Value Injection

The framework supports injecting configuration values into fields annotated with `@Value`.

Supported data types:

- `String`
- `int`

Example:

```java
@Value("app.name")
private String appName;

@Value("app.passMark")
private int passMark;
```

Configuration values are supplied through a `Map<String, String>`.

Java Reflection is used to access and update the annotated fields.

### 3.4 Data Validation

The `@Range` annotation validates integer values within specified limits.

Example:

```java
@Range(
    min = 0,
    max = 100,
    message = "Score must be between 0 and 100"
)
private int score;
```

If the value falls outside the allowed range, the validator returns a violation message.

### 3.5 Method Routing

The `@Route` annotation associates a request path with a Java method.

Example:

```java
@Route("/hello")
public String hello() {
    return "Welcome to " + appName;
}
```

The router searches registered beans and invokes the matching method using Reflection.

If no matching route exists, it returns a 404 message.

**Note:** CampusBoot simulates routing through Java method calls. It does not start an HTTP server.

---

## 4. Project Structure

```text
CampusBoot/
│
├── src/
│   ├── Component.java
│   ├── Value.java
│   ├── Range.java
│   ├── Route.java
│   │
│   ├── Container.java
│   ├── Validator.java
│   ├── Router.java
│   │
│   ├── GradeController.java
│   ├── ReportPrinter.java
│   ├── Student.java
│   └── Main.java
│
├── answers.txt
└── README.md
```

### File Descriptions

| File | Description |
|---|---|
| `Component.java` | Defines the component annotation |
| `Value.java` | Defines the configuration injection annotation |
| `Range.java` | Defines the integer validation annotation |
| `Route.java` | Defines the method routing annotation |
| `Container.java` | Implements object registration and value injection |
| `Validator.java` | Validates fields using Reflection |
| `Router.java` | Dispatches paths to annotated methods |
| `GradeController.java` | Example managed component |
| `ReportPrinter.java` | Example class without component annotation |
| `Student.java` | Example class containing validation constraints |
| `Main.java` | Application entry point |
| `answers.txt` | Answers to the reflection questions |

---

## 5. How the Framework Works

### Step 1: Component Scanning

The application scans the supplied classes.

```java
container.scan(
    GradeController.class,
    ReportPrinter.class
);
```

Classes annotated with `@Component` are registered automatically.

### Step 2: Configuration Injection

The application provides configuration values:

```java
Map<String, String> config = new HashMap<>();

config.put("app.name", "CampusBoot");
config.put("app.passMark", "40");

container.injectValues(config);
```

The container injects these values into matching annotated fields.

### Step 3: Data Validation

The validator examines fields annotated with `@Range`.

Example:

```java
Student student = new Student(120, 5);

List<String> errors = Validator.validate(student);
```

Because the score exceeds 100, a validation violation is generated.

### Step 4: Route Dispatching

The router invokes methods associated with specific paths.

```java
Router.dispatch(container, "/hello");
```

Expected response:

```text
Welcome to CampusBoot
```

---

## 6. How to Run the Project

### Requirements

- Java JDK 11 or later
- IntelliJ IDEA or another Java-compatible IDE

### Running with IntelliJ IDEA

1. Download the CampusBoot ZIP file.
2. Extract the ZIP archive.
3. Open IntelliJ IDEA.
4. Select **File > Open**.
5. Choose the extracted `CampusBoot` folder.
6. Configure the project SDK.
7. Mark the `src` folder as **Sources Root**, if necessary.
8. Open `Main.java`.
9. Click **Run Main.main()**.

The application will execute in the IntelliJ Run console.

### Running from the Command Line

Open a terminal inside the project directory.

Compile:

```bash
javac -d out src/*.java
```

Run:

```bash
java -cp out Main
```

---

## 7. Expected Program Output

```text
[Container] Registered: gradeController (GradeController)
[Container] Skipped: ReportPrinter (no @Component)
[Container] Injected: app.name -> GradeController.appName
[Container] Injected: app.passMark -> GradeController.passMark
--- Validation ---
VIOLATION: score - Score must be between 0 and 100 (actual: 120)
OK
VIOLATION: semester - Value out of range (actual: 15)
--- Routing ---
[Router] /hello -> Welcome to CampusBoot
[Router] /status -> Pass mark is 40
[Router] /unknown -> 404 Not Found: /unknown
```

### Execution Result

The application was compiled and executed successfully using Java 25 in IntelliJ IDEA.

The observed console output matched the assignment's provided sample output.

---

## 8. Understanding Java Reflection

Java Reflection allows a program to inspect classes, fields, methods, constructors, and annotations at runtime.

CampusBoot uses Reflection for the following operations:

| Reflection Operation | Purpose |
|---|---|
| `isAnnotationPresent()` | Detects annotations |
| `getAnnotation()` | Retrieves annotation values |
| `getDeclaredFields()` | Inspects class fields |
| `getDeclaredMethods()` | Inspects class methods |
| `getDeclaredConstructor()` | Finds constructors |
| `newInstance()` | Creates objects through constructors |
| `setAccessible(true)` | Enables access to private members |
| `Field.set()` | Injects field values |
| `Method.invoke()` | Executes annotated methods |

These operations demonstrate the basic mechanisms used by annotation-driven Java frameworks.

---

## 9. CampusBoot vs Spring Boot

| Feature | CampusBoot | Spring Boot |
|---|---|---|
| Component management | Basic IoC container | Advanced IoC container |
| Dependency injection | Configuration value injection | Full dependency injection |
| Validation | Custom integer range validation | Comprehensive validation support |
| Routing | Reflection-based method dispatch | HTTP request mapping |
| Configuration | Java Map | Properties, YAML, environment variables, etc. |
| Web server | Not included | Embedded server support |
| External dependencies | None | Spring ecosystem |

CampusBoot is an educational implementation designed to explain framework fundamentals.

It is not intended to replace Spring Boot in production applications.

---

## 10. Learning Outcomes

Through this assignment, the following concepts were explored:

1. Defining custom Java annotations.
2. Understanding annotation retention and targets.
3. Inspecting Java classes through Reflection.
4. Creating objects dynamically.
5. Implementing basic Inversion of Control.
6. Injecting configuration values.
7. Validating object fields.
8. Invoking methods dynamically.
9. Understanding the internal mechanisms of annotation-driven frameworks.

---

## 11. Assignment Submission

**Assignment Title:** Build CampusBoot with Custom Annotations

**Submission Format:** ZIP archive

**ZIP Filename:**

```text
IT23059_CampusBoot.zip
```

The submission archive contains the Java source files and `answers.txt`.

### GitHub Repository

[Software Engineering and Project Management](https://github.com/afif-zaman/SOFTWARE-ENGINEERING_AND_PROJECT-MANAGEMENT)

**Project Location:**

```text
ASSIGNMENTS/CampusBoot/
```

---

## 12. Conclusion

CampusBoot demonstrates how a small Java framework can implement fundamental annotation-driven features using Java Reflection.

The project provides practical experience with custom annotations, object creation, configuration injection, validation, and routing.

By implementing these features without external libraries, the assignment helps explain several foundational ideas behind Spring Boot and other Java frameworks.

---

## Author

**Afif Zaman**  
**Student ID:** IT23059  
**Course:** Software Engineering and Project Management

**GitHub:** [afif-zaman](https://github.com/afif-zaman)

---

**CampusBoot — Understanding Spring Boot Concepts Through Java Reflection**
