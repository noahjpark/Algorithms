/* Noah Park

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false; // edge cases
        
        int rows = matrix.length, cols = matrix[0].length, r = 0, c = cols - 1; // cache values and use two pointers
        
        while (r < rows && c > -1) { // start at the top right corner and move based on the fact that the rows and cols are sorted
            if (matrix[r][c] == target) return true; // found the target
            else if (matrix[r][c] > target) c--; // if too large, move left
            else r++; // if too small move down
        }
        
        return false; // target not found
    }
}
