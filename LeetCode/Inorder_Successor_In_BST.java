/* Noah Park

Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST. If the given node has no in-order successor in the tree, return null.

The successor of a node p is the node with the smallest key greater than p.val.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    // Intuition: We know that we can discard the left subtree and move to the right. Otherwise we store the root and move to the left because p is somewhere in the left subtree.
    // Time: O(log n) since we always split the tree in half though it could result in n traversals.
    // Space: O(1) constant.
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode s = null;
        
        while (root != null) {
            if (root.val <= p.val) root = root.right;
            else { s = root; root = root.left; }
        }
        
        return s;
    }
    
    // Intuition: Traverse inorder updating the prev and cur nodes. prev gets updated when we find p. If prev is not null and cur is, that is our successor. cur is only updated once.
    // Time: O(n) to search through the tree.
    // Space: O(log n) for the recursive stack space.
    TreeNode prev = null, cur = null;
    
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        inorder(root, p);
        return cur;
    }
    
    public void inorder(TreeNode root, TreeNode p) {
        if (root != null) {
            inorder(root.left, p);
            if (prev != null && cur == null) { cur = root; return; }
            if (root == p) prev = p;
            inorder(root.right, p);
        }
    }
}
