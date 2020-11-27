/* Noah Park

Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of all the numbers to the left of the index is equal to the sum of all the numbers to the right of the index.

If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

*/

class Solution {
    public int pivotIndex(int[] nums) {
        // Sliding window sort of problem
        int sum = 0, n = nums.length, cur = 0, i = 0;
        if(nums == null || n == 0) return -1; // edge case for empty array
        
        // sum up the elements in nums
        for(int num : nums)
            sum += num;
        
        // do a second pass to sum at each pivot point and compare to the sum of the right elements by subtracting the current pivot
        for(; i < n; i++) {
            if(i > 0) cur += nums[i - 1]; // we don't include pivot in cur sum so start adding past 0
            sum -= nums[i]; // subtract from original
            if(cur == sum) break; // We found the pivot
        }
        
        return i == n ? -1 : i; // if i == n, there was no pivot
    }
}
