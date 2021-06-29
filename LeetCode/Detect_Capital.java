/* Noah Park

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

*/

class Solution {
    
    // Intuition: Count the number of upper and lower case letters. If the number of upper or lower case letters matches the length of word, we satisfy either of the first rules. Otherwise, the uppercase count must be 1 and the first character is the capital letter.
    // Time: O(n) to iterate over word.
    // Space: O(1) constant.
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) return true;
        
        int capCount = 0, lowCount = 0, n = word.length();
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) capCount++;
            else lowCount++;
        }
        
        return capCount == n || lowCount == n || (capCount == 1 && Character.isUpperCase(word.charAt(0)));
    }
}
