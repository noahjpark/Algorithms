/* Noah Park

You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

*/

class Solution {
    
    // Intuition: Utilize a pointer to add each parallel character from word1 and word2. Clean up any remaining after the merging portion.
    // Time: O(max(n, m)) longer of word1 length and word2 length.
    // Space: O(n + m) for the return string.
    public String mergeAlternately(String word1, String word2) {
        int p = 0, n = word1.length(), m = word2.length();
        StringBuilder res = new StringBuilder();
        
        while (p < n && p < m) {
            res.append(word1.charAt(p));
            res.append(word2.charAt(p++));
        }
        
        while (p < n) res.append(word1.charAt(p++));
        while (p < m) res.append(word2.charAt(p++));
            
        return res.toString();
    }
}
