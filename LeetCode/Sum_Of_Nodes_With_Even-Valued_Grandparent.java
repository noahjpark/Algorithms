/* Noah Park

Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.

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
    int res = 0;
    
    // use helper methods to determine the sum
    public int sumEvenGrandparent(TreeNode root) {
        inorder(root);
        return res;
    }
    
    // inorder traverse and use sum grandchildren helper every time we find an even node
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            if (root.val % 2 == 0) res += sum(root);
            inorder(root.right);
        }
    }
    
    // sums values of the grandchildren of a given tree node
    public int sum(TreeNode g) {
        int sum = 0;
        
        // check left subtree child's children
        if (g.left != null) {
            if (g.left.left != null) sum += g.left.left.val;
            if (g.left.right != null) sum += g.left.right.val;
        }
        
        // check right subtree child's children
        if (g.right != null) {
            if (g.right.left != null) sum += g.right.left.val;
            if (g.right.right != null) sum += g.right.right.val;
        }
        
        return sum;
    }
}
