/* Noah Park

Given an integer num, return an array of the number of 1's in the binary representation of every number in the range [0, num].

*/

class Solution {
    
    // Intuition: Easily get all the bits except for the rightmost (n / 2 or n >> 1) then add on if the current number is odd or even.
    // Time: O(n) to loop over num.
    // Space: O(1) constant.
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        
        for (int i = 1; i <= num; i++) 
            res[i] = res[i >> 1] + (i & 1);
        
        return res;
    }
    
    // Intuition: Each num's bits counted.
    // Time: O(n log n) for looping over num then counting its 1 bits.
    // Space: O(1) constant.
    public int[] countBitsBruteForce(int num) {
        int[] res = new int[num + 1];
        for (; num >= 0; num--)
            res[num] = numBits(num);
        return res;
    }
    
    public int numBits(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) count++;
            num >>= 1;
        }
        return count;
    }
}
