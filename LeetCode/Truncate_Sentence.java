/* Noah Park

A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each of the words consists of only uppercase and lowercase English letters (no punctuation).

For example, "Hello World", "HELLO", and "hello world hello world" are all sentences.
You are given a sentence s​​​​​​ and an integer k​​​​​​. You want to truncate s​​​​​​ such that it contains only the first k​​​​​​ words. Return s​​​​​​ after truncating it.

*/

class Solution {
    
    // Intuition: Maintain the index at which the string should be truncated which will be the index where the last space is.
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public String truncateSentence(String s, int k) {
        int spaces = 0, i = 0;
        
        for (; i < s.length() && spaces < k; i++) 
            if (s.charAt(i) == ' ') spaces++;
        
        return (spaces == k) ? s.substring(0, i - 1) : s.substring(0, i);
    }
    
    // Intuition: Utilize the split method to split into words and add to the resulting output.
    // Time: O(n) to iterate over the words.
    // Space: O(n) for the output.
    public String truncateSentence2(String s, int k) {
        StringBuilder res = new StringBuilder();
        String[] words = s.split(" ");
        
        for (int i = 0; i < k; i++)
            res.append(words[i] + " ");
        
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
    
    // Intuition: Same as the top but builds the string instead of maintaining an index.
    // Time: O(n) to iterate over s.
    // Space: O(n) for the output.
    public String truncateSentence3(String s, int k) {
        StringBuilder res = new StringBuilder();
        int spaces = 0, i = 0;
        
        for (; i < s.length() && spaces < k; i++) {
            if (s.charAt(i) == ' ') spaces++;
            res.append(s.charAt(i));
        }
        
        if (spaces == k) res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
