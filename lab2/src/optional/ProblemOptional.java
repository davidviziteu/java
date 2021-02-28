package optional;

import compulsory.Destination;
import compulsory.Source;
import compulsory.SourceType;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * implementaion of class problem for the optional part
 */
public class ProblemOptional {
    ArrayList<SourceAbstract> sources = new ArrayList<>();
    ArrayList<Destination> destinations = new ArrayList<>();

    int[][] costMatrix;

    /**
     * setter
     * @param costMatrix sets new cost matrix to problem instance
     */
    public void setCostMatrix(int[][] costMatrix) {

        this.costMatrix = costMatrix;
        //throw error maybe? (for bonus part)
    }

    /**
     *
     * @return return copy of sources array
     */
    public ArrayList<SourceAbstract> getSources() {
        return new ArrayList<>(sources);
    }

    /**
     * method to add a new unique source to the problem instance
     * it checks if the new object to be added has been added before and it ignores it if so
     * @param source the new source object to be added
     */
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

    /**
     *
     * @return return copy of destinations array
     */
    public ArrayList<Destination> getDestinations() {
        return new ArrayList<>(destinations);
    }

    /**
     * method to add a new unique destination to the problem instance
     * it checks if the new object to be added has been added before and it ignores it if so
     * @param dest the new destination object to be added
     */
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
    /**
     * override of the toString() method
     * @return string formatting of the instance
     */
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
