// Noah Park
// Tree implementation for the tree data structure

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

}
