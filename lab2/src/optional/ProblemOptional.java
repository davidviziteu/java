package optional;

import compulsory.Destination;
import compulsory.Source;
import compulsory.SourceType;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;


public class ProblemOptional {
    ArrayList<SourceAbstract> sources = new ArrayList<>();
    ArrayList<Destination> destinations = new ArrayList<>();

    int[][] costMatrix;

    public void setCostMatrix(int[][] costMatrix) {

        this.costMatrix = costMatrix;
        //throw error maybe? (for bonus part)
    }

    public ArrayList<SourceAbstract> getSources() {
        return new ArrayList<>(sources);
    }

    public void addSource(SourceAbstract source) {
        for(var s : sources)
            if(s.equals(source)) {
                System.out.println("Adding the same source to the problem instance, skipping");
                return;
            }
        if(source.getSupplyCapacity() > 0) {
            this.sources.add(source);
        }
    }

    public ArrayList<Destination> getDestinations() {
        return new ArrayList<>(destinations);
    }

    public void addDestination(Destination dest) {
        for(var s : destinations)
            if(s.equals(dest)) {
                System.out.println("Adding the same destination, skipping");
                return;
            }
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

        return "\nProblem{" +
                "  \n----sources:\n" + sourceString +
                "  \n----destinations:\n" + destString +
                "  \ncostMatrix=" + Arrays.deepToString(costMatrix) +
                "\n}";
    }
}
