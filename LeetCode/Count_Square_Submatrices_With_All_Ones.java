/* Noah Park

Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

*/

class Solution {
    public int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int sum = 0;
        
        for(int col = 0; col < matrix[0].length; col++) {
            dp[0][col] = matrix[0][col];
            sum += dp[0][col];
        }
        
        for(int row = 1; row < matrix.length; row++) {
            dp[row][0] = matrix[row][0];
            sum += dp[row][0];
        }
        
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) dp[i][j] = 0;
                else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                sum += dp[i][j];
            }
        }
        
        return sum;
    }
}
