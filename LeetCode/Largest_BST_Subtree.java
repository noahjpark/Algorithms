/* Noah Park

Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.

A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:

The left subtree values are less than the value of their parent (root) node's value.
The right subtree values are greater than the value of their parent (root) node's value.
Note: A subtree must include all of its descendants.

Follow up: Can you figure out ways to solve it with O(n) time complexity?

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    // Intuition: Maintain the minimum and maximum value from each node and return it to its parent. This will tell us if we have a valid bst at any given point.
    // Time: O(n) to iterate over the tree.
    // Space: O(h) height of the tree for recursion.
    public int largestBSTSubtree(TreeNode root) {
        return isValid(root)[2];
    }
    
    public int[] isValid(TreeNode root) {
        if (root == null) return new int[]{ Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        
        int[] left = isValid(root.left), right = isValid(root.right);
        if (root.val > left[1] && root.val < right[0]) 
            return new int[]{ Math.min(left[0], root.val), Math.max(right[1], root.val), left[2] + right[2] + 1 };
        
        return new int[]{ Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2]) };
    }    
}
