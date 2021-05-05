/* Noah Park

Given an integer n, return the number of trailing zeroes in n!.

Follow up: Could you write a solution that works in logarithmic time complexity?

*/

class Solution {
    
    // Every power of 5, the number of zeroes increases by its division of 5. We stop when our multiple of five exceeds n.
    // Time: O(log n) to break the problem down by 5 each iteration.
    // Space: O(1) constant.
    public int trailingZeroes(int n) {
        int zeroes = 0, multiple = 5;
        
        for (; n >= multiple; multiple *= 5)
            zeroes += n / multiple;
        
        return zeroes;
    }
}
