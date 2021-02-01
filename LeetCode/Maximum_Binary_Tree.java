/* Noah Park

You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.

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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1); // call recursive helper
    }
    
    // Time: O(n^2)
    // Space: O(n)
    public TreeNode construct(int[] nums, int start, int end) {
        if (start > end) return null; // edge cases
        
        int idx = start;

        // find max node
        for (int i = start + 1; i <= end; i++)
            if (nums[i] > nums[idx]) idx = i;

        TreeNode node = new TreeNode(nums[idx]);

        // apply algorithm
        node.left = construct(nums, start, idx - 1);
        node.right = construct(nums, idx + 1, end);

        return node;
    }
}
