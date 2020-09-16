// Noah Park
/*

Problem: Write a function that takes in a potentially invalid BST and returns a boolean representing
whether the BST is valid.

Each BST node has an integer value, a left child node, and a right child node. A node is said to be
a valid BST node if and only if it satisfies the BST property: its value is strictly greater than the
value of every node to its left; its value is less than or equal to the values of every node to its right;
and its children nodes are either valid BST nodes themselves or None / null.

A BST is valid if and only if all of its nodes are valid BST nodes

*/

public class Validate_BST {
    // Validates a BST is in fact a BST
    public static boolean validateBst(BST tree) {
        // Use helper function while adjusting the bounds of what value the node must be
        // larger or smaller than
        // Starting from the root, the node can have any value; thus, use the minimum and maximum integer values as bounds
        return isBst(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Recursive helper method to check if a BST is a BST
    public static boolean isBst(BST tree, int min, int max){
        /*
        First ensure the value of our current node is valid for the tree.
        We adjust the bounds inwards as so: if we go left, the parent's value is passed
        in as the new max whereas if we go right, the parent's value is passed in as
        the new min. Thus, if tree's value is smaller than the min (checking right invalidation)
        or larger than the max (checking left invalidation), we want to return false
        */
        if(tree.value < min || tree.value >= max){
            return false;
        }

        // Following the explanation above, we need to pass in the new max/min depending on
        // if we are traversing to the left/right subtrees.
        if(tree.left != null && !isBst(tree.left, min, tree.value)){
            return false;
        }
        if(tree.right != null && !isBst(tree.right, tree.value, max)){
            return false;
        }

        // We have checked everything and all is good.
        return true;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
