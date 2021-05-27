/* Noah Park

You are given an m x n integer matrix heights representing the height of each unit cell in a continent. The Pacific ocean touches the continent's left and top edges, and the Atlantic ocean touches the continent's right and bottom edges.

Water can only flow in four directions: up, down, left, and right. Water flows from a cell to an adjacent one with an equal or lower height.

Return a list of grid coordinates where water can flow to both the Pacific and Atlantic oceans.

*/

class Solution {
    
    // Intuition: DFS from the outside inwards. Overlapping positions of reachable on both oceans are added to the result.
    // Time: O(m*n) to iterate over the heights.
    // Space: O(m*n) for the boolean arrays.
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0) return new ArrayList<>();
        
        res = new ArrayList<>();
        rows = heights.length;
        cols = heights[0].length;
        
        List<int[]> pac = new ArrayList<>(), atl = new ArrayList<>();
        boolean[][] pacReach = new boolean[rows][cols], atlReach = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            dfs(heights, i, 0, pacReach);
            dfs(heights, i, cols - 1, atlReach);
        }
        
        for (int i = 0; i < cols; i++) {
            dfs(heights, 0, i, pacReach);
            dfs(heights, rows - 1, i, atlReach);
        }
        
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (pacReach[i][j] && atlReach[i][j]) res.add(Arrays.asList( i, j ));
        
        return res;
    }
    
    public void dfs(int[][] heights, int i, int j, boolean[][] reach) {
        reach[i][j] = true;
        
        for (int[] dir : dirs) {
            int nr = i + dir[0], nc = j + dir[1];
            if (in(nr, nc) && !reach[nr][nc] && heights[nr][nc] >= heights[i][j]) dfs(heights, nr, nc, reach);
        }
    }
    
    // Intuition: BFS from the outside inwards. Overlapping positions of reachable on both oceans are added to the result.
    // Time: O(m*n) to iterate over the heights.
    // Space: O(m*n) to maintain the boolean arrays.
    public List<List<Integer>> pacificAtlanticbfs(int[][] heights) {
        if (heights == null || heights.length == 0) return new ArrayList<>();
        
        res = new ArrayList<>();
        rows = heights.length;
        cols = heights[0].length;
        
        Deque<int[]> pac = new LinkedList<>(), atl = new LinkedList<>();
        
        for (int i = 0; i < rows; i++) {
            pac.addLast(new int[]{ i, 0 });
            atl.addLast(new int[]{ i, cols - 1 });
        }
        
        for (int i = 0; i < cols; i++) {
            pac.addLast(new int[]{ 0, i });
            atl.addLast(new int[]{ rows - 1, i });
        }
        
        boolean[][] pacReach = bfs(heights, pac), atlReach = bfs(heights, atl);
        
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (pacReach[i][j] && atlReach[i][j]) res.add(Arrays.asList( i, j ));
        
        return res;
    }
    
    public boolean[][] bfs(int[][] heights, Deque<int[]> q) {
        boolean[][] reach = new boolean[rows][cols];
        
        while (!q.isEmpty()) {
            int r = q.peek()[0], c = q.removeFirst()[1];
            reach[r][c] = true;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (in(nr, nc) && !reach[nr][nc] && heights[nr][nc] >= heights[r][c]) { q.addLast(new int[]{ nr, nc }); reach[r][c] = true; }
            }
        }
        
        return reach;
    }
    
    // Intuition: Brute force computation from each position in the matrix. We can optimize by going from the outside inwards.
    // Time: O((m*n)^2) to iterate over the heights.
    // Space: O(m*n) to maintain the boolean copies.
    int[][] dirs = new int[][]{ {1, 0}, {0, 1}, {-1, 0}, {0, -1}, };
    List<List<Integer>> res;
    Boolean[][] pac, atl;
    int rows, cols;
    
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        if (heights == null || heights.length == 0) return new ArrayList<>();
        
        res = new ArrayList<>();
        rows = heights.length;
        cols = heights[0].length;
        pac = new Boolean[rows][cols];
        atl = new Boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[] reach = dfs(heights, i, j, Integer.MAX_VALUE, new boolean[rows][cols]);
                if (reach[0] && reach[1]) res.add(Arrays.asList( i, j ));
            }
        }
        
        return res;
    }
    
    public boolean[] dfs(int[][] heights, int i, int j, int prev, boolean[][] visited) {
        if (i < 0 || j < 0) return new boolean[]{ true, false };
        if (i >= rows || j >= cols) return new boolean[]{ false, true };
        if (heights[i][j] > prev) return new boolean[]{ false, false };
        
        boolean[] reach = new boolean[2];
        visited[i][j] = true;
        
        if (pac[i][j] != null && pac[i][j]) reach[0] = true;
        if (atl[i][j] != null && atl[i][j]) reach[1] = true;
        
        for (int[] dir : dirs) {
            if (reach[0] && reach[1]) break;
            int r = i + dir[0], c = j + dir[1];
            if (!in(r, c) || (in(r, c) && !visited[r][c])) {
                boolean[] canReach = dfs(heights, r, c, heights[i][j], visited);
                reach[0] |= canReach[0];
                reach[1] |= canReach[1];
            }
        }
        
        pac[i][j] = reach[0];
        atl[i][j] = reach[1];
        return reach;
    }
    
    public boolean in(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }
    
}
