package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;
import edu.ccrm.util.DuplicateEnrollmentException;
import edu.ccrm.util.MaxCreditLimitExceededException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CliManager 
{
    private final Scanner sc;
    private final StudentService studentService;
    private final InstructorService instructorService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final TranscriptService transcriptService;
    private final ImportExportService importExportService;
    private final BackupService backupService;

    public CliManager() 
    {
        this.sc = new Scanner(System.in);
        this.studentService = new StudentService();
        this.instructorService = new InstructorService(); 
        this.courseService = new CourseService();
        this.enrollmentService = new EnrollmentService();
        this.transcriptService = new TranscriptService(this.enrollmentService); 
        this.importExportService = new ImportExportService(this.studentService, this.courseService,this.instructorService);
        this.backupService = new BackupService();
    }
    public static void main(String[] args) 
    {
        CliManager app = new CliManager();
        app.run();
    }

    public void run()
    {
        importExportService.importData();
        while (true) 
        {
            displayMainMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) 
            {
                case 1:
                        handleStudentManagement();
                        break;
                case 2:
                        handleInstructorManagement();
                        break;
                case 3:
                        handleCourseManagement();
                        break;
                case 4:
                        handleEnrollmentManagement();
                        break;
                case 5:
                        handleExport();
                        break;
                case 6:
                        handleBackup(); 
                        break;
                case 0:
                        System.out.println("Exiting application. Goodbye!");
                        importExportService.exportData();
                        return; 
                default:
                        System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() 
    {
        System.out.println("\n===== Campus Course & Records Manager =====");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Instructors");
        System.out.println("3. Manage Courses");
        System.out.println("4. Manage Enrollments & Grades");
        System.out.println("5. Import/Export Data");
        System.out.println("6. Backup Data");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // Place these methods inside your CliManager.java class

    private void handleStudentManagement() 
    {
        while (true) 
        {
            displayStudentMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline character

            if (choice == 0) 
            {
                break; // Exit this sub-menu and return to the main menu
            }

            switch (choice) 
            {
                case 1:
                        addStudent();
                        break;
                case 2:
                        listAllStudents();
                        break;
                case 3:
                        findStudentById();
                        break;
                case 4:
                        updateStudent();
                        break;
                case 5:
                        deactivateStudent();
                        break;
                case 6:
                        printStudentTranscript();
                        break;
                default:
                        System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayStudentMenu() 
    {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add New Student");
        System.out.println("2. List All Students");
        System.out.println("3. Find Student by ID (View Profile)");
        System.out.println("4. Update Student Details");
        System.out.println("5. Deactivate Student");
        System.out.println("6. Print Student Transcript");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private void addStudent() 
    {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter full name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter registration number: ");
        String regNo = sc.nextLine();
    
        studentService.addStudent(name, email, regNo);
        System.out.println("Student added successfully!");
    }

    private void listAllStudents() 
    {
        System.out.println("\n--- List of All Students ---");
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) 
        {
            System.out.println("No students found in the system.");
        } 
        else 
        {
            students.forEach(System.out::println);
        }
    }

    private void findStudentById() 
    {
        System.out.print("Enter Student ID to find: ");
        int studentId = sc.nextInt();
        sc.nextLine();
    
        Student student = studentService.findStudentById(studentId);
        if (student != null) 
        {
            System.out.println("\n--- Student Profile ---");
            System.out.println(student);
        }
        else 
        {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private void updateStudent() 
    {
        System.out.print("Enter Student ID to update: ");
        int studentId = sc.nextInt();
        sc.nextLine();
    
        if (studentService.findStudentById(studentId) == null) 
        {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }
    
        System.out.print("Enter new full name: ");
        String newName = sc.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = sc.nextLine();
    
        if (studentService.updateStudent(studentId, newName, newEmail)) 
        {
            System.out.println("Student details updated successfully.");
        } 
        else 
        {
            System.out.println("Update failed.");
        }
    }

    private void deactivateStudent() 
    {
        System.out.print("Enter Student ID to deactivate: ");
        int studentId = sc.nextInt();
        sc.nextLine();
    
        if (studentService.deactivateStudent(studentId)) 
        {
            System.out.println("Student deactivated successfully.");
        } 
        else 
        {
            System.out.println("Deactivation failed. Student with ID " + studentId + " not found.");
        }
    }

    private void printStudentTranscript() 
    {
        System.out.print("Enter Student ID to generate transcript: ");
        int studentId = sc.nextInt();
        sc.nextLine();
    
        Student student = studentService.findStudentById(studentId);
        if (student != null) 
        {
            String transcript = transcriptService.generateTranscript(student);
            System.out.println(transcript);
        } 
        else 
        {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private void handleInstructorManagement() 
    {
        while (true) 
        {
            displayInstructorMenu();
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 0) 
            {
                break;
            }

            switch (choice) 
            {
                case 1:
                    addInstructor();
                    break;
                case 2:
                    listAllInstructors();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void displayInstructorMenu() 
    {
        System.out.println("\n--- Instructor Management ---");
        System.out.println("1. Add New Instructor");
        System.out.println("2. List All Instructors");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private void addInstructor() 
    {
        System.out.println("\n--- Add New Instructor ---");
        System.out.print("Enter full name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter department: ");
        String department = sc.nextLine();
    
        instructorService.addInstructor(name, email, department);
        System.out.println("Instructor added successfully!");
    }

    private void listAllInstructors() 
    {
        System.out.println("\n--- List of All Instructors ---");
        List<Instructor> instructorList = instructorService.getAllInstructors();
        if (instructorList.isEmpty()) {
            System.out.println("No instructors found.");
        } 
        else 
        {
            instructorList.forEach(System.out::println);
        }
    }

    private void handleCourseManagement() 
    {
        while (true) 
        {
            displayCourseMenu();
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 0) 
            {
                break;
            }

            switch (choice) 
            {
                case 1:
                    addCourse();
                    break;
                case 2:
                    listAllCourses();
                    break;
                case 3:
                    findCourseByCode();
                    break;
                case 4:
                    updateCourse();
                    break;
                case 5:
                    deactivateCourse();
                    break;
                case 6:
                    searchCoursesMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayCourseMenu() 
    {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add New Course");
        System.out.println("2. List All Courses");
        System.out.println("3. Find Course by Code");
        System.out.println("4. Update Course");
        System.out.println("5. Deactivate Course");
        System.out.println("6. Search Courses");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private void addCourse() 
    {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter Course Code (e.g., CS101): ");
        String code = sc.nextLine();
        System.out.print("Enter Course Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Credits: ");
        int credits = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.println("Select Semester:");
        for (Semester s : Semester.values()) 
        {
            System.out.println((s.ordinal() + 1) + ". " + s);
        }
        System.out.print("Enter choice: ");
        int semChoice = sc.nextInt() - 1;
        sc.nextLine();
        Semester semester = Semester.values()[semChoice];

        List<Instructor> availableInstructors = instructorService.getAllInstructors();
        if (availableInstructors.isEmpty()) 
        {
            System.out.println("Error: No instructors found. Please add an instructor first.");
            return;
        }
        System.out.println("Select Instructor:");
        availableInstructors.forEach(inst -> System.out.println(inst.getId() + ". " + inst.getFullName()));
        System.out.print("Enter instructor ID: ");
        int instId = sc.nextInt();
        sc.nextLine();
        Instructor instructor = instructorService.findInstructorById(instId);
        if (instructor == null) 
        {
            System.out.println("Error: Invalid instructor ID.");
            return;
        }

        courseService.addCourse(code, title, credits, instructor, semester, department);
        System.out.println("Course added successfully!");
    }

    private void listAllCourses() 
    {
        System.out.println("\n--- List of All Courses ---");
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) 
        {
            System.out.println("No courses found in the system.");
        } 
        else 
        {
            courses.forEach(System.out::println);
        }
    }

    private void findCourseByCode() 
    {
        System.out.print("Enter Course Code to find: ");
        String code = sc.nextLine();
        Course course = courseService.findCourseByCode(code);
        if (course != null) 
        {
            System.out.println("\n--- Course Details ---");
            System.out.println(course);
        } 
        else 
        {
            System.out.println("Course with code '" + code + "' not found.");
        }
    }

    private void updateCourse() 
    {
        System.out.print("Enter Course Code to update: ");
        String code = sc.nextLine();
        Course courseToUpdate = courseService.findCourseByCode(code);
        if (courseToUpdate == null) 
        {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter new Title (current: " + courseToUpdate.getTitle() + "): ");
        String newTitle = sc.nextLine();
        System.out.print("Enter new Credits (current: " + courseToUpdate.getCredits() + "): ");
        int newCredits = sc.nextInt();
        sc.nextLine();

        List<Instructor> availableInstructors = instructorService.getAllInstructors();
        System.out.println("Select New Instructor (current: " + courseToUpdate.getInstructor().getFullName() + "):");
        availableInstructors.forEach(inst -> System.out.println(inst.getId() + ". " + inst.getFullName()));
        System.out.print("Enter instructor ID: ");
        int instId = sc.nextInt();
        sc.nextLine();
        Instructor newInstructor = instructorService.findInstructorById(instId);
        if (newInstructor == null) 
        {
            System.out.println("Error: Invalid instructor ID. Update cancelled.");
            return;
        }
    
        if (courseService.updateCourse(code, newTitle, newCredits, newInstructor)) 
        {
            System.out.println("Course updated successfully.");
        } 
        else 
        {
            System.out.println("Update failed.");
        }
    }

    private void deactivateCourse() 
    {
        System.out.print("Enter Course Code to deactivate: ");
        String code = sc.nextLine();
        if (courseService.deactivateCourse(code)) 
        {
            System.out.println("Course deactivated successfully.");
        } 
        else 
        {
            System.out.println("Deactivation failed. Course not found.");
        }
    }

    private void searchCoursesMenu() 
    {
        System.out.println("\n--- Search Courses ---");
        System.out.println("1. By Department");
        System.out.println("2. By Instructor");
        System.out.println("3. By Semester");
        System.out.print("Enter search type: ");
        int choice = sc.nextInt();
        sc.nextLine();

        List<Course> results = new ArrayList<>();
        switch (choice) 
        {
            case 1:
                System.out.print("Enter Department name: ");
                String dept = sc.nextLine();
                results = courseService.findCoursesByDepartment(dept);
                break;
            case 2:
                System.out.print("Enter Instructor's full name: ");
                String prof = sc.nextLine();
                results = courseService.findCoursesByInstructor(prof);
                break;
            case 3:
                System.out.print("Enter Semester (e.g., FALL): ");
                try 
                {
                    Semester sem = Semester.valueOf(sc.nextLine().toUpperCase());
                    results = courseService.findCoursesBySemester(sem);
                } 
                catch (IllegalArgumentException e) 
                {
                    System.out.println("Invalid semester value.");
                }
                break;
            default:
                System.out.println("Invalid search type.");
                return;
        }

        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) 
        {
            System.out.println("No courses found matching your criteria.");
        } 
        else 
        {
            results.forEach(System.out::println);
        }
    }
    // Place these methods inside your CliManager.java class

    private void handleEnrollmentManagement() 
    {
        while (true) 
        {
            displayEnrollmentMenu();
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 0) 
            {
                break;
            }

            switch (choice) 
            {
                case 1:
                    enrollStudentInCourse();
                    break;
                case 2:
                    assignGradeToStudent();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayEnrollmentMenu() 
    {
        System.out.println("\n--- Enrollments & Grades ---");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. Assign Grade to Student");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private void enrollStudentInCourse() 
    {
        System.out.println("\n--- Enroll Student ---");
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine();

        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseByCode(courseCode);

        if (student == null || course == null) 
        {
            System.out.println("Error: Invalid Student ID or Course Code.");
            return;
        }   

        try 
        {
            enrollmentService.enrollStudent(student, course);
            System.out.println("Enrollment successful!");
        } 
        catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) 
        {
            System.err.println("Enrollment Failed: " + e.getMessage());
        }
    }

    private void assignGradeToStudent() 
    {
        System.out.println("\n--- Assign Grade ---");
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine();

        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseByCode(courseCode);

        if (student == null || course == null) 
        {
            System.out.println("Error: Invalid Student ID or Course Code.");
            return;
        }

        System.out.println("Select a Grade:");
        for (Grade g : Grade.values()) 
        {
            System.out.println((g.ordinal() + 1) + ". " + g);
        }
        System.out.print("Enter grade choice: ");
        int gradeChoice = sc.nextInt() - 1;
        sc.nextLine();
        Grade grade = Grade.values()[gradeChoice];

        if (enrollmentService.assignGrade(student, course, grade)) 
        {
            System.out.println("Grade assigned successfully.");
        } 
        else 
        {
            System.out.println("Failed to assign grade. Ensure the student is enrolled in the course.");
        }
    }

    private void handleExport() 
    {
        System.out.println("\nExporting all data to files...");
        importExportService.exportData();
    }

    private void handleBackup() 
    {
        System.out.println("\nCreating backup...");
        Path backupPath = backupService.createBackup();

        if (backupPath != null) 
        {
            System.out.println("Calculating backup size...");
            long sizeInBytes = backupService.calculateDirectorySize(backupPath);
            long sizeInKB = sizeInBytes / 1024;
            System.out.println("Total size of new backup: " + sizeInKB + " KB");
        }
    }
}
