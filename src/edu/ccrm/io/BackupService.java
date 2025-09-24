package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {
    private static final Path DATA_DIRECTORY = Paths.get("test_data");
    private static final Path BACKUP_ROOT_DIRECTORY = Paths.get("backups");

    public Path createBackup() {
        try {
            String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss").format(LocalDateTime.now());
            Path backupDir = BACKUP_ROOT_DIRECTORY.resolve("backup-" + timestamp);

            Files.createDirectories(backupDir);

            try (Stream<Path> files = Files.list(DATA_DIRECTORY)) {
                files.forEach(sourceFile -> {
                    try {
                        Files.copy(sourceFile, backupDir.resolve(sourceFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Could not copy file: " + sourceFile);
                    }
                });
            }

            System.out.println("Backup created successfully at: " + backupDir);
            return backupDir;
        } catch (IOException e) {
            System.err.println("Failed to create backup: " + e.getMessage());
            return null;
        }
    }

    public long calculateDirectorySize(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                .filter(Files::isRegularFile)
                .mapToLong(this::getFileSize)
                .sum();
        } catch (IOException e) {
            System.err.println("Could not calculate size of " + path + ": " + e.getMessage());
            return 0L;
        }
    }

    private long getFileSize(Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            return 0L;
        }
    }
}