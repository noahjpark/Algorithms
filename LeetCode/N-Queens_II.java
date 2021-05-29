/* Noah Park

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

*/

class Solution {
    
    // Intuition: Maintain the chosen columns and each diagonal. The rows are maintained as one choice for each call. Otherwise, this is typical backtracking. If we reach the end of the board (n), we have found a valid choice and can return 1. Otherwise, we iterate over all choices and choose valid choices before removing that choice and backtracking. All diagonals have the same (row - col) value; all anti-diagonals have the same (row + col) value.
    // Time: O(n!) to generate all possible combinations.
    // Space: O(n) at most n recursive calls deep for valid placements along with the diagonals/anti-diagonals/columns arrays.
    boolean[] diagonals, adiagonals, cols;
    int n;
    
    public int totalNQueens(int s) {
        n = s;
        cols = new boolean[n];
        diagonals = new boolean[(n - 1)*2 + 1];
        adiagonals = new boolean[(n - 1)*2 + 1];
        return dfs(0);
    }
    
    public int dfs(int row) {
        if (row == n) return 1;
        
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            if (!cols[i] && !diagonals[row - i + (n - 1)] && !adiagonals[row + i]) {
                cols[i] = true;
                diagonals[row - i + (n - 1)] = true;
                adiagonals[row + i] = true;
                
                res += dfs(row + 1);
                
                cols[i] = false;
                diagonals[row - i + (n - 1)] = false;
                adiagonals[row + i] = false;
            }
                
        }
            
        return res;
    }
    
}
