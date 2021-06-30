/* Noah Park

For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivelent or false otherwise.

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
    
    // Intuition: Inline comments speak for themselves. At any given node, we ensure everything matches and move through the tree.
    // Time: O(n) to iterate in a single pass over the "identical" trees.
    // Space: O(h) height of the recursion stack.
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }
    
    public boolean dfs(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) return true; // both end at the same position
        if (r1 == null || r2 == null) return false; // one ends early
        
        TreeNode l1 = r1.left, l2 = r2.left, rr1 = r1.right, rr2 = r2.right;
        if (count(l1, rr1) != count(l2, rr2)) return false; // child count is not the same
        
        // If there is at least one child, we check if the nulls don't match on the left/right sides and then check if the count was 2 if the values on one of the sides don't match up. These cases result in a swap attempt.
        if (count(l1, rr1) > 0) {
            if ((l1 == null && rr2 == null) || (rr1 == null && l2 == null) || (count(l1, rr1) == 2 && l1.val != l2.val)) {
                r1.left = rr1;
                r1.right = l1;
            }
        }
            
        return r1.val == r2.val && dfs(r1.left, r2.left) && dfs(r1.right, r2.right); // return true if the values at each point match, and everything else matches as well later down in the tree.
    }
    
    public int count(TreeNode l, TreeNode r) {
        if (l != null && r != null) return 2;
        return l == null && r == null ? 0 : 1;
    }
}
