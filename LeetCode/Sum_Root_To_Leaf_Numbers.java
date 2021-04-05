/* Noah Park

You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers.

A leaf node is a node with no children.

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
    
    // Intuition: Utilize a global total to add to otherwise follow the rules. Accumulate a temporary path sum and add it to the total when we reach a leaf node.
    // Time: O(n) to search through all the nodes in the tree.
    // Space: O(h) for the height of the tree.
    int total = 0;
    
    public int sumNumbers(TreeNode root) {
        sum(root, 0);
        return total;
    }
    
    public void sum(TreeNode root, int cur) {
        if (root == null) return;
        
        cur = cur * 10 + root.val;
        
        if (root.left == null && root.right == null) total += cur; 
        
        sum(root.left, cur);
        sum(root.right, cur);
    }
}
