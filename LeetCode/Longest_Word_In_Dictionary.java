/* Noah Park

Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.

If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.

*/

class Solution {
    
    // Intuition: Make all comparisons to see if all prefixes are in words.
    // Time: O(n^2) to iterate over each word and all prefixes of each word.
    // Space: O(n) to maintain the set.
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) return null;
        
        Set<String> set = new HashSet<>();
        for (String word : words) set.add(word);
        
        String res = "";
        for (String word : words) {
            boolean check = true;
            
            for (int i = 1; i < word.length(); i++) {
                if (!set.contains(word.substring(0, i))) { check = false; break; }
            }
            
            if (check && res.length() <= word.length()) {
                if (res.length() < word.length() || res.compareTo(word) > 0) res = word;
            }
        }
        
        return res;
    }
}
