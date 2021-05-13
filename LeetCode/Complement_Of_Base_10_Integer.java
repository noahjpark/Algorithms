/* Noah Park

Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary, 11 as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.

The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.  For example, the complement of "101" in binary is "010" in binary.

For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.

*/

class Solution {
    
    // Intuition: Simple bitwise manipulation. For each bit, OR the shifted bits with our result if the most least significant bit is a 0 and we must flip it. Only edge case is if num is zero.
    // Time: O(log n) To iterate over all bits.
    // Space: O(1) constant.
    public int bitwiseComplement(int num) {
        if (num == 0) return 1;
        
        int res = 0, i = 0;
        
        for (; num > 0; num >>= 1, i++) 
            if (num % 2 == 0) 
                res |= (1 << i);
                
                
        return res;
    }
}
