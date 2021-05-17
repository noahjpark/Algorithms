/* Noah Park

Given a string s, return the number of substrings of length k with no repeated characters.

*/

class Solution {
    
    // Intuition: Straightforward sliding window algorithm. Instead of the performance hit of using a hashmap, maintain a frequency int map utilizing counting sort and maintain the number of repeated characters. We know we have a repeat if the value of a character we are adding is already at 1. We know we are removing a repeated character if the value of the character we are removing is at 2. Whenever the window grows too large, we begin moving start forward. When the window is a match of size 'k', we attempt to add to the result based on the number of repeated characters in the substring.
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public int numKLenSubstrNoRepeats(String s, int k) {
        int start = 0, end = 0, rep = 0, res = 0;
        int[] freq = new int[26];
        
        for (; end < s.length(); end++) {
            char c = s.charAt(end);
            if (freq[c - 'a']++ == 1) rep++;
            
            if (end - start > k - 1) {
                char ch = s.charAt(start++);
                if (freq[ch - 'a']-- == 2) rep--;
            }
            
            if (end - start == k - 1 && rep == 0) res++;
        }
        
        return res;
    }
}
