/* Noah Park

On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

*/

class Solution {
    
    // Intuition: Prioritize the best path so far by always attempting to go to the smallest reachable point. When we reach the end, we will have taken the smallest path.
    // Time: O(n^2 * logn) to iterate over the grid and maintain the heap.
    // Space: O(n^2) for the heap.
    int[][] dirs = new int[][]{ {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
    
    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int n = grid.length, res = 0;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (grid[a[0]][a[1]] - grid[b[0]][b[1]]));
        minHeap.offer(new int[]{ 0, 0 });
        
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int r = cur[0], c = cur[1];
            
            res = Math.max(res, grid[r][c]);
            if (r == n - 1 && c == n - 1) break;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (in(n, nr, nc) && !visited[nr][nc]) {
                    minHeap.offer(new int[]{ nr, nc });
                    visited[nr][nc] = true;
                }
            }
        }
        
        return res;
    }
    
    public boolean in(int n, int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }
    
}
