/* Noah Park

Given the root of a binary tree, return the postorder traversal of its nodes' values.

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
    // Typical postorder algorithm
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }
    
    public void postorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            postorder(root.left, res);
            postorder(root.right, res);
            res.add(root.val);
        }
    }
}
