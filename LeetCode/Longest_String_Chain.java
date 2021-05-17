/* Noah Park

Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2. For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

*/

class Solution {
    
    // Intuition: Top down dynamic programming approach. Need to sort the array first to go from shorter characters into larger ones. Start by choosing each word in the array as the starting one and attempt to find the longest chain from that point. Memoize each word in the array to its longest chain to avoid recomputation. In the dp function, the base case is when we are at the longest word length (equal to the last word's length). We move from the next index and maintain the previous (i) to (i + 1) and we only care if the new difference is 1. If it is 1, we count the number of differences while maintaining structure (i.e. move the pointer in the longer string forward and count a difference each time we have a difference with the previous one). If the difference is in fact 1, we can attempt to recursively call the dp function to find a longer length. Each call is maximized with the result to ensure we find the longest chain.
    // Time: O(n*m) where n is the number of words in the array and m is the number of letters in each word. 
    // Space: O(n) for the memoized map.
    Map<String, Integer> mem = new HashMap<>();
    
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        
        int res = 0;
        for (int i = 0; i < words.length; i++) 
            res = Math.max(res, dp(words, i));
        
        return res;
    }
    
    public int dp(String[] words, int i) {
        if (words[i].length() == words[words.length - 1].length()) return 1;
        if (mem.containsKey(words[i])) return mem.get(words[i]);
        
        int length = 1;
        
        for (int j = i + 1; j < words.length && words[j].length() - words[i].length() <= 1; j++) {
            String word = words[j], prev = words[i];
            
            if (word.length() - prev.length() == 1) {
                int diff = 0, p1 = 0, p2 = 0;
                for (; p1 < word.length() && p2 < prev.length(); p1++, p2++) {
                    while (p1 < word.length() && word.charAt(p1) != prev.charAt(p2)) {
                        diff++;
                        p1++;
                    }
                }
                if (p1 < word.length()) diff++;
                
                if (diff == 1) length = Math.max(length, dp(words, j) + 1);
            }
        }
        
        mem.put(words[i], length);
        return length;
    }
}
