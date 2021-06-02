/* Noah Park

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

*/

class Solution {
    
    // Intuition: The following two solutions are bottom up dp with memoization similar to the Edit Distance algorithm. The first is an optimization on the second by maintaining a 1d array of the smaller of the two strings.
    // Time: O(n*m) for both making all comparisons.
    // Space: O(n*m) for the second, O(min(n, m)) for the first.
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        
        n = s1.length();
        m = s2.length();
        l = s3.length();
        if (n + m != l) return false; 
        if (n < m) return isInterleave(s2, s1, s3);
        
        boolean[] prev = new boolean[m + 1], cur = new boolean[m + 1];
        prev[0] = true;
        
        for (int i = 1; i < m + 1; i++)
            prev[i] = prev[i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (j == 0) cur[j] = prev[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                else cur[j] = (prev[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (cur[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
            prev = cur;
            cur = new boolean[m + 1];
        }
        
        return prev[m];
    }
    
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        
        n = s1.length();
        m = s2.length();
        l = s3.length();
        if (n + m != l) return false;     
        
        boolean[][] mem = new boolean[n + 1][m + 1];
        
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 && j == 0) mem[i][j] = true;
                else if (i == 0) mem[i][j] = mem[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                else if (j == 0) mem[i][j] = mem[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);    
                else mem[i][j] = (mem[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (mem[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        
        return mem[n][m];
    }
    
    // Intuition: top down dp with memoization to calculate each possibility. Similar to a 0/1 knapsack approach.
    // Time: O(n*m) for all comparisons.
    // Space: O(n*m) to maintain the memoized array.
    Boolean[][] mem;
    int n, m, l;
    
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        
        n = s1.length();
        m = s2.length();
        l = s3.length();
        if (n + m != l) return false;       
        mem = new Boolean[n][m];    
        
        return dp(s1, s2, s3, 0, 0, 0);
    }
    
    public boolean dp(String s1, String s2, String s3, int i, int j, int k) {
        if (i == n) return s2.substring(j).equals(s3.substring(k));
        if (j == m) return s1.substring(i).equals(s3.substring(k));
        if (mem[i][j] != null) return mem[i][j];
        
        boolean res = false;
        
        if ((s1.charAt(i) == s3.charAt(k) && dp(s1, s2, s3, i + 1, j, k + 1)) || (s2.charAt(j) == s3.charAt(k) && dp(s1, s2, s3, i, j + 1, k + 1))) res = true;
        
        mem[i][j] = res;
        return res;
    }
}
