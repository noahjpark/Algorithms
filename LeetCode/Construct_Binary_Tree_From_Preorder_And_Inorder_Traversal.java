/* Noah Park

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1); // build tree helper
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder, int p, int is, int ie) {
        if (p >= preorder.length || is > ie) return null; // once we have finished a branch we stop
        
        TreeNode cur = new TreeNode(preorder[p]); // set current node
        int idx = 0;
        for (int i = is; i <= ie; i++) // find index into inorder array
            if (inorder[i] == preorder[p]) idx = i;
        
        cur.left = buildTree(preorder, inorder, p + 1, is, idx - 1); // set left node recursively
        cur.right = buildTree(preorder, inorder, p + idx - is + 1, idx + 1, ie); // set right node recursively
        
        return cur;
    }
}
