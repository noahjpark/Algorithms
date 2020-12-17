/* Noah Park

There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

*/

class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0; // edge cases
        
        int n = costs.length;
        int[][] dp = new int[n][3]; // similar to min falling path sum, store in dp array
        dp[0] = costs[0]; // initial cost is always the same as the starting cost
        
        // iterate through the rest of the costs computing the best cost for each house so far
        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        
        // the last row will contain the best costs for each house at that point, we want the minimum of that.
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) min = Math.min(dp[n - 1][i], min);
        return min;
    }
}
