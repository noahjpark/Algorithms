/* Noah Park

Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.

*/

class Solution {
    // exclude vowels. pretty self explanatory
    public String removeVowels(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') res.append(c);
        return res.toString();
    }
}
