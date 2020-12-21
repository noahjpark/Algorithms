/* Noah Park

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0; // edge cases
        
        int n = nums.length;
        int[] mem = new int[n];
        mem[0] = 1;
        int max = 1;
        
        for (int i = 1; i < n; i++) { // iterate over all numbers and find the count of increasing to the current number
            int tempMax = 0;
            
            // get the largest count of increasing nums up to that point
            for (int j = 0; j < i; j++) if (nums[j] < nums[i]) tempMax = Math.max(tempMax, mem[j]);
            
            // add 1 to include the current number
            mem[i] = tempMax + 1;
            max = Math.max(max, mem[i]); // keep running max
        }
        
        return max;
    }
    
    // top down dynamic programming with memoization
    public int lengthOfLISTopDown(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return nums.length;
        Integer[][] mem = new Integer[nums.length + 1][nums.length];
        return helper(nums, 0, -1, mem);
    }
    
    public int helper(int[] nums, int i, int prev, Integer[][] mem) {
        if (i == nums.length) return 0;
        if (mem[prev + 1][i] != null) return mem[prev + 1][i];
        
        int include = (prev < 0 || nums[i] > nums[prev]) ? 1 + helper(nums, i + 1, i, mem) : 0;
        int exclude = helper(nums, i + 1, prev, mem);
        
        mem[prev + 1][i] = Math.max(include, exclude);
        return mem[prev + 1][i];
    }
}
