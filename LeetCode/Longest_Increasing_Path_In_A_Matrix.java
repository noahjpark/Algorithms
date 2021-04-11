/* Noah Park

Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

*/

class Solution {
    
    // Intuition: Apply dfs but we must memoize the values in order to not time fail. Go as deep as possible, update the path sum from there then add 1.
    // Time: O(r*c) rows and columns
    // Space: O(r*c)
    int[][] dir = new int[][]{ {0, 1}, {1, 0}, {0, -1}, {-1, 0} }, mem;
    
    public int dfs(int[][] mat, int i, int j) {
        if (mem[i][j] != 0) return mem[i][j];
        
        for (int[] d : dir) {
            int r = i + d[0], c = j + d[1];
            
            if (r >= 0 && c >= 0 && r < mat.length && c < mat[r].length && mat[r][c] > mat[i][j])
                mem[i][j] = Math.max(mem[i][j], dfs(mat, r, c));
        }
        
        mem[i][j]++;
        
        return mem[i][j];
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int rows = matrix.length, cols = matrix[0].length, max = 0;
        mem = new int[rows][cols];
        
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                max = Math.max(dfs(matrix, i, j), max);
            
        return max;
    }
}
