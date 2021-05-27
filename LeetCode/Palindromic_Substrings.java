/* Noah Park

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

*/

class Solution {
    
    // Intuition: Build all possible substrings treating each character as a new center point. Since palindromes can be even and odd in length, we have to add all even length palindromes and all odd length palindromes. 
    // Time: O(n^2) to build all palindrome possibilities which is much better than creating all possible subsequences and checking if they are palindromes (n^3).
    // Space: O(1) constant.
    public int countSubstrings(String s) {
        int res = 0, n = s.length();
        
        for (int i = 0; i < n; i++) {
            res += countPal(s, i, i);
            if (i + 1 < n) res += countPal(s, i, i + 1);
        }
            
        return res;
    }
    
    public int countPal(String s, int i, int j) {
        if (s.charAt(i) != s.charAt(j)) return 0;
        
        int res = 1, l = i - 1, r = j + 1, n = s.length();
        
        while (l >= 0 && r < n) {
            if (s.charAt(l--) != s.charAt(r++)) break;
            res++;
        }
        
        return res;
    }
    
}
