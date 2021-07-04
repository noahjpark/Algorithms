/* Noah Park

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

*/

class Solution {
    
    // Intuition: Typical bfs without modifying input.
    // Time: O(n*m) to iterate over the grid.
    // Space: O(n*m) for the queue.
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0][0] == 1) return -1;
        
        int[][] dirs = new int[][]{ {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1} };
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> q = new LinkedList<>();
        q.addLast(new int[]{ 0, 0 });
        visited[0][0] = true;
        int pathLen = 1;
        
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int r = q.getFirst()[0], c = q.removeFirst()[1];
                
                if (r == rows - 1 && c == cols - 1) return pathLen;
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (in(nr, nc, rows, cols) && !visited[nr][nc] && grid[nr][nc] == 0) {
                        visited[nr][nc] = true;
                        q.addLast(new int[]{ nr, nc });
                    }
                }
            }
            pathLen++;
        }
        
        return -1;
    }
    
    public boolean in(int r, int c, int rows, int cols) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }
}
