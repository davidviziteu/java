package optional;

import compulsory.Destination;

public class Solution {
    ProblemOptional data;
    /*
     * @param
     *
     */
    public Solution(ProblemOptional inst){
        data = inst;
    }

    public boolean isSolvable(){
        int totalResources = 0;
        for(var s : data.getSources())
            totalResources += s.getSupplyCapacity();
        int totalDemand = 0;
        for(var d : data.getDestinations())
            totalDemand += d.getDemand();
        return (totalResources >= totalDemand);
    }

    void printPartialSolution(SourceAbstract from, Destination to, int units){
//        System.out.println(from.getName() + " -> " + to.getName() + ": " +units + " units");
        System.out.printf("%-23s %-3s %-18s%-2s%4d%8s\n", from.getName(), "->", to.getName(), ":", units, "units");
    }

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
