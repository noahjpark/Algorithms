/* Noah Park

You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.


*/

class Solution {
    
    // Intuition: Sliding window while maintaining the number of even zeroes, even ones, odd zeroes, and odd ones. Each time we move the window, we update the even zero/even one at the start of the window, then swap the values (all odds become evens and all evens become odds). From here, we can add the new value at the end of the window based on the length of the string; order matters here.
    // Time: O(n) to iterate over s.
    // Space: O(n) for the string builder. Could append to end of s to improve space.
    public int minFlips(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int n = s.length(), res = n, zeroEven = 0, oneOdd = 0, zeroOdd = 0, oneEven = 0, end = n;
        StringBuilder t = new StringBuilder();
        t.append(s).append(s);
        
        for (int i = 0; i < n; i++) {
            if (t.charAt(i) == '0') {
                if (i % 2 == 0) zeroEven++;
                else zeroOdd++;
            } else {
                if (i % 2 == 0) oneEven++;
                else oneOdd++;
            }
        }
        
        res = Math.min(n - (zeroEven + oneOdd), n - (zeroOdd + oneEven));
        
        for (int i = 1; i < n; i++, end++) {
            // slide the window
            if (t.charAt(i - 1) == '0') zeroEven--;
            else oneEven--;
            
            // swap adjusted values
            int tempze = zeroEven, tempoe = oneEven;
            
            zeroEven = zeroOdd;
            oneEven = oneOdd;
            oneOdd = tempoe;
            zeroOdd = tempze;
            
            // add new value
            if (t.charAt(end) == '0') {
                if (n % 2 != 0) zeroEven++;
                else zeroOdd++;
            } else {
                if (n % 2 != 0) oneEven++;
                else oneOdd++;
            }
            
            res = Math.min(res, Math.min(n - (zeroEven + oneOdd), n - (zeroOdd + oneEven)));
        }
        
        return res;
    }
    
}
