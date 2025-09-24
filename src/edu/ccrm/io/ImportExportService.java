package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.InstructorService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ImportExportService {
    private final StudentService studentService;
    private final CourseService courseService;
    private final InstructorService instructorService;
    private final AppConfig config = AppConfig.getInstance();

    public ImportExportService(StudentService ss, CourseService cs, InstructorService is) {
        this.studentService = ss;
        this.courseService = cs;
        this.instructorService = is;
    }

    public void exportData() {
        try {
            Files.createDirectories(config.getDataDirectory());

            List<String> studentLines = studentService.getAllStudents().stream().map(Student::toCsvString).collect(Collectors.toList());
            Files.write(config.getStudentsFile(), studentLines);

            List<String> instructorLines = instructorService.getAllInstructors().stream().map(Instructor::toCsvString).collect(Collectors.toList());
            Files.write(config.getInstructorsFile(), instructorLines);

            List<String> courseLines = courseService.getAllCourses().stream().map(Course::toCsvString).collect(Collectors.toList());
            Files.write(config.getCoursesFile(), courseLines);

            System.out.println("Data successfully exported to: " + config.getDataDirectory());
        } catch (IOException e) {
            System.err.println("Error during data export: " + e.getMessage());
        }
    }

    public void importData() {
        System.out.println("Starting data import...");

        // 1. Import Instructors
        try {
            if (Files.exists(config.getInstructorsFile())) {
                Files.lines(config.getInstructorsFile())
                    .filter(line -> line != null && !line.trim().isEmpty()) // Ignore empty lines
                    .map(line -> line.split(","))
                    .forEach(parts -> {
                        if (parts.length >= 4) {
                            String fullName = parts[1].trim();
                            String email = parts[2].trim();
                            String department = parts[3].trim();
                            instructorService.addInstructor(fullName, email, department);
                        }
                    });
            }
        } catch (IOException e) {
            System.err.println("Error importing instructor data: " + e.getMessage());
        }
        
        // 2. Import Students
        try {
            if (Files.exists(config.getStudentsFile())) {
                Files.lines(config.getStudentsFile())
                    .filter(line -> line != null && !line.trim().isEmpty())
                    .map(line -> line.split(","))
                    .forEach(parts -> {
                        if (parts.length >= 5) {
                            String regNo = parts[1].trim();
                            String fullName = parts[2].trim();
                            String email = parts[3].trim();
                            studentService.addStudent(fullName, email, regNo);
                        }
                    });
            }
        } catch (IOException e) {
            System.err.println("Error importing student data: " + e.getMessage());
        }

        // 3. Import Courses
        try {
            if (Files.exists(config.getCoursesFile())) {
                 Files.lines(config.getCoursesFile())
                    .filter(line -> line != null && !line.trim().isEmpty())
                    .map(line -> line.split(","))
                    .forEach(parts -> {
                        if (parts.length >= 6) {
                            try {
                                String code = parts[0].trim();
                                String title = parts[1].trim();
                                int credits = Integer.parseInt(parts[2].trim());
                                String instructorName = parts[3].trim();
                                Semester semester = Semester.valueOf(parts[4].trim().toUpperCase());
                                String department = parts[5].trim();

                                Instructor instructor = instructorService.findInstructorByName(instructorName);
                                if (instructor != null) {
                                    courseService.addCourse(code, title, credits, instructor, semester, department);
                                } else {
                                    System.err.println("Import Warning: Instructor '" + instructorName + "' not found for course '" + code + "'.");
                                }
                            } catch (Exception e) {
                                System.err.println("Skipping malformed course line: " + String.join(",", parts));
                            }
                        }
                    });
            }
        } catch (IOException e) {
            System.err.println("Error importing course data: " + e.getMessage());
        }
        System.out.println("Data import process completed.");
    }
}

