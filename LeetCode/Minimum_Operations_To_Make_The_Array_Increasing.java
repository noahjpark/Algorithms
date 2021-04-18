/* Noah Park

You are given an integer array nums (0-indexed). In one operation, you can choose an element of the array and increment it by 1.

For example, if nums = [1,2,3], you can choose to increment nums[1] to make nums = [1,3,3].
Return the minimum number of operations needed to make nums strictly increasing.

An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1. An array of length 1 is trivially strictly increasing.

*/

class Solution {
    // Intuition: Maintain the previous number in the sequence and the total number of operations. If the previous number is not smaller than the current one, apply the number of operations it would take to make the current larger and update prev after according to what we would have updated the current value to without modifying the input array. Otherwise, simply move previous forward.
    // Time: O(n)
    // Space: O(1)
    public int minOperations(int[] nums) {
        int ops = 0, prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prev >= nums[i]) { ops += (prev - nums[i] + 1); prev++; }
            else prev = nums[i];
        }
        return ops;
    }
}
