/* Noah Park

Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.

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
    
    // Intuition: Map all node values to their frequencies. Do a second pass to find the number of them we need. Do a third pass to populate the final array.
    // Time: O(n) three pass solution
    // Space: O(n) to map each node if they are all unique
    Map<Integer, Integer> map = new HashMap<>();
    Integer mode = 0;
    
    public int[] findMode(TreeNode root) {
        inorder(root);
        
        int count = 0, i = 0;
        for (Integer key : map.keySet())
            if (map.get(key) == mode) count++;
        
        int[] res = new int[count];
        for (Integer key : map.keySet())
            if (map.get(key) == mode) res[i++] = key;
        
        return res;        
    }
    
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            map.put(root.val, map.getOrDefault(root.val, 0) + 1);
            mode = Math.max(map.get(root.val), mode);
            inorder(root.right);
        }
    }
}
