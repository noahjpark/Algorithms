/* Noah Park

Given a square matrix mat, return the sum of the matrix diagonals.

Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.

*/

class Solution {
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        for(int i = 0; i < mat.length; i++)
            sum += mat[i][i];
        
        for(int i = mat.length - 1; i >= 0; i--)
            sum += mat[i][mat.length - 1 - i];  
        
        if(mat.length % 2 == 1) sum -= mat[mat.length / 2][mat.length / 2];
        
        return sum;
    }
}
