/* Noah Park

Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.

*/

class Solution {
    
    // Intuition: Utilize a bfs style traversal when trying to see how far we can go from each jump.
    // Time: O(n) for looping over nums length.
    // Space: O(1)
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        
        int jumps = 0, currentReach = 0, nextReach = 0, n = nums.length;
        
        for (int i = 0; i < n; i++) {
            nextReach = Math.max(nextReach, i + nums[i]); // find end of our current level
            if (nextReach >= n - 1) break;
            if (currentReach == i) {
                currentReach = nextReach;
                jumps++;
            }
        }
        
        return jumps + 1; // count final jump after breaking
    }
    
    // Intuition: Utilize bottom up dp with memoization to cache the values of the best number of jumps at each index starting from the end with no jumps.
    // Time: O(m*n) where n is the length of nums and m is the largest number in nums.
    // Space: O(n) for the memoized array.
    public int jumpBottomUpDP(int[] nums) {
        if (nums.length == 1) return 0;
        
        int n = nums.length;
        int[] memo = new int[n];
        memo[n - 1] = 0;
        
        for (int i = n - 2; i >= 0; i--) {
            int minJumps = Integer.MAX_VALUE;
            
            for (int j = 1; j <= nums[i]; j++) {
                if (j + i < n) {
                    int next = memo[i + j] + 1;
                    if (next == Integer.MIN_VALUE) next = Integer.MAX_VALUE;
                    minJumps = Math.min(minJumps, next);
                }
            }
            
            memo[i] = minJumps;
        }
        
        return memo[0];
    }
    
    // Intuition: Utilize top down dp with memoization to cache the values of the best number of jumps at each index.
    // Time: O(m*n) where n is the length of nums and m is the largest number in nums.
    // Space: O(n) for the recursion call stack and memoized array.
    Integer[] mem;
    
    public int jumpTopDownDP(int[] nums) {
        mem = new Integer[nums.length];
        return dp(nums, 0);
    }
    
    public int dp(int[] nums, int idx) {
        if (idx == nums.length - 1) return 0;
        else if (mem[idx] != null) return mem[idx];
        
        int jumps = Integer.MAX_VALUE;
        
        for (int i = 1; i <= nums[idx]; i++) {
            if (idx + i < nums.length) {
                int next = dp(nums, idx + i) + 1;
                if (next == Integer.MIN_VALUE) next = Integer.MAX_VALUE;
                jumps = Math.min(jumps, next);
            }
        }
        
        mem[idx] = jumps;
        return mem[idx];
    }
}
