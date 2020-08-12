// Noah Park
// Node class for tree data structure

import java.util.ArrayList;

public class Node<T extends Comparable<T>> {

    // Name of the node and an arraylist of its children
    // visited and marked for algorithms
    private T name;
    private boolean visited;
    private ArrayList<Node<T>> children;
    private boolean marked;

    // Base constructor sets name and initializes and empty arraylist
    public Node(T name){
        this.name = name;
        this.children = new ArrayList<>();
        this.visited = false;
    }

    // Adds a child to the children list
    public void addChild(Node child){
        this.children.add(child);
    }

    // Returns the name of the node
    public T getName(){
        return this.name;
    }

    // Returns the children of the node
    public ArrayList<Node<T>> getChildren(){
        return this.children;
    }

    // Sets the name of the node
    public void setName(T name){
        this.name = name;
    }

    // Returns if the node has already been visited or not
    public boolean isVisited(){
        return this.visited;
    }

    // Visit the node
    public void visit(){
        this.visited = true;
    }

    // Reset node attributes
    public void reset(){
        this.visited = false;
        this.marked = false;
    }

    // Returns if the node has been marked yet
    public boolean isMarked(){
        return this.marked;
    }

    // Set the node as marked
    public void mark(){
        this.marked = true;
    }

    // Compare two nodes to check if they are equal or not
    public boolean equals(Node<T> other) {
        // Check that the names match
        if(this.getName().compareTo(other.getName()) != 0){
            return false;
        }

        // Check each child to make sure they match
        // If not return false
        for(int i = 0; i < this.getChildren().size(); i++){
            if(this.getChildren().get(i).getName().compareTo(other.getChildren().get(i).getName()) != 0){
                return false;
            }
        }

        // Everything matches, the nodes are equal and we return true
        return true;
    }
}
