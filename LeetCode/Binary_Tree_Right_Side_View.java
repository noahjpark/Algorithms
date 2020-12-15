/* Noah Park

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();   // return list     
        Queue<TreeNode> q = new LinkedList<>();  // queue for bfs
        if (root != null) q.offer(root); // offer if root not null
        
        // iterate over all levels
        while (!q.isEmpty()) {
            int size = q.size(); // cache level for looping usage
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll(); // visit current node
                if (i == size - 1) res.add(cur.val); // add to the new arraylist at the end of each level
                
                // add children
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        
        return res;
    }
}
