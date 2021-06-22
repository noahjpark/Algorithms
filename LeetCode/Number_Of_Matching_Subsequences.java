/* Noah Park

Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".

*/

class Solution {
    
    // Intuition: Iterate over each word and match to s with optimizations. We use a map to avoid recomputation. We use a frequency array to break from inner loop early if s doesn't contain the appropriate character.
    // Time: O(s + n*max(m, s)) where n is the number of words, m is the longest word, and s is the length of s.
    // Space: O(n) to maintain the words in the map.
    public int numMatchingSubseq(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0) return 0;
        
        int n = words.length, res = 0;
        Map<String, Integer> map = new HashMap<>();
        boolean[] freq = new boolean[26];
        
        for (char c : s.toCharArray())
            freq[c - 'a'] = true;
        
        for (int i = 0; i < n; i++) {
            if (map.containsKey(words[i])) { res += map.get(words[i]); continue; }
            if (words[i].length() > words[i].length()) continue;
            //if (s.equals(words[i])) { res++; continue; }
            
            String t = words[i];
            int p1 = 0, p2 = 0;
            
            for (; p1 < s.length() && p2 < t.length(); p1++) {
                if (s.charAt(p1) == t.charAt(p2)) p2++;
                else if (!freq[t.charAt(p2) - 'a']) break;
            }
        
            if (p2 == t.length()) { res++; map.put(t, 1); }
            else map.put(t, 0);
        }
        
        return res;
    }
}
