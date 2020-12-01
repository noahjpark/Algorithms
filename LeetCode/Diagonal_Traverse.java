/* Noah Park

Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

*/

class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0]; // edge case for if the matrix is empty
        int rows = matrix.length, cols = matrix[0].length; // number of rows and columns
        int size = rows * cols, r = 0, c = 0; // total size of the matrix and r/c pointer into matrix
        boolean left = false; // indicates whether we are moving left or right
        int[] res = new int[size]; // return array
        
        for (int i = 0; i < size; i++) { // iterate through all elements
            res[i] = matrix[r][c]; // get the element from the matrix with the current pointers
            
            if (left) { // moving left
                if (r == rows - 1) { c++; left = false; } // if we are at the bottom row, only update the column and direction
                else if (r < rows - 1 && c == 0) { r++; left = false; } // else if we are not at the bottom but are at the left wall, update the row and direction
                else { r++; c--; } // else update moving diagonally left since we are not at a boundary
            } else { // moving right
                if (c == cols - 1) { r++; left = true; } // if we are at the right wall, only update the row and direction
                else if (c < cols - 1 && r == 0) { c++; left = true; } // else if we are not at the right wall but are at the top row, update the column and direection
                else { r--; c++; } // else update moving diagonally right since we are not at a boundary
            }
        }
        
        return res;
    }
}
