/* Noah Park

Given the root of a binary tree, the depth of each node is the shortest distance to the root.

Return the smallest subtree such that it contains all the deepest nodes in the original tree.

A node is called the deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.

Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/

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
    
    int depth = 0, n = 0;
    TreeNode res = null;
    
    // Intuition: Two pass dfs approach to first find the maximum depth and how many nodes are at that depth. Then we use a second dfs to find the first node that contains all nodes at that depth with an optimization to stop recursing once we have updated this node.
    // Time: O(n) two passes over the tree.
    // Space: O(h) height of the tree for the recursion.
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        countDeepest(root, 0);
        dfs(root, 0);
        
        return res;
    }
    
    public int dfs(TreeNode root, int d) {
        if (root == null || res != null) return 0;
        if (d == depth) {
            if (n == 1) res = root;   
            return 1;
        }
        
        int left = dfs(root.left, d + 1), right = dfs(root.right, d + 1);
        if (left + right == n && res == null) res = root;
        
        return left + right;
    }
    
    public void countDeepest(TreeNode root, int d) {
        if (root == null) return;
        
        if (d > depth) { depth = d; n = 1; }
        else if (d == depth) n++;
        
        countDeepest(root.left, d + 1);
        countDeepest(root.right, d + 1);
    }
}
