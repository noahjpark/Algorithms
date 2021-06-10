/* Noah Park

Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.

Note that the root node is at depth 1.

The adding rule is:

Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
cur's original left subtree should be the left subtree of the new left subtree root.
cur's original right subtree should be the right subtree of the new right subtree root.
If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.

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
    
    // Intuition: Traverse the tree. At every point we reach the level before we insert, we insert the node to that position.
    // Time: O(n) to traverse the tree.
    // Space: O(h) height of the tree.
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode res = new TreeNode(val, root, null);
            return res;
        }
        
        dfs(root, val, depth, 1);
        return root;
    }
    
    public void dfs(TreeNode root, int val, int depth, int cur) {
        if (root == null) return;
        
        if (cur + 1 == depth) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        }
        
        dfs(root.left, val, depth, cur + 1);
        dfs(root.right, val, depth, cur + 1);
    }
    
}
