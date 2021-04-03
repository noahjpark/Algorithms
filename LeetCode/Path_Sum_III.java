/* Noah Park

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

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
    
    // Intuition: Use a prefix sum strategy to check over all nodes in the tree against the ideal path sum in a single pass through the tree.
    // Time: O(n) to iterate over all nodes in the tree
    // Space: O(n) to store the prefix sums in the map
    
    Map<Integer, Integer> map = new HashMap<>();
    
    public int pathSum(TreeNode root, int sum) {
        idealPathSum(root, sum, 0);
        return count;
    }
    
    public void idealPathSum(TreeNode root, int sum, int cur) {
        if (root == null) return;
        
        cur += root.val
        
        if (cur == sum) count++;
        
        count += map.getOrDefault(cur - sum, 0);
        map.put(cur, map.getOrDefault(cur, 0) + 1);
        
        idealPathSum(root.left, sum, cur);
        idealPathSum(root.right, sum, cur);
        
        map.put(cur, map.get(cur) - 1); // as you finish a deep recursive call, you need to remove it as it is no longer a part of the current subproblem
    }
    
    // Intuition: Iterate over all nodes in the tree and check the path sum of each of them.
    // Time: O(n^2) iterating over the tree for each node in the tree
    // Space: O(n) for the largest level of the tree
    
    int count = 0;
    
    public int pathSumNaive(TreeNode root, int sum) {
        if (root == null) return 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            
            path(cur, sum, 0);
            
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
        }
        
        return count;
    }
    
    public void path(TreeNode root, int sum, int curSum) {
        if (root == null) return;
        if (curSum + root.val == sum) count++;
        
        path(root.left, sum, curSum + root.val);
        path(root.right, sum, curSum + root.val);
    }
}
