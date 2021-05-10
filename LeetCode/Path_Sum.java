/* Noah Park

Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

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
    
    // Intuition: Basic path sum checking left and right subtrees.
    // Time: O(n) to iterate over all nodes in the tree.
    // Space: O(h) height of the tree.
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPath(root, targetSum, 0);
    }
    
    public boolean hasPath(TreeNode root, int target, int cur) {
        if (root == null) return false;
        else if (root.left == null && root.right == null && root.val + cur == target) return true;
        return hasPath(root.left, target, cur + root.val) || hasPath(root.right, target, cur + root.val);
    }
}
