/* Noah Park

Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

It is guaranteed that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.

*/

class Solution {
       
    // Intuition: Maintain the maximum and minimum product thus far. 0s and negatives are what we have to worry about. At any given point, 0s will zero out our values. Negatives will minimize the maximum to a "smaller" negative value (larger overall) and can be used later if finding another minimum.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0);
        
        int max = nums[0], min = nums[0], res = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int m = max;
            max = Math.max(nums[i], Math.max(nums[i]*m, nums[i]*min));
            min = Math.min(nums[i], Math.min(nums[i]*m, nums[i]*min));
            
            res = Math.max(res, max);
        }
        
        return res;
    }

}
