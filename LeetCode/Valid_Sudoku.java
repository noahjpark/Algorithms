/* Noah Park

Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.

*/

class Solution {
    // second more concise solution using hash set and strings
    public boolean isValidSudoku(char[][] board) {
        Set<String> visit = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (visit.contains(board[i][j] + " row " + i) || visit.contains(board[i][j] + " col " + j) || visit.contains(board[i][j] + " quadrant " + i / 3 + " " + j / 3)) return false;
                    visit.add(board[i][j] + " row " + i);
                    visit.add(board[i][j] + " col " + j);
                    visit.add(board[i][j] + " quadrant " + i / 3 + " " + j / 3);
                }
            }
        }
        
        return true;
    }
    
    // initial solution
    public boolean isValidSudokuInitial(char[][] board) {
        Map<Integer, int[]> row = new HashMap<>(); // store all seen numbers from a particular row
        Map<Integer, int[]> col = new HashMap<>(); // same for cols
        Map<Integer, int[]> quadrants = new HashMap<>(); // same for quadrants
        
        // iterate over the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = Integer.parseInt(String.valueOf(board[i][j])); // convert to a number
                    
                    // row
                    if (!row.containsKey(i)) row.put(i, new int[10]);
                    if (row.get(i)[num] == 1) return false;
                    row.get(i)[num]++;
                        
                    // col
                    if (!col.containsKey(j)) col.put(j, new int[10]);
                    if (col.get(j)[num] == 1) return false;
                    col.get(j)[num]++;
                    
                    int quad = 1;
                    if (j > 2 && j < 6) quad = 2;
                    else if (j > 5 && j < 9) quad = 3;
                    
                    int mult = 1;
                    if (i > 2 && i < 6) mult = 4;
                    else if (i > 5 && j < 9) mult = 5;
                    
                    int key = quad * mult; // get a unique value for each quadrant
                    
                    // quadrant
                    if (!quadrants.containsKey(key)) quadrants.put(key, new int[10]);
                    if (quadrants.get(key)[num] == 1) return false;
                    quadrants.get(key)[num]++;
                }
            }
        }
        
        return true;
    }
}
