/* Noah Park

In a binary tree, a lonely node is a node that is the only child of its parent node. The root of the tree is not lonely because it does not have a parent node.

Given the root of a binary tree, return an array containing the values of all lonely nodes in the tree. Return the list in any order.

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
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> res = new ArrayList<>(); // resulting list
        preorder(root, res); // populate the list using preorder
        return res; // return the list
    }
    
    public void preorder(TreeNode root, List<Integer> res) {
        if (root != null) { // if root is not null we can see if it only has one child
            preorder(root.left, res); // left tree
            if (root.left == null ^ root.right == null) res.add(root.left == null ? root.right.val : root.left.val); // if only one is null, add that child's value
            preorder(root.right, res); // right tree
        }
    }
}
