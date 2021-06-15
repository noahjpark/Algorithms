/* Noah Park

You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Return true if you can make this square and false otherwise.

*/

class Solution {
    
    // Intuition: Originally was going to try dp to get 4 sums, but this is more straightforward. Simply maintain the four equal sides based on an original prefix sum. Recursively attempt to make each side.
    // Time: O(4^n) to try all possible combinations.
    // Space: O(n) for the recursion stack.
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length == 0) return false;
        
        int prefix = 0;
        for (int match : matchsticks) prefix += match;
        if (prefix % 4 != 0) return false;
        prefix /= 4;
        Arrays.sort(matchsticks);
        
        return dp(matchsticks, 0, 0, 0, 0, matchsticks.length - 1, prefix);
    }
    
    public boolean dp(int[] matchsticks, int s1, int s2, int s3, int s4, int i, int prefix) {
        if (i < 0) return s1 == s2 && s2 == s3 && s3 == s4;
        if (s1 > prefix || s2 > prefix || s3 > prefix || s4 > prefix) return false;
        return dp(matchsticks, s1 + matchsticks[i], s2, s3, s4, i - 1, prefix) || dp(matchsticks, s1, s2 + matchsticks[i], s3, s4, i - 1, prefix) ||
            dp(matchsticks, s1, s2, s3 + matchsticks[i], s4, i - 1, prefix) || dp(matchsticks, s1, s2, s3, s4 + matchsticks[i], i - 1, prefix);
    }
}
