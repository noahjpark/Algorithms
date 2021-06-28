/* Noah Park

Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/

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
    public TreeNode lcaDeepestLeaves(TreeNode root) {
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
