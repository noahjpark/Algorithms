/* Noah Park

Given a matrix A, return the transpose of A.

The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.

*/

class Solution {
    public int[][] transpose(int[][] A) {
        int[][] trans = new int[A[0].length][A.length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++)
                trans[j][i] = A[i][j];
        }
        
        return trans;
    }
}
