/* Noah Park

Given a binary tree, return the sum of values of its deepest leaves.

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
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0; // edge case
        
        Queue<TreeNode> q = new LinkedList<>(); // do a level order bfs traversal using a queue
        q.offer(root);
        int sum = 0;
        
        while (!q.isEmpty()) { // iterate over all nodes in the tree
            sum = 0; // only count the current level sum
            int size = q.size(); 
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                
                sum += cur.val; // compute sum
                
                // offer children that are viable
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        
        return sum;
    }
}
