/* Noah Park

We have two special characters:

The first character can be represented by one bit 0.
The second character can be represented by two bits (10 or 11).
Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.

*/

class Solution {
    
    // Intuition: If the current bit is a 0, it is a single bit. Otherwise, it starts with a 1 and is a 2 bit sequence. Move accordingly. If we ever get to n - 1 we must have a 1 bit.
    // Time: O(n) to iterate over bits.
    // Space: O(1) constant.
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0, n = bits.length;
        
        while (i < n) {
            if (i == n - 1) return true;
            else if (bits[i] == 0) i++;
            else i += 2;
        }
        
        return false;
    }
}
