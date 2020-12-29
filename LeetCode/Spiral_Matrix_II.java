/* Noah Park

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

*/

class Solution {
    public int[][] generateMatrix(int n) {
        // generate empty matrix of proper size
        int size = n * n;
        int[][] res = new int[n][n];
        
        // use 4 pointers for the row start/end and column start/end
        int rstart = 0, rend = n - 1, cstart = 0, cend = n - 1;
        
        // iterate over all values we need to populate into the resulting matrix
        int num = 1;
        while (num <= size) {
            for (int i = cstart; i <= cend; i++) res[rstart][i] = num++; // top row of our current submatrix
            for (int i = rstart + 1; i <= rend; i++) res[i][cend] = num++; // right column of our current submatrix
            for (int i = cend - 1; i >= cstart; i--) res[rend][i] = num++; // bottom row of our current submatrix
            for (int i = rend - 1; i > rstart; i--) res[i][cstart] = num++; // left column of our current submatrix
            
            // update pointers
            rstart++; rend--;
            cstart++; cend--;
        }
        
        return res;
    }
}
