// Noah Park
/*

Problem: Write a function that takes in a Binary Tree and returns a list of its
branch sums ordered from leftmost branch sum to rightmost branch sum.

A branch sum is the sum of all values in a Binary Tree branch. A Binary Tree branch is a
path of nodes in a tree that starts at the root node and ends at any leaf node.

Each BinaryTree node has an integer value, a left child node, and a right child node.
Children nodes can either be BinaryTree nodes themselves or None / null.

*/

import java.util.ArrayList;
import java.util.List;

public class Branch_Sums {
    // Basic Binary Tree class
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Driver function
    // Operates in O(n) time and space
    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> branches = new ArrayList<>(); // branches ArrayList
        addBranches(root, branches, 0); // Call recursive helper function to build the array list starting from a 0 sum
        return branches;
    }

    // Counts all the branches recursively
    public static void addBranches(BinaryTree root, List<Integer> branches, int curSum){
        // We reach a leaf node and can add to the array list
        // Update cursum to add to the branches array list
        // We are adding from left to right by calling the left subtrees first
        if(root.left == null && root.right == null){
            branches.add(curSum + root.value);
            return;
        }

        if(root.left != null) {
            addBranches(root.left, branches, curSum + root.value);
        }
        if(root.right != null) {
            addBranches(root.right, branches, curSum + root.value);
        }
    }
}
