/* Noah Park

Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.

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
    
    // Intuition: To maximize the difference between a child and the current node, we maintain the smallest and largest values seen so far in the tree.
    // Time: O(n) to iterate over the tree.
    // Space: O(h) height of the tree.
    int res = 0;
    
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }
    
    public int[] dfs(TreeNode root) {
        if (root == null) return new int[]{ Integer.MAX_VALUE, Integer.MIN_VALUE };
        
        int[] left = dfs(root.left), right = dfs(root.right), cur = new int[]{ root.val, root.val };
        cur[0] = Math.min(cur[0], Math.min(left[0], right[0]));
        cur[1] = Math.max(cur[1], Math.max(left[1], right[1]));
        
        res = Math.max(res, Math.max(Math.abs(root.val - cur[0]), Math.abs(root.val - cur[1])));
        
        return cur;
    }
}
