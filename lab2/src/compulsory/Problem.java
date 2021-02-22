package compulsory;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem {
    ArrayList<Destination> destinations = new ArrayList<>();
    ArrayList<Source> sources = new ArrayList<>();
    int[][] costMatrix;

    public void setCostMatrix(int[][] costMatrix) {
        this.costMatrix = costMatrix;
        //throw error maybe? (for bonus part)
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void addSource(Source source) {
        if(source.getSupplyCapacity() > 0) {
            this.sources.add(source);
        }
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void addDestination(Destination dest) {
        if(dest.getDemand() > 0){
            this.destinations.add(dest);
        }
    }

    @Override
    public String toString() {
        StringBuilder sourceString = new StringBuilder();
        for(var s : sources)
            sourceString.append(s.toString());

        StringBuilder destString = new StringBuilder();
        for(var d : destinations)
            destString.append(d.toString());

        return "Problem{" +
                "  \n sources:\n" + sourceString +
                "  \n destionations:\n" + destString +
                "  \n costMatrix=" + Arrays.deepToString(costMatrix) +
                "\n}";
    }

    public static void main(String[] args) {
        int[][] costMatrix1 = {{2, 3, 1}, {5, 4, 8}, {5, 6, 8}};
        Source gigaFactoryBerlin = new Source("giga factory berlin", SourceType.FACTORY, 20);
        Source gigaFactoryShanghai = new Source("giga factory shanghai", SourceType.FACTORY, 25);
        Source warehouseEurope = new Source("europe warehouse", SourceType.WAREHOUSE, 200);
        Destination country1 = new Destination("romania", 300);
        Destination country2 = new Destination("netherlands", 500);
        Destination country3 = new Destination("greece", 100);
        Problem instance = new Problem();
        instance.addSource(gigaFactoryBerlin);
        instance.addSource(gigaFactoryShanghai);
        instance.addSource(warehouseEurope);
        instance.setCostMatrix(costMatrix1);
        instance.addDestination(country1);
        instance.addDestination(country2);
        instance.addDestination(country3);
        System.out.println(instance);
    }
}
