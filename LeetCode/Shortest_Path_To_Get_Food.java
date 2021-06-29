/* Noah Park

You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

*/

class Solution {
    
    int[][] dirs = new int[][]{ {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
        
    // Intuition: BFS to get the shortest path from the start after finding the start in the grid.
    // Time: O(n*m) two passes to find the start and visit the cells in the grid.
    // Space: O(n*m) for the visited structure and queue.
    public int getFood(char[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        
        int rows = grid.length, cols = grid[0].length, dist = 0;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '*') { 
                    q.addLast(new int[]{ i, j }); 
                    visited[i][j] = true;
                    break; 
                }
            }
            if (q.size() > 0) break;
        }
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int r = q.getFirst()[0], c = q.removeFirst()[1];
                
                if (grid[r][c] == '#') return dist;
                visited[r][c] = true;
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (in(nr, nc, rows, cols) && !visited[nr][nc] && grid[nr][nc] != 'X') {
                        q.addLast(new int[]{ nr, nc });
                        visited[nr][nc] = true;
                    }
                }
            }
            
            dist++;
        }
        
        return -1;
    }
    
    public boolean in(int r, int c, int n, int m) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }
}
