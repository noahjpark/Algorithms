/* Noah Park

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

*/

class Solution {
    
    // bottom up dynamic programming with memoization
    public int uniquePaths(int m, int n) {
        int[][] mem = new int[m][n]; // memoized array
        
        // iterate over all "spaces"
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) { mem[i][j] = 1; continue; } // initialize start to 1 since there is 1 path to the start from the start
                
                int val = 0; // accumulate total paths from adjacent cells
                
                if (i - 1 >= 0) val += mem[i - 1][j]; // check the above cell
                if (j - 1 >= 0) val += mem[i][j - 1]; // check the left cell
                
                mem[i][j] = val; // update with the combined paths from above and left
            }
        }
        
        return mem[m - 1][n - 1]; // total paths to the bottom position
    }
}
