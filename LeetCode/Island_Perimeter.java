/* Noah Park

You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

*/

class Solution {
    
    // Intuition: bfs is typical bfs and dfs is typical dfs. The idea is to not have to search through unnecessary areas.
    // Time and Space for both are O(m*n) for searching through the grid and maintaining a visited 2d array
    int perimeter = 0;
    
    public void bfs(int[][] grid, int i, int j, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ i, j });
        
        while (!q.isEmpty()) {
            int[] coor = q.poll();
            int r = coor[0], c = coor[1];
            
            if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) { perimeter++; continue; }
            if (visited[r][c]) continue;
            
            visited[r][c] = true;
            
            q.offer(new int[]{ r - 1, c }); q.offer(new int[]{ r + 1, c });
            q.offer(new int[]{ r, c - 1 }); q.offer(new int[]{ r, c + 1 });
        }
    }
    
    public void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) { perimeter++; return; }
        if (visited[i][j]) return;
        
        visited[i][j] = true;
        
        dfs(grid, i - 1, j, visited); dfs(grid, i + 1, j, visited);
        dfs(grid, i, j - 1, visited); dfs(grid, i, j + 1, visited);
    }
    
    
    // Intuition: Simply checking the perimeter of each grid square that is a '1' and adding if out of bounds of a '0' is more efficient in theory.
    // Time: O(n) to search through the grid (could be defined as O(1) since the grid bounds are at most 100)
    // Space: O(1) constant
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length;
        //boolean[][] visited = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if (grid[i][j] == 1) {
                //     bfs(grid, i, j, visited);
                //     i = rows;
                //     break;
                // }
                // if (grid[i][j] == 1) {
                //     dfs(grid, i, j, visited);
                //     i = rows;
                //     break;
                // }
                if (grid[i][j] == 1) { // optimization to this would be to check just the upper and left cells and subtract twice instead of adding four times
                    if (notLand(grid, i - 1, j)) perimeter++;
                    if (notLand(grid, i + 1, j)) perimeter++;
                    if (notLand(grid, i, j + 1)) perimeter++;
                    if (notLand(grid, i, j - 1)) perimeter++;
                }
            }
        }
        
        return perimeter;
    }
    
    public boolean notLand(int[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0;
    }
}
