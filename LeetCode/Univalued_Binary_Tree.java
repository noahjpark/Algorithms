/* Noah Park

A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

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
    // Time: O(n)
    // Space: O(h) where h is the height of the tree
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        
        return isUnivalTree(root, root.val);
    }
    
    public boolean isUnivalTree(TreeNode root, int val) {
        if (root == null) return true;
        
        return root.val == val && isUnivalTree(root.left, val) && isUnivalTree(root.right, val);
    }
}
