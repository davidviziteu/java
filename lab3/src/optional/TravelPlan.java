package optional;

import compulsory.baseClasses.Location;

import java.util.List;

public class TravelPlan {
    private List<Location> preferences;
    private CityOptional city;
    private int[][] matrix;
    public TravelPlan(CityOptional city, List<Location> preferences){
        this.city = city;
        this.preferences = preferences;
    }
    void computeMatrix(){
        matrix = new int[city.getLocationCount()][city.getLocationCount()];
        var locationList = city.getLocationList();
        for(var i = 0; i < locationList.size(); ++i) {
            for(var to : locationList.get(i).getTravelTimes().entrySet()) {
                matrix[i][city.getLocationIdx(to.getKey())] = to.getValue();
            }
        }
        locationList.forEach(l -> System.out.print(l.getName() + " "));
        System.out.println();
        for(var line : matrix){
            for (var nr : line){
                System.out.printf("%-8d ", nr);
            }
            System.out.println();
        }
        // intai preferinte si apoi cost
    }

    public void computeShortestPath(Location l1, Location l2){
        computeMatrix();
        var from = city.getLocationIdx(l1);
        var to = city.getLocationIdx(l2);

        this.V = city.getLocationCount();
        var dist = dijkstra(matrix, from);
        //then..?
    }

    int V = 9;
    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            if(dist[i] == Integer.MAX_VALUE)
                System.out.println(i + " \t\t -1" );
            else
                System.out.println(i + " \t\t " + dist[i]);
    }


    int[] dijkstra(int graph[][], int src)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist);
        return dist;
    }
}
