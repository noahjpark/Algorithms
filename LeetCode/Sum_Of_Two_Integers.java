/* Noah Park

Given two integers a and b, return the sum of the two integers without using the operators + and -.

*/

class Solution {
    
    // Intuition: xor operation gets the summed values but doesn't include any remainder bits. and operation gets the carry which we then shift to the left by 1 to add on the next iteration. No need to split up operations based on negatives because of two's complement.
    // Time: O(1) since integers are capped at 32 bits.
    // Space: O(1) constant.
    public int getSum(int a, int b) {
        while (b != 0) {
            int temp = a;
            a ^= b;
            b = (temp & b) << 1;
        }
        
        return a;
    }
    
    // Same as above but splitting into separate operations.
    public int getSum2(int a, int b) {
        int c = Math.abs(a), d = Math.abs(b);
        if (c < d) return getSum(b, a);
        
        int sign = a > 0 ? 1 : -1; // since a will be the larger number, it trumps the sign.
        
        if (a * b >= 0) {
            while (d > 0) {
                int temp = c;
                c ^= d;
                d = (temp & d) << 1;
            }
        } else {
            while (d > 0) {
                int temp = c;
                c ^= d;
                d = ((~temp) & d) << 1;
            }
        }
        
        return c * sign;
    }
}
