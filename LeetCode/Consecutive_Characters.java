/* Noah Park

Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.

Return the power of the string.

*/

class Solution {
    
    // Intuition: Sliding window over s to maintain each window of consecutive matching characters.
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public int maxPower(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int res = 0, start = 0, end = 0, n = s.length();
        
        while (end < n) {
            while (end < n && s.charAt(end) == s.charAt(start)) end++;
            res = Math.max(res, end - start);
            start = end;
        }
        
        return res;
    }
}
