// Noah Park
/*

Problem: Given a sorted (increasing order) array with unique integer
elements, write an algorithm to create a binary search tree with minimal
height.

*/

public class Minimal_Tree<T extends Comparable<T>> {

    // Returns a minimum height BST given a sorted array
    public BST<T> createBST(T[] arr){

        // If array is null or is empty, return an empty tree
        if(arr == null || arr.length == 0){
            return new BST<>(null);
        }

        // Recursive helper function to create the minimum height
        // BST
        return new BST<>(createBSTHelper(arr, 0, arr.length - 1));
    }

    // Recursively builds the minimum height BST
    public BinaryNode<T> createBSTHelper(T[] arr, int start, int end){
        // If the start is greater than the end, set the left/right child
        // to null
        if(start > end){
            return null;
        }

        // Store a reference node using the center of the start/end indices
        int center = (end + start) / 2;
        BinaryNode<T> node = new BinaryNode<>(arr[center]);

        // Set its left and right using the first half and second half of the
        // array, respectively, as it is in sorted order
        node.setLeft(createBSTHelper(arr, start, center - 1));
        node.setRight(createBSTHelper(arr, center + 1, end));

        return node;
    }

    // Print function to help test if the above function worked
    public void printPreOrder(BinaryNode<T> root){
        printPreOrderHelper(root, 0);
    }

    // Recursive helper function for printPreOrder to print out the nodes on each level
    public void printPreOrderHelper(BinaryNode<T> node, int level){
        if(node != null){
            System.out.println("Key: " + node.getKey() + " | Level: " + level);
            printPreOrderHelper(node.getLeft(), level + 1);
            printPreOrderHelper(node.getRight(), level + 1);
        }
    }

    // Testing for the above functions
    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        Minimal_Tree<Integer> m = new Minimal_Tree<>();
        Integer[] intArr = new Integer[7];

        for(int i = 0; i < intArr.length; i++){
            intArr[i] = i;
        }

        BST<Integer> bst = m.createBST(intArr);

        m.printPreOrder(bst.getRoot());
    }

}
