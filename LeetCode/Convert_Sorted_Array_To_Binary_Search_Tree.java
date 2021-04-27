/* Noah Park

Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

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
    
    // Intuition: Since we have a sorted array, we can constantly break the array in half to minimize the height difference on each side.
    // Time: O(n) to go through nums.
    // Space: O(log n) for the height of the tree.
    public TreeNode sortedArrayToBST(int[] nums) {
        return createTree(nums, 0, nums.length - 1);
    }
    
    public TreeNode createTree(int[] nums, int i, int j) {
        if (i > j) return null;
        
        int mid = i + (j - i) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = createTree(nums, i, mid - 1);
        root.right = createTree(nums, mid + 1, j);
        
        return root;
    }
}
