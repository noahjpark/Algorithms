/* Noah Park

You are given two integers, x and y, which represent your current location on a Cartesian grid: (x, y). You are also given an array points where each points[i] = [ai, bi] represents that a point exists at (ai, bi). A point is valid if it shares the same x-coordinate or the same y-coordinate as your location.

Return the index (0-indexed) of the valid point with the smallest Manhattan distance from your current location. If there are multiple, return the valid point with the smallest index. If there are no valid points, return -1.

The Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2).

*/

class Solution {
    
    // Intuition: Calculate the distance whenever we have a valid point. If the distance is smaller than our current found distance, we update the smallest index. Optimized to return whenever we find a matching point of distance 0.
    // Time: O(n) to iterate over points.
    // Space: O(1) constant.
    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length, res = Integer.MAX_VALUE, idx = -1;
        
        for (int i = 0; i < n; i++) {
            if (x == points[i][0] || y == points[i][1]) { 
                int diff = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]);
                if (diff < res) {
                    res = diff; 
                    idx = i; 
                }
            }
            if (res == 0) return idx;
        }
        
        return idx;
    }
}
