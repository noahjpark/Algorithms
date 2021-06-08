/* Noah Park

There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop at the destination. If the ball cannot stop at destination, return -1.

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

You may assume that the borders of the maze are all walls (see examples).

*/

class Solution {
    
    int[][] dirs = new int[][]{ {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
    int rows, cols, dr, dc;    
    
    // Intuition: Dijkstra's greedy algorithm for maintaining the shortest path to each reachable position. The priority queue (min heap) is an optimization to continue searching from the nearest neighbor by maintaining the distance from the start to each point in the queue. 
    // Time: O(n*m*log(n*m)) to iterate over the grid utilizing the heap.
    // Space: O(n*m) for the distances array and heap.
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        rows = maze.length;
        cols = maze[0].length;
        dr = destination[0];
        dc = destination[1];
        
        int[][] distances = new int[rows][cols];
        for (int[] row : distances) Arrays.fill(row, Integer.MAX_VALUE);
        distances[start[0]][start[1]] = 0;
        
        dijkstra(maze, distances, start);
        return distances[dr][dc] == Integer.MAX_VALUE ? -1 : distances[dr][dc];
    }
    
    public void dijkstra(int[][] maze, int[][] distances, int[] start) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        minHeap.offer(new int[]{ start[0], start[1], 0 });
        
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int r = cur[0], c = cur[1], dis = cur[2];
            if (distances[r][c] < dis) continue; // We already have a shorter distance
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1], ndis = 0;
                
                while (in(nr, nc, maze) && maze[nr][nc] == 0) {
                    nr += dir[0];
                    nc += dir[1];
                    ndis++;
                }
                
                if (distances[r][c] + ndis < distances[nr - dir[0]][nc - dir[1]]) {
                    distances[nr - dir[0]][nc - dir[1]] = distances[r][c] + ndis;
                    minHeap.offer(new int[]{ nr - dir[0], nc - dir[1], distances[nr - dir[0]][nc - dir[1]] });
                }
            }
        }
    }
    
    // Intuition: breadth first search from the beginning while maintaining the shortest paths to each point in the matrix with a visited copy that maintains the shortest distance to each point. We have to visit all possibilities to see if there was a shorter path in a different direction. 
    // Time: O(m*n*max(m, n)) we of course would in the worst case visit all cells in the maze. However, we can also travel to in the depth of the largest dimension as well.
    // Space: O(m*n) for the visited array and queue.
    public int shortestDistance2(int[][] maze, int[] start, int[] destination) {
        int dr = destination[0], dc = destination[1], rows = maze.length, cols = maze[0].length;
        Deque<int[]> q = new LinkedList<>();
        Integer[][] visited = new Integer[rows][cols];
        //for (int[] row : visited) Arrays.fill(row, -1);
        
        q.addLast(new int[]{ start[0], start[1], 0 });
        visited[start[0]][start[1]] = 0;
        
        while (!q.isEmpty()) {
            int r = q.getFirst()[0], c = q.getFirst()[1], dis = q.removeFirst()[2];
            
            if (r == dr && c == dc) continue;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1], ndis = dis;
                
                while (in(nr, nc, maze) && maze[nr][nc] == 0) { 
                    nr += dir[0]; 
                    nc += dir[1]; 
                    ndis++; 
                }
                
                if (visited[nr - dir[0]][nc - dir[1]] == null || visited[nr - dir[0]][nc - dir[1]] > ndis) { 
                    visited[nr - dir[0]][nc - dir[1]] = ndis; 
                    q.addLast(new int[]{ nr - dir[0], nc - dir[1], ndis }); 
                }
            }
        }
        
        return visited[dr][dc] == null ? -1 : visited[dr][dc];
    }
    
    public boolean in(int r, int c, int[][] maze) {
        return r >= 0 && c >= 0 && r < maze.length && c < maze[0].length;
    }
    
}
