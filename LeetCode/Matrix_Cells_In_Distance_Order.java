/* Noah Park

We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.

Additionally, we are given a cell in that matrix with coordinates (r0, c0).

Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)

*/

class Solution {
    // Time: O(n*m) visiting all spaces BFS implementation 
    // Space: O(n*m) rows and cols space for visited array
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        boolean[][] visited = new boolean[R][C];
        int[][] res = new int[R * C][2];
        int idx = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ r0, c0 });
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0], col = cur[1];
            
            if (row < 0 || row >= R || col < 0 || col >= C || visited[row][col]) continue;
            
            res[idx++] = new int[]{ row, col };
            visited[row][col] = true;
            
            q.offer(new int[]{ row + 1, col }); q.offer(new int[]{ row - 1, col });
            q.offer(new int[]{ row, col + 1 }); q.offer(new int[]{ row, col - 1 });
        }
        
        return res;
    }
    
    // Time: O(n*m) rows * cols 
    // Space: O(1) if not counting the return array space
    public int[][] allCellsDistOrderFirst(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int idx = 0;
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[idx++] = new int[]{ i, j };
            }
        }
        
        Comparator<int[]> c = new Comparator<>() {
            public int compare(int[] arr1, int[] arr2) {
                return Integer.compare(Math.abs(arr1[0] - r0) + Math.abs(arr1[1] - c0), Math.abs(arr2[0] - r0) + Math.abs(arr2[1] - c0));
            }  
        };
        
        Arrays.sort(res, c);
        
        return res;
    }
}
