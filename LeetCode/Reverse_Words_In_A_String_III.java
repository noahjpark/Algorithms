/* Noah Park

Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

*/

class Solution {
    
    // Intuition: Could use string buffer or additional string builder but likely wouldn't be appropriate for interview setting. Instead, do the manual reverse then add a space between each word.
    // Time: O(n*m) where n is the number of words and m is the number of letters in each word.
    // Space: O(n*m) for the resulting sentence.
    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder res = new StringBuilder();
        
        for (String word : split) {
            for (int i = word.length() - 1; i >= 0; i--) res.append(word.charAt(i));
            res.append(" ");
        }
        
        return res.toString().trim();
    }
}
