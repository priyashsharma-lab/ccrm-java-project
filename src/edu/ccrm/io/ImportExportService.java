package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.InstructorService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImportExportService {
    private final StudentService studentService;
    private final CourseService courseService;
    private final InstructorService instructorService;

    private static final Path DATA_DIRECTORY = Paths.get("test_data");
    private static final Path STUDENTS_FILE = DATA_DIRECTORY.resolve("students.csv");
    private static final Path INSTRUCTORS_FILE = DATA_DIRECTORY.resolve("instructors.csv");
    private static final Path COURSES_FILE = DATA_DIRECTORY.resolve("courses.csv");

    public ImportExportService(StudentService studentService, CourseService courseService, InstructorService instructorService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    public void exportData() {
        try {
            Files.createDirectories(DATA_DIRECTORY);


            List<String> studentLines = studentService.getAllStudents().stream().map(Student::toCsvString).collect(Collectors.toList());
            Files.write(STUDENTS_FILE, studentLines);


            List<String> instructorLines = instructorService.getAllInstructors().stream().map(Instructor::toCsvString).collect(Collectors.toList());
            Files.write(INSTRUCTORS_FILE, instructorLines);


            List<String> courseLines = courseService.getAllCourses().stream().map(Course::toCsvString).collect(Collectors.toList());
            Files.write(COURSES_FILE, courseLines);

            System.out.println("Data successfully exported to: " + DATA_DIRECTORY);
        } catch (IOException e) {
            System.err.println("Error during data export: " + e.getMessage());
        }
    }

    public void importData() {
        try {
            if (Files.exists(INSTRUCTORS_FILE)) {
                Files.lines(INSTRUCTORS_FILE).map(line -> line.split(",")).forEach(parts -> {
                    if (parts.length >= 4) {
                        String fullName = parts[1];
                        String email = parts[2];
                        String department = parts[3];
                        instructorService.addInstructor(fullName, email, department);
                    }
                });
            }
        } catch (IOException e) {
            System.err.println("Error importing instructor data: " + e.getMessage());
        }
        
    
        try {
            if (Files.exists(STUDENTS_FILE)) {
                Files.lines(STUDENTS_FILE).map(line -> line.split(",")).forEach(parts -> {
                    if (parts.length >= 5) {
                        String regNo = parts[1];
                        String fullName = parts[2];
                        String email = parts[3];
                        studentService.addStudent(fullName, email, regNo);
                    }
                });
            }
        } catch (IOException e) {
            System.err.println("Error importing student data: " + e.getMessage());
        }

        try {
            if (Files.exists(COURSES_FILE)) {
                 Files.lines(COURSES_FILE).map(line -> line.split(",")).forEach(parts -> {
                    if (parts.length >= 6) {
                        String code = parts[0];
                        String title = parts[1];
                        int credits = Integer.parseInt(parts[2]);
                        String instructorName = parts[3];
                        Semester semester = Semester.valueOf(parts[4]);
                        String department = "CSE";

                        Instructor instructor = instructorService.findInstructorByName(instructorName);

                        if (instructor != null) {
                            courseService.addCourse(code, title, credits, instructor, semester, department);
                        } else {
                            System.err.println("Warning: Could not find instructor '" + instructorName + "' for course " + code);
                        }
                    }
                });
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error importing course data: " + e.getMessage());
        }
        System.out.println("Data import process completed.");
    }
}