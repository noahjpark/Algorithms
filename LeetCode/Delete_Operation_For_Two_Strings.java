/* Noah Park

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

*/

class Solution {
    
    // Intuition: Utilized prior knowledge of the edit distance algorithm to store the best deletions so far to create each substring in ideal time. There are two cases at each pair of letters: they match or they don't. If they match we take our previous diagonal since nothing will have changed or needs to be changed. Otherwise, we take the minimum deletions to match the words from the left or top neighbor and add 1 more deletion for the new character.
    // Time: O(n*m) to make all comparisons and fill the dp array.
    // Space: O(n*m) for the dp array although this can be optimized to n + m by decreasing the necessary rows to 2.
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 1; i < n + 1; i++) dp[i][0] = i;
        for (int i = 1; i < m + 1; i++) dp[0][i] = i;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int min = Math.min(dp[i][j + 1], dp[i + 1][j]);
                dp[i + 1][j + 1] = (word1.charAt(i) == word2.charAt(j)) ? dp[i][j] : min + 1;
            }
        }
        
        return dp[n][m];
    }
}
