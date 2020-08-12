// Noah Park
// Graph class to represent it as a data structure

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<T extends Comparable<T>> {

    // Stores all the nodes in the graph
    private ArrayList<Node<T>> nodes;

    // Base constructor initializes the graph as an arraylist
    public Graph(){
        this.nodes = new ArrayList<>();
    }

    // Returns the nodes in the graph as an arraylist
    public ArrayList<Node<T>> getNodes(){
        return this.nodes;
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }

    // Reset the graph
    public void reset(){
        for(Node<T> node : nodes){
            node.reset();
        }
    }

    // Quick implementation of depth first search on a graph
    // CALL RESET AFTER CALLING THIS FUNCTION TO DO MORE TESTING
    // OTHERWISE THE NODES WILL STILL BE VISITED
    public void dfs(Node<T> root){
        // Stop looking for the next node once we reach the end
        // of a string of children/path
        if(root == null){
            return;
        }

        root.visit(); // Visit the current node
        System.out.println(root.getName());

        // Go through each node in the adjacency list and check if
        // it is visited. If not, call dfs on the child
        for(Node<T> node : this.nodes){
            if(!node.isVisited()){
                dfs(node);
            }
        }
    }

    // Quick implementation of breadth first search on a graph
    public void bfs(Node<T> root){
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
            for(Node<T> node : front.getChildren()){
                if(!node.isMarked()){
                    node.mark();
                    to_search.add(node);
                }
            }
        }

        // Reset the graph attributes
        this.reset();
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
