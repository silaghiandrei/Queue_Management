package businessLogic;

import model.Client;
import model.Server;
import statistic.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeStrategy implements Strategy {
    @Override
    public List<Server> addClient(List<Server> servers, Client client) {
        List<Server> serversChosen = new ArrayList<>();
        AtomicInteger minWaitingPeriod = new AtomicInteger(Integer.MAX_VALUE);

        for (Server server : servers) {
            int serverWaitingPeriod = server.getWaitingPeriod().intValue();
            if (serverWaitingPeriod < minWaitingPeriod.intValue()) {
                minWaitingPeriod.set(serverWaitingPeriod);
                serversChosen.clear();
                serversChosen.add(server);
            } else if (serverWaitingPeriod == minWaitingPeriod.intValue()) {
                serversChosen.add(server);
            }
        }

        if (serversChosen.size() > 1) {
            serversChosen = new ShortestQueueStrategy().addClient(serversChosen, client);
        } else if (serversChosen.size() == 1) {
            serversChosen.getFirst().addClient(client);
        }

        return serversChosen;
    }
}
