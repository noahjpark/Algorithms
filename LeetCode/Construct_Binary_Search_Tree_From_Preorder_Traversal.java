/* Noah Park

Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.

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
    
    // Intuition: The only thing we have to worry about is when to stop building in a particular direction. If the index goes out of bounds, naturally we stop. However, the only other thing we need to worry about is that the current value we want to use to build our new node is too large. Note that it cannot be too small since this is a bst and we are going in preorder order. The maximum value is wherever we are coming from and it allows us to know if we need to finish the recursive line in one direction and backtrack.
    // Time: O(n) to create the tree.
    // Space: O(h) to go to the depth of the tree.
    int i = 0;
    
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        
        return dfs(preorder, Integer.MAX_VALUE);
    }
    
    public TreeNode dfs(int[] preorder, int max) {
        if (i >= preorder.length || preorder[i] > max) return null;
        
        TreeNode root = new TreeNode(preorder[i++]);
        root.left = dfs(preorder, root.val);
        root.right = dfs(preorder, max);
            
        return root;
    }
}
