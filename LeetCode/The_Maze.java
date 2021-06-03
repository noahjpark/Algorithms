/* Noah Park

There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).

 

*/

class Solution {
    
    // Intuition: Same approach as dfs but with bfs.
    // Time: O(n*m) to iterate over the maze.
    // Space: O(n*m) for the queue and visited array.
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        rows = maze.length;
        cols = maze[0].length;
        dr = destination[0]; dc = destination[1];
        visited = new boolean[rows][cols];
        int[][] dirs = new int[][]{ {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
        Deque<int[]> q = new LinkedList<>();
        q.addLast(start);
        visited[start[0]][start[1]] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int r = cur[0], c = cur[1];
            
            if (r == dr && c == dc) return true; // end condition
            
            for (int[] dir : dirs) {
                int nr = r, nc = c;
                while (nr >= 0 && nc >= 0 && nr < rows && nc < cols && maze[nr][nc] != 1) { nr += dir[0]; nc += dir[1]; }
                nr -= dir[0]; nc -= dir[1];
                if (!visited[nr][nc]) { visited[nr][nc] = true; q.addLast(new int[]{ nr, nc }); }
            }
//             // left
//             while (c > 0 && maze[r][c - 1] != 1) c--;
//             if (!visited[r][c]) { visited[r][c] = true; q.addLast(new int[]{ r, c }); }
            
//             // down
//             r = cur[0]; c = cur[1];
//             while (r < rows - 1 && maze[r + 1][c] != 1) r++;
//             if (!visited[r][c]) { visited[r][c] = true; q.addLast(new int[]{ r, c }); }
            
//             // right
//             r = cur[0]; c = cur[1];
//             while (c < cols - 1 && maze[r][c + 1] != 1) c++;
//             if (!visited[r][c]) { visited[r][c] = true; q.addLast(new int[]{ r, c }); }
            
//             // up
//             r = cur[0]; c = cur[1];
//             while (r > 0 && maze[r - 1][c] != 1) r--;
//             if (!visited[r][c]) { visited[r][c] = true; q.addLast(new int[]{ r, c }); }
        }
        
        return false;
    }
    
    // Intuition: A dfs search made sense to me to start with. At each point, before choosing a new direction (making dfs calls), the ball rolls in the intended direction until it hits a wall. I maintain the direction the ball is coming from as an argument to move the ball before making dfs calls. If after moving the ball, we are on the destination, then we are stopped and have reached the end. Otherwise, I maintain a visited array of all positions we have rolled from and avoid revisiting already rolled positions.
    // Time: O(n*m) to visit the grid.
    // Space: O(n*m) for the boolean array.
    int rows, cols, sr, sc, dr, dc;
    boolean[][] visited;
    
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        rows = maze.length;
        cols = maze[0].length;
        sr = start[0]; sc = start[1];
        dr = destination[0]; dc = destination[1];
        visited = new boolean[rows][cols];
        
        return dfs(maze, sr, sc, -1);
    }
    
    // dir: 0 -> left, 1 -> down, 2 -> right, 3 -> up
    public boolean dfs(int[][] maze, int i, int j, int dir) {
        while (dir == 0 && j > 0 && maze[i][j - 1] != 1) j--;
        while (dir == 1 && i < rows - 1 && maze[i + 1][j] != 1) i++;
        while (dir == 2 && j < cols - 1 && maze[i][j + 1] != 1) j++;
        while (dir == 3 && i > 0 && maze[i - 1][j] != 1) i--;
        
        if (i == dr && j == dc) return true; // ball is stopped on the destination
        // keep track of each position we roll from, don't repeat from the same position
        if (i < 0 || j < 0 || i >= rows || j >= cols || visited[i][j]) return false;
        visited[i][j] = true;
        
        return dfs(maze, i, j, 0) || dfs(maze, i, j, 1) || dfs(maze, i, j, 2) || dfs(maze, i, j, 3);
    }
    /*
    [[0,0,1,0,0],
     [0,0,0,0,0],
     [0,0,0,1,0],
     [1,1,0,1,1],
     [0,0,0,0,0]]
     
     [0,4]
     [1,2]
    */
}
