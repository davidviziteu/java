import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        //        optional
        long startTime = System.nanoTime();
        int n = 0;
        var rand = new Random();
        String input;
        if (args.length != 1) {
            System.out.println("no args given. please give n here:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            input = in.readLine();
        } else input = args[0];
        try {
            n = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("that's not an integer");
            exit(0);
        }
        var matrix = new int[n][n];
        tree_matrix = new int[n][n];
        var edges = rand.nextInt(n * (n - 1) / 2 + 1);
        for (var i = 0; i < edges; ++i) {
            var node1 = rand.nextInt(n);
            var node2 = rand.nextInt(n);
            if (node1 == node2 || matrix[node1][node2] == 1) {
                --i;
                continue;
            }
            matrix[node1][node2] = 1;
            matrix[node2][node1] = 1;
        }

        do_the_connected_stuff(matrix);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("app runtime: " + totalTime / 1000000000 + " ms");
    }

    public static void do_the_connected_stuff(int[][] matrix) {
        int[] visited = new int[matrix.length];
        for (var i = 0; i < matrix.length; ++i) {
            if (visited[i] == 0) {
                visited[i] = i + 1;
                visited = get_connected_comp(i, matrix, visited, i + 1);
            }
        }
        //old laptop :((
        if(matrix.length < 3000) {
            display_matrix_nice(matrix);
            System.out.println("above is the graph matrix\n");
        }

        if (is_not_connected(visited)) {
            System.out.println("the graph is not connected");
            print_conn_comps(visited);
        }
        else{
            if(matrix.length < 3000)
                display_matrix_nice(tree_matrix);
            System.out.println("the graph is connected. above is the tree matrix");

        }
        System.out.println();
    }

    public static boolean is_not_connected(int[] visited) {
        for (var elem : visited) {
            if (elem != visited[0])
                return true;
        }
        return false;
    }

    public static void print_conn_comps(int[] visited) {
        lab1_optional.visited = visited;
        int nr_of_components = 0;
        System.out.print("connected components:");
        for (var i = 0; i < visited.length; ++i) {
            if (visited[i] != -1) {
                System.out.print("\n" + i);
                ++nr_of_components;
            }

            for (var j = i + 1; j < visited.length; ++j) {
                if (visited[i] == visited[j] && visited[i] != -1) {
                    System.out.print(" " + j);
                    visited[j] = -1;
                }
            }
        }
        System.out.println("\nthe graph has " + nr_of_components + " connected components");
    }

    public static int[] get_connected_comp(int start, int[][] matrix, int[] visited, int color) {
        for (var i = 0; i < matrix.length; ++i) {
            if (matrix[start][i] == 1 && visited[i] == 0) {
                visited[i] = color;
                tree_matrix[start][i] = tree_matrix[i][start] = 1; //it assumes that the graph is connected and
                // it computes the DFS tree matrix;
                visited = get_connected_comp(i, matrix, visited, color);
            }
        }
        return visited;
    }
    public static int get_max(int[] arr){
        int max = arr[0];
        for(var nr : arr){
            if(nr > max)
                max = nr;
        }
        return max;
    }
    public static int[][] tree_matrix;
//    public static int[] compute_tree_matrix(int start, int[][] matrix, int[] visited) {
//        for (var i = 0; i < matrix.length; ++i) {
//            if (matrix[start][i] == 1 && visited[i] == 0) {
//                visited[i] = 1;
//                visited = compute_tree_matrix(i, matrix, visited);
//            }
//        }
//        return visited;
//    }
    public static void display_matrix_nice(int[][] matrix){
        for (int[] row : matrix) {
            for (int x : row) {
                if (x == 1)
                    System.out.print("🔲 ");
                else
                    System.out.print("🔳 ");
            }
            System.out.println();
        }
    }
}