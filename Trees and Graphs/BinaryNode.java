// Noah Park
// Node for the BST

public class BinaryNode<T extends Comparable<T>> {

    // key and value are unique to the node
    // left and right represent the left and right children
    // in a BST
    private T key;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private boolean visited, marked; // For performing bfs/dfs

    // Initializes a BinaryNode object with the passed in key and
    // value with its left/right set to null
    public BinaryNode(T key){
        this.key = key;
        this.left = null;
        this.right = null;
        this.visited = false;
        this.marked = false;
    }

    // Returns the key of this node
    public T getKey(){
        return this.key;
    }

    // Updates the key of this node
    public void setKey(T key){
        this.key = key;
    }

    // Returns the left child of this node
    public BinaryNode<T> getLeft(){
        return this.left;
    }

    // Returns the right child of this node
    public BinaryNode<T> getRight(){
        return this.right;
    }

    // Sets the left child of this node
    public void setLeft(BinaryNode<T> left){
        this.left = left;
    }

    // Sets the right child of this node
    public void setRight(BinaryNode<T> right){
        this.right = right;
    }

    // Resets the visited and marked attributes
    public void reset(){
        this.visited = false;
        this.marked = false;
    }

    // Returns if the node has been visited
    public boolean isVisited(){
        return this.visited;
    }

    // Returns if the node has been marked (bfs)
    public boolean isMarked(){
        return this.marked;
    }

    // Visits the node
    public void visit(){
        this.visited = true;
    }

    // Marks the node (bfs)
    public void mark(){
        this.marked = true;
    }

}
