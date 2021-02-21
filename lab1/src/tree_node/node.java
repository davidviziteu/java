package tree_node;

import java.util.ArrayList;
import java.util.List;

public class node {
    public int level;
    public ArrayList<node> sons = new ArrayList<node>();;
    public node(int level){
        this.level = level;
    }
}