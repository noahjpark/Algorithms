/* Noah Park

You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

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
    // Time: O(h) height of tree and Space: O(1)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode cur = root;
        
        if (root == null) root = new TreeNode(val);
        
        while (cur != null) {
            if (cur.val < val) {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                } 
                else cur = cur.right;
            } else {
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                }
                else cur = cur.left;
            }
        }
        
        return root;
    }
    
    public Treenode insertIntoBSTConcise(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) root.left = insertIntoBSTConcise(root.left, val);
        else root.right = insertIntoBSTConcise(root.right, val);
        return root;
    }
    
    // Time: O(h) height of tree and Space: O(h)
    public TreeNode insertIntoBSTRec(TreeNode root, int val) {
        if (root == null) root = new TreeNode(val);
        else insert(root, val);
        return root;
    }
    
    public void insert(TreeNode root, int val) {
        if (root != null) {
            if (root.val < val) {
                if (root.right == null) root.right = new TreeNode(val);
                else insert(root.right, val);
            } else {
                if (root.left == null) root.left = new TreeNode(val);
                else insert(root.left, val);
            }
        }
    }
}
