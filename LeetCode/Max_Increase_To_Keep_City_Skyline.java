/* Noah Park

In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there. We are allowed to increase the height of any number of buildings, by any amount (the amounts can be different for different buildings). Height 0 is considered to be a building as well. 

At the end, the "skyline" when viewed from all four directions of the grid, i.e. top, bottom, left, and right, must be the same as the skyline of the original grid. A city's skyline is the outer contour of the rectangles formed by all the buildings when viewed from a distance. See the following example.

What is the maximum total sum that the height of the buildings can be increased?

*/

class Solution {
    
    // Find the maximum skyline looking from right/left and top/bottom. Then find the maximum increase we can get while maintaining all skylines (i.e. taking the minimum of the associated skylines and finding the maximum increase we can get to it).
    // Time: O(n^2) two passes and it is a square grid.
    // Space: O(n) to maintain a copy of the rows and columns maxes.
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length, res = 0;
        int[] rmax = new int[rows], cmax = new int[cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rmax[i] = Math.max(rmax[i], grid[i][j]);
                cmax[j] = Math.max(cmax[j], grid[i][j]);
            }
        }
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                res += Math.min(rmax[i], cmax[j]) - grid[i][j];
        
        return res;
    }
}
