/* Noah Park

Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

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
    
    int i = 0, j = 0;
    
    // Intuition: Maintain both global indices for pre and post instead of just pre. We continue building the left subtree of the current root until we reach the post value, then the index will be updated for the next call in the stack.
    // Time: O(n) to build tree.
    // Space: O(h) height of the tree.
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, post);
    }
    
    public TreeNode construct(int[] pre, int[] post) {
        if (i >= pre.length || j >= post.length) return null;
        
        TreeNode root = new TreeNode(pre[i++]);
        
        if (root.val != post[j]) root.left = construct(pre, post);
        if (root.val != post[j]) root.right = construct(pre, post);
        j++;
        
        return root;
    }
    
}
