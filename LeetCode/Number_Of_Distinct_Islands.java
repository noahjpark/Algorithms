/* Noah Park

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

*/

class Solution {
    // 2d matrix dfs implementation
    public void dfs(int[][] grid, int i, int j, StringBuilder res, String dir) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) return; // if out of bounds or in the water, return
        
        grid[i][j] = 0; // update the grid (visit)
        res.append(dir); // append the direction we came from
        
        // call dfs on each neighbor with the corresponding direction
        dfs(grid, i - 1, j, res, "u");
        dfs(grid, i + 1, j, res, "d");
        dfs(grid, i, j - 1, res, "l"); 
        dfs(grid, i, j + 1, res, "r");
        
        // need to backtrack as to not accidentally say two islands are identical when they are not
        // PATHING MATTERS, this tells us when we go back along a path we came from
        res.append("b");
    }
    
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0; // edge cases
        
        int rows = grid.length, cols = grid[0].length; 
        Set<String> set = new HashSet<>(); // set will only have unique island paths
        
        // iterate over the entire grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) { // only need to check when we find an island
                    StringBuilder s = new StringBuilder(); // store the path in a string
                    dfs(grid, i, j, s, "*"); // * just denotes origin
                    set.add(s.toString()); // add that string to the set
                }
            }
        }
        
        return set.size(); // set can only contain unique paths, so its size will be the number of unique islands
    }
}
