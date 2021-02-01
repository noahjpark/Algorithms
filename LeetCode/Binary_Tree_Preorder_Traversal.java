/* Noah Park

Given the root of a binary tree, return the preorder traversal of its nodes' values.

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
    // Time: O(n)
    // Space: O(h) where h is the height of the tree
    // typical preorder algorithm
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }
    
    public void preorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            preorder(root.left, res);
            preorder(root.right, res);
        }
    }
}
