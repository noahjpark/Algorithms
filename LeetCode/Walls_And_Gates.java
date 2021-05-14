class Solution {
    
    // Intuition: Started with a bfs from each gate while optimizing to not update already minimized distances. From here the only way to go was to do all gates at once rather than one at a time.
    // Time: O(n*m) to check the rooms.
    // Space: O(n*m) for the visited structure and queue since all rooms could be gates.
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        
        int rows = rooms.length, cols = rooms[0].length, dis = 0;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> q = new ArrayDeque<>();
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                if (rooms[i][j] == 0) q.add(new int[]{ i, j });
        
        while (!q.isEmpty()) {
            int level = q.size(); 
            
            for (int k = 0; k < level; k++) {
                int row = q.peek()[0], col = q.poll()[1];
                
                if (rooms[row][col] <= dis && rooms[row][col] > 0) continue;
                rooms[row][col] = dis;
                
                if (row - 1 >= 0 && rooms[row - 1][col] == Integer.MAX_VALUE && !visited[row - 1][col]) { q.offer(new int[]{ row - 1, col }); visited[row - 1][col] = true; }
                if (col - 1 >= 0 && rooms[row][col - 1] == Integer.MAX_VALUE && !visited[row][col - 1]) { q.offer(new int[]{ row, col - 1 }); visited[row][col - 1] = true; }
                if (row + 1 < rooms.length && rooms[row + 1][col] == Integer.MAX_VALUE && !visited[row + 1][col]) { q.offer(new int[]{ row + 1, col }); visited[row + 1][col] = true; }
                if (col + 1 < rooms[0].length && rooms[row][col + 1] == Integer.MAX_VALUE && !visited[row][col + 1]) { q.offer(new int[]{ row, col + 1 }); visited[row][col + 1] = true; }
            }
            
            dis++;
        }
    }
    
    public void bfs(int[][] rooms, int i, int j) {
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{ i, j });
        visited[i][j] = true;
        int dis = 0;
        
        while (!q.isEmpty()) {
            int level = q.size();
            
            for (int k = 0; k < level; k++) {
                int row = q.peek()[0], col = q.poll()[1];
                
                if (rooms[row][col] <= dis && rooms[row][col] > 0) continue;
                rooms[row][col] = dis;
                
                if (row - 1 >= 0 && rooms[row - 1][col] > 0 && !visited[row - 1][col]) { q.offer(new int[]{ row - 1, col }); visited[row - 1][col] = true; }
                if (col - 1 >= 0 && rooms[row][col - 1] > 0 && !visited[row][col - 1]) { q.offer(new int[]{ row, col - 1 }); visited[row][col - 1] = true; }
                if (row + 1 < rooms.length && rooms[row + 1][col] > 0 && !visited[row + 1][col]) { q.offer(new int[]{ row + 1, col }); visited[row + 1][col] = true; }
                if (col + 1 < rooms[0].length && rooms[row][col + 1] > 0 && !visited[row][col + 1]) { q.offer(new int[]{ row, col + 1 }); visited[row][col + 1] = true; }
            }
            dis++;
        }
    } 
        
}
