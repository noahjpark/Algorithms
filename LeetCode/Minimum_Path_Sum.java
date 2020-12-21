/* Noah Park

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

*/

class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length;
        int[][] mem = new int[rows][cols]; // memoized array to not overwrite the grid
        mem[0][0] = grid[0][0];
        
        // store best path on the top row and left column
        for (int i = 1; i < cols; i++) mem[0][i] = mem[0][i - 1] + grid[0][i];
        for (int i = 1; i < rows; i++) mem[i][0] = mem[i - 1][0] + grid[i][0];
        
        // take minimum from left and top to find best path to that square
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++)
                mem[i][j] = grid[i][j] + Math.min(mem[i - 1][j], mem[i][j - 1]);
        }
        
        return mem[rows - 1][cols - 1];
    }
}
