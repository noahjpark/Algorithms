/* Noah Park

Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:

Characters ('a' to 'i') are represented by ('1' to '9') respectively.
Characters ('j' to 'z') are represented by ('10#' to '26#') respectively. 
Return the string formed after mapping.

It's guaranteed that a unique mapping will always exist.

*/

class Solution {
    
    // Intuition: Iterates over s checking if there is a hashtag 2 places in front indicating it is a letter after i, or if we take the singular number at our current index to convert to a letter.
    // Time: O(n) to iterate over s.
    // Space: O(1) not counting the resulting string, O(n) if counting the resulting string.
    public String freqAlphabets(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            if (i + 2 < n && s.charAt(i + 2) == '#') { res.append(decode(s.substring(i, i + 3))); i+=2; }
            else res.append(decode(s.substring(i, i + 1)));
        }
        
        return res.toString();
    }
    
    // Decodes the given string based on the rules in the problem. "Characters ('a' to 'i') are represented by ('1' to '9') respectively. Characters ('j' to 'z') are represented by ('10#' to '26#') respectively."
    public char decode(String s) {
        if (s.length() == 1) return (char) ((s.charAt(0) - '1') + 'a');
        if (s.charAt(0) == '1') return (char) ((s.charAt(1) - '0') + 'j');
        return (char) ((s.charAt(1) - '0') + 't');
    }
    
}
