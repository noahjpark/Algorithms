/* Noah Park

Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.

A subarray is defined as a contiguous sequence of numbers in an array.

A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi < numsi+1. Note that a subarray of size 1 is ascending.

*/

class Solution {
    
    // Intuition: Start with the first number in the sequence. At each new number, if we are ascending accumulate the sum, otherwise reset the sum to the new number. Always maximize the result at each iteration.
    // Time: O(n) to iterate through nums.
    // Space: O(1) constant.
    public int maxAscendingSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int res = nums[0], curMax = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) curMax += nums[i];
            else curMax = nums[i];
            
            res = Math.max(res, curMax);
        }
        
        return res;
    }
}
