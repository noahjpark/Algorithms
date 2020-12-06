/* Noah Park

On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

*/

class Solution {
    // 2d matrix dfs traversal
    public int dfs(int[][] mat, int i, int j, int cur, int target) {
		if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length || mat[i][j] == -1) return 0; // out of bounds or bad path to -1 results in no paths added
		else if (mat[i][j] == 2 && cur == target) return 1; // if we are at the end and the cur number of 0s equals the target 0s, we found a new path

		if (mat[i][j] == 0) cur++; // if we are at a 0, update the cur number of 0s counter
        
		int temp = mat[i][j]; // store what was in the matrix
		mat[i][j] = -1; // update to -1 to not recount the same path
        
        // count paths in all directions (only 3 will occur since the one we are coming from will be -1) results in O(3^n) time complexity
		int paths = dfs(mat, i - 1, j, cur, target) + dfs(mat, i, j - 1, cur, target) + dfs(mat, i + 1, j, cur, target) + dfs(mat, i, j + 1, cur, target);
        
		mat[i][j] = temp; // backtrack and update back to the original value
        
		return paths; // return total paths from the current cell
	}
    
    public int uniquePathsIII(int[][] grid) {
		int rows = grid.length, cols = grid[0].length;

        // count number of obstacles and start/end cells to be subtracted from the total elements to find the number of 0s needed (target)
        // we also find the starting indices of the starting cell '1'
		int obstacles = 0, i = 0, j = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
                if (grid[row][col] != 0) obstacles++;
                if (grid[row][col] == 1) { i = row; j = col; }
            }
		}

		int target = (rows * cols) - obstacles; // number of 0s required

		return dfs(grid, i, j, 0, target); // count valid paths to ending cell
    }
}
