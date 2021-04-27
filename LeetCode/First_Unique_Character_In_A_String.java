/* Noah Park

Given a string s, return the first non-repeating character in it and return its index. If it does not exist, return -1.

*/

class Solution {
    
    // Intuition: Utilize counting sort to map each character to an array.
    // Time: O(n) two passes through s.
    // Space: O(1) since the array is always size 26.
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1) return i;
        
        return -1;
    }
    
    // Intuition: Utilize string methods to check that the first and last index of the character match.
    // Time: O(n^2)
    // Space: O(1) constant.
    public int firstUniqChar2(String s) {
        char[] chars = s.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (s.indexOf(c) == s.lastIndexOf(c)) return i;
        }
        
        return -1;
    }
}
