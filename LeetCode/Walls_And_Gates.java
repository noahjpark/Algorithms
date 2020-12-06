/* Noah Park

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

*/

class Solution {  
    // TLE on overriding each gate, we have to do it in one pass
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return; // edge case if rooms is empty or null
        
        // num rows, cols, and max value as local storage
        int rows = rooms.length, cols = rooms[0].length, max = Integer.MAX_VALUE;
        Queue<int[]> q = new LinkedList<>(); // queue for bfs implementation
        
        // iterate through the rooms and add all gates to the queue so we can evenly update the adjacent cells until there are no more INF
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(rooms[i][j] == 0) q.offer(new int[]{ i, j });
            }
        }
        
        // traverse until there are no more INF
        while (!q.isEmpty()) {
            int[] cur = q.poll(); // current coordinates and new distance from previous cell
            int r = cur[0], c = cur[1], val = rooms[r][c] + 1;
            
            // check the cell above to see if it has not been updated yet, update if so and add to the queue
            if (r > 0 && rooms[r - 1][c] == max) { 
                rooms[r - 1][c] = val; 
                q.offer(new int[]{ r - 1, c }); 
            }
            // check the cell below to see if it has not been updated yet, update if so and add to the queue
            if (r < rows - 1 && rooms[r + 1][c] == max) { 
                rooms[r + 1][c] = val; 
                q.offer(new int[]{ r + 1, c });
            }
            // check the cell left to see if it has not been updated yet, update if so and add to the queue
            if (c > 0 && rooms[r][c - 1] == max) {
                rooms[r][c - 1] = val;
                q.offer(new int[]{ r, c - 1 }); 
            }
            // check the cell right to see if it has not been updated yet, update if so and add to the queue
            if (c < cols - 1 && rooms[r][c + 1] == max) {
                rooms[r][c + 1] = val; 
                q.offer(new int[]{ r, c + 1 }); 
            }
        }
    }
}
