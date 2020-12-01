/* Noah Park

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

*/

class Solution {
    // BFS implementation
    public int bfs(int[][] grid, int i, int j) {
        int count = 0, n = grid.length, m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ i, j }); // offer current coordinates
        
        while (!q.isEmpty()) { // iterate until empty
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            
            if (r < 0 || c < 0 || r >= n || c >= m || grid[r][c] == 0) continue; // if out of bounds or 0, we stop
            
            grid[r][c] = 0; // update grid and count so we don't recount 1s we have already seen
            count++;
            
            // offer all neighbors
            q.offer(new int[] { r - 1, c }); q.offer(new int[] { r, c - 1 });
            q.offer(new int[] { r + 1, c }); q.offer(new int[] { r, c + 1 });
        }
        
        // return count of neighboring 1s
        return count;
    }
    
    // DFS implementation
    public int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) return 0; // if out of bounds or 0, we stop
        grid[i][j] = 0; // update grid so we don't recount 1s we have already seen 
        return 1 + dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j + 1) + dfs(grid, i, j - 1); // return count in all neighboring directions
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) return 0; // edge case
        
        int n = grid.length, m = grid[0].length, size = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                size = Math.max(dfs(grid, i, j), size); // could use dfs or bfs to solve
            }
        }
        
        return size;
    }
}
