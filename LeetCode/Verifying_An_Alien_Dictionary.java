/* Noah Park

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

*/

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int n = words.length; // cache words length
        
        // iterate through the words comparing the current one to the one in front of it
        for (int i = 0; i < n - 1; i++)
            if (compare(words[i], words[i + 1], order) > -1) return false; // if we don't get -1, the words are not in order
        
        return true; // all words are in order
    }
    
    public int compare(String a, String b, String order) {
        int p1 = 0, p2 = 0; // two pointers for the two strings
        
        // iterate until one finishes
        while (p1 < a.length() && p2 < b.length()) {
            char c1 = a.charAt(p1), c2 = b.charAt(p2); 
            if (c1 == c2) { p1++; p2++; } // if the characters match, we move to the next one
            else return order.indexOf(c1) - order.indexOf(c2); // otherwise return whether they are in order (negative) or out of order (positive)
        }
        
        return p1 == a.length() ? -1 : 1; // if one fell off, all characters match from that word, return negative if that one is the first one and positive otherwise
    }
}
