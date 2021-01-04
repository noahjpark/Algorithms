/* Noah Park

(This problem is an interactive problem.)

A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.

You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.

*/

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int col = binarySearch(binaryMatrix, 0); // start at the leftmost one of the first row or at the row size
        
        if (col == 0) return 0; // edge case if we already found the leftmost possible column
        
        List<Integer> dimensions = binaryMatrix.dimensions();
        int rows = dimensions.get(0), cols = dimensions.get(1), i = 0, j = col; // i and j are the pointers
        
        // iterate through all rows to check if there are new leftmost columns
        while (i < rows) {
            j--; // we are either on a 1 from the first row or one more than the row size if there were no 1s, we start one more in and try to 
            // find a one in a new row, as this would result in a new leftmost one
            
            // move downwards until we find a one or fall off the matrix dimensions
            while (i < rows && binaryMatrix.get(i, j) == 0) i++;
            
            if (i == rows) break; // fell off matrix dimensions, use our previous leftmost column value 'col' when we return
            
            while (j > 0 && binaryMatrix.get(i, j - 1) == 1) j--; // then move left until we hit the left most one index
            
            if (j == 0) return 0; // if the leftmost one index is 0, we can simply stop
            
            col = j; // otherwise update the leftmost one index return value
        }
        
        return col == cols ? -1 : col; // if col is not valid, there are no onees in the matrix, otherwise we can return col
    }
    
    // binary search to find the leftmost one in a given row
    public int binarySearch(BinaryMatrix binaryMatrix, int row) {
        List<Integer> dimensions = binaryMatrix.dimensions(); // dimensions of the matrix
        
        int cols = dimensions.get(1), left = 0, right = cols - 1;
        
        // left will be one more than right when the loop ends
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // every time we find a one, move right to the left of that one
            // every time we find a zero, move left to the right of that zero
            // this results in left ending on the leftmost one or one larger than the row length of the matrix
            if (binaryMatrix.get(row, mid) == 1) right = mid - 1;
            else left = mid + 1;
        }
        
        return left;
    }
}
