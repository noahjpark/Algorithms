/* Noah Park

Given the root of a binary tree, return the number of uni-value subtrees.

A uni-value subtree means all nodes of the subtree have the same value.

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
    
    // Intuition: Maintain the total univalued subtrees since recursion will go dfs we can evaluate from the leaves upwards. All leaves increase the univals. If we have a null, we return null. Otherwise, we store the left and right children. If they are null, match them to their parent nodes. From here check that all nodes match (root, left child and right child). If everything matches we continue passing on the root's value. If we have a mismatch we can pass a bad value upwards since we no longer will be able to increase unival upwards. I chose int max value since that is out of bounds.
    // Time: O(n) to search all nodes.
    // Space: O(h) height of the tree.
    int unival = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        count(root);
        return unival;
    }
    
    public Integer count(TreeNode root) {
        if (root == null) return null;
        
        Integer left = count(root.left), right = count(root.right);

        if (left == null) left = root.val;
        if (right == null) right = root.val;

        if (root.val == left && root.val == right) unival++;
        else return Integer.MAX_VALUE;
        
        return root.val;
    }
}
