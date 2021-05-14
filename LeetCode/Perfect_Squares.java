/* Noah Park

Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

*/

class Solution {
    
    // Intuition: BFS approach by calculating all perfect squares then continuously building each value towards n. If we find a match, we minimize res, otherwise if the current value is too large OR the total numbers at any point is too large OR we have a better totalNums so far, we don't need to keep going down that path. 
    // Time and Space are icky for this problem.
    
    // Optimized greedily to stop when we reach that point. At each level, reach as far as we can. If that level reaches the end, we have found our shortest path.
    public int numSquares(int n) {
        if (n <= 0) return 0;
        
        List<Integer> squares = perfect(n);
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] used = new boolean[n];
        int res = 1;
        
        // loop until queue is empty
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                
                for (int num = 1; num*num <= n; num++) {
                    int s = num*num;
                    
                    if (cur + s == n) return res;
                    else if (cur + s > n) break;
                    
                    if (!used[cur + s]) {
                        q.offer(cur + s);
                        used[cur + s] = true;
                    }
                }
            }
            
            res++;
        }
        
        return res;
    }
    
}
