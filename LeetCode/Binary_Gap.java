/* Noah Park

Given a positive integer n, find and return the longest distance between any two adjacent 1's in the binary representation of n. If there are no two adjacent 1's, return 0.

Two 1's are adjacent if there are only 0's separating them (possibly no 0's). The distance between two 1's is the absolute difference between their bit positions. For example, the two 1's in "1001" have a distance of 3.

*/

class Solution {
    
    // Intuition: Keep track of the distance between each 1. Before actually tracking adjacent 1s, we need to start on a one so we break down n until its rightmost digit is a 1.
    // Time: O(log n) to iterate through the digits of n.
    // Space: O(1) constant.
    public int binaryGap(int n) {
        int res = 0, cur = 0;
        
        while ((n & 1) != 1) n >>= 1;
        
        for (; n > 0; n >>= 1) {
            res = Math.max(res, cur);
            if ((n & 1) == 1) cur = 0;
            cur++;
        }
        
        return res;
    }
}
