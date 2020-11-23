/* Noah Park

In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

*/

class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length, cols = nums[0].length;
        // If the elements don't match in size, we can't reshape
        if(r * c != rows * cols) return nums;
        
        // Fill the new array using two pointers
        int[][] res = new int[r][c];
        int ridx = 0, cidx = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                res[ridx][cidx++] = nums[i][j];
                // If the column pointer is at the end of its bounds, reset to 0 and update row pointer
                if(cidx == c) { cidx = 0; ridx++; }
            }
        }
        
        return res;
    }
}
