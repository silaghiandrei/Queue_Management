package businessLogic;

import model.Client;
import model.Server;
import statistic.Statistics;

import java.util.ArrayList;
import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public List<Server> addClient(List<Server> servers, Client client) {
        int smallestQueueSize = Integer.MAX_VALUE;
        List<Server> serversChosen = new ArrayList<>();
        for (Server server : servers) {
            if (server.getClients().size() < smallestQueueSize) {
                smallestQueueSize = server.getClients().size();
                serversChosen.clear();
                serversChosen.add(server);
            } else if (server.getClients().size() == smallestQueueSize) {
                serversChosen.add(server);
            }
        }
        if(!serversChosen.isEmpty()) {
            serversChosen.getFirst().addClient(client);
        }
        return serversChosen;
    }
}
