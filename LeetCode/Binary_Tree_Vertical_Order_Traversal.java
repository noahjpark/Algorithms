/* Noah Park

Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

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
    
    int ld = 0, rd = 0;
    Map<TreeNode, Integer> nodes = new HashMap<>();
    
    // Intuition: Maintain the leftmost and rightmost depths so we can set up the return list with all of the buckets. At the same time, we can map each node to its associated depth with respect to the root (0). Because we have the outer depths, we know how much to adjust the all depths. Then it is a simple level order traversal to ensure the ordering is left to right for similar row valued nodes.
    // Time: O(n) two passes over the tree.
    // Space: O(n) for the recursion, map, queue, and return list.
    public List<List<Integer>> verticalOrder(TreeNode t) {
        if (t == null) return new ArrayList<>();
        
		findDepth(t, 0);
        int diff = rd - ld + 1, adjust = 0 - ld;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < diff; i++)
            res.add(new ArrayList<>());
        
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(t);
        
        while (!q.isEmpty()) {
            int lev = q.size();
            
            for (int i = 0; i < lev; i++) {
                TreeNode cur = q.removeFirst();
                
                res.get(nodes.get(cur) + adjust).add(cur.val);
                
                if (cur.left != null) q.addLast(cur.left);
                if (cur.right != null) q.addLast(cur.right);
            }
        }
        
		return res;
    }
    
    public void findDepth(TreeNode t, int d) {
        if (t == null) return;
        
        ld = Math.min(ld, d);
        rd = Math.max(rd, d);
        nodes.put(t, d);
        
        findDepth(t.left, d - 1);
        findDepth(t.right, d + 1);
    }

}
