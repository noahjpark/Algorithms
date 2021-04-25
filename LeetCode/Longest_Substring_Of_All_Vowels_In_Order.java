/* Noah Park

A string is considered beautiful if it satisfies the following conditions:

Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.

Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.

A substring is a contiguous sequence of characters in a string.

*/

class Solution {
    
    // Intuition: Sliding window to check characters of the rule alphabetic and containing aeiou.
    // Time: O(n) since we are checking each character once.
    // Space: O(1) constant.
    public int longestBeautifulSubstring(String word) {
        int length = 0, start = 0, end = 0, n = word.length();
        
        while (end < n) {
            while (start < n && word.charAt(start) != 'a') start++; // get start to next beginning 'a' character of a substring
            end = start;
            
            //System.out.println(start + " " + end);
            while (end < n && word.charAt(end) == 'a') end++;
            if (end == n || word.charAt(end) != 'e') { start = end; continue; }
            
            while (end < n && word.charAt(end) == 'e') end++;
            if (end == n || word.charAt(end) != 'i') { start = end; continue; }
            
            while (end < n && word.charAt(end) == 'i') end++;
            if (end == n || word.charAt(end) != 'o') { start = end; continue; }
            
            while (end < n && word.charAt(end) == 'o') end++;
            if (end == n || word.charAt(end) != 'u') { start = end; continue; }
            
            while (end < n && word.charAt(end) == 'u') end++;
            length = Math.max(length, end - start);
            start = end;
        }
        
        return length;
    }
}
