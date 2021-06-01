/* Noah Park

We are given a binary tree (with root node root), a target node, and an integer value k.

Return a list of the values of all nodes that have a distance k from the target node.  The answer can be returned in any order.

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
    
    // Intuition: Maintain an array of parents so we can move up in the tree. Also, we maintain a visited array to help with the algorithm: Start by populating the parents array. Once we have this, we attempt to dfs down to our target level from our target node. To start with we want all nodes at level k from target as a "root" node. Then we move up to its parent and get all nodes at level k - 1 from that node and so on until k becomes 0 and we would simply add just that node we are on. This results in all reachable nodes of distance k from the original target node.
    // Time: O(n) to iterate once and find the parents. Then iterate again to find all resulting nodes. We only ever visit each node once.
    // Space: O(n) for the resulting list / O(1) otherwise since parents and visited are constant. For a larger scenario, we would use a map and set resulting in O(n) space complexity.
    List<Integer> res = new ArrayList<>();
    TreeNode[] parents = new TreeNode[501];
    boolean[] visited = new boolean[501];    
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root, null);
        
        for (; k >= 0 && target != null; k--) {
            dfs(target, k, 0);
            visited[target.val] = true;
            target = parents[target.val];
        }
        
        return res;
    }
    
    public void dfs(TreeNode target, int k, int i) {
        if (target == null || visited[target.val]) return;
        
        visited[target.val] = true;
        if (i == k) res.add(target.val);
        dfs(target.left, k, i + 1);
        dfs(target.right, k, i + 1);
    }
    
    public void dfs(TreeNode root, TreeNode prev) {
        if (root != null) {
            parents[root.val] = prev;
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }
}
