/* Noah Park

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

*/

class Solution {
    public void bfs(char[][] board, int i, int j){
        // Typical matrix BFS algorithm
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { i, j }); // store coordinates
        while (!q.isEmpty()) {
            int[] coor = q.poll(); // remove the current cell
            int r = coor[0], c = coor[1];
            if(r < 0 || c < 0 || r >= board.length || c >= board[0].length) continue; // coordinates are out of bounds *optimization* over if statements
            
            // If we have an 'X' or an '*', skip it as we have either already checked it or it doesn't matter, essentially we want to mark 'O's that are connected to any edge with an '*' to be updated later
            if(board[r][c] != 'O') continue;
            board[r][c] = '*'; // mark as connected to the edge
            
            // offer all neighbors
            q.offer(new int[] { r - 1, c });
            q.offer(new int[] { r, c - 1 });
            q.offer(new int[] { r + 1, c });
            q.offer(new int[] { r, c + 1 });
        }
    }
    
    public void solve(char[][] board) {
        if(board.length == 0) return; // edge case
        int rows = board.length, cols = board[0].length;
        
        // call bfs on all border cells so we can mark all 'O's that are connected to the edge with a '*'.
        for (int col = 0; col < cols; col++) {
            bfs(board, 0, col); bfs(board, rows - 1, col);
        }
        for (int row = 0; row < rows; row++) {
            bfs(board, row, 0); bfs(board, row, cols - 1);
        }
        
        // bfs has been called on all border cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if the cell is still an 'O', it wasn't connected to a border and must have been surrounded by 'X's and should be updated to an 'X'
                // else if the board is an '*', it was connected to a border and should be an 'O'
                if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == '*') board[i][j] = 'O';
            }
        }
    }
}
