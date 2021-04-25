/* Noah Park

Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.

Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.

A full binary tree is a binary tree where each node has exactly 0 or 2 children.

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
    
    // Intuition: Compute all possible tree values and utilize memoization to cache the values and avoid recomputation of subtrees we already made.
    // Time: O(2^n)
    // Space: O(2^n)
    Map<Integer, List<TreeNode>> mem = new HashMap<>();
    
    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) return new ArrayList<>();
        if (mem.containsKey(n)) return mem.get(n);
        
        List<TreeNode> res = new ArrayList<>();
        
        if (n == 1) res.add(new TreeNode(0));
        
        for (int i = 1; i < n; i += 2) {
            // Store all left and right subtree possibilities
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - i - 1);
            
            // Set all tree possibilites
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(0);
                    root.left = l; root.right = r;
                    res.add(root);
                }
            }
        }
        
        mem.put(n, res);
        return res;
    }
}
