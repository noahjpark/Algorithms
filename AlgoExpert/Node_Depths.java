// Noah Park
/*

Problem: The distance between a node in a Binary Tree and the tree's root is called the node's
depth.

Write a function that takes in a Binary Tree and returns the sum of its nodes' depths.

Each BinaryTree node has an integer value, a left child node, and a right child node.
Children nodes can either be BinaryTree nodes themselves or None / null.

*/

public class Node_Depths {
    // Driver function to count all depths of the nodes in the tree
    // Runs in O(n) time and O(h) space (height of the binary tree)
    // Space is because we are pushing the nodes onto the stack frame
    public static int nodeDepths(BinaryTree root) {
        return countDepths(root, 0); // Call helper function with starting depth of 0
    }

    // Recursive helper function to count depths of the nodes in the tree
    public static int countDepths(BinaryTree root, int curDepth){
        // If the root is null, return 0. This adds 0 to the current depth
        if(root == null){
            return 0;
        }

        // Add the current depth plus the recursive call to the left and right which will inadvertently
        // add the depths of all nodes in the left and right subtrees resulting in a total value being returned
        // equal to that of all node depths in the tree.
        return curDepth + countDepths(root.left, curDepth + 1) + countDepths(root.right, curDepth + 1);
    }

    // Basic BinaryTree class for testing
    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
