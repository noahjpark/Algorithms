/* Noah Park

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.

A leaf is a node with no children.

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
    // Time: O(n) to look through all nodes in the tree
    // Space: O(h) the height of the tree
    // Intuition, use a backtracking algorithm to build the paths and remove the most recent node once that path has been satisfied.
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        pathSum(root, targetSum, res, new ArrayList<>(), 0);
        return res;
    }
    
    public void pathSum(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path, int curPathSum) {
        if (root.left == null && root.right == null && curPathSum + root.val == targetSum) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(root.val);
            res.add(newPath);
        } else {
            path.add(root.val);
            if (root.left != null) pathSum(root.left, targetSum, res, path, curPathSum + root.val);
            if (root.right != null) pathSum(root.right, targetSum, res, path, curPathSum + root.val);
            path.remove(path.size() - 1);
        }
    }
}
