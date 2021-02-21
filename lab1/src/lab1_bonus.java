import java.util.Random;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import tree_node.*;

public class lab1_bonus {
    public static int MAX_SONS = 5;
    public static int MAX_NODES = 50;
    public static node generate_tree(int nodes){
        Queue<node> Queue = new LinkedList<>();
        var rand = new Random();
        int level = 0;
        var root = new node(level);
        var final_tree = root;
        var i = 1;
        while(i < nodes){
            var nr_of_sons = rand.nextInt(MAX_SONS);
            for(var k = 0; k < nr_of_sons; ++k) {
                var new_son = new node(root.level + 1);
                root.sons.add(new_son);
                ++i;
                Queue.add(new_son);
            }
            root = Queue.remove();
        }
        return final_tree;
    }
    static int number = 0;
    public static void print_tree(node root){
        if(root != null) {
            print_tabs(root.level);
            if(root.sons.isEmpty())
                System.out.println("-node" + number++);
            else {
                System.out.println("+node" + number++);
                root.sons.forEach(lab1_bonus::print_tree);
            }
        }
    }
    public static void print_tabs(int how_many){
        for(var i = 0; i < how_many; ++i){
            System.out.print(' ');
        }
    }
    public static void main(String[] args) {
        var rand = new Random();
        var tree_nodes = 3 + rand.nextInt(MAX_NODES);
        System.out.println("the tree has " + tree_nodes + " nodes");
        var tree = generate_tree(tree_nodes);
        print_tree(tree);
        System.out.println();
    }
}

