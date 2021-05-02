/* Noah Park

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

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
    
    Map<TreeNode, Integer> map = new HashMap<>();
    
    // Intuition: Find the height of each node as the root of the tree to ensure balance. Map should optimize the calls to height.
    // Time: O(n log n) to find the height at each point.
    // Space: O(h) for the height of the tree.
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int height(TreeNode root) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        
        int height = 1 + Math.max(height(root.left), height(root.right));
        map.put(root, height);
        return height;
    }
}
