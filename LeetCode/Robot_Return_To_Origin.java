/* Noah Park

There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.

The move sequence is represented by a string, and the character moves[i] represents its ith move. Valid moves are R (right), L (left), U (up), and D (down). If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.

Note: The way that the robot is "facing" is irrelevant. "R" will always make the robot move to the right once, "L" will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.

*/

class Solution {
    
    // Intuition: Maintain the total left/right moves and up/down moves. If the pointers are both on 0 we came back to the start. If the character is left/right we increment/decrement respectively. If the character is up/down we increment/decrement respectively.
    // Time: O(n) to iterate over moves.
    // Space: O(1) constant.
    public boolean judgeCircle(String moves) {
        int left = 0, up = 0;
        
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            
            if (c == 'L') left++;
            else if (c == 'R') left--;
            else if (c == 'U') up++;
            else up--;
        }
        
        return left == 0 && up == 0;
    }
}
