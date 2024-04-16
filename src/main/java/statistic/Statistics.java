package statistic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    public static int averageWaitingTime = 0;
    public static int averageServiceTime = 0;
    public static int peakHour = 0;

    public synchronized static void addWaitingTime(int waitingTime) {
        averageWaitingTime += waitingTime;
    }

    public synchronized static void addServiceTime(int serviceTime) {
        averageServiceTime += serviceTime;
    }

    public synchronized static void setPeakHour(int hour) {
        peakHour = hour;
    }

    public static void writeToFile(String logMessage) {
        try {
            String fileName = "log_of_events.txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(logMessage);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
