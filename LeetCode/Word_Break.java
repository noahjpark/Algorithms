/* Noah Park

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

*/

class Solution {
    
    // top down dynamic programming with memoization after TLE without memoization
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> mem = new HashMap<>(); // stores whether a given string is contained in the wordDict - avoid recomputation
        return dp(s, wordDict, mem); // use the dp helper
    }
    
    public boolean dp(String s, List<String> wordDict, Map<String, Boolean> mem) {
        if (mem.containsKey(s)) return mem.get(s); // don't recompute if we already have found a solution to this subproblem
        else if (wordDict.contains(s)) return true; // if the dictionary contains s, we of course return true
        
        boolean res = false; // find the result
    
        // iterate over all words in the dictionary
        for (String word : wordDict)
            // if the word length is valid and s contains the word, cut the word at that point using the helper function
            if (s.length() >= word.length() && s.substring(0, word.length()).equals(word)) res |= dp(s.substring(word.length(), s.length()), wordDict, mem);
    
        // cache the result so we don't recompute the problem
        mem.put(s, res);
        return res;
    }
}
