package model;

import statistic.Statistics;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private final Queue<Client> clients;
    private final AtomicInteger waitingPeriod;
    private boolean running = true;


    public Server() {
        this.clients = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addClient(Client newClient){
        clients.add(newClient);
        waitingPeriod.addAndGet(newClient.getServiceTime());
        System.out.println(waitingPeriod.get());
        Statistics.addWaitingTime(waitingPeriod.get());
    }

    @Override
    public void run() {
        while (running) {
            synchronized (this) {
                if (!clients.isEmpty()) {
                    Client nextClient = clients.peek();
                    while (nextClient.getServiceTime() > 1) {
                        waitingPeriod.decrementAndGet();
                        nextClient.setServiceTime(nextClient.getServiceTime() - 1);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    clients.poll();
                    waitingPeriod.decrementAndGet();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }


    public Queue<Client> getClients() {
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public Integer returnServerSize() {
        return clients.size();
    }

    public String toString() {
        if(clients.isEmpty()) {
            return "Queue: empty";
        } else {
            StringBuilder clientsList = new StringBuilder("Queue: ");
            for(Client client : clients) {
                clientsList.append(client.toString());
                clientsList.append(" ");
            }
            return clientsList.toString();
        }
    }

    public String displayInInterface() {
        StringBuilder clientsList = new StringBuilder();
        if(!clients.isEmpty()) {
            for(Client client : clients) {
                clientsList.append(client.displayByServiceTime());
                clientsList.append("  ");
            }
        }
        return clientsList.toString();
    }
}
