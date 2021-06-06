/* Noah Park

Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.

Return the minimum number of steps to make t an anagram of s.

An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

*/

class Solution {
    
    // Intuition: Maintain the frequencies of each string (s and t). The difference in number of a particular letter is added to the result. At the end the result will be twice the size of the number of replacements we must due since each letter can be transformed into another. This means that when we add 2 because there are distinct letters in s and t, the letter in t can be transformed to the letter in s in 1 transformation. Thus, we divide res by 2.
    // Time: O(n) to iterate over s and t -> two passes.
    // Space: O(1) since the frequency arrays are size 26.
    public int minSteps(String s, String t) {
        int[] freq1 = new int[26], freq2 = new int[26];
        int res = 0;
        
        for (char c : s.toCharArray())
            freq1[c - 'a']++;
        
        for (char c : t.toCharArray())
            freq2[c - 'a']++;
        
        for (int i = 0; i < 26; i++) 
            res += Math.abs(freq1[i] - freq2[i]);
        
        return res / 2;
    }
}
