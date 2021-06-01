/* Noah Park

You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.

In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.

Return the minimum number of moves required to make every node have exactly one coin.

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
    
    // Intuition: Post order dfs traversal. At each point, we consider how many coins the left and right subtrees have/need. If we are receiving a positive value, there is an excess, whereas a negative value indicates that it needs one. For the number of moves, we translate all values to positive and add them. Otherwise, we can return the literal values as this will be the remaining coins at each node.
    // Time: O(n) to post order through the tree.
    // Space: O(h) height of the tree.
    int res = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }
    
    public int dfs(TreeNode root) {
        if (root == null) return 0;
        
        int left = dfs(root.left), right = dfs(root.right);
        res += Math.abs(left) + Math.abs(right);
        
        return root.val + left + right - 1;
    }
}
