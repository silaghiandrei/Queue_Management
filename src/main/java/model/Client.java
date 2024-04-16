package model;

public class Client implements Comparable<Client> {
    private final Integer id;
    private final Integer arrivalTime;
    private Integer serviceTime;

    public Client(Integer id, Integer arrivalTime, Integer serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Client otherClient) {
        return this.arrivalTime.compareTo(otherClient.arrivalTime);
    }

    public String toString() {
        return "(" + id + ", " + arrivalTime + ", " + serviceTime + ")";
    }

    public String displayByServiceTime() {
        return "(" + id  + ", " + serviceTime + ")";
    }
}
