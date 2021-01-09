/* Noah Park

On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.

The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.

Return the number of pawns the rook can capture in one move.

*/

class Solution {
    // Time: O(r*c) where r is the rows of the board and j is the columns
    // Space: O(1)
    public int numRookCaptures(char[][] board) {
        if (board == null || board.length == 0) return 0; // edge cases
        
        // cache values
        int pawns = 0, rows = board.length, cols = board[0].length, r = -1, c = -1;
        
        // find rook
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'R') {
                    r = i;
                    c = j;
                    break;
                }
            }
            if (r != -1) break;
        }
        
        // intuition: check four cardinal directions straight until we reach the end of the board, a p, or a B. update pawns if we find the p
        
        // up 
        for (int i = r - 1; i > -1; i--) {
            if (board[i][c] == 'p') pawns++;
            if (board[i][c] != '.') break;
        }
        
        // down
        for (int i = r + 1; i < rows; i++) {
            if (board[i][c] == 'p') pawns++;
            if (board[i][c] != '.') break;
        }
        
        // left
        for (int j = c - 1; j > -1; j--) {
            if (board[r][j] == 'p') pawns++;
            if (board[r][j] != '.') break;
        }
        
        // right
        for (int j = c + 1; j < cols; j++) {
            if (board[r][j] == 'p') pawns++;
            if (board[r][j] != '.') break;
        }
        
        return pawns;
    }
}
