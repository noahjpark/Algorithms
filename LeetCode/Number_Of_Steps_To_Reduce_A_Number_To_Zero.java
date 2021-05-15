/* Noah Park

Given a non-negative integer num, return the number of steps to reduce it to zero. If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.

*/

class Solution {
    
    // Intuition: Follow the instructions.
    // Time: O(n) if we subtract 1 each time.
    // Space: O(1) constant.
    public int numberOfSteps(int num) {
        int steps = 0;
        
        while (num > 0) {
            if (num % 2 == 0) num /= 2;
            else num--;
            steps++;
        }
        
        return steps;
    }
}
