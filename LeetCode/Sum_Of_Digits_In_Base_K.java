/* Noah Park

Given an integer n (in base 10) and a base k, return the sum of the digits of n after converting n from base 10 to base k.

After converting, each digit should be interpreted as a base 10 number, and the sum should be returned in base 10.

*/

class Solution {
    
    // Intuition: Convert to base k then add the digits.
    // Time: O(log subk n + log sub10 n) to convert to base k then sum digits.
    // Space: O(1) constant.
    public int sumBase(int n, int k) {
        if (k != 10) n = convert(n, k);
        
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
    
    public int convert(int n, int k) {
        int rep = 0;
        while (n > 0) {
            rep = (rep + n % k) * 10;
            n /= k;
        }
        return rep;
    }
}
