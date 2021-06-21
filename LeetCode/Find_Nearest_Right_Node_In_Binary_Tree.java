/* Noah Park

Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u, or return null if u is the rightmost node in its level.

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
    
    // Intuition: Maintain a global treenode and depth that represent where u will be at. We will inorder over the tree until we reach u. At that point, we update res and depth to u and its depth respectively. From here, we look to find the next node at the depth d (keeping in mind preorder looks left->cur->right) meaning that the next node at u's depth will be the one to its immediate right. When we find this node, we update res and keep a clause that stops us from continuing searching in the tree (res not null and res not u). Otherwise, we search the whole tree and res would end on u which means we return null.
    // Time: O(n) to search the tree.
    // Space: O(h) height of the tree for recursion.
    TreeNode res = null;
    int depth = -1;
    
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        dfs(root, u, 0);
        return res == u ? null : res;
    }
    
    public void dfs(TreeNode root, TreeNode u, int d) {
        if (root == null) return;
        if (res != null && res != u) return;
        
        dfs(root.left, u, d + 1);
        if (res == null && root == u) { res = u; depth = d; }
        else if (res == u && d == depth) res = root;
        dfs(root.right, u, d + 1);
    }
}
