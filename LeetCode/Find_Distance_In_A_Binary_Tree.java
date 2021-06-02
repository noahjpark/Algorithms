/* Noah Park

Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.

The distance between two nodes is the number of edges on the path from one to the other.

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
    
    // Intuition: Be considerate of what we are returning at each point in the tree. If p or q are in the subtree, the depths of these will be returned. The following cases are considered: If we are on p or q and the other is in the subtree of this node, we take the depth of the lower node minus the current depth d. Otherwise, if we are on p or q and there is no target in their subtree, we just return the depth. If we are on a normal node (not p or q) we return the values from their left and right subtrees. Either p/q is in the subtree of the other, or the lca of the two returns the combination all the way through to the root. This optimizes the bottom solution by doing everything in the initial pass instead of dealing with a map and doing the logn search afterwards.
    // Time: O(n) to iterate over the tree in a single pass.
    // Space: O(h) height of the tree.
    public int findDistance(TreeNode root, int p, int q) {
        if (p == q) return 0;
        return dfs(root, p, q, 0);
    }
    
    public int dfs(TreeNode root, int p, int q, int d) {
        if (root == null) return 0;
        
        int left = dfs(root.left, p, q, d + 1), right = dfs(root.right, p, q, d + 1);
        if (root.val == p || root.val == q) return (left == 0 && right == 0) ? d : left != 0 ? left - d : right - d;
        return left + right;
    }
    
    // Intuition: Create parent pointers while maintaining the depth of each node. Get each node to their same level and move up until finding the corresponding parent.
    // Time: O(n + logn) to iterate over the tree then move up the pointers until they meet.
    // Space: O(n) to maintain the map.
    Map<TreeNode, Pair<TreeNode, Integer>> map = new HashMap<>();
    TreeNode p, q;
    int pval, qval;
    
    public int findDistance2(TreeNode root, int pp, int qq) {
        pval = pp;
        qval = qq;
        dfs(root, null, 0);
        
        int pdepth = map.get(p).getValue(), qdepth = map.get(q).getValue(), res = 0;
        
        while (pdepth > qdepth) { p = map.get(p).getKey(); res++; pdepth--; }
        while (qdepth > pdepth) { q = map.get(q).getKey(); res++; qdepth--; }
        
        while (p != q) {
            p = map.get(p).getKey();
            q = map.get(q).getKey();
            res += 2;
        }
        
        return res;
    }
    
    public void dfs(TreeNode root, TreeNode prev, int d) {
        if (p != null && q != null) return;
        
        if (root != null) {
            if (root.val == pval) p = root;
            if (root.val == qval) q = root;
            map.put(root, new Pair(prev, d));
            dfs(root.left, root, d + 1);
            dfs(root.right, root, d + 1);
        }
    }
}
