/* Noah Park

You are given a 0-indexed string s that has lowercase English letters in its even indices and digits in its odd indices.

There is a function shift(c, x), where c is a character and x is a digit, that returns the xth character after c.

For example, shift('a', 5) = 'f' and shift('x', 0) = 'x'.
For every odd index i, you want to replace the digit s[i] with shift(s[i-1], s[i]).

Return s after replacing all digits. It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.

*/

class Solution {
    
    // Intuition: Build the string.
    // Time: O(n) to iterate over s.
    // Space: O(n) for the result.
    public String replaceDigits(String s) {
        if (s == null || s.length() == 0) return s;
        
        StringBuilder res = new StringBuilder();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) res.append(s.charAt(i));
            else res.append((char) (s.charAt(i - 1) + (s.charAt(i) - '0')));
        }
        
        return res.toString();
    }
}
