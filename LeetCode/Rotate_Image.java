/* Noah Park

You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

*/

class Solution {
    public void rotate(int[][] matrix) {
        int rstart = 0, rend = matrix.length - 1, cstart = 0, cend = matrix[0].length - 1;
        
        while (rstart < rend) {
            int j = 0;
            for (int i = cstart; i < cend; i++) {
                // update along right wall
                int temp = matrix[rstart + j][cend];
                matrix[rstart + j][cend] = matrix[rstart][i];
               
                // update along bottom wall
                int temp2 = matrix[rend][cend - j];
                matrix[rend][cend - j] = temp;
                temp = temp2;
               
                // update along left wall
                temp2 = matrix[rend - j][cstart];
                matrix[rend - j][cstart] = temp;
               
                // update original top wall
                matrix[rstart][i] = temp2;
                j++; // increment all walls except top
            }  
            // row and column bounds
            rstart++; cstart++;
            rend--; cend--;
        }
    }
}
