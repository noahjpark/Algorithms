/* Noah Park

There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n. A frog starts at point 0 in the second lane and wants to jump to point n. However, there could be obstacles along the way.

You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3) describes an obstacle on the lane obstacles[i] at point i. If obstacles[i] == 0, there are no obstacles at point i. There will be at most one obstacle in the 3 lanes at each point.

For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
The frog can only travel from point i to point i + 1 on the same lane if there is not an obstacle on the lane at point i + 1. To avoid obstacles, the frog can also perform a side jump to jump to another lane (even if they are not adjacent) at the same point if there is no obstacle on the new lane.

For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
Return the minimum number of side jumps the frog needs to reach any lane at point n starting from lane 2 at point 0.

Note: There will be no obstacles on points 0 and n.

*/

class Solution {
    
    // Intuition: Store the minimum paths for each lane up to obstacles length. At each point, calculate the path moving forward one (assuming there is no obstacle in the way) then update with the minimum path so far if you can jump from a smaller one and retain a smaller value.
    // Time: O(n) to iterate over the obstacles
    // Space: O(n) for the memoized array
    public int minSideJumps(int[] obstacles) {
        if (obstacles == null || obstacles.length == 0) return 0;
        
        int n = obstacles.length;
        int[][] mem = new int[n][3];
        mem[0][0] = mem[0][2] = 1; // if we wanted to start in any other lane, we would have to jump
        
        for (int i = 1; i < n; i++) {
            // update current path by adding 0 from moving forward from the cell behind it (if there is an obstacle, mark with maximum integer value)
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] == j + 1) mem[i][j] = Integer.MAX_VALUE;
                else mem[i][j] = mem[i - 1][j];
            }
            
            // store the minimum path so far on our current ith iteration
            int minJumps = Math.min(mem[i][0], Math.min(mem[i][1], mem[i][2]));
            
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] == j + 1) continue;
                mem[i][j] = Math.min(minJumps + 1, mem[i][j]); // we either keep our current path or jump from a smaller one if the smaller one plus a jump is still smaller
            }
        }
        
        return Math.min(mem[n - 1][0], Math.min(mem[n - 1][1], mem[n - 1][2])); // minimum path will be at the end of the array
    }
}
