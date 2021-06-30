/* Noah Park

Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.

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
    
    // Intuition: Adjust the initial root pointer until it is in the valid range [low,high]. Then we just need to trim the rest of the tree. At each node, we adjust it to its appropriate value and set its left/right pointers to the recursively found appropriate values.
    // Time: O(n) single pass through the tree to trim it.
    // Space: O(h) height for the recursion stack.
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        while (root != null && ((root.val < low) || (root.val > high))) {
            while (root != null && root.val < low) root = root.right;
            while (root != null && root.val > high) root = root.left;
        }
        
        trim(root, low, high);
        return root;
    }
    
    public TreeNode trim(TreeNode root, int low, int high) {
        while (root != null && root.val < low) root = root.right;
        while (root != null && root.val > high) root = root.left;
        
        if (root == null) return root;
        
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        
        return root;
    }
}
