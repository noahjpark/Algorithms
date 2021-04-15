/* Noah Park

Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:

i - k <= r <= i + k,
j - k <= c <= j + k, and
(r, c) is a valid position in the matrix.

*/

class Solution {
    
    // Intuition: Apply the range sum algorithm which prefixes the entire rectangle from (0, 0) to (i, j). Then the range sum can be calculated by subtracting the unused area to the left of our block, the unused area above our block, then adding the area to the left upper diagonal which was subtracted twice.
    // Time: O(r*c) two passes: one to prefix, one to utilize the prefix in the range sum algorithm.
    // Space: O(r*c) for the prefix matrix and the resulting one.
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int rows = mat.length, cols = mat[0].length;
        int[][] prefix = new int[rows + 1][cols + 1], res = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                prefix[i + 1][j + 1] = prefix[i + 1][j] + prefix[i][j + 1] - prefix[i][j] + mat[i][j];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int r2 = Math.min(rows, i + k + 1), c2 = Math.min(cols, j + k + 1), r1 = Math.max(0, i - k), c1 = Math.max(0, j - k); // since prefix has one extra row and column, we include the row/col idx and use + 1 with r2/c2
                res[i][j] = prefix[r2][c2] - prefix[r2][c1] - prefix[r1][c2] + prefix[r1][c1];
            }
        }
        
        return res;
    }
    
    // Intuition: Solve the problem as stated brute force style.
    // Time: O(r*c*(k*2 + 1)^2)
    // Space: O(r*c) for the resulting matrix
    public int[][] matrixBlockSumNaive(int[][] mat, int k) {
        int rows = mat.length, cols = mat[0].length;
        int[][] res = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = blockSum(mat, k, i, j);
            }
        }
        
        return res;
    }
    
    public int blockSum(int[][] mat, int k, int i, int j) {
        int sum = 0;
        
        for (int r = i - k; r <= i + k; r++)
            for (int c = j - k; c <= j + k; c++) 
                if (inBounds(mat, r, c)) sum += mat[r][c];
            
        return sum;
    }
    
    public boolean inBounds(int[][] mat, int r, int c) {
        return r >= 0 && c >= 0 && r < mat.length && c < mat[0].length;
    }
}
