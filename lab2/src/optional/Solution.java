package optional;

import compulsory.Destination;

/**
 * class for solving an instance of the problem
 */
public class Solution {
    ProblemOptional data;

    /**
     * Class constructor
     * @param inst takes a problem class instance as parameter to access it's data
     */
    public Solution(ProblemOptional inst){
        data = inst;
    }

    /**
     *
     * if sum(demand) <= sum(supply) then the problem is solvable. otherwise it isn't
     * @return true / false depending if the problem instance is solvable
     */
    public boolean isSolvable(){
        int totalResources = 0;
        for(var s : data.getSources())
            totalResources += s.getSupplyCapacity();
        int totalDemand = 0;
        for(var d : data.getDestinations())
            totalDemand += d.getDemand();
        return (totalResources >= totalDemand);
    }

    /**
     * prints a nice formatting of a pair of supply-destination and the amount of units delivered
     * @param from source of units
     * @param to destination of units
     * @param units amount of units sent from source to destination
     */
    void printPartialSolution(SourceAbstract from, Destination to, int units){
        System.out.printf("%-23s %-3s %-18s%-2s%4d%8s\n", from.getName(), "->", to.getName(), ":", units, "units");
    }

    /**
     * solves a problem instance
     */
    public void solve(){
        var srcs = data.getSources();
        var dests = data.getDestinations();
        for (Destination d : dests) {
            for (int j = 0; j < srcs.size(); ++j) {
                var s = srcs.get(j);
                var diff = s.getSupplyCapacity() - d.getDemand();
                if (diff > 0) {
                    s.setSupplyCapacity(diff);
                    printPartialSolution(s, d, d.getDemand());
                    break;
                } else if (diff == 0) {
                    printPartialSolution(s, d, d.getDemand());
                    srcs.remove(s);
                    --j;
                    break;
                } else {
                    printPartialSolution(s, d, s.getSupplyCapacity());
                    d.setDemand(d.getDemand() - s.getSupplyCapacity());
                    srcs.remove(s);
                    --j;
                }
            }
        }

    }
}
