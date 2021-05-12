/* Noah Park

Given a binary search tree, return a balanced binary search tree with the same node values.

A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.

If there is more than one answer, return any of them.

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
    
    // Intuition: Populate the a sorted list of the tree's values (dfs inorder algorithm). Then build using a binary search the entire tree starting from the center.
    // Time: O(n) one pass for the inorder algorithm, one pass to build the tree clone.
    // Space: O(h) where h is the depth of the tree.
    List<Integer> l = new ArrayList<>();
    
    public TreeNode balanceBST(TreeNode root) {
        inorder(root);    
        return balance(0, l.size() - 1);
    }
    
    public TreeNode balance(int i, int j) {
        if (i > j) return null;
        
        int mid = i + (j - i) / 2;
        TreeNode root = new TreeNode(l.get(mid));
        
        root.left = balance(i, mid - 1);
        root.right = balance(mid + 1, j);
        
        return root;
    }
    
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            l.add(root.val);
            inorder(root.right);
        }
    }
    
}
