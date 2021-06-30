/* Noah Park

Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.

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
    
    List<TreeNode> res = new ArrayList<>();
    Pair<TreeNode, TreeNode>[] map = new Pair[1001];
    boolean[] set = new boolean[1001];
    
    // Intuition: Three pass solution. First pass populates the set to easily access whether a node is to be deleted. Then we can go through and delete the nodes while populating a map with the parents of each node. Finally, we have all the information we need to do one more pass through the deleting values and add all children from the map that are not also contained in the deleted set.
    // Time: O(n) three passes through the tree values.
    // Space: O(h + n) height of the recursion stack plus n assuming we don't constrain to 1000.
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) return res; 
        
        boolean deleteRoot = false;
        for (int val : to_delete) {
            if (val == root.val) deleteRoot = true;
            set[val] = true;
        }
        
        dfs(root);
        
        if (!deleteRoot) res.add(root);
        for (int val : to_delete) {
            Pair<TreeNode, TreeNode> pair = map[val];
            if (pair == null) continue;
            if (pair.getKey() != null) res.add(pair.getKey());
            if (pair.getValue() != null) res.add(pair.getValue());
        }
        
        return res;
    }
    
    public void dfs(TreeNode root) {
        if (root == null) return;
        
        dfs(root.left);
        dfs(root.right);
        if (root.left != null && set[root.left.val]) root.left = null;
        if (root.right != null && set[root.right.val]) root.right = null;
        map[root.val] = new Pair(root.left, root.right);
    }
}
