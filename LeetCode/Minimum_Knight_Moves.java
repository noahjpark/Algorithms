/* Noah Park

In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

*/

class Solution {
    
    // Intuition: BFS in all directions. Originally used a hash set but this TLEd on going in all directions so I had to optimize to only go in at most 2 directions at a time. Optimized again to use a bitmap.
    // Time and Space: O((max(abs(x), abs(y)))^2)
    int[][] dirs = new int[][]{ {-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1} };
    int[][] q1 = new int[][]{ {1, 2}, {2, 1} }, q2 = new int[][]{ {2, -1}, {1, -2} }, q3 = new int[][]{ {-1, -2}, {-2, -1} }, q4 = new int[][]{ {-2, 1}, {-1, 2} };
    
    public int minKnightMoves(int x, int y) {
        //Set<String> visited = new HashSet<>();
        boolean[][] visited = new boolean[605][605];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{ 0, 0 });
        //visited.add(new String("0,0"));
        visited[0][0] = true;
        
        for (int moves = 0; !q.isEmpty(); moves++) {
            int size = q.size(); 
            
            for (int i = 0; i < size; i++) {
                int r = q.peek()[0], c = q.poll()[1];
                
                if (r == y && c == x) return moves;
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    
                    if (!visited[nr + 302][nc + 302]) {
                        q.offer(new int[]{ nr, nc });
                        visited[nr + 302][nc + 302] = true;
                    }
                }
                
                // if (r <= y && c <= x) {
                //     for (int[] dir : q1) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                //     for (int[] dir : q2) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                // } else if (r <= y && c >= x) {
                //     for (int[] dir : q2) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                //     for (int[] dir : q3) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                // } else if (r >= y && c >= x) {
                //     for (int[] dir : q3) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                //     for (int[] dir : q4) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                // } else {
                //     for (int[] dir : q4) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                //     for (int[] dir : q1) {
                //         int nr = r + dir[0], nc = c + dir[1];
                //         if (!visited.contains(new String(nr + "," + nc))) {
                //             q.offer(new int[]{ nr, nc });
                //             visited.add(new String(nr + "," + nc));
                //         }
                //     }
                // }
            }
        }
        
        return -1;
    }
    
}
