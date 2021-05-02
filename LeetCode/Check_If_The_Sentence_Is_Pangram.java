/* Noah Park

A pangram is a sentence where every letter of the English alphabet appears at least once.

Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.

*/

class Solution {
    
    // Intuition: Count the number of unique characters in the sentence.
    // Time: O(n) to iterate over sentence.
    // Space: O(1) constant.
    public boolean checkIfPangram(String sentence) {
        int[] map = new int[26];
        int count = 0;
        
        for (char c : sentence.toCharArray())
            if (map[c - 'a']++ == 0) count++;
        
        return count == 26;
    }
}
