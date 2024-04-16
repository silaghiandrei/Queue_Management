package businessLogic;

import model.Client;
import model.Server;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Server> servers;
    private final Integer numberOfServers;
    private final Integer numberOfClients;
    private Strategy strategy;
    private final List<Thread> serverThreads;
    private Integer maxNumberOfClientsAtATime = 0;

    public Scheduler(Integer numberOfServers, Integer numberOfClients) {

        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        servers = new ArrayList<>();
        serverThreads = new ArrayList<>();
        int counter = 0;
        while(counter < numberOfServers) {
            Server server = new Server();
            servers.add(counter, server);

            Thread serverThread = new Thread(server);
            serverThreads.add(serverThread);
            serverThread.start();

            System.out.println("Thread number " + counter + " has started!");

            counter++;
        }
    }

    public void dispatchClient(Client client) {
        strategy.addClient(servers, client);
    }


    public Integer getMaxNoServers() {
        return numberOfServers;
    }
    public Integer getNumberOfClients() {
        return numberOfClients;
    }
    public List<Server> getServers() {
        return servers;
    }
    public List<Thread> getServerThreads() {
        return serverThreads;
    }
    public Strategy getStrategy() {
        return strategy;
    }
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer getMaxNumberOfClientsAtATime() {
        return maxNumberOfClientsAtATime;
    }

    public void setMaxNumberOfClientsAtATime(Integer maxNumberOfClientsAtATime) {
        this.maxNumberOfClientsAtATime = maxNumberOfClientsAtATime;
    }

}
