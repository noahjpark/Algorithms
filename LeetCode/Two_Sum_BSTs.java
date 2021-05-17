/* Noah Park

Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.

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
    
    // Intuition: Iterate through the first tree and store all complements to reach target in a hash set which we will then utilize in a linear search through the second tree. If the set contains a value of the second tree, we found a match.
    // Time: O(n + m) where n is the size of tree 1 and m is the size of tree 2.
    // Space: O(n + h1 + h2) where n is the size of tree 1, h1 is the height of tree 1, and h2 is the height of tree 2.
    Set<Integer> set = new HashSet<>();
    
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        dfs(root1, target);
        return find(root2);
    }
    
    public void dfs(TreeNode root, int target) {
        if (root != null) {
            set.add(target - root.val);
            dfs(root.left, target);
            dfs(root.right, target);
        }
    }
    
    public boolean find(TreeNode root) {
        if (root == null) return false;
        if (set.contains(root.val)) return true;
        return find(root.left) || find(root.right);
    }
}
