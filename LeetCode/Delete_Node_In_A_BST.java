/* Noah Park

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Follow up: Can you solve it with time complexity O(height of tree)?

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
    
    // Intuition: Always treat the removing node as a root and write a function that removes a node given at the root.
    // Time: O(h) where h is the height of the tree.
    // Space: O(1)
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root, parent = null;
        
        while (cur != null) {
            if (cur.val == key) break;
            parent = cur;
            if (cur.val < key) cur = cur.right;
            else cur = cur.left;
        }
        
        if (parent == null) return deleteRoot(cur);
        else if (parent.left == cur) parent.left = deleteRoot(cur);
        else parent.right = deleteRoot(cur);
        
        return root;
    }
    
    public TreeNode deleteRoot(TreeNode root) {
        if (root == null) return null;
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        
        TreeNode replace = root.right, parent = null;
        while (replace.left != null) { parent = replace; replace = replace.left; } // replace will be the smallest in the right subtree of root
        replace.left = root.left; // replace left is currently null
        if (root.right != replace) { // if replace's right needs to be updated, replace's parent will take replace's subtree and replace's right will take root's subtree
            parent.left = replace.right;
            replace.right = root.right;
        }
        
        return replace;
    }
}
