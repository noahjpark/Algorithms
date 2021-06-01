/* Noah Park

Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.

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
 
    // Intuition: Start by dfsing over the tree to find the range of values to add to each index in the resulting list; In addition to this we update a map of each node to its correspnding position with respect to the root (0). From here we bfs since earlier rows in the same index in the resulting list should come first. Following this, each value in the same row comes in sorted order, thus an ordered queue to maintain values proves useful. I maintain a list of priority queues at the start of the problem to assist with this. Then it just turns into a typical level order traversal to add each value to its corresponding ordered list then transfer the ordered list to the resulting list. Another way to go about this would be to maintain a tree map of tree maps where the inner tree map is sorted by row value in correspondance to numerous priority queues. The outer tree map is sorted by columns.
    // Time: O(nlogn) for the priority queue.
    // Space: O(n) to maintain the resulting list, map and priorityqueue.
    int lr = 0, rr = 0;
    List<List<Integer>> res = new ArrayList<>();
    Map<TreeNode, Integer> map = new HashMap<>();
    List<PriorityQueue<Integer>> temp = new ArrayList<>();
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0);
        int len = (rr - lr) + 1;
        
        for (int i = 0; i < len; i++) {
            res.add(new ArrayList<>());
            temp.add(new PriorityQueue<>());   
        }
        
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.removeFirst();
                int idx = map.get(cur) - lr;
                
                temp.get(idx).offer(cur.val);
                
                if (cur.left != null) q.addLast(cur.left);
                if (cur.right != null) q.addLast(cur.right);
            }
            
            for (int i = 0; i < len; i++) {
                PriorityQueue<Integer> pq = temp.get(i);
                while (!pq.isEmpty()) 
                    res.get(i).add(pq.poll());
            }
        }
            
        return res;
    }
    
    public void dfs(TreeNode root, int range) {
        if (root != null) {
            lr = Math.min(lr, range);
            rr = Math.max(rr, range);
            map.put(root, range);
            dfs(root.left, range - 1);
            dfs(root.right, range + 1);
        }
    }
    
}
