/* Noah Park

Balanced strings are those that have an equal quantity of 'L' and 'R' characters.

Given a balanced string s, split it in the maximum amount of balanced strings.

Return the maximum amount of split balanced strings.

*/

class Solution {
    
    // Intuition: Greedily split into the smallest substrings of equal R/L occurrences. 
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public int balancedStringSplit(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int l = 0, r = 0, n = s.length(), res = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') l++;
            else r++;
            
            if (l == r && l > 0) {
                res++;
                l = r = 0;
            }
        }
        
        return res;
    }
}
