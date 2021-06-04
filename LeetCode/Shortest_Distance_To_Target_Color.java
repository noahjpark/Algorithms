/* Noah Park

You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.

*/

class Solution {
    
    // Intuition: Utilize dp to pre process all shortest paths to return all queries in constant time. This involves a pass through from left to right and a pass from right to left. We maintain the closest value going in each direction and store that closest value in the map. 
    // Time: O(n + m) to iterate over colors and queries.
    // Space: O(n) to maintain the map.
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        int n = colors.length;
        int[] right = new int[]{ 0, 0, 0 }, left = new int[]{ n - 1, n - 1, n - 1 };
        Integer[][] map = new Integer[3][n];
        
        for (int i = 0; i < n; i++) {
            int c = colors[i] - 1;
            
            for (int j = right[c]; j < i + 1; j++) 
                map[c][j] = i - j;
            right[c] = i + 1;
        }
        
        for (int i = n - 1; i >= 0; i--) {
            int c = colors[i] - 1;
            
            for (int j = left[c]; j > i - 1; j--) 
                map[c][j] = map[c][j] == null ? j - i : Math.min(j - i, map[c][j]);
            left[c] = i - 1;
        }
        
        for (int[] query : queries)
            res.add(map[query[1] - 1][query[0]] == null ? -1 : map[query[1] - 1][query[0]]);
        
        return res;
    }
    
    // Intuition: Work outwards from the current point to find our shortest distance.
    // Time: O(n*m) to iterate over each color for each query.
    // Space: O(n) to maintain the map assuming we don't have an upper bound.
    public List<Integer> shortestDistanceColor2(int[] colors, int[][] queries) {
        Integer[][] map = new Integer[50000][3];
        List<Integer> res = new ArrayList<>();
        int n = colors.length;
        
        for (int[] query : queries) {            
            int idx = query[0], color = query[1], l = idx - 1, r = idx + 1, count = 1;
            
            if (colors[idx] == color) { res.add(0); continue; }
            if (map[idx][color - 1] != null) { res.add(map[idx][color - 1]); continue; }
            
            while (l > -1 || r < n) {
                if (l > -1) {
                    if (colors[l] == color) break;
                    else l--;
                } 
                if (r < n) {
                    if (colors[r] == color) break;
                    else r++;
                }
                count++;
            }
            
            if (l < 0 && r >= n) count = -1;
            
            res.add(count);
            map[idx][color - 1] = count;
        }
        
        return res;
    }
}
