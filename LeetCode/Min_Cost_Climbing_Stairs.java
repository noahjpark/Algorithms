/* Noah Park

You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

*/

class Solution {
    
    // Intuition: bottom up dp. Maintain the two best costs to this point. At the end, there are two positions that are valid to reach the top floor. We want the minimum of them.
    // Time: O(n) to iterate over costs.
    // Space: O(1) constant.
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        
        int old = cost[0], prev = cost[1], n = cost.length;
        for (int i = 2; i < n; i++) {
            int cur = cost[i] + Math.min(old, prev);
            old = prev;
            prev = cur;
        }
        
        return Math.min(old, prev);
    }
}
