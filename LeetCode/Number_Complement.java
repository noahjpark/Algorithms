/* Noah Park

Given a positive integer num, output its complement number. The complement strategy is to flip the bits of its binary representation.

*/

class Solution {
    
    // Intuition: Simple bitwise manipulation. For each bit, OR the shifted bits with our result if the most least significant bit is a 0 and we must flip it. Only edge case is if num is zero.
    // Time: O(log n) To iterate over all bits.
    // Space: O(1) constant.
    public int findComplement(int num) {
        if (num == 0) return 1;
        
        int res = 0, i = 0;
        
        for (; num > 0; num >>= 1, i++) 
            if (num % 2 == 0) 
                res |= (1 << i);
                
                
        return res;
    }
}
