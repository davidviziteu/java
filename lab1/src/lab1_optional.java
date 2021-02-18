import static java.lang.System.exit;
import java.util.Random;
// Let n be an odd integer given as a command line argument. Validate the argument!
//                Create a n x n matrix, representing the adjacency matrix of a random graph .
//                Display on the screen the generated matrix (you might want to use the geometric shapes from the
//                Unicode chart to create a "pretty" representation of the matrix).
//        Verify if the generated graph is connected and display the connected components (if it is not).
//        Assuming that the generated graph is connected, implement an algorithm that creates a partial tree of the graph.
//        Display the adjacency matrix of the tree.
//                For larger n display the running time of the application in nanoseconds (DO NOT display the matrices).
//                Try n > 30_000. You might want to adjust the JVM Heap Space using the VM options -Xms4G -Xmx4G.
//                Launch the application from the command line, for example: java Lab1 100.

public class lab1_optional {
    public static void main(String[] args) {
        //        optional
        int n = 5;
        var rand = new Random();
        if (args.length != 1) {
            System.out.println("no args given. i need exactly 1 integer. I'm assigning a default val to n...");
//            exit(0);
        }
        else try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("that's not an integer");
            exit(0);
        }
        var matrix = new int[n][n];
        var edges = rand.nextInt(n);
        for(var i = 0; i < edges; ++i){
            var node1 = rand.nextInt(n);
            var node2 = rand.nextInt(n);
            if(node1 == node2 || matrix[node1][node2] == 1) {
                --i;
                continue;
            }
            matrix[node1][node2] = 1;
            matrix[node2][node1] = 1;
        }
        for (int[] row : matrix) {
            for (int x : row){
                if(x == 1)
                    System.out.print("🔲 ");
                else
                    System.out.print("🔳 ");
            }
            System.out.println();
        }
        do_the_connected_stuff(matrix);
    }

    public static void do_the_connected_stuff(int[][] matrix){
        var visited = new int[matrix.length];
        System.out.println("connected components (nodes):");
        for(var i = 0; i < matrix.length; ++i){
            if(visited[i] == 0) {
                visited[i] = i + 1;
//                System.out.print(i + " ");
                visited = get_connected_comp(i, matrix, visited, i + 1);
//                System.out.println();
            }
        }

        for(var i : visited){
            System.out.print(i + " ");
        }

    }
    public static int[] get_connected_comp(int start, int[][] matrix, int[] visited, int color){
        for(var i = 0 ; i < matrix.length; ++i){
            if(matrix[start][i] == 1 && visited[i] == 0) {
                visited[i] = color;
//                System.out.print(i + " ");
                visited = get_connected_comp(i, matrix, visited, color);
            }
        }
        return visited;
    }
}