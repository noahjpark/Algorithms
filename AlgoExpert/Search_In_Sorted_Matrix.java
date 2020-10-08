// Noah Park
/*

Problem: You're given a two-dimensional array (a matrix) of distinct integers and a target integer. Each
row in the matrix is sorted, and each column is also sorted; the matrix doesn't necessarily have the same
height and width.

Write a function that returns an array of the row and column indices of the target integer if it's contained
in the matrix, otherwise [-1, -1].

*/

public class Search_In_Sorted_Matrix {
    // Time: O(n+m) | Space: O(1)
    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        // Find the appropriate row and column to search through
        int row = findRow(matrix, target);
        int col = findCol(matrix, target);

        // If we know the value cannot be searched for, we can return the (-1, -1)
        if(row == -1 || col == -1){
            return new int[] {-1, -1};
        }

        // Iterate through the target column where target could be
        // Return true if we find it, otherwise break when we reach a value too large
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][col] == target){
                return new int[] {i, col};
            }
            else if(matrix[i][col] > target){
                break;
            }
        }

        // Iterate through the target row where target could be
        // Return true if we find it, otherwise break when we reach a value too large
        for(int i = 0; i < matrix.length; i++){
            if(matrix[row][i] == target){
                return new int[] {row, i};
            }
            else if(matrix[row][i] > target){
                break;
            }
        }

        // The target is not in the matrix
        return new int[] {-1, -1};
    }

    public static int findRow(int[][] matrix, int target){
        int row = -1;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][0] > target){
                break;
            }
            row = i;
        }
        return row;
    }

    public static int findCol(int[][] matrix, int target){
        int col = -1;
        for(int i = 0; i < matrix[0].length; i++){
            if(matrix[0][i] > target){
                break;
            }
            col = i;
        }
        return col;
    }

    // Clement's solution that is a bit shorter
    // Basically we move the pointers based on the value at those indices
    public static int[] searchSortedMatrix(int[][] matrix, int target){
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col > -1){
            // If the value is too large, we can decrease our column index to get a smaller value
            if(matrix[row][col] > target) col--;

            // Else if the value is too small, we can decrease our row index to get a larger value
            else if(matrix[row][col] < target) row++;

            // Else we found the target value and can return the row and column
            else return new int[] {row, col};
        }
        // No target value found in the matrix
        return new int[] {-1, -1};
    }
}
