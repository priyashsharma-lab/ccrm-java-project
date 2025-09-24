package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppConfig {
    private static AppConfig instance;

    // Define constant paths for your data files.
    private static final Path DATA_DIRECTORY = Paths.get("test_data"); // <-- CHANGED
    private static final Path STUDENTS_FILE = DATA_DIRECTORY.resolve("students.csv");
    private static final Path INSTRUCTORS_FILE = DATA_DIRECTORY.resolve("instructors.csv");
    private static final Path COURSES_FILE = DATA_DIRECTORY.resolve("courses.csv");
    private static final Path BACKUP_ROOT_DIRECTORY = Paths.get("backups");

    private AppConfig() {
        // Private constructor to prevent instantiation
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    // --- Getters to access the paths ---
    public Path getDataDirectory() { return DATA_DIRECTORY; }
    public Path getStudentsFile() { return STUDENTS_FILE; }
    public Path getInstructorsFile() { return INSTRUCTORS_FILE; }
    public Path getCoursesFile() { return COURSES_FILE; }
    public Path getBackupRootDirectory() { return BACKUP_ROOT_DIRECTORY; }
}

