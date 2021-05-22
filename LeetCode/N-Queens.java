/* Noah Park

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

*/

class Solution {
    
    // Intuition: Typical backtracking problem. Essentially we try to create all possibilities of a queen on a position in the grid. We can go for faster time by maintaining the grid and not having to constantly loop in the recursion to choose the next position. The used array maintains if a particular column has already been used. We don't have to worry about rows since we only choose one at each recursive call. The diagonals and adiagonals arrays keep track of the used up diagonals with the intuition that all regular diagonals are row - col and anti-diagonals are row + col. Since diagonals can be negative, I padded with a value of n - 1 since the maximum size for the boaard is n. I also opted for arrays instead of hash sets for the speed and memory usage. Maintaining a character grid is much faster than iterating with a stringbuilder, so this was a fun optimization that improved time quite a bit. I maintain the row at each recursive call and add to the resulting list if the row reaches n (i.e. placed all queens already). If we are placing a queen, we update the board to a 'Q', add the row as a string to the temporary list, then update the used boolean structures. After the recursive call, we update them back for the backtracking.
    // Time: O(n!) to create all possibilities.
    // Space: O(n^2) to maintain the board. If we add extra iterations we can obtain a O(n) space with the same time at a moderate hit to the literal runtime.
    List<List<String>> res = new ArrayList<>();
    boolean[] used = new boolean[9], diagonals = new boolean[17], adiagonals = new boolean[17];
    char[][] board;
    int n;
    
    public List<List<String>> solveNQueens(int size) {
        board = new char[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = '.';
        n = size;
        dfs(new ArrayList<>(), 0);
        return res;
    }
    
    public void dfs(List<String> s, int row) {
        if (row == n) { res.add(new ArrayList<>(s)); return; }
        
        for (int i = 0; i < n; i++) {
            
            // Looping goes here if we omit the use of a board structure.
            
            if (!used[i] && !diagonals[row - i + n - 1] && !adiagonals[row + i]) {
                board[row][i] = 'Q';
                s.add(new String(board[row]));
                used[i] = diagonals[row - i + n - 1] = adiagonals[row + i] = true;
                
                dfs(s, row + 1);
                
                board[row][i] = '.';
                s.remove(s.size() - 1);
                used[i] = diagonals[row - i + n - 1] = adiagonals[row + i] = false;
            }
        }
    }
}
