/* Noah Park

Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.

*/

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        // If at any point, the value to the left corner of our current value does not match, return false
        int rows = matrix.length, cols = matrix[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++)
                if(i - 1 > -1 && j - 1 > - 1 && matrix[i][j] != matrix[i - 1][j - 1]) return false;
        }
        return true;
    }
}
