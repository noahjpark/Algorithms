/* Noah Park

You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.

Return the number of consistent strings in the array words.

*/

class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int[] freq = new int[26]; // counting sort
        for (char c : allowed.toCharArray()) freq[c - 'a']++; // populate frequency array
        
        int count = words.length; // start with all words and decrease when there is an invalid one
        
        for (String word : words) {
            for (char c : word.toCharArray()) if (freq[c - 'a'] == 0) { count--; break; } // if a word has an invalid character, it doesn't count
        }
        
        return count;
    }
}
