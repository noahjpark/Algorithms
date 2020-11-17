/* Noah Park

Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the number of special positions in mat.

A position (i,j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).

*/

class Solution {
    public int numSpecial(int[][] mat) {
        int special = 0;
        for(int i = 0; i < mat.length; i++){
            int idx = -1;
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] == 1 && idx == -1) idx = j;
                else if(mat[i][j] == 1 && idx != -1){
                    idx = -1;
                    break;
                }
            }
            
            if(idx == -1) continue;
            int numOnes = 0;
            for(int row = 0; row < mat.length; row++){
                if(mat[row][idx] == 1) numOnes++;
            }
            if(numOnes == 1) special++;
        }
        
        return special;
    }
}
