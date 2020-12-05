/* Noah Park

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

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
    
    // Intuition: p and q are in the tree so their LCA is either root, or some child of root.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        
        TreeNode leftTree = lowestCommonAncestor(root.left, p, q), rightTree = lowestCommonAncestor(root.right, p, q); // either both are not null or one is not null
        
        if (leftTree != null && rightTree != null) return root; // neither subtree was an LCA of both p and q
        
        // Else, one is an LCA
        return leftTree == null ? rightTree : leftTree;
    }
    
    // suboptimal tree traversal
    public TreeNode lowestCommonAncestorSuboptimal(TreeNode root, TreeNode p, TreeNode q) {
        int plevel = findLevel(root, p), qlevel = findLevel(root, q); // find the levels
        
        while (plevel < qlevel) { q = findParent(root, q); qlevel--; } // move backwards until on the same level
        while (qlevel < plevel) { p = findParent(root, p); plevel--; }
        
        // iterate until they meet going back each time
        while (plevel > 0) {
            if (p == q) break;
            q = findParent(root, q);
            p = findParent(root, p);
            plevel--;
        }
        
        return p; // return the meeting
    }
    
    // finds the level the target node is on
    public int findLevel(TreeNode root, TreeNode target) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();

                if (cur == target) return level;

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            
            level++;
        }
        
        return level;
    }
    
    // finds the parent of the target node
    public TreeNode findParent(TreeNode root, TreeNode target) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            
            if (cur.left == target || cur.right == target) return cur;
            
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
        }
        
        return null;
    }
}
