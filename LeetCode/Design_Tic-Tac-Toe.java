/* Noah Park

Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
Follow up:
Could you do better than O(n2) per move() operation?

*/

class TicTacToe {
    
    // Intuition: Maintain the running sum of each row, column, and diagonal based on adding 1 for the first player and subtracting for the second. If we reach n or -n, that player won the game.
    // Time: O(1) since we maintain the running sum.
    // Space: O(n) for the rows and cols arrays.
    int[] rows, cols;
    int d1 = 0, d2 = 0, n;
    
    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
    }
    
    public int move(int row, int col, int player) {
        int v = player == 1 ? 1 : -1;
        
        rows[row] += v;
        cols[col] += v;
        if (row == col) d1 += v;
        if (col == (n - row - 1)) d2 += v;
        
        return Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(d1) == n || Math.abs(d2) == n ? player : 0;
    }
    
    // Intuition: Brute force calculate each possible winning point: horizontal, vertical, and each diagonal.
    // Time: O(n) to check a direction.
    // Space: O(1) constant
//     int[][] board;
//     int n;

//     /** Initialize your data structure here. */
//     public TicTacToe(int n) {
//         board = new int[n][n];
//         this.n = n;
//     }
    
//     /** Player {player} makes a move at ({row}, {col}).
//         @param row The row of the board.
//         @param col The column of the board.
//         @param player The player, can be either 1 or 2.
//         @return The current winning condition, can be either:
//                 0: No one wins.
//                 1: Player 1 wins.
//                 2: Player 2 wins. */
//     public int move(int row, int col, int player) {
//         board[row][col] = player;
//         int hcount = 0, vcount = 0, drcount = 0, dlcount = 0;
        
//         // check horizontal, vertical, and each diagonal if applicable
//         for (int i = 0; i < n; i++) {
//             if (board[row][i] == player) hcount++;
//             if (board[i][col] == player) vcount++;
//             if (board[i][i] == player) drcount++;
//             if (board[i][n - i - 1] == player) dlcount++;
//         }
        
//         return hcount == n || vcount == n || drcount == n || dlcount == n ? player : 0;
//     }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
