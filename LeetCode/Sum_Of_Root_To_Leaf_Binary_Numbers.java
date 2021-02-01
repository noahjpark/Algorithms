/* Noah Park

You are given the root of a binary tree where each node has a value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers. The answer is guaranteed to fit in a 32-bits integer.

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
    int sum = 0;
    
    // Time: O(n)
    // Space: O(h) where h is the height for the recursive stack space
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0; // edge case
        
        sumRootToLeaf(root, 0);
        return sum;
    }
    
    public void sumRootToLeaf(TreeNode root, int cur) {
        if (root == null) return; // edge case
        
        // shift over to 'store the value' then add the current bit onto the right side
        cur <<= 1;
        cur |= root.val;
        
        // if we are at a leaf, update the sum
        if (root.left == null && root.right == null) sum += cur;
        
        // dfs on left and right side
        sumRootToLeaf(root.left, cur);
        sumRootToLeaf(root.right, cur);
    }
}
