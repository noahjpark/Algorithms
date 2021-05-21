/* Noah Park

Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.

*/

class Solution {
    
    // Intuition: Map each character to one unique character. Each time a map is found, we add to the result list.
    // Time: O(n*m) where n is the number of characters in pattern and m is the number of words.
    // Space: O(m) number of words.
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        
        for (String word : words)
            if (match(word, pattern)) res.add(word);
        
        return res;
    }
    
    public boolean match(String s1, String pat) {
        char[] map = new char[26];
        boolean[] used = new boolean[26];
        
        for (int i = 0; i < pat.length(); i++) {
            char c = s1.charAt(i), p = pat.charAt(i);
            
            if (map[p - 'a'] == '\0') {
                if (used[c - 'a']) return false;
                map[p - 'a'] = c; used[c - 'a'] = true; 
            }
            else if (map[p - 'a'] != c) return false;
        }
        
        return true;
    }
}
