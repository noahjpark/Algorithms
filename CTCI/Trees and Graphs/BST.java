// Noah Park
// Class to represent a BST

import java.util.Stack;

public class BST<T extends Comparable<T>> {

    // Represents the root of the BST
    private BinaryNode<T> root;

    // Base constructor that initializes the root to null with no
    // arguments
    public BST(){
        this.root = null;
    }

    // Secondary constructor that initializes the root to the given
    // argument 'root'
    public BST(BinaryNode<T> root){
        this.root = root;
    }

    // Returns the root
    public BinaryNode<T> getRoot(){
        return this.root;
    }

    // Sets the root
    public void setRoot(BinaryNode<T> root){
        this.root = root;
    }

    // Resets the visited/marked attributes of all nodes in the tree
    public void reset(){
        if(this.root == null){ // Obviously don't do anything if the root is null
            return;
        }
        // Use a stack to traverse the tree without modifying the marked/visited attributes
        // Simply resets each nodes' attributes using the reset() method I wrote
        Stack<BinaryNode<T>> s = new Stack<>();
        s.push(this.root);
        while(!s.isEmpty()){
            BinaryNode<T> node = s.pop();
            node.reset();
            if(node.getLeft() != null){
                s.push(node.getLeft());
            }
            if(node.getRight() != null){
                s.push(node.getRight());
            }
        }
    }

    // Returns the size of the BST
    public int getSize(){
        int ans = 0;
        Stack<BinaryNode<T>> s = new Stack<>();
        s.push(this.root);
        while(!s.isEmpty()){
            BinaryNode<T> node = s.pop();
            ans++;
            if(node.getLeft() != null){
                s.push(node.getLeft());
            }
            if(node.getRight() != null){
                s.push(node.getRight());
            }
        }

        return ans;
    }

}
