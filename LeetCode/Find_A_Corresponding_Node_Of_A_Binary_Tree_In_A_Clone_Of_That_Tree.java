/* Noah Park

Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a copy of the original tree.

Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.

Follow up: Solve the problem if repeated values on the tree are allowed.

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
    TreeNode res = null; // resulting node
    
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        inorder(original, cloned, target); // call inorder dfs to find the target correspondence in cloned
        return res;
    }
    
    public void inorder(TreeNode t, TreeNode c, TreeNode target) {
        if (t != null) {
            inorder(t.left, c.left, target);
            if (t == target) res = c; // if the target matches, update res
            inorder(t.right, c.right, target);
        }
    }
}
