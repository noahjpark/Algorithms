/* Noah Park

Given an array nums of integers, for each integer nums[i] we may choose any x with -k <= x <= k, and add x to nums[i].

After this process, we have some array result.

Return the smallest possible difference between the maximum value of result and the minimum value of result.

*/

class Solution {
    
    // Intuition: To get the minimum possible difference, we need to minimize the max and maximize the min in the array. We go through the array and find the min and max. We can take the difference between these two and minimize it by a factor of 2k as we can subtract k from the max and add k to the min. If this value is negative, we can return 0 as we don't quite need up to 2k. Otherwise, return this difference.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0], max = nums[0];
        
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        
        return Math.max(max - min - (k*2), 0);
    }
}
