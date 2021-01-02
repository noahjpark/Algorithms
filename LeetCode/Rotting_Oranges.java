/* Noah Park

In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

*/

class Solution {
    // update the four directions
    public int bfs(int[][] grid, int i , int j, Queue<int[]> q) {
        int rotten = 0;
        
        if (i + 1 < grid.length && grid[i + 1][j] == 1) { 
            grid[i + 1][j] = 2; 
            rotten++;
            q.offer(new int[]{ i + 1, j });
        } if (i - 1 > -1 && grid[i - 1][j] == 1) { 
            grid[i - 1][j] = 2; 
            rotten++; 
            q.offer(new int[]{ i - 1, j });
        } if (j + 1 < grid[0].length && grid[i][j + 1] == 1) { 
            grid[i][j + 1] = 2; 
            rotten++; 
            q.offer(new int[]{ i, j + 1 });
        } if (j - 1 > -1 && grid[i][j - 1] == 1) { 
            grid[i][j - 1] = 2; 
            rotten++; 
            q.offer(new int[]{ i, j - 1 });
        }
        
        return rotten;
    }
    
    // O(n * m) time and O(n * m) space
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1; // edge cases
        
        int rows = grid.length, cols = grid[0].length, fresh = 0, minutes = -1; // fresh are number of fresh oranges, minutes are number of minutes elapsed
        Queue<int[]> q = new LinkedList<>(); // store coordinates of rotten in queue
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) q.offer(new int[]{ i, j }); // add a rotten fruit
                else if (grid[i][j] == 1) fresh++; // keep track of how many fresh fruit there are
            }
        }
        
        if (q.isEmpty() && fresh == 0) return 0; // if we have no fresh or rotten fruit, simply return 0
        
        while (!q.isEmpty()) { // pass along the rotten fruit to fresh fruit
            //print(grid);
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] pos = q.poll();
                fresh -= bfs(grid, pos[0], pos[1], q); // 4 directions, subtract the number of fruit affected from the remaining fresh fruit
            }
            minutes++; // increment the minutes
            //System.out.println();
        }
        
        return fresh > 0 ? -1 : minutes; // if there are fresh fruit remaining, it is impossible for all to become rotten as they are not connected -> -1, otherwise the number of minutes it took to turn all fresh to rotten
    }
    
    // helper printing function
    public void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
