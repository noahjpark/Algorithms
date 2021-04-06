/* Noah Park

Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

*/

class Solution {
    // Intuition: Improving the recursive solution to tabulation for time and space improvements
    // Time: O(n^2) for the tabulation 
    // Space: O(n) for the dp memoization
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++)
                dp[i] += dp[j - 1] * dp[i - j];
        }        
        
        return dp[n];
    }
    
    // Intuition: Apply the formula F(i, n) = G(i - 1) * G(n - i). Typical recursive dp solution.
    // Time: O(2^n) recursive tree
    // Space: O(2^n) recursive tree
    public int numTrees2(int n) {
        return numTrees(1, n);
    }
    
    public int numTrees(int l, int r) {
        if (l >= r) return 1;
        
        int count = 0;
        for (int i = l; i <= r; i++) 
            count += numTrees(l, i - 1) * numTrees(i + 1, r);
        return count;
    }
}
