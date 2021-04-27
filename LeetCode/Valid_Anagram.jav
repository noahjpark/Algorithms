/* Noah Park

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

*/

class Solution {
    
    // Intuition: Utilize counting sort to map the frequencies of chars from s and t to the arrays.
    // Time: O(n + m) to iterate over s and t.
    // Space: O(1) constant size frequency arrays.
    public boolean isAnagram(String s, String t) {
        int[] freq1 = new int[26], freq2 = new int[26];
        
        for (char c : s.toCharArray()) freq1[c - 'a']++;
        for (char c : t.toCharArray()) freq2[c - 'a']++;
        
        for (int i = 0; i < 26; i++) 
            if (freq1[i] != freq2[i]) return false;
        
        return true;
    }
}
