/* Noah Park

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and space is marked as 1 and 0 respectively in the grid.

*/

class Solution {
    
    // bottom up dynamic programming with memoization
    public int uniquePathsWithObstacles(int[][] mat) {
        int rows = mat.length, cols = mat[0].length; // store rows and columns from the obstacle matrix
        
        if (mat[0][0] != 0 || mat[rows - 1][cols - 1] != 0) return 0; // edge case if there are not valid start and end positions
        
        int[][] mem = new int[rows][cols]; // memoized array
        
        // iterate over the matrix to find paths
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) { mem[i][j] = 1; continue; } // initialize the start position to 1 since there is 1 path to the start from the start
                else if (mat[i][j] == 1) { mem[i][j] = 0; continue; } // if there is a 1, there are no paths to the obstacle since there is an obstacle, mark as 0
                
                // otherwise, calculate all incoming possible paths from the top and left
                int val = 0;
                
                if (i - 1 >= 0) val += mem[i - 1][j]; // top
                if (j - 1 >= 0) val += mem[i][j - 1]; // left
                
                mem[i][j] = val; // update the memoized array with the combined paths from the top and left
            }
        }
        
        return mem[rows - 1][cols - 1]; // the bottom corner is the total paths that can reach that cell from the beginning 
    }
}
