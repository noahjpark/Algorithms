/* Noah Park

Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.

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
    
    Set<Integer> set = new HashSet<>(); // set contains complement values
    
    // Time and Space: O(n)
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        
        return preorder(root, k);
    }
    
    // modified preorder algorithm utilizes the set to find complements for two sum algorithm
    public boolean preorder(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(root.val)) return true;
        
        set.add(k - root.val);
        
        return preorder(root.left, k) || preorder(root.right, k);
    }
}
