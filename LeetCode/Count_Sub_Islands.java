/* Noah Park

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

*/

class Solution {
    
    // Intuition: Essentially the idea here is to do the typical island count but maintain the number of visited cells in the first grid as well. To achieve this, I have the dfs function keep track of the visited 1s from grid2 as well as grid 1 in a size 2 integer array. If each island count returns the same number of visited islands from each grid, we have a matching island.
    // Time: O(n*m) to visited all islands in grid2.
    // Space: O(n*m) for the recursion depth.
    int n, m;
    boolean[][] v1, v2;
    
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        if (grid1 == null || grid2 == null || grid1.length == 0 || grid2.length == 0) return 0;
        
        n = grid1.length;
        m = grid1[0].length;
        v1 = new boolean[n][m];
        v2 = new boolean[n][m];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1 && !v2[i][j]) {
                    int[] cur = dfs(grid1, grid2, i, j);
                    if (cur[0] == cur[1]) count++;
                }
            }
        }
        
        return count;
    }
    
    // [number of visited spots in grid1, number of visited spots in grid2]
    public int[] dfs(int[][] grid1, int[][] grid2, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || v2[i][j] || grid2[i][j] == 0) return new int[]{ 0, 0 };
        
        int[] res = new int[]{ 0, 0 };
        v2[i][j] = true;
        res[1]++;
        if (grid1[i][j] == 1) res[0]++;
        
        int[] left = dfs(grid1, grid2, i, j - 1), right = dfs(grid1, grid2, i, j + 1), up = dfs(grid1, grid2, i - 1, j), down = dfs(grid1, grid2, i + 1, j);
        res[0] += (left[0] + right[0] + up[0] + down[0]);
        res[1] += (left[1] + right[1] + up[1] + down[1]);
        
        return res;
    }
    
}
