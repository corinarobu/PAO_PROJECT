package jdbc.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVLoggerService {

    private static final String CSV_FILE_PATH = "actions_log.csv";

    public void logAction(String actionName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            LocalDateTime timestamp = LocalDateTime.now();
            String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String line = actionName + "," + formattedTimestamp + "\n";
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
