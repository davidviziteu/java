package compulsory;

public class Destination {
    String name;
    int demand;

    public Destination(String name, int demand) {
        this.name = name;
        this.demand = demand;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", demandCapacity=" + demand +
                "}\n";
    }
}
