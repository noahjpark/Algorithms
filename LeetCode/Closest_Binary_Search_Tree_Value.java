/* Noah Park

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

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
    // Time: O(log n) | Space: O(h) where h is the height of the tree
    public int closestValue(TreeNode root, double target) {
        return findClosest(root, target, Integer.MAX_VALUE, Double.MAX_VALUE);
    }
    
    // finds the closest value to the target
    public int findClosest(TreeNode root, double target, int closest, double difference) {
        if (root == null) return closest; // return the closest found in the path
        
        // if we find a closer difference, update closest pointer and difference value
        if (Math.abs(root.val - target) < difference) { closest = root.val; difference = Math.abs(root.val - target); }
        
        // stay along the path of least differences
        return root.val < target ? findClosest(root.right, target, closest, difference) : findClosest(root.left, target, closest, difference);
    }
}
