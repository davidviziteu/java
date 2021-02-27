package optional;


import compulsory.Destination;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[][] costMatrix1 = {{2, 3, 1}, {5, 4, 8}, {5, 6, 8}};
        Factory gigaFactoryBerlin = new Factory("giga factory berlin", 20);
        Factory gigaFactoryShanghai = new Factory("giga factory shanghai", 25);
        Warehouse warehouseEurope = new Warehouse("europe warehouse", 200);
        Destination country1 = new Destination("romania", 30);
        Destination country2 = new Destination("netherlands", 200);
        Destination country3 = new Destination("greece", 10);
        ProblemOptional instance = new ProblemOptional();
        instance.addSource(gigaFactoryBerlin);
        instance.addSource(gigaFactoryBerlin);
        instance.addSource(gigaFactoryShanghai);
        instance.addSource(warehouseEurope);
        instance.setCostMatrix(costMatrix1);
        instance.addDestination(country1);
        instance.addDestination(country2);
        instance.addDestination(country3);
//        System.out.println(instance);
        var solutionInstance = new Solution(instance);
        if(solutionInstance.isSolvable())
            solutionInstance.solve();
        else System.out.println("Unsolvable instance");
    }
}