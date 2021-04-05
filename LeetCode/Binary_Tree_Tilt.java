/* Noah Park

Given the root of a binary tree, return the sum of every tree node's tilt.

The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right subtree node values. If a node does not have a left child, then the sum of the left subtree node values is treated as 0. The rule is similar if there the node does not have a right child.

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
    // Intuition: Instead of mapping, we can return the sum at each point but calculate the tilt in the pass itself.
    // Time: O(n) to pass through all the nodes in the tree.
    // Space: O(h) the height of the tree.
    int tilt = 0;
    
    public int findTilt(TreeNode root) {
        calculate(root);
        return tilt;
    }
    
    public int calculate(TreeNode root) {
        if (root == null) return 0;
        
        int left = calculate(root.left), right = calculate(root.right), t = Math.abs(left - right);
        tilt += t;
        
        return left + right + root.val;
    }
    
    // Intuition: map all sums of subtrees to each node. Then a second pass can create the tilt.
    // Time: O(n) 2 pass solution.
    // Space: O(n) to store all nodes in the map.
    Map<TreeNode, Integer> map = new HashMap<>();
    
    public int findTiltInitial(TreeNode root) {
        tilt(root);
        return addTilt(root);
    }
    
    public int addTilt(TreeNode root) {
        if (root == null) return 0;
        int left = map.getOrDefault(root.left, 0), right = map.getOrDefault(root.right, 0);
        return Math.abs(left - right) + addTilt(root.left) + addTilt(root.right);
    }
    
    public int tilt(TreeNode root) {
        if (root == null) return 0;
        int left = tilt(root.left), right = tilt(root.right);
        map.put(root, left + right + root.val);
        return left + right + root.val;
    }
}
