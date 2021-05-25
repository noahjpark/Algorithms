/* Noah Park

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

*/

class Solution {
    
    // Intuition: This is exactly the same as House Robber where we consider the largest value we can have stolen up to a given point. The only caviat is that we have to keep in mind that the first and last house are now neighbors. The simplest way to consider the solution is to find the maximum when stealing from the first house and also from the last. In this way, we can traverse from left to right then from right to left to maintain the largest sum we are able to steal from either direction. This avoids the first and last house problem.
    // Time: O(n) condensed into a single pass.
    // Space: O(1) constant.
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int old = nums[0], prev = Math.max(old, nums[1]), n = nums.length, rold = nums[n - 1], rprev = Math.max(rold, nums[n - 2]), j = n - 3, max = Math.max(old, Math.max(prev, Math.max(rold, rprev)));
        
        for (int i = 2; i < n - 1; i++) {
            int cur = Math.max(prev, old + nums[i]), cur2 = Math.max(rprev, rold + nums[j--]);
            max = Math.max(max, Math.max(cur, cur2));
            
            old = prev;
            prev = cur;
            rold = rprev;
            rprev = cur2;
        }
        
        return max;
    }
}
