/* Noah Park

Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.

*/

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder(); // Store the completed strings
        
        // Store word1 in s1 and word2 in s2
        for(String s : word1)
            s1.append(s);
        for(String s : word2)
            s2.append(s);
        
        // Return if the strings are equal
        return s1.toString().equals(s2.toString());
    }
}
