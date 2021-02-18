package tree_node;
public class node {
    public int level;
    public node left;
    public node right;
    public node(int level){
        this.level = level;
        left = null;
        right = null;
    }
}