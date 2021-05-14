/* Noah Park

You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

*/

class Solution {
    
    // Intuition: Top Down Dynamic Programming approach. Original recursion passed but had to optimize with 2D memoized array. Each index i in nums could have a repeat sum which is what we are looking for.
    // Time: O(l*n) length of nums * how large the sum can get.
    // Space: O(l*n) length of nums * how large the sum can get.
    Integer[][] mem;
    
    public int findTargetSumWays(int[] nums, int target) {
        mem = new Integer[nums.length][2001];
        return dp(nums, 0, target, 0);
    }
    
    public int dp(int[] nums, int i, int target, int sum) {
        if (i == nums.length) return sum == target ? 1 : 0;
        if (mem[i][sum + 1000] != null) return mem[i][sum + 1000];
        
        int ways = dp(nums, i + 1, target, sum + nums[i]) + dp(nums, i + 1, target, sum - nums[i]);
        mem[i][sum + 1000] = ways;
        return ways;
    }
    
}
