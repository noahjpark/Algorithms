/* Noah Park

On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

*/

class Solution {
    
    // Intuition: Based on the solution, we can prove that after one pass, we have either not landed on our original square or point a different direction than north like we started.
    // Time: O(n) single pass over instructions.
    // Space: O(1) constant.
    public boolean isRobotBounded(String instructions) {
        int[][] dirs = new int[][]{ {1, 0}, {0, -1}, {-1, 0}, {0, 1} };
        int r = 0, c = 0, dir = 0;
        
        for (char ch : instructions.toCharArray()) {
            if (ch == 'L') dir = (dir + 3) % 4;
            else if (ch == 'R') dir = (dir + 1) % 4;
            else { r += dirs[dir][0]; c += dirs[dir][1]; }
        }
        
        return dir != 0 || (r == 0 && c == 0);
    }
    
    // Intuition: Same time complexity as the above but does 4 passes and utilizes a set.
    // Time: O(n) to iterate over instructions 4 times.
    // Space: O(n) to maintain the set.
    public boolean isRobotBounded1(String instructions) {
        Set<String> set = new HashSet<>();
        int r = 0, c = 0, dir = 0; // 0 -> up, 1 -> down, 2 -> left, 3 -> right
        
        for (int j = 0; j < 4; j++) {
            set.add(new String(r + "," + c));
            
            for (char ch : instructions.toCharArray()) {
                if (dir == 0) {
                    if (ch == 'G') r++;
                    else if (ch == 'L') dir = 2;
                    else dir = 3;
                } else if (dir == 1) {
                    if (ch == 'G') r--;
                    else if (ch == 'L') dir = 3;
                    else dir = 2;
                } else if (dir == 2) {
                    if (ch == 'G') c--;
                    else if (ch == 'L') dir = 1;
                    else dir = 0;
                } else {
                    if (ch == 'G') c++;
                    else if (ch == 'L') dir = 0;
                    else dir = 1;
                }
            }
            
            if (set.contains(new String(r + "," + c))) return true;
        }
        
        return false;
    }
}
