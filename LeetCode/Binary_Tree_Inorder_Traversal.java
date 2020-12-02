/* Noah Park

Given the root of a binary tree, return the inorder traversal of its nodes' values.

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrderIterative(root, res);
        return res;
    }
    
    // recursive version
    public void inOrder(TreeNode root, List<Integer> res) {
        if (root != null) {
            inOrder(root.left, res);
            res.add(root.val);
            inOrder(root.right, res);
        }
    }
    
    // iterative version using a stack
    public void inOrderIterative(TreeNode root, List<Integer> res) {
        if(root != null) {
            Stack<TreeNode> s = new Stack<>();
            TreeNode temp = root;
            while (!s.isEmpty() || temp != null) {
                // add all the way to the left that we can
                while(temp != null) { s.push(temp); temp = temp.left; }

                // do the current
                temp = s.pop();
                res.add(temp.val);
                // move to the right
                temp = temp.right;
            }
        }
    }
}
