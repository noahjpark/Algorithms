/* Noah Park

Given a sentence that consists of some words separated by a single space, and a searchWord.

You have to check if searchWord is a prefix of any word in sentence.

Return the index of the word in sentence where searchWord is a prefix of this word (1-indexed).

If searchWord is a prefix of more than one word, return the index of the first word (minimum index). If there is no such word return -1.

A prefix of a string S is any leading contiguous substring of S.

*/

class Solution {
    
    // Intuition: Check the prefix of each word in the sentence and return the occurrence as soon as we find it. Otherwise return -1.
    // Time: O(n*m) to iterate over all words in the sentence then all characters in each word.
    // Space: O(1) constant.
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] split = sentence.split(" ");
        
        for (int i = 0; i < split.length; i++) {
            String word = split[i];
            
            if (word.length() < searchWord.length()) continue;
            else if (word.equals(searchWord)) return i + 1;
            
            int p1 = 0, p2 = 0;
            while (p1 < word.length() && p2 < searchWord.length() && word.charAt(p1) == searchWord.charAt(p2)) { p1++; p2++; }
            if (p2 == searchWord.length()) return i + 1;
        }
        
        return -1;
    }
}
