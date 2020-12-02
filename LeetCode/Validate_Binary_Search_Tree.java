/* Noah Park

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

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
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return inOrder(root);
        //return checkBST(root, null, null);
    }
    
    // in order iterative solution with stack O(n)
    public boolean inOrder(TreeNode root) {
        Integer num = null;
        if (root != null) {
            Stack<TreeNode> s = new Stack<>();
            TreeNode temp = root;
            while (!s.isEmpty() || temp != null) {
                while (temp != null) { s.push(temp); temp = temp.left; }
                
                temp = s.pop();
                if (num != null && num >= temp.val) return false;
                else num = temp.val;
                temp = temp.right;
            }
        }
        return true;
    }
    
    // recursive efficient version with pivot numbers O(n)
    public boolean checkBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        if (!checkBST(root.left, min, root.val)) return false;
        if (!checkBST(root.right, root.val, max)) return false;
        
        return true;
    }
    
    // brute force recursion O(n^2)
    public boolean isValidBSTBruteForce(TreeNode root) {
        if (root == null) return true;
        boolean isValid = checkLeft(root.left, root.val) && checkRight(root.right, root.val);
        return isValid && isValidBST(root.left) && isValidBST(root.right);
    }
    
    public boolean checkLeft(TreeNode root, int value) {
        if (root == null) return true;
        return root.val < value && checkLeft(root.left, value) && checkLeft(root.right, value);
    }
    
    public boolean checkRight(TreeNode root, int value) {
        if (root == null) return true;
        return root.val > value && checkRight(root.left, value) && checkRight(root.right, value);
    }
}
