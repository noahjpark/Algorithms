/* Noah Park

Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

*/

class Solution {
    
    // Intuition: Utilizes the idea of combinatorics to avoid calculating every possible outcome. Fixes the two rows we are looking at then counts the adjacent 1s that form corners. Then choosing rectangles is utilizing the formula n choose 2. Could optimize to choose the smaller dimension to iterate over quadratically.
    // Time: O(r^2*c) iterating over nested rows then checking each of their columns.
    // Space: O(1) constant.
    public int countCornerRectangles(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length, res = 0;
        
        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < rows; j++) {
                int c = 0;
                
                for (int k = 0; k < cols; k++) 
                    if (grid[i][k] == 1 && grid[j][k] == 1) c++;
                
                res += (c * (c - 1) / 2);
            }
        }
        
        return res;
    }
    
    // Brute force counting corners.
    // Time: O(r^2*c^2) iterating nested each row/column to count all four corners.
    // Space: O(1) constant.
    public int countCornerRectangles2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length, res = 0;
        
        for (int j = 0; j < cols - 1; j++) 
            for (int i = 0; i < rows - 1; i++) 
                if (grid[i][j] == 1) 
                    for (int k = j + 1; k < cols; k++) 
                        if (grid[i][k] == 1) 
                            for (int l = i + 1; l < rows; l++) 
                                if (grid[l][j] == 1 && grid[l][k] == 1) res++;
        
        return res;
    }
    
}
