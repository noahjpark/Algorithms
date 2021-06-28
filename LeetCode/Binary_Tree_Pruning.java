/* Noah Park

Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

A subtree of a node node is node plus every node that is a descendant of node.

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
    
    // Intuition: Maintain the number of ones in each subtree at a given node. Whenever we find 0, prune that branch. Continue this through the root. In the driver function, we check if there were no ones at all (edge case if root is 0 and also has no subtrees with ones) and set the entire tree to null if so.
    // Time: O(n) to visit the entire tree.
    // Space: O(h) recursion stack space.c
    public TreeNode pruneTree(TreeNode root) {
        int ones = prune(root);
        return ones == 0 ? null : root;
    }
    
    public int prune(TreeNode root) {
        if (root == null) return 0;
        
        int left = prune(root.left), right = prune(root.right), cur = root.val == 1 ? 1 : 0;
        if (left == 0) root.left = null;
        if (right == 0) root.right = null;
        
        return left + right + cur;
    }
    
}
