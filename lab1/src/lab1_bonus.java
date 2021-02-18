import java.util.Random;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import tree_node.*;

public class lab1_bonus {
    public static node generate_tree(int nodes){
        Queue<node> Queue = new LinkedList<>();
        var rand = new Random();
        int level = 0;
        var root = new node(level);
        var final_tree = root;
        var i = 1;
        while(i < nodes){
            root.right = new node(root.level + 1) ;
            ++i;
            Queue.add(root.right);

            if(rand.nextInt(2) == 1){
                root.left = new node(root.level + 1);
                ++i;
                Queue.add(root.left);
            }
            root = Queue.remove();
        }
        return final_tree;
    }
    static int number = 0;
    public static void print_tree(node root){
        if(root != null) {
            print_tabs(root.level);
            if(root.left == root.right && root.left == null)
                System.out.println("-node" + number++);
            else {
                System.out.println("+node" + number++);
                print_tree(root.left);
                print_tree(root.right);
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
        var tree_nodes = 3 + rand.nextInt(50);
        System.out.println("the tree has " + tree_nodes + " nodes");
        var tree = generate_tree(tree_nodes);
        print_tree(tree);
        System.out.println();
    }
}

