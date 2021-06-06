/* Noah Park

Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

*/

class Solution {
    
    // Intuition: Attempt all rotations and compare to target.
    // Time: O(n^2) to make the comparisons as there are only 4 rotations.
    // Space: O(n^2) to make a new array for easy rotations. Can transpose then reverse each row instead to do this in place.
    public boolean findRotation(int[][] mat, int[][] target) {
        if (mat == null || target == null || mat.length == 0 || target.length == 0) return false;
        
        if (isEqual(mat, target)) return true;
        
        for (int i = 0; i < 3; i++) {
            mat = rotate(mat);
            if (isEqual(mat, target)) return true;
        }
        
        return false;
    }
    
    public int[][] rotate(int[][] mat) {
        int[][] res = new int[mat.length][mat[0].length];
        int r = 0, c = mat[0].length - 1;
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                res[r++][c] = mat[i][j];
                if (r == mat.length) { r = 0; c--; }
            }
        }
        
        return res;
    }
    
    public boolean isEqual(int[][] m1, int[][] m2) {
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j < m1[0].length; j++)
                if (m1[i][j] != m2[i][j]) return false;
        
        return true;
    }
}
/*
1 2 3
4 5 6
7 8 9

1 4 7
2 5 8
3 6 9

7 4 1
8 5 2
9 6 3
*/
