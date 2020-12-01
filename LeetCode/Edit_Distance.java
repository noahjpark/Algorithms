/* Noah Park

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character

*/

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length(); // word lengths
        
        int[][] dp = new int[n + 1][m + 1]; // dp array storing our minimum edits so far
        
        // initialize 0 row and column with the number of edits from an empty string to any given character up through m and n
        for (int i = 0; i < m + 1; i++) dp[0][i] = i;
        for (int i = 1; i < n + 1; i++) dp[i][0] = i;
        
        // iterate through the dp array updating as we move
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(word1.charAt(i) == word2.charAt(j)) dp[i + 1][j + 1] = dp[i][j]; // if the character matches, take the replace character from the previous i,j
                else dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1])) + 1; // else add 1 to the minimum swaps surrounding the current cell
            }
        }
        
        return dp[n][m]; // bottom corner will be the minimum swaps
    }
}
