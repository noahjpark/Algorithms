/* Noah Park

A string is good if there are no repeated characters.

Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.

Note that if there are multiple occurrences of the same substring, every occurrence should be counted.

A substring is a contiguous sequence of characters in a string

*/

class Solution {
    
    // Intuition: Sliding window of size 3 maintaining the number of distinct characters.
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public int countGoodSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int res = 0, st = 0, e = 0, n = s.length(), chs = 0;
        int[] freq = new int[26];
        
        for (; e < n; e++) {
            char c = s.charAt(e);
            if (freq[c - 'a']++ == 0) chs++;
            
            if (e - st > 2) {
                char ch = s.charAt(st++);
                if (freq[ch - 'a']-- == 1) chs--;
            }
            
            if (e - st == 2 && chs == 3) res++;
        }
        
        return res;
    }
}
