/* Noah Park

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

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
    // Time and Space: O(n)
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return new ArrayList<>(); // edge cases
        
        // result list and queue for bfs
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        // modified bfs to calculate the average at each level
        while (!q.isEmpty()) {
            int size = q.size();
            double avg = 0;
            
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                
                avg += cur.val;
                
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            
            res.add(avg / size);
        }
        
        return res;
    }
}
