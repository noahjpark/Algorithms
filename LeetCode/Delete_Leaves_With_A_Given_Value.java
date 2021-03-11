/* Noah Park

Given a binary tree root and an integer target, delete all the leaf nodes with value target.

Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you can't).

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
    Map<TreeNode, Boolean> map = new HashMap<>();
    
    // Time: O(n) | Space: O(h) height of the tree
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return root;
        if (root.left != null) root.left = removeLeafNodes(root.left, target);
        if (root.right != null) root.right = removeLeafNodes(root.right, target);
        return root.left == root.right && root.val == target ? null : root;
    }
    
    // Time: O(n) | Space: O(n)
    public TreeNode removeLeafNodesTrivial(TreeNode root, int target) {
        if (allMatch(root, target)) return null;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            
            if (cur.left != null) {
                if (map.get(cur.left)) cur.left = null;
                else q.offer(cur.left);
            }
            
            if (cur.right != null) {
                if (map.get(cur.right)) cur.right = null;
                else q.offer(cur.right);
            }
        }
        
        return root;
    }
    
    // Time: O(n) | Space: O(h) height of the tree
    public boolean allMatch(TreeNode root, int target) {
        if (root == null) return true;
        if (map.containsKey(root)) return map.get(root);
        
        boolean left = allMatch(root.left, target), right = allMatch(root.right, target);
        
        map.put(root, left && right && root.val == target);
        return map.get(root);
    }
}
