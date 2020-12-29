/* Noah Park

Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.

Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

*/

class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return; // edge cases
        
        int rows = matrix.length, cols = matrix[0].length;
        boolean firstRow = false, firstCol = false; // keeps track of if the first row and col need to be zeroed out
        
        // mark first row and column for zeroing out those i and j later, if i/j are 0 then mark the first row or col to be zeroed later as well
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) firstRow = true;
                    if (j == 0) firstCol = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // zero out positions from first row/col markings
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        }
        
        // zero out the first row
        if (firstRow) {
            for (int i = 0; i < cols; i++) matrix[0][i] = 0;
        }
        
        // zero out the first col
        if (firstCol) {
            for (int i = 0; i < rows; i++) matrix[i][0] = 0;
        }
    }
    
    public void setZeroesSubOptimal(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return; // edge cases
        
        // sets will store all row and columns that need to be zeroed out
        int rows = matrix.length, cols = matrix[0].length;
        HashSet<Integer> r = new HashSet<>();
        HashSet<Integer> c = new HashSet<>();
        
        // store all row/column coordinates that need to be zeroed
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    r.add(i); c.add(j);
                }
            }
        }
        
        // zero out the rows and cols
        for (Integer row : r)
            for (int i = 0; i < cols; i++) matrix[row][i] = 0;
        
        for (Integer col : c)
            for (int i = 0; i < rows; i++) matrix[i][col] = 0;
    }
}
