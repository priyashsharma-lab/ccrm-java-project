Campus Course & Records Manager (CCRM)

1. Project Overview
The Campus Course & Records Manager (CCRM) is a console-based Java application designed to serve as a lightweight student information system for an academic institute. It provides functionalities to manage student, instructor, and course records through a simple, menu-driven command-line interface.

The application is built entirely in Java SE and demonstrates a wide range of core and advanced Java features, including a robust Object-Oriented design, modern file I/O with NIO.2, the Stream API for data processing, and foundational design patterns.

Core Features:
Student Management: Add, list, update, deactivate, and view student profiles.

Instructor Management: Add and list instructors to be assigned to courses.

Course Management: Create courses with details like code, credits, and department. Search for courses by department, instructor, or semester.

Enrollment & Grading: Enroll students in courses with business rule validation (e.g., credit limits) and assign grades.

Academic Transcripts: Generate and print detailed academic transcripts for students, including a calculated GPA.

Data Persistence: Import and export application data (students, instructors, courses) from/to simple CSV files.

File Utilities: Create timestamped backups of all application data and calculate the size of backup directories.

2. How to Run the Project
Prerequisites
Java Development Kit (JDK): Version 11 or higher.

IDE: VS Code with the "Extension Pack for Java" or Eclipse IDE.

Running from the Command Line
Navigate to Project Root: Open a terminal or command prompt and navigate to the root directory of the project (the folder containing the src folder).

Compile: Compile all .java source files. The -d bin flag creates a bin directory for the compiled .class files.

javac -d bin src/edu/ccrm/cli/*.java src/edu/ccrm/domain/*.java src/edu/ccrm/io/*.java src/edu/ccrm/service/*.java src/edu/ccrm/util/*.java

Run: Execute the main class from the bin directory.

java -cp bin edu.ccrm.cli.CliManager

Running from VS Code
Open the CliManager.java file.

Click the "Run" button that appears above the main method.

Interact with the application in the integrated terminal.

3. Java Platform Explanations
Evolution of Java
1995: Java 1.0 is released by Sun Microsystems with the "Write Once, Run Anywhere" promise.

1998: J2SE 1.2 is released, introducing the Swing GUI toolkit and Collections framework.

2004: J2SE 5.0 introduces major features like Generics, Enums, and Annotations.

2014: Java 8 is released, marking a significant evolution with Lambdas, the Stream API, and a new Date/Time API.

2017: Java moves to a faster 6-month release cycle starting with Java 9.

2018-Present: Long-Term Support (LTS) versions (11, 17, 21) provide stability for enterprise applications, while new features are added in intermediate releases.

Java ME vs. SE vs. EE
Feature

Java ME (Micro Edition)

Java SE (Standard Edition)

Java EE (Enterprise Edition)

Purpose

Mobile & embedded devices (IoT, TV)

Desktop, server, and console applications

Large-scale, distributed enterprise applications

Core API

A small subset of the Java SE API

The core Java platform (lang, util, io, etc.)

Builds on Java SE, adding extensive libraries

Key Features

Small footprint, power efficiency

JVM, Collections, Swing, NIO, Streams, etc.

Servlets, JSP, EJB, JPA for web & business logic

Example App

A smart home thermostat application

This CCRM Project, Minecraft, a desktop calculator

An online banking portal, a large e-commerce site

Java Architecture: JDK, JRE, and JVM
JVM (Java Virtual Machine): The core component that executes Java bytecode. It is platform-dependent (e.g., there's a JVM for Windows, one for macOS), which is what allows Java code to be platform-independent ("Run Anywhere").

JRE (Java Runtime Environment): A package that provides everything needed to run a compiled Java application. It contains the JVM and the Java Class Library (core classes like String, List, etc.). You need the JRE to run a Java program.

JDK (Java Development Kit): A full development kit for building Java applications. It contains everything in the JRE, plus development tools like the compiler (javac) and debugger (jdb). You need the JDK to write and compile Java code.

4. Setup and Installation Screenshots
JDK Installation (java -version)
(Insert your screenshot here showing the output of the java -version command in your terminal)

VS Code Project Setup
(Insert your screenshot here showing the project structure in the VS Code explorer)

5. Technical Requirements Mapping Table
This table maps the mandatory technical requirements from the project statement to the specific location in the source code where they are demonstrated.

Core
Packages: edu.ccrm.domain, edu.ccrm.service, etc.

Main Class: CliManager.java

Loops (while, for): CliManager.java (main run loop, menu handlers)

Decision (if, switch): CliManager.java (main switch, input validation)

OOP (Object-Oriented Programming)
Encapsulation: All classes in domain package (e.g., Student.java)

Inheritance (extends): Student.java and Instructor.java extend Person.java

Abstraction (abstract): Person.java (abstract class and getProfile method)

Polymorphism: CliManager.java: Handlers use Person references.

Overriding (@Override): toString() and getProfile() in Student and Instructor

Enums with Fields: Grade.java (contains grade points)

Advanced
Custom Exceptions: DuplicateEnrollmentException.java

try-catch: CliManager.java -> enrollStudentInCourse()

Lambdas & Streams: CourseService.java -> Search methods

NIO.2 File I/O: ImportExportService.java (Files.read/write)

Date/Time API: BackupService.java (timestamped folder names)

Recursion: BackupService.java -> calculateDirectorySize()

6. Usage and Sample Data
Running an Operation
Start the application.

At the main menu, enter 2 to manage instructors.

Enter 1 to add a new instructor.

Fill in the details.

Return to the main menu and add a course, assigning the instructor you just created.

Exit the application with 0 to save your data.

Sample Data Files (test-data folder)
The test-data folder should contain simple CSV files to test the import functionality.

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
This project was completed as part of an academic curriculum. For further clarification on specific Java concepts and best practices, I consulted a variety of online resources. In particular, articles and examples from GeeksforGeeks were helpful for reinforcing my understanding of the Java Stream API and advanced file I/O operations.