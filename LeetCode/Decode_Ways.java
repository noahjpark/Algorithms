/* Noah Park

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

The answer is guaranteed to fit in a 32-bit integer.

*/

class Solution {
    
    // bottom up memoization dynamic programming
    public int numDecodings(String s) {
        int n = s.length(); // cached value
        
        if (n == 0 || s.charAt(0) == '0') return 0; // edge case
        
        int[] dp = new int[n + 1]; // memoized array
        dp[n] = 1;
        
        // pretty much identical to the top down solution
        for (int i = n - 1; i > -1; i--) {
            if (s.charAt(i) == '0') continue;
            dp[i] = dp[i + 1];
            if (i < s.length() - 1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) dp[i] += dp[i + 2];
        }
        
        return dp[0];
    }
    
    // top down memoization dynamic programming
    public int numDecodingsTopDown(String s) {
        if (s.length() == 0) return 0; // no ways
        Integer[] dp = new Integer[s.length()]; // memoized array
        return decode(s, 0, dp);
    }
    
    public int decode(String s, int i, Integer[] dp) {
        if (s.length() == i) return 1; // reach the end of the string is one way
        if (s.charAt(i) == '0') return 0; // if we have a 0, we return 0 according to the rules
        if (dp[i] != null) return dp[i]; // if we computed the subproblem already, don't recompute
        
        int val = decode(s, i + 1, dp); // any single digit number there is only one way so we start with that 
        if (i < s.length() - 1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) val += decode(s, i + 2, dp); // if we can do a double digit number, attempt to if the first digit is a one or 2 but only if the second digit is less than 7 for 2
        
        dp[i] = val; // store computed subproblem value in the memoized array
        return val;
    }
}
