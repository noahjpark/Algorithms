/* Noah Park

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

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
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) return new ArrayList<>(); // edge cases
        
        // populate the list
        List<List<Integer>> res = new ArrayList<>();
        find(root, res);
        return res;
    }
    
    // Time: O(n)
    // Space: O(n)
    public int find(TreeNode root, List<List<Integer>> res) {
        if (root == null) return 0; // if we reach the bottom, return 0
        
        // get the current max depth, subtract one when indexing into the list
        int cur = 1 + Math.max(find(root.left, res), find(root.right, res));
        if (cur - 1 >= res.size()) res.add(new ArrayList<>());
        res.get(cur - 1).add(root.val);
        
        return cur;
    }
    
    // first solution, didn't like that I used the findDepth method within the populate method
    // Time: O(n^2)
    // Space: O(n)
    public List<List<Integer>> findLeavesFirst(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        int size = findDepth(root);
        
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < size; i++)
            res.add(new ArrayList<>());
        
        populate(root, res);
        
        return res;
    }
    
    public void populate(TreeNode root, List<List<Integer>> res) {
        if (root == null) return;
        
        res.get(findDepth(root) - 1).add(root.val);
        
        populate(root.left, res);
        populate(root.right, res);
    }
    
    public int findDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = findDepth(root.left) + 1;
        int right = findDepth(root.right) + 1;
        
        return Math.max(left, right);
    }
}
