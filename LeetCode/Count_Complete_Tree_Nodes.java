/* Noah Park

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

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
    
    // Intuition: Binary search like approach. For each node, we continue checking the outer left and right heights. If they match, we have added enough lower nodes and can add the second half (2^d - 1). Otherwise, we add 1 to the overall total.
    // Time: O(h^2) where h is the height of the tree; h ~ log(n)
    // Space: O(h) where he is the height of the tree.
    public int leftDepth(TreeNode root) {
        int d = 0;
        while (root != null) { root = root.left; d++; }
        return d;
    }
    
    public int rightDepth(TreeNode root) {
        int d = 0;
        while (root != null) { root = root.right; d++; }
        return d;
    }
    
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        int l = leftDepth(root), r = rightDepth(root);
        
        return l == r ? (1 << l) - 1 : 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    // Intuition: linear search on the tree.
    // Time: O(n) for each node in the tree.
    // Space: O(h) where h is the height of the tree.
    public int countNodesNaive(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.right) + countNodes(root.left);
    }
}
