package statistic;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private FileWriter fileWriter;

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void writeToFile(String logMessage) {
        try {
            String fileName = "log_of_events.txt";
            fileWriter = new FileWriter(fileName, true);
            fileWriter.write(logMessage);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearLogFile() {
        try {
            String fileName = "log_of_events.txt";
            fileWriter = new FileWriter(fileName, false);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
