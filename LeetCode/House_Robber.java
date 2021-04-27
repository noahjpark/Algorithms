/* Noah Park

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

*/

class Solution {
    
    // Intuition: Improve on the lower solution by dropping the need for memoization since we are only looking at the previous two values at any given point. Approach like fibonacci.
    // Time: O(n) to check all houses in nums.
    // Space: O(1) constant.
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        int prev = 0, cur = nums[0], n = nums.length;
        
        for (int i = 1; i < n; i++) {
            int next = Math.max(cur, prev + nums[i]);
            prev = cur;
            cur = next;
        }
        
        return cur;
    }
    
    // Intuition: Simple bottom up dynamic programming with memoization to store the best amount we can rob at each subarray end. 
    // Time: O(n) to check all houses in nums.
    // Space: O(n) to memoize best values in nums.
    public int robMemoized(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        int n = nums.length;
        int[] mem = new int[n + 1];
        mem[0] = 0; mem[1] = nums[0];
        
        for (int i = 1; i < n; i++) {
            mem[i + 1] = Math.max(mem[i], mem[i - 1] + nums[i]);
        }
        
        return mem[n];
    }
}
