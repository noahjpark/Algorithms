/* Noah Park

Given the root of a binary tree, return the leftmost value in the last row of the tree.

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
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1; // edge cases
        
        // store the left value for updating through each level pass
        int left = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        // iterate in level order
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                
                // update the first left value at each level which will eventually be the leftmost from the last level
                if (i == 0) left = cur.val;
                
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        
        return left;
    }
}
