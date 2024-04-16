package businessLogic;

import model.Client;
import model.Server;

import java.util.List;

public interface Strategy {
    List<Server> addClient(List<Server> servers, Client client);
}

