/* Noah Park

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

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
    // dfs solution with slightly better space complexity than the bfs one
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>(); // return list
        dfs(root, 0, res); // call dfs
        return res;
    }
    
    public void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if(root == null) return; // obviously stop if the node is bad
        
        if (level >= res.size()) res.add(new LinkedList<>()); // if we're on a new level, add a new list
            
        // add to the front or the end based on odd or even level
        if(level % 2 == 1) res.get(level).add(0, root.val);
        else res.get(level).add(root.val);
        
        // call dfs on left and right children
        dfs(root.left, level + 1, res); dfs(root.right, level + 1, res);
    }
    
    // optimized bfs
    public List<List<Integer>> zigzagLevelOrderbfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(root == null) return res; // edge case if root is empty
        
        Queue<TreeNode> q = new LinkedList<>(); // use queue for bfs traversal
        q.offer(root);
        q.offer(null); // use null to denote the end of a level
        int level = 0;
        LinkedList<Integer> temp = new LinkedList<>(); // use linked list for O(1) operations
        
        while (!q.isEmpty()) { // iterate over all tree nodes
            TreeNode cur = q.poll();
            if (cur != null) {
                if(level % 2 == 1) temp.addFirst(cur.val);
                else temp.addLast(cur.val);
                
                // offer the children
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            else { // end of level
                res.add(temp);
                temp = new LinkedList<>(); // update temp after adding it to the return list
                if (!q.isEmpty()) q.add(null); // add the end of the level denotation if we aren't finished
                level++; // update level pointer
            }
        }
        
        return res;
    }
    
    // bfs implementation
    public List<List<Integer>> zigzagLevelOrderSuboptimal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(root == null) return res; // edge case if root is empty
        
        Queue<TreeNode> q = new LinkedList<>(); // use queue for bfs traversal
        q.offer(root);
        int level = 0;
        
        while (!q.isEmpty()) { // iterate over all tree nodes
            LinkedList<Integer> temp = new LinkedList<>(); // use linked list for O(1) operations
            int size = q.size();
            for (int k = 0; k < size; k++) { // slightly inefficient looping over elements
                TreeNode cur = q.poll();
                
                if (level % 2 == 0) temp.addFirst(cur.val); // add to front or end based on level
                else temp.addLast(cur.val);
                
                // offer children
                if (cur.right != null) q.offer(cur.right);
                if (cur.left != null) q.offer(cur.left);
            }
            res.add(temp); // add level list
            level++;
        }
        
        return res;
    }
}
