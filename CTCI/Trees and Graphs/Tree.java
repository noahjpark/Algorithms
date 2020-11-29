// Noah Park
// Tree implementation for the tree data structure

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree<T extends Comparable<T>> {

    // Root of the tree
    private Node<T> root;

    // Base constructor initializes the root to null
    public Tree(){
        this.root = null;
    }

    // Second constructor initializes the root to the argument 'root'
    public Tree(Node<T> root){
        this.root = root;
    }

    // Returns the root
    public Node<T> getRoot(){
        return this.root;
    }

    // Updates the root
    public void setRoot(Node<T> root){
        this.root = root;
    }

    // Reset the tree
    public void reset(){
        Stack<Node<T>> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            Node<T> cur = s.pop();
            cur.reset();
            for(Node<T> c : cur.getChildren()){
                s.push(c);
            }
        }
    }

    // Quick implementation of breadth first search on a graph to find a specific node
    public boolean bfs(Node<T> root, Node<T> target){
        // Use a queue to visit all children first then move on
        // We mark the root to notify that we will search it
        // Then add it to the queue
        Queue<Node<T>> to_search = new LinkedList<>();
        root.mark();
        to_search.add(root);

        // While the queue is not empty we want to remove the
        // current node and visit it
        // Then loop through its children and mark each one that
        // is not marked as well as add it to the queue if it is
        // not yet marked.
        // This will result in us adding all children at each level
        // iteratively in order.
        while(!to_search.isEmpty()){
            Node<T> front = to_search.remove();
            front.visit();
            //System.out.println(front.getName());
            if(front.equals(target)){ // Check if the current node matches the target node
                // Found target
                // Reset the graph and return true
                this.reset();
                return true;
            }
            for(Node<T> node : front.getChildren()){
                if(!node.isMarked()){
                    node.mark();
                    to_search.add(node);
                }
            }
        }

        // We searched and couldn't find target
        // Reset the graph and return false
        this.reset();
        return false;
    }

}
