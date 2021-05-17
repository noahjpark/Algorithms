/* Noah Park

This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the current board.

*/

class Solution {
    
    // Intuition: Follow the rules. Originally, I thought it allowed for bfs style eliminations not only in the vertical/horizontal directions with full 3 row/col minimums. Then approached with the in place updating of the values we want to remove. Iterate over each value looking at the horizontal eliminations then the vertical. Once all eliminating values are set to negative, we are able to go through and update the board.
    public int[][] candyCrush(int[][] board) {
        if (board == null || board.length == 0) return board;
        
        int rows = board.length, cols = board[0].length;
        
        while (true) {
            boolean stable = true;
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols - 2; j++) {
                    if (board[i][j] != 0 && Math.abs(board[i][j]) == Math.abs(board[i][j + 1]) && Math.abs(board[i][j]) == Math.abs(board[i][j + 2])) {
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -Math.abs(board[i][j]);
                        stable = false;   
                    }
                }
            }
            
            for (int i = 0; i < rows - 2; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] != 0 && Math.abs(board[i][j]) == Math.abs(board[i + 1][j]) && Math.abs(board[i][j]) == Math.abs(board[i + 2][j])) {
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -Math.abs(board[i][j]);
                        stable = false;
                    }
                }
            }
            
            for (int j = 0; j < cols; j++) {
                int r = rows - 1;
                for (int i = rows - 1; i >= 0; i--) 
                    if (board[i][j] > 0) board[r--][j] = board[i][j];
                while (r >= 0)
                    board[r--][j] = 0;
            }
            
            if (stable) break;
        }
        
        return board;
    }
    
}
