/* Noah Park

Given an m x n board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

*/

class Solution {
    public boolean dfs(char[][] board, String word, int idx, int i, int j){
        if(i < 0 || j < 0 || i >= board.length || j >= board[i].length) return false; // out of bounds
        else if(word.charAt(idx) != board[i][j]) return false; // character mismatch
        else if(idx == word.length() - 1) return true; // character match and at the end of the word (found the word on the board)
        char temp = board[i][j]; // backtracking: need to overwrite current character so it can't be reused. store in temp to restore the board after the recursive calls
        board[i][j] = '*'; // override the character
        // see if any direction produces a valid result
        boolean res = dfs(board, word, idx + 1, i, j + 1) || dfs(board, word, idx + 1, i, j - 1) ||
            dfs(board, word, idx + 1, i - 1, j) || dfs(board, word, idx + 1, i + 1, j);
        board[i][j] = temp; // put the character back for the other dfs calls in exist()
        return res;
    }
    
    public boolean exist(char[][] board, String word) {
        // Call dfs on each character to see if the word can be formed using that character
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++)
                if(dfs(board, word, 0, i, j)) return true; // word was formed
        }
        return false; // no character sequence matched our word
    }
}
