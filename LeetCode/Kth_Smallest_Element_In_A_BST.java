/* Noah Park

Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.

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
  
    // Intuition: Use a stack (iterative) algorithm to terminate the traversal sooner instead of going through the full traversal since we only need the kth smallest.
    // Time: O(h + k) where h is the height of the tree on the left side and k is the kth element we are looking for.
    // Space: O(h) where h is the height of the tree.
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        
        while (!s.isEmpty()) {
            while (root != null) { s.push(root); root = root.left; }
            root = s.pop();
            if (k-- == 1) return root.val;
            root = root.right;
        }
        
        return -1;
    }
    
    // Intuition: Populate a list with the inorder traversal of the tree.
    // Time and Space: O(n) for the tree nodes.
    public int kthSmallestRec(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res.get(k - 1);
    }
    
    public void inorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }
    }
}
