/* Noah Park

Given the root node of a binary search tree, return the sum of values of all nodes with a value in the range [low, high].

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
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0; // If there is no value, we return 0
        int sum = 0, left = 0, right = 0; // Store the sum of the current node, left subtree, and right subtree
        if(root.val >= L && root.val <= R) { // If we have a valid node, store the root's value and find the sums of the left and right subtrees
            sum = root.val;
            right = rangeSumBST(root.right, L, R);
            left = rangeSumBST(root.left, L, R);
        }
        
        // *Optimization* If the root value is too small, we don't need to look in the left subtree, only the right
        else if(root.val < L) right = rangeSumBST(root.right, L, R);
        
        // *Optimization* If the root value is too large, we don't need to look in the right subtree, only the left
        else if(root.val > R) left = rangeSumBST(root.left, L, R);
        
        return left + right + sum;
    }
}
