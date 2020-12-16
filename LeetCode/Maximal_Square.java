/* Noah Park

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0; // edge cases
        
        // cached values and dp matrix
        int rows = matrix.length, cols = matrix[0].length, size = 0;
        int[][] dp = new int[rows][cols];
        
        // set the top row - can only be 0 or 1
        for (int i = 0; i < cols; i++) {
            dp[0][i] = (matrix[0][i] == '1') ? 1 : 0;
            size = Math.max(size, dp[0][i]);
        }
        
        // set the left column - can only be 0 or 1
        for (int i = 0; i < rows; i++) {
            dp[i][0] = (matrix[i][0] == '1') ? 1 : 0;
            size = Math.max(size, dp[i][0]);
        }
        
        // update the rest of the matrix based on squared values
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    size = Math.max(size, dp[i][j]);
                } 
            }
        }
        
        return size*size; // this is the actual square size
    }
}
