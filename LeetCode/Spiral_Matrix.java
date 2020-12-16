/* Noah Park

Given an m x n matrix, return all elements of the matrix in spiral order.

*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new ArrayList<>(); // edge cases
        
        // spiral list
        List<Integer> res = new ArrayList<>();
        
        // cached values and 4 pointers
        int rows = matrix.length, cols = matrix[0].length, size = rows * cols;
        int rend = rows - 1, rstart = 0, cend = cols - 1, cstart = 0;
        
        // iterate until the list is full
        while (res.size() < size) {
            // top row and right column
            for (int i = cstart; i <= cend; i++) res.add(matrix[rstart][i]);
            for (int i = rstart + 1; i <= rend; i++) res.add(matrix[i][cend]);
            
            if (res.size() == size) break; // don't recount backwards if full now
            
            // bottom row and left column
            for (int i = cend - 1; i >= cstart; i--) res.add(matrix[rend][i]);
            for (int i = rend - 1; i > rstart; i--) res.add(matrix[i][cstart]);
            
            // update pointers
            rstart++; rend--;
            cstart++; cend--;
        }    
        
        return res;
    }
}
