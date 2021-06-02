/* Noah Park

You are given an n x n grid where we place some 1 x 1 x 1 cubes that are axis-aligned with the x, y, and z axes.

Each value v = grid[i][j] represents a tower of v cubes placed on top of the cell (i, j).

We view the projection of these cubes onto the xy, yz, and zx planes.

A projection is like a shadow, that maps our 3-dimensional figure to a 2-dimensional plane. We are viewing the "shadow" when looking at the cubes from the top, the front, and the side.

Return the total area of all three projections.

*/

class Solution {
    
    // Intuition: optimize the bottom solution to improve the space and time slightly.
    // Time: O(n^2) to iterate over the grid.
    // Space: O(1) constant.
    public int projectionArea(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int res = 0, n = grid.length;
        
        for (int i = 0; i < n; i++) {
            int rmax = 0, cmax = 0;
            
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) res++;
                rmax = Math.max(rmax, grid[i][j]);
                cmax = Math.max(cmax, grid[j][i]);
            }
            
            res += (rmax + cmax);
        }
        
        return res;
    }
    
    // Intuition: Add largest of each row, largest of each col, and include 1 for each non zero value.
    // Time: O(n^2) to iterate over grid plus another pass for the cols.
    // Space: O(n) for the cols array.
    public int projectionArea2(int[][] grid) {
        int res = 0, n = grid.length;
        int[] cols = new int[n];
        
        for (int i = 0; i < n; i++) {
            int rowMax = 0;
            for (int j = 0; j < n; j++) {
                rowMax = Math.max(rowMax, grid[i][j]);
                if (grid[i][j] > 0) res++;
                cols[j] = Math.max(cols[j], grid[i][j]);
            }
            res += rowMax;
        }
        
        for (int col : cols) 
            res += col;
        
        return res;
    }
}
