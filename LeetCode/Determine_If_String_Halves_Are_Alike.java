/* Noah Park

You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.

Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.

Return true if a and b are alike. Otherwise, return false.

*/

class Solution {
    
    // Intuition: Simply count through the first and second halves of the string and determine if the vowel count is the same. Optimized to break if there are too many vowels in the second half.
    // Time: O(n) since the vowel string is constant
    // Space: O(1)
    public boolean halvesAreAlike(String s) {
        int v = 0, n = s.length();
        
        for (int i = 0; i < n / 2; i++) if (isVowel(s.charAt(i))) v++;
        for (int i = n / 2; i < n; i++) if (isVowel(s.charAt(i))) { v--; if (v < 0) return false; }
        
        return v == 0;
    }
    
    public boolean isVowel(char c) {
        String vowels = "aeiouAEIOU";
        return vowels.indexOf(c) != -1;
    }
}
