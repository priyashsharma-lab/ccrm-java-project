Campus Course & Records Manager (CCRM)

1. Project Overview

For this project, I created a console application using Java SE that works as a simple information system for a small college. It's a command-line tool that lets you manage all the basic records: student and instructor profiles, the course catalog, and enrollments.
The program is built with a focus on good object-oriented design, with the code separated into different layers for data, services, and the user interface.

Core Features:

1. Student Management: Add, list, update, or deactivate students and view their profiles.

2. Instructor Management: Keep a list of instructors who can be assigned to courses.

3. Course Management: Create courses, complete with course codes, credits, and departments. You can also search for courses by department, instructor, or semester.

4. Enrollment & Grading: A system for enrolling students into courses that checks rules (like credit limits) and lets you assign grades.

5. Academic Transcripts: You can generate and print a detailed transcript for any student, which includes a calculated GPA.

6. Data Persistence: The app saves and loads all data (students, instructors, courses) from simple CSV files so nothing is lost between sessions.

7. File Utilities: I included a feature to create a timestamped backup of all data files.

  
2. How to Run the Project

What You'll Need:
- Java Development Kit (JDK), version 11 or newer.

- An IDE like VS Code (with the Java Extension Pack) or Eclipse.

Running from the Command Line:

1. Open a terminal in the project's main folder (the one that contains the src directory).

2. Compile the code. This command will compile all the .java files and put the output .class files into a new bin folder.
javac -d bin src/edu/ccrm/cli/*.java src/edu/ccrm/domain/*.java src/edu/ccrm/io/*.java src/edu/ccrm/service/*.java src/edu/ccrm/util/*.java

3. Run the application. This command executes the main class
java -cp bin edu.ccrm.cli.CliManager


3. Quick Explanations of Java Concepts
Java's Evolution

- 1995: Sun Microsystems releases Java 1.0.

- 1998: J2SE 1.2 comes out, adding key frameworks like Swing and Collections.

- 2004: J2SE 5.0 is a big one, introducing Generics, Enums, and Annotations.

- 2014: Java 8 completely changes the game with Lambdas, the Stream API, and a new Date/Time API.

- 2017-Present: Java now has a faster 6-month release cycle, with Long-Term Support (LTS) versions like 11, 17, and 21 offering stability.

Java ME vs. SE vs. EE

Java ME (Micro Edition):

- Purpose: Designed for mobile and embedded devices, like IoT gadgets or smart TVs.
- Core API: Uses a small subset of the Java SE API to keep the footprint small.
- Example: The software running on a smart home thermostat.

Java SE (Standard Edition):

- Purpose: The core Java platform used for developing desktop, server, and console applications.
- Core API: Includes the complete standard Java library (lang, util, io, etc.).
- Example: This CCRM Project, or games like Minecraft.

Java EE (Enterprise Edition):

- Purpose: Used for building large-scale, distributed enterprise applications, especially web applications.
- Core API: Extends Java SE by adding extensive libraries for web services, servlets, and more.
- Example: An online banking portal or a major e-commerce website.

Java Architecture: JDK, JRE, and JVM

- JVM (Java Virtual Machine): This is what actually runs the compiled Java code. There's a different JVM for Windows, Mac, and Linux, which is what lets Java code be "Write Once, Run Anywhere."

- JRE (Java Runtime Environment): This is what you need to run a Java application. It includes the JVM and the core Java libraries.

- JDK (Java Development Kit): This is what you need to write Java applications. It includes everything in the JRE, plus the compiler (javac) and other development tools.


4. Where to Find Technical Requirements in the Code
Here’s a map of where I implemented the required technical concepts.

Core

- Packages: edu.ccrm.domain, edu.ccrm.service, etc.
- Main Class: CliManager.java
- Loops (while, for): CliManager.java (in the main run loop and menu handlers)
- Decision (if, switch): CliManager.java (in the main switch and for input validation)

OOP (Object-Oriented Programming)

- Encapsulation: All classes in the domain package (like Student.java) have private fields with public getters/setters.
- Inheritance (extends): Student.java and Instructor.java both extend the Person.java class.
- Abstraction (abstract): Person.java is an abstract class with an abstract getProfile method.
- Polymorphism: The menu handlers in CliManager.java use Person references to handle both Student and Instructor objects.
- Overriding (@Override): I overrode the toString() and getProfile() methods in the Student and Instructor classes.
- Enums with Fields: Grade.java is an enum that holds a double value for the grade points.

Advanced

- Custom Exceptions: DuplicateEnrollmentException.java
- try-catch: CliManager.java in the enrollStudentInCourse() method.
- Lambdas & Streams: CourseService.java in the search methods.
- NIO.2 File I/O: ImportExportService.java uses Files.readAllLines and Files.write.
- Date/Time API: BackupService.java uses this for creating timestamped folder names.
- Recursion: BackupService.java has the calculateDirectorySize() method.


5. Usage and Sample Data
How to Run a Quick Test:

- Start the app.
- Go to the instructor menu (2) and add a new instructor.
- Go back to the main menu, then go to the course menu (3) and add a new course, assigning the instructor you just made.
- Exit the app with 0 to save the data to the CSV files.

Sample Data Files (test-data folder)
I've included a test-data folder with some sample CSV files you can use to test the import feature.

instructors.csv

1,PQR,pqr@,CSE
2,Ayush,ayush@gmai.com,MAT
3,Ram,ram@gmail.com,CSE


students.csv

1,24ABC10021,Priyash Sharma,priyash123@gmail.com,Active
2,24BCE10024,Manas,manans123@gmail.com,Active
3,24BAI10322,Rahul,rahul@gmail.com,Active
4,25WER10433,Alok,alok@gmail.com,Active


courses.csv

CSE2001,DSA,4,PQR,WINTER,Active
MAT2001,Calculus,4,PQR,INTERIM,Active
CSE1002,Java,3,Ram,WINTER,Active


7. Acknowledgements
While working on this project, I used a few online resources to double-check concepts. I found GeeksforGeeks to be particularly helpful for examples related to the Java Stream API and some of the more advanced file I/O operations.
