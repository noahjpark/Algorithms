/* Noah Park

Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

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
    
    // Intuition: Accumulate good in the global good variable. Maintain the largest node value in the current path so far. If the current node is larger than or equal to the largest node value, we found a good node. No matter what, we keep traversing updating largest if necessary to ensure we check all nodes in the tree and don't stop on bad ones.
    // Time: O(n) to search the whole tree.
    // Space: O(h) for the height of the tree.
    int good = 0;
    
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        inorder(root, root.val);
        return good;
    }
    
    public void inorder(TreeNode root, int largest) {
        if (root == null) return;
        else if (root.val >= largest) good++;
        inorder(root.left, Math.max(largest, root.val));
        inorder(root.right, Math.max(largest, root.val));
    }
}
