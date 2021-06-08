/* Noah Park

Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree's nodes are unique.

Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T is the lowest node that has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that is on the path from node x to some leaf node.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    TreeNode target = null;
    Set<Integer> set = new HashSet<>();
    int n;
    
    // Intuition: Return an additional value each time we find a node in nodes. When we have a total of nodes.length, we can update our return node once and stop.
    // Time: O(n) to iterate over the tree.
    // Space: O(m) to maintain the nodes list in a set.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null || nodes == null || nodes.length == 0) return null;
        for (TreeNode node : nodes) set.add(node.val);
        n = nodes.length;
        dfs(root);
        return target;
    }
    
    public int dfs(TreeNode root) {
        if (root == null || target != null) return 0;
        
        int left = dfs(root.left), right = dfs(root.right), cur = set.contains(root.val) ? 1 : 0;
        if (left + right + cur == n && target == null) target = root;
        
        return left + right + cur;
    }
}
