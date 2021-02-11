/* Noah Park

Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

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
    
    int prev = -1, minimum = Integer.MAX_VALUE; // prev is the previous value in sorted order in the bst, minimum is the minimum difference
    
    // gets the minimum difference between nodes in the tree
    // Time: O(n) - one pass
    // Space: O(h) where h is the height of the bst
    public int getMinimumDifference(TreeNode root) {
        preorder(root);
        return minimum;
    }
    
    // modified preorder algorithm
    public void preorder(TreeNode root) {
        if (root != null) {
            preorder(root.left);
            
            // if we have seen at least one node, check the current difference from the previous in sorted order
            if (prev != -1) minimum = Math.min(minimum, root.val - prev);
            prev = root.val; // update prev to the next in the sequence in the bst
            preorder(root.right);
        }
    }
    
    // gets minimum difference between any node in the bst
    // Time: O(n) - two pass
    // Space: O(n) - for storage of the bst into a list
    public int getMinimumDifferenceFirst(TreeNode root) {
        // store bst in list
        List<Integer> sorted = new ArrayList<>();
        
        // use preorder for one pass sorting
        help(root, sorted);
        
        int min = Integer.MAX_VALUE;
        
        // find minimum difference between adjacent spots
        for (int i = 1; i < sorted.size(); i++) 
            min = Math.min(sorted.get(i) - sorted.get(i - 1), min);
        
        return min;
    }
    
    // typical preorder algorithm
    public void help(TreeNode root, List<Integer> sorted) {
        if (root != null) {
            help(root.left, sorted);
            sorted.add(root.val);
            help(root.right, sorted);
        }
    }
}
