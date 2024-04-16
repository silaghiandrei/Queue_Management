package businessLogic;

import gui.SimulationFrame;
import model.Client;
import model.Server;
import statistic.Logger;
import statistic.Statistics;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {
    private Scheduler scheduler;
    private SimulationFrame simulationFrame;
    private List<Client> clients;

    private int interval;
    private int numberOfClients = -1;
    private int numberOfServers = -1;
    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;

    private final Logger logger;

    public SimulationManager() {
        SimulationFrame simulationFrame = new SimulationFrame();
        this.setSimulationFrame(simulationFrame);

        logger = new Logger();
    }

    public synchronized void generateRandomClients() {
        clients = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = (int) (Math.random() * (maxArrival - minArrival + 1)) + minArrival;
            int serviceTime = (int) (Math.random() * (maxService - minService + 1)) + minService;

            Client client = new Client(i, arrivalTime, serviceTime);
            clients.add(client);
            Statistics.addServiceTime(client.getServiceTime());
        }
        sortClients();
        notifyAll();
    }

    public void sortClients() {
        Collections.sort(clients);
    }

    public void setSimulationFrame(SimulationFrame simulationFrame) {
        this.simulationFrame = simulationFrame;
        attachListeners();
    }

    private void attachListeners() {
        if (simulationFrame != null) {
            JButton validateButton = simulationFrame.getValidateInput();
            validateButton.addActionListener(e -> {
                numberOfClients = Integer.parseInt(simulationFrame.getClientTextField().getText());
                numberOfServers = Integer.parseInt(simulationFrame.getServerTextField().getText());
                interval = Integer.parseInt(simulationFrame.getIntervalTextField().getText());
                minArrival = Integer.parseInt(simulationFrame.getMinArrivalTextField().getText());
                maxArrival = Integer.parseInt(simulationFrame.getMaxArrivalTextField().getText());
                minService = Integer.parseInt(simulationFrame.getMinServiceTextField().getText());
                maxService = Integer.parseInt(simulationFrame.getMaxServiceTextField().getText());

                scheduler = new Scheduler(numberOfServers, numberOfClients);
                scheduler.setStrategy(new TimeStrategy());

                simulationFrame.drawServerSquares(numberOfServers);

                generateRandomClients();
            });
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            logger.clearLogFile();
            while (clients == null || clients.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        int currentTime = 0;
        while (currentTime <= interval) {
            logger.writeToFile("Time " + currentTime + "\n");

            while (!clients.isEmpty() && clients.getFirst().getArrivalTime() == currentTime) {
                scheduler.dispatchClient(clients.getFirst());
                clients.removeFirst();
            }

            logger.writeToFile("Waiting queue: ");
            for(Client client : clients) {
                logger.writeToFile(client + " ");
            }
            logger.writeToFile("\n");

            Integer currentServersSize = 0;

            for (Server server : scheduler.getServers()) {
                currentServersSize += server.returnServerSize();
                if(currentServersSize > scheduler.getMaxNumberOfClientsAtATime()) {
                    scheduler.setMaxNumberOfClientsAtATime(currentServersSize);
                    Statistics.setPeakHour(currentTime);
                }
                logger.writeToFile(server.toString());
                logger.writeToFile("\n");
            }

            simulationFrame.drawQueues(scheduler.getServers());

            logger.writeToFile("\n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTime++;
        }

        for(Server server : scheduler.getServers()) {
            server.setRunning(false);
        }

        System.out.println("Simulation completed.");

        Statistics.writeToFile(String.format("Average waiting time %.2f\n", Statistics.averageWaitingTime / (double) numberOfClients));
        Statistics.writeToFile(String.format("Average service time %.2f\n", Statistics.averageServiceTime / (double) numberOfClients));
        Statistics.writeToFile(String.format("Peak hour %d\n", Statistics.peakHour));
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public static void main(String[] args) {
        SimulationManager simulationManager = new SimulationManager();

        Thread thread = new Thread(simulationManager);
        thread.start();
        System.out.println("Thread for the main queue has started!");
    }
}
