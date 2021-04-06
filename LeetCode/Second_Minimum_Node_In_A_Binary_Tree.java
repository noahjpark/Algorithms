/* Noah Park

Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

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
    
    // Intuition: Store the current minimum and the original minimum. If we haven't updated the current minimum past the original minimum, do so as soon as we find a large enough value. Then continue iterating over the tree constantly taking the smallest node that is still larger than the original minimum.
    // Time: O(n) to look through all of the tree nodes if they are the same
    // Space: O(h) where h is the height of the tree.
    int min, original;
    public int findSecondMinimumValue(TreeNode root) {
        min = original = root.val;
        path(root);
        return min > root.val ? min : -1;
    }
    
    public void path(TreeNode root) {
        if (root == null || (min > original && root.val > min)) return;
        if (min == original && root.val > original) min = root.val;
        else if (root.val > original) min = Math.min(root.val, min);

        path(root.left);
        path(root.right);
    }
}
