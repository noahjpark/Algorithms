/* Noah Park

Given a string s, return true if a permutation of the string could form a palindrome.

*/

class Solution {
    
    // Intuition: Use counting sort to map the frequencies of the characters in the string. If there is more than 1 character that has an odd count, we can't make a palindrome.
    // Time: O(n) to look through the string.
    // Space: O(1)
    public boolean canPermutePalindrome(String s) {
        char[] freq = new char[26];
        
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        
        int oddCount = 0;
        
        for (int f : freq) {
            if (f % 2 == 1) oddCount++;
            if (oddCount > 1) return false;
        }
        
        return true;
    }
}
