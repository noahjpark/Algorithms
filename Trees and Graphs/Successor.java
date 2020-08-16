// Noah Park
/*

Problem: Write an algorithm to find the "next" node (i.e., in-order
successor ) of a given node in a binary search tree. You may assume that each node has
a link to its parent.

*/

public class Successor<T extends Comparable<T>> {

    // Finds the 'in-order' successor to the passed in node
    public BinaryNode<T> findSuccessor(BST<T> bst, BinaryNode<T> node){
        //If node == null then it has no successor
        if(node == null){
            return null;
        }

        BinaryNode<T> successor = null;

        // The successor is in the right subtree
        if(node.getRight() != null){
            BinaryNode<T> right = node.getRight();
            // Find leftmost node of the right subtree
            while(right.getLeft() != null){
                right = right.getLeft();
            }
            successor = right;
        }

        // We need to find the parent
        else{
            successor = this.findParent(bst, node);
        }

        return successor;
    }

    // Find the parent node to the given node
    public BinaryNode<T> findParent(BST<T> bst, BinaryNode<T> node){
        // If node == null, then it has no parent
        if(node == null){
            return null;
        }

        BinaryNode<T> root = bst.getRoot();
        while(true){
            BinaryNode<T> left = root.getLeft();
            BinaryNode<T> right = root.getRight();
            if(left.getKey().compareTo(node.getKey()) == 0){
                return root;
            }
            else if(right.getKey().compareTo(node.getKey()) == 0){
                return root;
            }

            if(root.getKey().compareTo(node.getKey()) < 0){
                root = right;
            }
            else if(root.getKey().compareTo(node.getKey()) > 0){
                root = left;
            }
        }
    }

    public static void main(String[] args){

    }

}
