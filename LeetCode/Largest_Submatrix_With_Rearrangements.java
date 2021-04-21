/* Noah Park

You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.

Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.

*/

class Solution {
    
    // Intuition: Store the consecutive ones at each point (i, j) then iterate over the consecutive ones and calculate the largest area.
    // Time: O(n * m log m) where n is the rows and m is the columns.
    // Space: O(n*m) to not modify the matrix input array. If we modify matrix, the space will be constant O(1).
    public int largestSubmatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length, area = 0;
        int[][] grid = new int[rows][cols];
        grid[0] = matrix[0];
    
        for (int i = 1; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                if (matrix[i][j] == 1) grid[i][j] = grid[i - 1][j] + 1;
        
        for (int i = 0; i < rows; i++) {
            Arrays.sort(grid[i]);
        
            for (int j = 0; j < cols; j++)
                area = Math.max(area, grid[i][j] * (cols - j));
        }
        
        return area;
    }
}
