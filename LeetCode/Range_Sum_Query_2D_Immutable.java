/* Noah Park

Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

*/

class NumMatrix {
    
    // Intuition: Range prefix sum the rectangle up to each (i, j) pair in the matrix. From here, we can add and subtract any subrectangle by taking the bottom right corner - 1 left of bottom left corner - 1 above top right corner then adding the doubly subtracted left diagonal of the top left corner. Prefix has one extra row and column of zeroes at the 0th row/column to maintain this looking left or above when prefixing.
    // Time: O(m*n) to build the prefix once. O(1) for any calls to sumRegion since prefix doesn't change.
    // Space: O(m*n) to maintain the prefixed array.
    int[][] mat, prefix;
    int rows, cols;

    public NumMatrix(int[][] matrix) {
        mat = matrix;
        rows = matrix.length;
        cols = matrix[0].length;
        prefix = new int[rows + 1][cols + 1];
        prefix[1][1] = matrix[0][0];
        
        for (int i = 1; i < rows; i++) prefix[i + 1][1] += (prefix[i][1] + matrix[i][0]);
        for (int i = 1; i < cols; i++) prefix[1][i + 1] += (prefix[1][i] + matrix[0][i]);
        
        for (int i = 1; i < rows; i++) 
            for (int j = 1; j < cols; j++) 
                prefix[i + 1][j + 1] = prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j] + matrix[i][j];
    }
    
    public void print(int[][] m) {
        for (int[] row : m) {
            for (int col : row)
                System.out.print(col + " ");
            System.out.println();
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefix[row2 + 1][col2 + 1] - prefix[row2 + 1][col1] - prefix[row1][col2 + 1] + prefix[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
