/* Noah Park

Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

*/

class Solution {       
    public void dfs(char[][] grid, int i, int j, boolean[][] visited){
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0' || visited[i][j]) return;
        
        visited[i][j] = true;
        
        dfs(grid, i + 1, j, visited); dfs(grid, i - 1, j, visited);
        dfs(grid, i, j + 1, visited); dfs(grid, i, j - 1, visited);
    }
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length, numIslands = 0;
        boolean[][] visited = new boolean[rows][cols];
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1' && !visited[i][j]) { dfs(grid, i, j, visited); numIslands++; }
            }
        }
        
        return numIslands;
    }
}
