/* Noah Park

Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

*/

class Solution {
    
    // Intuition: Start the cur bit at whatever the lsb is. Then just change it each iteration and ensure it always matches.
    // Time: O(log n) to iterate through the bits of n.
    // Space: O(1) constant.
    public boolean hasAlternatingBits(int n) {
        int cur = n & 1;
        
        while (n > 0) {
            if (cur != (n & 1)) return false;
            cur ^= 1;
            n >>= 1;
        }
        
        return true;
    }
}
