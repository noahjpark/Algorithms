/* Noah Park

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

*/

class Solution {
    
    // Intuition: First approached with a less ideal bfs that searched at each non zero value. Then approached the problem by doing a consecutive bfs from all zeroes at once building the distance we've gone from the past zeroes at the same time. Finally, took a dp approach going from top left to bottom always looking at the previously calculated left/top values if we have them. Finally we had to go back from the bottom right to the top left checking the bottom and right values if they existed. This allowed us to take the best possible distance in both directions.
    // Time: O(n*m) for both approaches.
    // Space: O(n*m) for the resulting updated matrix.
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) return mat;
        
        rows = mat.length;
        cols = mat[0].length;
        res = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] != 0) {
                    int left = j > 0 ? res[i][j - 1] : max, top = i > 0 ? res[i - 1][j] : max, min = Math.min(left, top);
                    res[i][j] = min == max ? max : min + 1;
                }
            }
        }
        
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    int right = j < cols - 1 ? res[i][j + 1] : max, bottom = i < rows - 1 ? res[i +  1][j] : max, min = Math.min(right, bottom) == max ? max : Math.min(right, bottom) + 1;
                    res[i][j] = Math.min(res[i][j], min);
                }
            }
        }
        
        return res;
    }
    
    int[][] dirs = new int[][]{ {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    int[][] res;
    int rows, cols, max = Integer.MAX_VALUE;
    
    public int[][] updateMatrix2(int[][] mat) {
        if (mat == null || mat.length == 0) return mat;
        
        rows = mat.length;
        cols = mat[0].length;
        res = new int[rows][cols];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] used = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{ i, j });
                    used[i][j] = true;
                }
            }
        }
        
        for (int dis = 0; !q.isEmpty(); dis++) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int r = q.peek()[0], c = q.poll()[1];
                res[r][c] = dis;
                
                for (int[] dir : dirs) {
                    int nr = dir[0] + r, nc = dir[1] + c;
                    if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && !used[nr][nc]) {
                        q.offer(new int[]{ nr, nc });
                        used[nr][nc] = true;
                    }
                }
            }
        }
        
        return res;
    }
    
//     int[][] res;
//     int rows, cols;
    
//     public int[][] updateMatrix(int[][] mat) {
//         if (mat == null || mat.length == 0) return mat;
        
//         rows = mat.length;
//         cols = mat[0].length;
//         res = new int[rows][cols];
        
//         for (int i = 0; i < rows; i++) 
//             for (int j = 0; j < cols; j++) 
//                 if (mat[i][j] != 0) bfs(mat, i, j);
        
//         return res;
//     }
    
//     public void bfs(int[][] mat, int i, int j) {
//         boolean[][] visited = new boolean[rows][cols];
//         Queue<int[]> q = new ArrayDeque<>();
//         q.offer(new int[]{ i, j });
//         int dis = 0, left = j - 1 >= 0 ? res[i][j - 1] : Integer.MAX_VALUE, top = i - 1 >= 0 ? res[i - 1][j] : Integer.MAX_VALUE, min = Math.min(left, top) == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(left, top) + 1;
        
//         while (!q.isEmpty() && dis < min) {
//             int size = q.size();
            
//             for (int k = 0; k < size; k++) {
//                 int r = q.peek()[0], c = q.poll()[1];
                
//                 if (visited[r][c]) continue;
//                 visited[r][c] = true;
                
//                 if (mat[r][c] == 0) {
//                     res[i][j] = dis;
//                     return;
//                 }
                
//                 if (r - 1 >= 0 && !visited[r - 1][c]) q.offer(new int[]{ r - 1, c });
//                 if (c - 1 >= 0 && !visited[r][c - 1]) q.offer(new int[]{ r, c - 1 });
//                 if (r + 1 < rows && !visited[r + 1][c]) q.offer(new int[]{ r + 1, c });
//                 if (c + 1 < cols && !visited[r][c +  1]) q.offer(new int[]{ r, c + 1 });
//             }
            
//             dis++;
//         }
        
//         res[i][j] = dis;
//     }
    
//     public void print(int[][] m) {
//         for (int[] row : m) {
//             for (int col : row)
//                 System.out.print(col + " " );
//             System.out.println(" ");
//         }
//     }
    
}
