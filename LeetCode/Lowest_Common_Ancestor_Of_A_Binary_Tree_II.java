/* Noah Park

Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.

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
    
    TreeNode target = null, p, q;
    
    // Intuition: dfs over the tree while maintaining counts. If we have a value of 2 returned and have not updated the target, we found the target and can update it. Once target is updated, we prune the rest of the searches.
    // Time: O(n) to iterate over the tree
    // Space: O(h) height of the tree for recursion.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode pp, TreeNode qq) {
        if (root == null) return null;
        p = pp;
        q = qq;
        dfs(root);
        return target;
    }
    
    public int dfs(TreeNode root) {
        if (root == null || target != null) return 0;
        
        int left = dfs(root.left), right = dfs(root.right), cur = (root.val == p.val || root.val == q.val) ? 1 : 0;
        if (left + right + cur == 2 && target == null) target = root;

        return left + right + cur;
    }
}
