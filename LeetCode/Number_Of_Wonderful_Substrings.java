/* Noah Park

A wonderful string is a string where at most one letter appears an odd number of times.

For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.

A substring is a contiguous sequence of characters in a string.

*/

class Solution {
    
    // Intuition: Bitmask maintains the current status of characters in the string (odd or even occurrences). At each character, we update the mask and maintain a map of counts where we update the occurrences of the mask.
    public long wonderfulSubstrings(String word) {
        if (word == null || word.length() == 0) return 0;
        
        int n = word.length(), mask = 0;
        long[] cnt = new long[1024];
        cnt[0] = 1;
        long res = 0;
        
        for (char c : word.toCharArray()) {
            mask ^= (1 << (c - 'a'));
            res += cnt[mask]; // even valued wonderful substring
            
            // odd valued wonderful substring
            for (int i = 0; i < 10; i++) 
                res += cnt[(mask ^ (1 << i))];
            
            cnt[mask]++;
        }
        
        return res;
    }
    
    // Intuition: TLE brute force solution to compare all possible substrings.
    // Time: O(n^2) to make all comparisons.
    // Space: O(1) constant.
    public long wonderfulSubstrings2(String word) {
        if (word == null || word.length() == 0) return 0;
        
        long res = 0;
        int n = word.length();
        
        for (int i = 0; i < n; i++) {
            int[] freq = new int[10];
            int odd = 0;
            
            for (int j = i; j < n; j++) {
                freq[word.charAt(j) - 'a']++;
                
                if (freq[word.charAt(j) - 'a'] % 2 == 1) odd++;
                else odd--;
                
                if (odd < 2) res++;
            }
        }
        return res;
    }
}
