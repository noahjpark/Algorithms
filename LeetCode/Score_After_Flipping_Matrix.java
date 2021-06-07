/* Noah Park

We have a two dimensional matrix grid where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

*/

class Solution {
    
    // Intuition: Same as the bottom but doesn't actually update the matrix itself. We begin by starting with each binary number maximized with the leftmost bit as a 1. Then from there we attempt to see if there are any counts of columns with more zeroes than 1s and take the larger number. if (grid[i][0] == grid[i][j]) means that since we aren't updating the matrix itself, some 0s will actually be 1s and vis versa. We can tell by checking what that rows first value is. If the first value is a 0 (remember we transferred all rows technically with their first bit as a 1), all zeroes in that row are actually represented as 1s and the opposite for all 1s in this row.
    // Time: O(m*n) to iterate over the grid.
    // Space: O(1) constant.
    public int matrixScore(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length, res = (1 << (cols - 1)) * rows; 
        
        for (int j = 1; j < cols; j++) {
            int c = 0;
            
            for (int i = 0; i < rows; i++)
                if (grid[i][0] == grid[i][j]) c++;
            
            res += (1 << (cols - 1 - j)) * Math.max(c, rows - c);
        }
        
        return res;
    }
    
    // Intuition: We can maximize a number by making its leftmost bit a 1. Then we check if we can maximize the total number of 1s in any column. We do this if so. 
    // Time: O(n*m) three passes.
    // Space: O(m) to maintain the columns count.
    public int matrixScore2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length;
        int[] count = new int[cols];
        
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] ^= 1;
                    if (grid[i][j] == 0) count[j]++;
                }
            } else {
                for (int j = 0; j < cols; j++)
                    if (grid[i][j] == 0) count[j]++;
            }
        }
        
        for (int j = 0; j < cols; j++) 
            if (count[j] > rows / 2) 
                for (int i = 0; i < rows; i++)
                    grid[i][j] ^= 1;
            
        int res = 0;
        
        for (int i = 0; i < rows; i++) {
            int temp = 0;
            
            for (int j = 0; j < cols; j++)
                if (grid[i][j] == 1) temp |= (1 << (cols - 1 - j));
            
            res += temp;
        }
        
        return res;
    }
}
