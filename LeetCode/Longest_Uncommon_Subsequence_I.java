/* Noah Park

Given two strings a and b, return the length of the longest uncommon subsequence between a and b. If the longest uncommon subsequence does not exist, return -1.

An uncommon subsequence between two strings is a string that is a subsequence of one but not the other.

A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.

For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).

*/

class Solution {
    
    // Intuition: Either the two strings are equal in length or unequal in length. If they are unequal in length, the longer string is the non subsequence maximum. Otherwise, the strings are either equal or not equal when they are the same length. If they are equal, there are no subsequences that don't match. Otherwise, it is guaranteed that we can return the length of the equal strings.
    // Time and Space: O(1) constant.
    public int findLUSlength2(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
    
    // Intuition: More appropriate problem solving. Consider the case when the strings are of unequal length, we can always return the longer one. Otherwise, consider all possible subsequences of one string into another and find the longest. Optimized to stop if the entire string is not a subsequence.
    // Time: O(n^3) to consider all subsequences but the optimization forces a stop far before this.
    // Space: O(1) constant.
    public int findLUSlength(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) return 0;
        if (b.length() != a.length()) return Math.max(a.length(), b.length());
        
        int n = a.length(), res = -1;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 > res && !subsequence(a.substring(0, j + 1), b)) res = j - i + 1;
                if (res == n) return res;
            }
        }
        
        return res;
    }
    
    public boolean subsequence(String a, String b) {
        int p1 = 0, p2 = 0, n = a.length(), m = b.length();
        
        while (p1 < n && p2 < m) {
            if (a.charAt(p1) == b.charAt(p2)) { p1++; p2++; }
            else p2++;
        }
        
        return p1 == n;
    }
    
}
