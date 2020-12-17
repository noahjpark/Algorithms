/* Noah Park

Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.

Return the minimum sum of a falling path with non-zero shifts.

*/

class Solution {
    public int minFallingPathSum(int[][] arr) {
        if (arr == null || arr.length == 0) return 0; // edge cases
        
        // similar to falling path sum 1, store best path so far in dp array
        int n = arr.length, m = arr[0].length;
        int[][] dp = new int[n][m];
        dp[0] = arr[0];
        
        // iterate over the rest of the array computing the best sum seen so far
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) { // for each value in each row we need to find the min in the previous row that is not in the same column
                int min = Integer.MAX_VALUE;
                
                for (int k = 0; k < m; k++) if (j != k) min = Math.min(min, dp[i - 1][k]); // find min
                
                dp[i][j] = arr[i][j] + min; // store the best path so far
            }
        }
        
        // last column min will be the best falling path sum we could have 
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) min = Math.min(min, dp[n - 1][i]);
        return min;
    }
}
