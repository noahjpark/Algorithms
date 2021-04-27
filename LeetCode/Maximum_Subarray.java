/* Noah Park

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

*/

class Solution {
    
    // Intuition: Store the best subarray sum as we are moving. We either choose to start a new subarray sum if it is larger than the largest so far or add it to the current to make a larger sum. Kind of greedily trying to maximize the sum.
    // Time: O(n) to loop over nums.
    // Space: O(1) constant. Improved over using a memoized array since we are only using the previous subarray sum at any given point.
    public int maxSubArray(int[] nums) {
        int max = nums[0], prev = max;
        
        for (int i = 1; i < nums.length; i++) {
            prev = Math.max(nums[i], prev + nums[i]);
            max = Math.max(prev, max);
        }
        
        return max;
    }
}
